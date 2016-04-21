package com.xavier.daigoufyp.view.listcell;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.xavier.daigoufyp.R;
import com.xavier.daigoufyp.model.Product;
import com.xavier.daigoufyp.utils.TimerHelper;
import com.xavier.daigoufyp.utils.Utils;

import org.w3c.dom.Text;

import java.text.DateFormat;

import roboguice.RoboGuice;
import roboguice.inject.InjectView;

/**
 * Created by xavier on 6/4/16.
 */
public class ProductListCellView extends LinearLayout {
    private static String TAG = "ProductListCellView";

    Context context;

    @InjectView(R.id.userIconImageView)
    ImageView userIconImageView;

    @InjectView(R.id.userNameTextView)
    TextView userNameTextView;

    @InjectView(R.id.productNameTextView)
    TextView productNameTextView;

    @InjectView(R.id.productPriceTextView)
    TextView productPriceTextView;

    @InjectView(R.id.productCategoryTextView)
    TextView productCategoryTextView;

    @InjectView(R.id.productCountryTextView)
    TextView productCountryTextView;

    @InjectView(R.id.productDurationTextView)
    TextView productDurationTextView;

    @InjectView(R.id.productPicImageView)
    ImageView productPicImageView;

    TimerHelper timerHelper;

    public ProductListCellView(Context context) {
        super(context);
        init(context);
    }

    public ProductListCellView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ProductListCellView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ProductListCellView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    protected void init(Context context) {
        this.context = context;
        inflate(context, R.layout.view_product_item, this);
        RoboGuice.getInjector(getContext()).injectMembers(this);
        RoboGuice.getInjector(getContext()).injectViewMembers(this);
        timerHelper = new TimerHelper(context);
    }

    public void bindModel(Product product) {
        userNameTextView.setText(product.user_name);
        productNameTextView.setText(product.product_name);
        productPriceTextView.setText("HKD " + product.product_price);
        productCategoryTextView.setText(product.category);
        productCountryTextView.setText(product.country);

        ViewGroup.LayoutParams params = productPicImageView.getLayoutParams();
        params.height = (int) (Utils.screenWidth * Utils.hdRatio);
        productPicImageView.setLayoutParams(params);

        try {
            Picasso.with(context)
                    .load(product.user_profile_pic)
                    .placeholder(R.mipmap.ic_launcher)
                    .resize(Utils.screenWidth / 8, (int) (Utils.screenWidth / 8 * Utils.hdRatio))
                    .into(userIconImageView);

            Picasso.with(context)
                    .load(product.product_pics.get(0).product_pic_url)
                    .placeholder(R.mipmap.ic_launcher)
                    .error(R.mipmap.ic_launcher)
                    .resize(Utils.screenWidth, (int) (Utils.screenWidth * Utils.hdRatio))
                    .into(productPicImageView, new Callback() {
                        @Override
                        public void onSuccess() {
                            Log.v(TAG, "onSuccess");
                        }

                        @Override
                        public void onError() {
                            Log.v(TAG, "onError");
                        }
                    });
        } catch (Exception ex) {
        }

        timerHelper.setTimerHelperListener(new TimerHelper.TimerHelperListener() {
            @Override
            public void onTimerStarted(String startTime) {
                productDurationTextView.setText(startTime);
            }

            @Override
            public void onTimerTicking(String tickTime) {
                productDurationTextView.setText(tickTime);
            }

            @Override
            public void onTimerFinished() {
                productDurationTextView.setText(context.getString(R.string.product__finished));
            }
        });
        timerHelper.countDown(Long.parseLong(product.product_end_time));
    }
}
