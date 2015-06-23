package frogermcs.io.githubclient.data.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Miroslaw Stanek on 22.04.15.
 */
public class User implements Parcelable {
    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        public User[] newArray(int size) {
            return new User[size];
        }
    };
    public String login;
    public long id;
    public String url;
    public String email;

    public User() {
    }

    protected User(Parcel in) {
        this.login = in.readString();
        this.id = in.readLong();
        this.url = in.readString();
        this.email = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.login);
        dest.writeLong(this.id);
        dest.writeString(this.url);
        dest.writeString(this.email);
    }
}
