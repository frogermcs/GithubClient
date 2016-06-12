package frogermcs.io.githubclient.data.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Miroslaw Stanek on 22.04.15.
 */
public class Repository implements Parcelable {
    public static final int TYPE_NORMAL = 0;
    public static final int TYPE_BIG = 1;
    public static final int TYPE_FEATURED = 2;

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
    public int stargazers_count;
    public int forks_count;

    public Repository() {
    }

    private Repository(Parcel in) {
        this.id = in.readLong();
        this.name = in.readString();
        this.url = in.readString();
        this.stargazers_count = in.readInt();
        this.forks_count = in.readInt();
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
        dest.writeInt(this.stargazers_count);
        dest.writeInt(this.forks_count);
    }
}
