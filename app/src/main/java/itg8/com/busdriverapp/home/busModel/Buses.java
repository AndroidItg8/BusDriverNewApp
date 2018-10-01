
package itg8.com.busdriverapp.home.busModel;

import java.util.ArrayList;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Buses implements Parcelable
{

    @SerializedName("busName")
    @Expose
    private String busName;

    @SerializedName("fullName")
    @Expose
    private String driverName;
    private List<User> userList;

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getDriverNumber() {
        return driverNumber;
    }

    public void setDriverNumber(String driverNumber) {
        this.driverNumber = driverNumber;
    }

    @SerializedName("mobileNumber")
    @Expose
    private String driverNumber;
    @SerializedName("busNumber")
    @Expose
    private String busNumber;

    @SerializedName("users")
    @Expose

    private Object  user ;


    public String getBusName() {
        return busName;
    }

    public void setBusName(String busName) {
        busName = busName;
    }

    public String getBusNumber() {
        return busNumber;
    }

    public void setBusNumber(String busNumber) {
        this.busNumber = busNumber;
    }


    public Object getUser() {
        return user;
    }

    public void setUser(Object user) {
        this.user = user;
    }


    public static Creator<Buses> getCREATOR() {
        return CREATOR;
    }
  public Buses() {
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public List<User> getUserList() {
        return userList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.busName);
        dest.writeString(this.driverName);
        dest.writeTypedList(this.userList);
        dest.writeString(this.driverNumber);
        dest.writeString(this.busNumber);
        dest.writeParcelable((Parcelable) this.user, flags);
    }

    protected Buses(Parcel in) {
        this.busName = in.readString();
        this.driverName = in.readString();
        this.userList = in.createTypedArrayList(User.CREATOR);
        this.driverNumber = in.readString();
        this.busNumber = in.readString();
        this.user = in.readParcelable(Object.class.getClassLoader());
    }

    public static final Creator<Buses> CREATOR = new Creator<Buses>() {
        @Override
        public Buses createFromParcel(Parcel source) {
            return new Buses(source);
        }

        @Override
        public Buses[] newArray(int size) {
            return new Buses[size];
        }
    };
}
