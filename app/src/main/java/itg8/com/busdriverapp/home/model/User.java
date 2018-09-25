package itg8.com.busdriverapp.home.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User implements Parcelable {
//    {"userID":"1190","fullName":"D . .","InBus":"false"}
    @SerializedName("userID")
    @Expose
    private String userID;

    @SerializedName("fullName")
    @Expose
    private String fullName;

    @SerializedName("InBus")
    @Expose
    private String inBus;


    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getInBus() {
        return inBus;
    }

    public void setInBus(String inBus) {
        this.inBus = inBus;
    }



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.userID);
        dest.writeString(this.fullName);
        dest.writeString(this.inBus);
    }

    protected User(Parcel in) {
        this.userID = in.readString();
        this.fullName = in.readString();
        this.inBus = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
