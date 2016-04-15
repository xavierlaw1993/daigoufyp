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
    public int product_id;

    @Expose
    public int user_id;

    @Expose
    public String product_name;

    @Expose
    public List<ProductPicture> product_pics;

    @Expose
    public String product_description;

    @Expose
    public long product_create_time;

    @Expose
    public long product_end_time;

    @Expose
    public double product_price;

    @Expose
    public int quantity;

    @Expose
    public String remark;

    @Expose
    public String category;

    @Expose
    public String country;

    protected Product(Parcel in) {
        product_id = in.readInt();
        user_id = in.readInt();
        product_name = in.readString();
        product_description = in.readString();
        product_create_time = in.readLong();
        product_end_time = in.readLong();
        product_price = in.readDouble();
        quantity = in.readInt();
        remark = in.readString();
        category = in.readString();
        country = in.readString();
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
                ", user_id=" + user_id +
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(product_id);
        dest.writeInt(user_id);
        dest.writeString(product_name);
        dest.writeString(product_description);
        dest.writeLong(product_create_time);
        dest.writeLong(product_end_time);
        dest.writeDouble(product_price);
        dest.writeInt(quantity);
        dest.writeString(remark);
        dest.writeString(category);
        dest.writeString(country);
    }
}
