
package itg8.com.busdriverapp.request.model;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AttendanceAdmin implements Parcelable
{

    @SerializedName("roles")
    @Expose
    private List<Role> roles = null;
    public final static Creator<AttendanceAdmin> CREATOR = new Creator<AttendanceAdmin>() {


        @SuppressWarnings({
            "unchecked"
        })
        public AttendanceAdmin createFromParcel(Parcel in) {
            return new AttendanceAdmin(in);
        }

        public AttendanceAdmin[] newArray(int size) {
            return (new AttendanceAdmin[size]);
        }

    }
    ;

    protected AttendanceAdmin(Parcel in) {
        in.readList(this.roles, (Role.class.getClassLoader()));
    }

    public AttendanceAdmin() {
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(roles);
    }

    public int describeContents() {
        return  0;
    }

}
