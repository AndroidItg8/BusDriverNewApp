
package itg8.com.busdriverapp.home.model;

import java.util.ArrayList;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RouteEList implements Parcelable
{

    @SerializedName("pkid")
    @Expose
    private int pkid;
    @SerializedName("RouteName")
    @Expose
    private String RouteName;
    @SerializedName("Address")
    @Expose
    private String Address;
    @SerializedName("Longitude")
    @Expose
    private String Longitude;
    @SerializedName("Latitude")
    @Expose
    private String Latitude;
    @SerializedName("StudentList")
    @Expose
    private List<itg8.com.busdriverapp.home.model.StudentList> StudentList = new ArrayList<itg8.com.busdriverapp.home.model.StudentList>();
    public final static Parcelable.Creator<RouteEList> CREATOR = new Creator<RouteEList>() {


        @SuppressWarnings({
            "unchecked"
        })
        public RouteEList createFromParcel(Parcel in) {
            RouteEList instance = new RouteEList();
            instance.pkid = ((int) in.readValue((int.class.getClassLoader())));
            instance.RouteName = ((String) in.readValue((String.class.getClassLoader())));
            instance.Address = ((String) in.readValue((String.class.getClassLoader())));
            instance.Longitude = ((String) in.readValue((String.class.getClassLoader())));
            instance.Latitude = ((String) in.readValue((String.class.getClassLoader())));
            in.readList(instance.StudentList, (itg8.com.busdriverapp.home.model.StudentList.class.getClassLoader()));
            return instance;
        }

        public RouteEList[] newArray(int size) {
            return (new RouteEList[size]);
        }

    }
    ;

    /**
     * 
     * @return
     *     The pkid
     */
    public int getPkid() {
        return pkid;
    }

    /**
     * 
     * @param pkid
     *     The pkid
     */
    public void setPkid(int pkid) {
        this.pkid = pkid;
    }

    /**
     * 
     * @return
     *     The RouteName
     */
    public String getRouteName() {
        return RouteName;
    }

    /**
     * 
     * @param RouteName
     *     The RouteName
     */
    public void setRouteName(String RouteName) {
        this.RouteName = RouteName;
    }

    /**
     * 
     * @return
     *     The Address
     */
    public String getAddress() {
        return Address;
    }

    /**
     * 
     * @param Address
     *     The Address
     */
    public void setAddress(String Address) {
        this.Address = Address;
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
     *     The StudentList
     */
    public List<itg8.com.busdriverapp.home.model.StudentList> getStudentList() {
        return StudentList;
    }

    /**
     * 
     * @param StudentList
     *     The StudentList
     */
    public void setStudentList(List<itg8.com.busdriverapp.home.model.StudentList> StudentList) {
        this.StudentList = StudentList;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(pkid);
        dest.writeValue(RouteName);
        dest.writeValue(Address);
        dest.writeValue(Longitude);
        dest.writeValue(Latitude);
        dest.writeList(StudentList);
    }

    public int describeContents() {
        return  0;
    }

}
