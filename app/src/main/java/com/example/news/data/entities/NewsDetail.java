package com.example.news.data.entities;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class NewsDetail implements Parcelable {

    public static final Creator<NewsDetail> CREATOR = new Creator<NewsDetail>() {
        @Override
        public NewsDetail createFromParcel(Parcel in) {
            return new NewsDetail(in);
        }

        @Override
        public NewsDetail[] newArray(int size) {
            return new NewsDetail[size];
        }
    };
    @SerializedName("format")
    private String mFormat;
    @SerializedName("height")
    private Long mHeight;
    @SerializedName("url")
    private String mUrl;
    @SerializedName("width")
    private Long mWidth;

    protected NewsDetail(Parcel in) {
        mFormat = in.readString();
        if (in.readByte() == 0) {
            mHeight = null;
        } else {
            mHeight = in.readLong();
        }
        mUrl = in.readString();
        if (in.readByte() == 0) {
            mWidth = null;
        } else {
            mWidth = in.readLong();
        }
    }

    public String getFormat() {
        return mFormat;
    }

    public void setFormat(String format) {
        mFormat = format;
    }

    public Long getHeight() {
        return mHeight;
    }

    public void setHeight(Long height) {
        mHeight = height;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        mUrl = url;
    }

    public Long getWidth() {
        return mWidth;
    }

    public void setWidth(Long width) {
        mWidth = width;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mFormat);
        if (mHeight == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeLong(mHeight);
        }
        parcel.writeString(mUrl);
        if (mWidth == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeLong(mWidth);
        }
    }
}
