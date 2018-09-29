
package itg8.com.busdriverapp.home.busModel;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User implements Parcelable
{

    @SerializedName("routeName")
    @Expose
    private String routeName;
    @SerializedName("busName")
    @Expose
    private String busName;
    @SerializedName("busNumber")
    @Expose
    private String busNumber;
    @SerializedName("checkpoints")
    @Expose
    private Checkpoints checkpoints;
    @SerializedName("busPhoto")
    @Expose
    private Object busPhoto;
    public final static Parcelable.Creator<User> CREATOR = new Creator<User>() {


        @SuppressWarnings({
            "unchecked"
        })
        public User createFromParcel(Parcel in) {
            User instance = new User();
            instance.routeName = ((String) in.readValue((String.class.getClassLoader())));
            instance.busName = ((String) in.readValue((String.class.getClassLoader())));
            instance.busNumber = ((String) in.readValue((String.class.getClassLoader())));
            instance.checkpoints = ((Checkpoints) in.readValue((Checkpoints.class.getClassLoader())));
            instance.busPhoto = ((Object) in.readValue((Object.class.getClassLoader())));
            return instance;
        }

        public User[] newArray(int size) {
            return (new User[size]);
        }

    }
    ;

    /**
     * 
     * @return
     *     The routeName
     */
    public String getRouteName() {
        return routeName;
    }

    /**
     * 
     * @param routeName
     *     The routeName
     */
    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    /**
     * 
     * @return
     *     The busName
     */
    public String getBusName() {
        return busName;
    }

    /**
     * 
     * @param busName
     *     The busName
     */
    public void setBusName(String busName) {
        this.busName = busName;
    }

    /**
     * 
     * @return
     *     The busNumber
     */
    public String getBusNumber() {
        return busNumber;
    }

    /**
     * 
     * @param busNumber
     *     The busNumber
     */
    public void setBusNumber(String busNumber) {
        this.busNumber = busNumber;
    }

    /**
     * 
     * @return
     *     The checkpoints
     */
    public Checkpoints getCheckpoints() {
        return checkpoints;
    }

    /**
     * 
     * @param checkpoints
     *     The checkpoints
     */
    public void setCheckpoints(Checkpoints checkpoints) {
        this.checkpoints = checkpoints;
    }

    /**
     * 
     * @return
     *     The busPhoto
     */
    public Object getBusPhoto() {
        return busPhoto;
    }

    /**
     * 
     * @param busPhoto
     *     The busPhoto
     */
    public void setBusPhoto(Object busPhoto) {
        this.busPhoto = busPhoto;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(routeName);
        dest.writeValue(busName);
        dest.writeValue(busNumber);
        dest.writeValue(checkpoints);
        dest.writeValue(busPhoto);
    }

    public int describeContents() {
        return  0;
    }

}
