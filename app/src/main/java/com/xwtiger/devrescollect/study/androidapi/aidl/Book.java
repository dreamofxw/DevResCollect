package com.xwtiger.devrescollect.study.androidapi.aidl;

import android.os.Parcel;
import android.os.Parcelable;

public class Book implements Parcelable {

    public String name;
    public String publishtime;
    public String author;

    public Book(){};

    protected Book(Parcel in) {
        name = in.readString();
        publishtime = in.readString();
        author = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(publishtime);
        dest.writeString(author);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };
}
