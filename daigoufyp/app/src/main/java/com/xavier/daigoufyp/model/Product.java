package com.xavier.daigoufyp.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;

import java.util.List;

/**
 * Created by xavier on 6/4/16.
 */
public class Product implements Parcelable {
    private static String TAG = "Product";

    @Expose
    public String product_id;

    @Expose
    public String user_id;

    @Expose
    public String user_name;

    @Expose
    public String user_profile_pic;

    @Expose
    public String product_name;

    @Expose
    public List<ProductPicture> product_pics;

    @Expose
    public String product_description;

    @Expose
    public String product_create_time;

    @Expose
    public String product_end_time;

    @Expose
    public String product_price;

    @Expose
    public String quantity;

    @Expose
    public String remark;

    @Expose
    public String category;

    @Expose
    public String country;


    protected Product(Parcel in) {
        product_id = in.readString();
        user_id = in.readString();
        user_name = in.readString();
        user_profile_pic = in.readString();
        product_name = in.readString();
        product_description = in.readString();
        product_create_time = in.readString();
        product_end_time = in.readString();
        product_price = in.readString();
        quantity = in.readString();
        remark = in.readString();
        category = in.readString();
        country = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(product_id);
        dest.writeString(user_id);
        dest.writeString(user_name);
        dest.writeString(user_profile_pic);
        dest.writeString(product_name);
        dest.writeString(product_description);
        dest.writeString(product_create_time);
        dest.writeString(product_end_time);
        dest.writeString(product_price);
        dest.writeString(quantity);
        dest.writeString(remark);
        dest.writeString(category);
        dest.writeString(country);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

    @Override
    public String toString() {
        return "Product{" +
                "product_id=" + product_id +
                ", user_id='" + user_id + '\'' +
                ", user_name='" + user_name + '\'' +
                ", user_profile_pic='" + user_profile_pic + '\'' +
                ", product_name='" + product_name + '\'' +
                ", product_pics=" + product_pics +
                ", product_description='" + product_description + '\'' +
                ", product_create_time=" + product_create_time +
                ", product_end_time=" + product_end_time +
                ", product_price=" + product_price +
                ", quantity=" + quantity +
                ", remark='" + remark + '\'' +
                ", category='" + category + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}

