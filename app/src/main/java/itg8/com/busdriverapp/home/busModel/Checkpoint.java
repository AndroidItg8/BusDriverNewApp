
package itg8.com.busdriverapp.home.busModel;

import java.util.ArrayList;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Checkpoint implements Parcelable
{

    @SerializedName("CheckpointID")
    @Expose
    private String CheckpointID;
    @SerializedName("CheckpointAddress")
    @Expose
    private String CheckpointAddress;
    @SerializedName("Created_By")
    @Expose
    private String CreatedBy;
    @SerializedName("Created_At")
    @Expose
    private String CreatedAt;
    @SerializedName("Is_Active")
    @Expose
    private String IsActive;
    @SerializedName("Longitude")
    @Expose
    private String Longitude;
    @SerializedName("Latitude")
    @Expose
    private String Latitude;
    @SerializedName("users")
    @Expose
    private Object users;
    private List<User_> usersChild;

    /**
     * 
     * @return
     *     The CheckpointID
     */
    public String getCheckpointID() {
        return CheckpointID;
    }

    /**
     * 
     * @param CheckpointID
     *     The CheckpointID
     */
    public void setCheckpointID(String CheckpointID) {
        this.CheckpointID = CheckpointID;
    }

    /**
     * 
     * @return
     *     The CheckpointAddress
     */
    public String getCheckpointAddress() {
        return CheckpointAddress;
    }

    /**
     * 
     * @param CheckpointAddress
     *     The CheckpointAddress
     */
    public void setCheckpointAddress(String CheckpointAddress) {
        this.CheckpointAddress = CheckpointAddress;
    }

    /**
     * 
     * @return
     *     The CreatedBy
     */
    public String getCreatedBy() {
        return CreatedBy;
    }

    /**
     * 
     * @param CreatedBy
     *     The Created_By
     */
    public void setCreatedBy(String CreatedBy) {
        this.CreatedBy = CreatedBy;
    }

    /**
     * 
     * @return
     *     The CreatedAt
     */
    public String getCreatedAt() {
        return CreatedAt;
    }

    /**
     * 
     * @param CreatedAt
     *     The Created_At
     */
    public void setCreatedAt(String CreatedAt) {
        this.CreatedAt = CreatedAt;
    }

    /**
     * 
     * @return
     *     The IsActive
     */
    public String getIsActive() {
        return IsActive;
    }

    /**
     * 
     * @param IsActive
     *     The Is_Active
     */
    public void setIsActive(String IsActive) {
        this.IsActive = IsActive;
    }

    /**
     * 
     * @return
     *     The Longitude
     */
    public String getLongitude() {
        return Longitude;
    }

    /**
     * 
     * @param Longitude
     *     The Longitude
     */
    public void setLongitude(String Longitude) {
        this.Longitude = Longitude;
    }

    /**
     * 
     * @return
     *     The Latitude
     */
    public String getLatitude() {
        return Latitude;
    }

    /**
     * 
     * @param Latitude
     *     The Latitude
     */
    public void setLatitude(String Latitude) {
        this.Latitude = Latitude;
    }

    /**
     * 
     * @return
     *     The users
     */
    public Object getUsers() {
        return users;
    }

    /**
     * 
     * @param users
     *     The users
     */
    public void setUsers(Object users) {
        this.users = users;
    }

    public static Creator<Checkpoint> getCREATOR() {
        return CREATOR;
    }

    public List<User_> getUsersChild() {
        return usersChild;
    }

    public void setChildUser(List<User_> usersChild) {

        this.usersChild = usersChild;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.CheckpointID);
        dest.writeString(this.CheckpointAddress);
        dest.writeString(this.CreatedBy);
        dest.writeString(this.CreatedAt);
        dest.writeString(this.IsActive);
        dest.writeString(this.Longitude);
        dest.writeString(this.Latitude);
        dest.writeTypedList(this.usersChild);
    }



    public Checkpoint() {
    }

    protected Checkpoint(Parcel in) {
        this.CheckpointID = in.readString();
        this.CheckpointAddress = in.readString();
        this.CreatedBy = in.readString();
        this.CreatedAt = in.readString();
        this.IsActive = in.readString();
        this.Longitude = in.readString();
        this.Latitude = in.readString();
        this.usersChild = in.createTypedArrayList(User_.CREATOR);
    }

    public static final Creator<Checkpoint> CREATOR = new Creator<Checkpoint>() {
        @Override
        public Checkpoint createFromParcel(Parcel source) {
            return new Checkpoint(source);
        }

        @Override
        public Checkpoint[] newArray(int size) {
            return new Checkpoint[size];
        }
    };
}
