package com.xavier.daigoufyp.utils;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;

/**
 * Created by zensis on 18/4/16.
 */
public class CameraUtil extends Activity {
    private final static int RESULT_TAKE_PHOTO = 100;
    private static final String TAG = "CameraUtil";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            //Take photo From camera
            Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(i, RESULT_TAKE_PHOTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case RESULT_TAKE_PHOTO:

                if (resultCode == Activity.RESULT_OK && data != null && data.getData() != null) {
                    try {
                        Uri selectedImage = data.getData();
//                        String[] filePathColumn = {MediaStore.Images.Media.DATA};
//                        Cursor cursor = getContentResolver().query(selectedImage,
//                                filePathColumn, null, null, null);
//                        cursor.moveToFirst();
//                        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
//                        String photoPath = cursor.getString(columnIndex);
//                        cursor.close();

                        //return Photo Path to the Main Activity
                        Intent returnFromCameraIntent = new Intent();
                        returnFromCameraIntent.putExtra("photoPath", selectedImage.toString());
                        setResult(RESULT_OK, returnFromCameraIntent);
                        finish();
                    } catch (Exception e) {
                        e.printStackTrace();
                        Intent returnFromGalleryIntent = new Intent();
                        setResult(RESULT_CANCELED, returnFromGalleryIntent);
                        finish();
                    }
                } else {
                    Log.v(TAG, "RESULT_CANCELED");
                    Intent returnFromCameraIntent = new Intent();
                    setResult(RESULT_CANCELED, returnFromCameraIntent);
                    finish();
                }
                break;
        }
    }
}
