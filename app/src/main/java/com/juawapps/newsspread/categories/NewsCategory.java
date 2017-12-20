package com.juawapps.newsspread.categories;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Class holding a news category object.
 */

@SuppressWarnings({"unused", "WeakerAccess"})
public class NewsCategory implements Parcelable {
    private final int mLabel;
    private final String mKey;

    public NewsCategory(String key, int labelResId) {
        mLabel = labelResId;
        mKey = key;
    }

    public int getLabel() {
        return mLabel;
    }

    public String getKey() {
        return mKey;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.mLabel);
        dest.writeString(this.mKey);
    }

    private NewsCategory(Parcel in) {
        this.mLabel = in.readInt();
        this.mKey = in.readString();
    }

    public static final Creator<NewsCategory> CREATOR = new Creator<NewsCategory>() {
        @Override
        public NewsCategory createFromParcel(Parcel source) {
            return new NewsCategory(source);
        }

        @Override
        public NewsCategory[] newArray(int size) {
            return new NewsCategory[size];
        }
    };
}
