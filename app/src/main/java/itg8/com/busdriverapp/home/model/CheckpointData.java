
package itg8.com.busdriverapp.home.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CheckpointData implements Parcelable
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
    protected final static Object NOT_FOUND_VALUE = new Object();
    public final static Parcelable.Creator<CheckpointData> CREATOR = new Creator<CheckpointData>() {


        @SuppressWarnings({
            "unchecked"
        })
        public CheckpointData createFromParcel(Parcel in) {
            CheckpointData instance = new CheckpointData();
            instance.routeName = ((String) in.readValue((String.class.getClassLoader())));
            instance.busName = ((String) in.readValue((String.class.getClassLoader())));
            instance.busNumber = ((String) in.readValue((String.class.getClassLoader())));
            instance.checkpoints = ((Checkpoints) in.readValue((Checkpoints.class.getClassLoader())));
            return instance;
        }

        public CheckpointData[] newArray(int size) {
            return (new CheckpointData[size]);
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

    @SuppressWarnings({
        "unchecked"
    })
    protected boolean declaredProperty(String name, Object value) {
        if ("routeName".equals(name)) {
            if (value instanceof String) {
                setRouteName(((String) value));
            } else {
                throw new IllegalArgumentException(("property \"routeName\" is of type \"java.lang.String\", but got "+ value.getClass().toString()));
            }
            return true;
        } else {
            if ("busName".equals(name)) {
                if (value instanceof String) {
                    setBusName(((String) value));
                } else {
                    throw new IllegalArgumentException(("property \"busName\" is of type \"java.lang.String\", but got "+ value.getClass().toString()));
                }
                return true;
            } else {
                if ("busNumber".equals(name)) {
                    if (value instanceof String) {
                        setBusNumber(((String) value));
                    } else {
                        throw new IllegalArgumentException(("property \"busNumber\" is of type \"java.lang.String\", but got "+ value.getClass().toString()));
                    }
                    return true;
                } else {
                    if ("checkpoints".equals(name)) {
                        if (value instanceof Checkpoints) {
                            setCheckpoints(((Checkpoints) value));
                        } else {
                            throw new IllegalArgumentException(("property \"checkpoints\" is of type \"itg8.com.busdriverapp.home.model.Checkpoints\", but got "+ value.getClass().toString()));
                        }
                        return true;
                    } else {
                        return false;
                    }
                }
            }
        }
    }

    @SuppressWarnings({
        "unchecked"
    })
    protected Object declaredPropertyOrNotFound(String name, Object notFoundValue) {
        if ("routeName".equals(name)) {
            return getRouteName();
        } else {
            if ("busName".equals(name)) {
                return getBusName();
            } else {
                if ("busNumber".equals(name)) {
                    return getBusNumber();
                } else {
                    if ("checkpoints".equals(name)) {
                        return getCheckpoints();
                    } else {
                        return notFoundValue;
                    }
                }
            }
        }
    }

    @SuppressWarnings({
        "unchecked"
    })
    public<T >T get(String name) {
        Object value = declaredPropertyOrNotFound(name, CheckpointData.NOT_FOUND_VALUE);
        if (CheckpointData.NOT_FOUND_VALUE!= value) {
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
        dest.writeValue(routeName);
        dest.writeValue(busName);
        dest.writeValue(busNumber);
        dest.writeValue(checkpoints);
    }

    public int describeContents() {
        return  0;
    }

}
