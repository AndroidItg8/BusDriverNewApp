
package itg8.com.busdriverapp.home.busModel;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

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
    public final static Parcelable.Creator<User_> CREATOR = new Creator<User_>() {


        @SuppressWarnings({
            "unchecked"
        })
        public User_ createFromParcel(Parcel in) {
            User_ instance = new User_();
            instance.userID = ((String) in.readValue((String.class.getClassLoader())));
            instance.fullName = ((String) in.readValue((String.class.getClassLoader())));
            instance.InBus = ((String) in.readValue((String.class.getClassLoader())));
            return instance;
        }

        public User_[] newArray(int size) {
            return (new User_[size]);
        }

    }
    ;

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

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(userID);
        dest.writeValue(fullName);
        dest.writeValue(InBus);
    }

    public int describeContents() {
        return  0;
    }

}
