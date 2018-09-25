
package itg8.com.busdriverapp.home.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

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


    public Object getUsers() {
        return users;
    }

    public void setUsers(Object users) {
        this.users = users;
    }

    protected final static Object NOT_FOUND_VALUE = new Object();
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

    @SuppressWarnings({
        "unchecked"
    })
    protected boolean declaredProperty(String name, Object value) {
        if ("CheckpointID".equals(name)) {
            if (value instanceof String) {
                setCheckpointID(((String) value));
            } else {
                throw new IllegalArgumentException(("property \"CheckpointID\" is of type \"java.lang.String\", but got "+ value.getClass().toString()));
            }
            return true;
        } else {
            if ("CheckpointAddress".equals(name)) {
                if (value instanceof String) {
                    setCheckpointAddress(((String) value));
                } else {
                    throw new IllegalArgumentException(("property \"CheckpointAddress\" is of type \"java.lang.String\", but got "+ value.getClass().toString()));
                }
                return true;
            } else {
                if ("Created_By".equals(name)) {
                    if (value instanceof String) {
                        setCreatedBy(((String) value));
                    } else {
                        throw new IllegalArgumentException(("property \"Created_By\" is of type \"java.lang.String\", but got "+ value.getClass().toString()));
                    }
                    return true;
                } else {
                    if ("Created_At".equals(name)) {
                        if (value instanceof String) {
                            setCreatedAt(((String) value));
                        } else {
                            throw new IllegalArgumentException(("property \"Created_At\" is of type \"java.lang.String\", but got "+ value.getClass().toString()));
                        }
                        return true;
                    } else {
                        if ("Is_Active".equals(name)) {
                            if (value instanceof String) {
                                setIsActive(((String) value));
                            } else {
                                throw new IllegalArgumentException(("property \"Is_Active\" is of type \"java.lang.String\", but got "+ value.getClass().toString()));
                            }
                            return true;
                        } else {
                            if ("Longitude".equals(name)) {
                                if (value instanceof String) {
                                    setLongitude(((String) value));
                                } else {
                                    throw new IllegalArgumentException(("property \"Longitude\" is of type \"java.lang.String\", but got "+ value.getClass().toString()));
                                }
                                return true;
                            } else {
                                if ("Latitude".equals(name)) {
                                    if (value instanceof String) {
                                        setLatitude(((String) value));
                                    } else {
                                        throw new IllegalArgumentException(("property \"Latitude\" is of type \"java.lang.String\", but got "+ value.getClass().toString()));
                                    }
                                    return true;
                                } else {
                                    return false;
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @SuppressWarnings({
        "unchecked"
    })
    protected Object declaredPropertyOrNotFound(String name, Object notFoundValue) {
        if ("CheckpointID".equals(name)) {
            return getCheckpointID();
        } else {
            if ("CheckpointAddress".equals(name)) {
                return getCheckpointAddress();
            } else {
                if ("Created_By".equals(name)) {
                    return getCreatedBy();
                } else {
                    if ("Created_At".equals(name)) {
                        return getCreatedAt();
                    } else {
                        if ("Is_Active".equals(name)) {
                            return getIsActive();
                        } else {
                            if ("Longitude".equals(name)) {
                                return getLongitude();
                            } else {
                                if ("Latitude".equals(name)) {
                                    return getLatitude();
                                } else {
                                    return notFoundValue;
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @SuppressWarnings({
        "unchecked"
    })
    public<T >T get(String name) {
        Object value = declaredPropertyOrNotFound(name, Checkpoint.NOT_FOUND_VALUE);
        if (Checkpoint.NOT_FOUND_VALUE!= value) {
            return ((T) value);
        } else {
            throw new IllegalArgumentException((("property \""+ name)+"\" is not defined"));
        }
    }

    @SuppressWarnings({
        "unchecked"
    })
    public void set(String name, Object value) {
        if (!declaredProperty(name, value)) {
            throw new IllegalArgumentException((("property \""+ name)+"\" is not defined"));
        }
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(CheckpointID);
        dest.writeValue(CheckpointAddress);
        dest.writeValue(CreatedBy);
        dest.writeValue(CreatedAt);
        dest.writeValue(IsActive);
        dest.writeValue(Longitude);
        dest.writeValue(Latitude);
    }

    public int describeContents() {
        return  0;
    }

}
