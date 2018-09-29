
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
    private List<User_> users = new ArrayList<User_>();
    public final static Parcelable.Creator<Checkpoint> CREATOR = new Creator<Checkpoint>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Checkpoint createFromParcel(Parcel in) {
            Checkpoint instance = new Checkpoint();
            instance.CheckpointID = ((String) in.readValue((String.class.getClassLoader())));
            instance.CheckpointAddress = ((String) in.readValue((String.class.getClassLoader())));
            instance.CreatedBy = ((String) in.readValue((String.class.getClassLoader())));
            instance.CreatedAt = ((String) in.readValue((String.class.getClassLoader())));
            instance.IsActive = ((String) in.readValue((String.class.getClassLoader())));
            instance.Longitude = ((String) in.readValue((String.class.getClassLoader())));
            instance.Latitude = ((String) in.readValue((String.class.getClassLoader())));
            in.readList(instance.users, (itg8.com.busdriverapp.home.busModel.User_.class.getClassLoader()));
            return instance;
        }

        public Checkpoint[] newArray(int size) {
            return (new Checkpoint[size]);
        }

    }
    ;

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
    public List<User_> getUsers() {
        return users;
    }

    /**
     * 
     * @param users
     *     The users
     */
    public void setUsers(List<User_> users) {
        this.users = users;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(CheckpointID);
        dest.writeValue(CheckpointAddress);
        dest.writeValue(CreatedBy);
        dest.writeValue(CreatedAt);
        dest.writeValue(IsActive);
        dest.writeValue(Longitude);
        dest.writeValue(Latitude);
        dest.writeList(users);
    }

    public int describeContents() {
        return  0;
    }

}
