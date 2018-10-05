
package itg8.com.busdriverapp.request.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WSResponse implements Parcelable
{

    @SerializedName("AttendanceAdmin")
    @Expose
    private AttendanceAdmin attendanceAdmin;
    public final static Creator<WSResponse> CREATOR = new Creator<WSResponse>() {


        @SuppressWarnings({
            "unchecked"
        })
        public WSResponse createFromParcel(Parcel in) {
            return new WSResponse(in);
        }

        public WSResponse[] newArray(int size) {
            return (new WSResponse[size]);
        }

    }
    ;

    protected WSResponse(Parcel in) {
        this.attendanceAdmin = ((AttendanceAdmin) in.readValue((AttendanceAdmin.class.getClassLoader())));
    }

    public WSResponse() {
    }

    public AttendanceAdmin getAttendanceAdmin() {
        return attendanceAdmin;
    }

    public void setAttendanceAdmin(AttendanceAdmin attendanceAdmin) {
        this.attendanceAdmin = attendanceAdmin;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(attendanceAdmin);
    }

    public int describeContents() {
        return  0;
    }

}
