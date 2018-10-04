
package itg8.com.busdriverapp.home.busModel;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class User_ implements Parcelable
{

    @SerializedName("userID")
    @Expose
    private String userID;
    @SerializedName("fullName")
    @Expose
    private String fullName;
    @SerializedName("InBus")
    @Expose
    private String InBus;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    private String address;


    /**
     * 
     * @return
     *     The userID
     */
    public String getUserID() {
        return userID;
    }

    /**
     * 
     * @param userID
     *     The userID
     */
    public void setUserID(String userID) {
        this.userID = userID;
    }

    /**
     * 
     * @return
     *     The fullName
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * 
     * @param fullName
     *     The fullName
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * 
     * @return
     *     The InBus
     */
    public String getInBus() {
        return InBus;
    }

    /**
     * 
     * @param InBus
     *     The InBus
     */
    public void setInBus(String InBus) {
        this.InBus = InBus;
    }

    public User_() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.userID);
        dest.writeString(this.fullName);
        dest.writeString(this.InBus);
        dest.writeString(this.address);
    }

    protected User_(Parcel in) {
        this.userID = in.readString();
        this.fullName = in.readString();
        this.InBus = in.readString();
        this.address = in.readString();
    }

    public static final Creator<User_> CREATOR = new Creator<User_>() {
        @Override
        public User_ createFromParcel(Parcel source) {
            return new User_(source);
        }

        @Override
        public User_[] newArray(int size) {
            return new User_[size];
        }
    };
}
