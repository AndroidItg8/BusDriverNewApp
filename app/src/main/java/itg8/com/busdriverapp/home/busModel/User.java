
package itg8.com.busdriverapp.home.busModel;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

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
    private List<Checkpoint> listCheckPoints;

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


    public static Creator<User> getCREATOR() {
        return CREATOR;
    }

    public List<Checkpoint> getListCheckPoints() {
        return listCheckPoints;
    }

    public void setCheckPointList(List<Checkpoint> listCheckPoints) {

        this.listCheckPoints = listCheckPoints;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.routeName);
        dest.writeString(this.busName);
        dest.writeString(this.busNumber);
        dest.writeParcelable(this.checkpoints, flags);
        dest.writeParcelable((Parcelable) this.busPhoto, flags);
        dest.writeTypedList(this.listCheckPoints);
    }

    public User() {
    }

    protected User(Parcel in) {
        this.routeName = in.readString();
        this.busName = in.readString();
        this.busNumber = in.readString();
        this.checkpoints = in.readParcelable(Checkpoints.class.getClassLoader());
        this.busPhoto = in.readParcelable(Object.class.getClassLoader());
        this.listCheckPoints = in.createTypedArrayList(Checkpoint.CREATOR);
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
