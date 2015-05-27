package frogermcs.io.githubclient.data.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Miroslaw Stanek on 22.04.15.
 */
public class Repository implements Parcelable {
    public static final Parcelable.Creator<Repository> CREATOR = new Parcelable.Creator<Repository>() {
        public Repository createFromParcel(Parcel source) {
            return new Repository(source);
        }

        public Repository[] newArray(int size) {
            return new Repository[size];
        }
    };
    public long id;
    public String name;
    public String url;

    public Repository() {
    }

    private Repository(Parcel in) {
        this.id = in.readLong();
        this.name = in.readString();
        this.url = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeString(this.name);
        dest.writeString(this.url);
    }
}
