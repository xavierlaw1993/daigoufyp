package com.xavier.daigoufyp.controller.page.product;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.xavier.daigoufyp.R;
import com.xavier.daigoufyp.controller.page.abs.AbsSpiceFragment;
import com.xavier.daigoufyp.utils.CameraUtil;
import com.xavier.daigoufyp.utils.GalleryUtil;
import com.xavier.daigoufyp.utils.Utils;
import com.xavier.daigoufyp.view.IntentActionDialog;

import net.yazeed44.imagepicker.model.ImageEntry;
import net.yazeed44.imagepicker.util.Picker;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import roboguice.inject.InjectView;

/**
 * Created by zensis on 18/4/16.
 */
public class NewProductFragment extends AbsSpiceFragment {
    private final int GALLERY_ACTIVITY_CODE = 200;
    private final int CAMERA_ACTIVITY_CODE = 300;
    private final int RESULT_CROP = 400;

    @InjectView(R.id.productMainPictureImageView)
    ImageView productMainPictureImageView;

    @InjectView(R.id.emptyThumbnailImageView)
    ImageView emptyThumbnailImageView;

    @InjectView(R.id.productMainPictureHintTextView)
    TextView productMainPictureHintTextView;

    @InjectView(R.id.productNameEditText)
    EditText productNameEditText;

    @InjectView(R.id.addMorePicturesButton)
    Button addMorePicturesButton;

    @InjectView(R.id.addedPicturesCountTextView)
    TextView addedPicturesCountTextView;

    @InjectView(R.id.nextButton)
    Button nextButton;

    ArrayList<Bitmap> imageBitmapList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_new_product, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ViewGroup.LayoutParams params = productMainPictureImageView.getLayoutParams();
        params.height = (int) (Utils.screenWidth * Utils.hdRatio);
        productMainPictureImageView.setLayoutParams(params);

        productMainPictureImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentActionDialog dialog = new IntentActionDialog(getActivity());
                dialog.setOnButtonClickListener(new IntentActionDialog.OnButtonClickListener() {
                    @Override
                    public void onCameraActionClick() {
                        Intent camera_intent = new Intent(getActivity(), CameraUtil.class);
                        startActivityForResult(camera_intent, CAMERA_ACTIVITY_CODE);
                    }

                    @Override
                    public void onGalleryActionClick() {
                        Intent gallery_Intent = new Intent(getActivity(), GalleryUtil.class);
                        startActivityForResult(gallery_Intent, GALLERY_ACTIVITY_CODE);
                    }
                });
                dialog.show();
            }
        });

        addMorePicturesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Picker.Builder(getActivity(), new Picker.PickListener() {
                    @Override
                    public void onPickedSuccessfully(ArrayList<ImageEntry> images) {
                        addedPicturesCountTextView.setText("+" + images.size());
                        for (ImageEntry imageEntry : images) {
                            imageBitmapList.clear();
                            imageBitmapList.add(decodeSampledBitmapFromResource(imageEntry.path,
                                    Utils.screenWidth, (int) (Utils.screenWidth * Utils.hdRatio)));
                        }
                    }

                    @Override
                    public void onCancel() {
                    }
                }, R.style.MIP_theme)
                        .setPickMode(Picker.PickMode.MULTIPLE_IMAGES)
                        .setLimit(6)
                        .build()
                        .startActivity();
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (productMainPictureImageView.getDrawable() == null) {
//                    Utils.showFailureSnackbar(getView(),
//                            "You need to select a main image for your product");
//                } else if (TextUtils.isEmpty(productNameEditText.getEditableText()))
//                    Utils.showFailureSnackbar(getView(),
//                            "You need to enter a name for your product");
//                else {
//                    imageBitmapList.add(((BitmapDrawable) (productMainPictureImageView.getDrawable())).getBitmap());
                    Intent i = new Intent(getActivity(), NewProductDetailActivity.class);
                    i.putExtra("PRODUCT_NAME", productNameEditText.getEditableText().toString());
//                    i.putParcelableArrayListExtra("PRODUCT_PICS_BITMAP_LIST", imageBitmapList);
                    startActivity(i);
//                }
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_ACTIVITY_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                String photoPath = data.getStringExtra("photoPath");
                Uri photoUri = Uri.parse(photoPath);
                //perform Crop on the Photo Took from Camera
                performCrop("", photoUri);
            }
        }
        if (requestCode == GALLERY_ACTIVITY_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                String picturePath = data.getStringExtra("picturePath");
                //perform Crop on the Image Selected from Gallery
                performCrop(picturePath, null);
            }
        }
        if (requestCode == RESULT_CROP) {
            if (resultCode == Activity.RESULT_OK) {
                Bundle extras = data.getExtras();
                Bitmap selectedBitmap = extras.getParcelable("data");
                // Set The Bitmap Data To ImageView
                productMainPictureImageView.setImageBitmap(selectedBitmap);
                emptyThumbnailImageView.setVisibility(View.GONE);
                productMainPictureHintTextView.setVisibility(View.GONE);

            }
        }
    }

    private void performCrop(String picUri, Uri photoUri) {
        try {
            //Start Crop Activity
            Intent cropIntent = new Intent("com.android.camera.action.CROP");
            // indicate image type and Uri
            Uri contentUri;
            if (!TextUtils.isEmpty(picUri)) {
                File f = new File(picUri);
                contentUri = Uri.fromFile(f);
            } else {
                contentUri = photoUri;
            }

            cropIntent.setDataAndType(contentUri, "image/*");
            // set crop properties
            cropIntent.putExtra("crop", "true");
            // indicate aspect of desired crop
            cropIntent.putExtra("aspectX", 16);
            cropIntent.putExtra("aspectY", 9);
            cropIntent.putExtra("scale", true);
            // indicate output X and Y
            cropIntent.putExtra("outputX", Utils.screenWidth);
            cropIntent.putExtra("outputY", (int) (Utils.screenWidth * Utils.hdRatio));

            // retrieve data on return
            cropIntent.putExtra("return-data", true);
            // start the activity - we handle returning in onActivityResult
            startActivityForResult(cropIntent, RESULT_CROP);
        }
        // respond to users whose devices do not support the crop action
        catch (ActivityNotFoundException anfe) {
            // display an error message
            String errorMessage = "your device doesn't support the crop action!";
            Utils.showFailureSnackbar(getView(), errorMessage);
        }
    }

    public Bitmap decodeSampledBitmapFromResource(String resId, int reqWidth, int reqHeight) {
        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(resId, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(resId, options);
    }

    public int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 2;

        if (height > reqHeight || width > reqWidth) {
            if (width > height) {
                inSampleSize = Math.round((float) height / (float) reqHeight);
            } else {
                inSampleSize = Math.round((float) width / (float) reqWidth);
            }
        }
        return inSampleSize;
    }
}
