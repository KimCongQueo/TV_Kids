package quanth.vn.com.tv_kids.models;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;


/**
 * Created by Admin on 4/13/2017.
 */

public class User implements Parcelable {
    @SerializedName("id")
    private String id;
    @SerializedName("tokenId")
    private String tokenId;
    @SerializedName("email")
    private String email;
    @SerializedName("displayName")
    private String displayName;
    @SerializedName("photoUrl")
    private Uri photoUrl;

    public User(){}

    private User(Parcel in) {
        id = in.readString();
        tokenId = in.readString();
        email = in.readString();
        displayName = in.readString();
        photoUrl = in.readParcelable(Uri.class.getClassLoader());
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public Uri getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(Uri photoUrl) {
        this.photoUrl = photoUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(tokenId);
        dest.writeString(email);
        dest.writeString(displayName);
        dest.writeParcelable(photoUrl, flags);
    }
}
