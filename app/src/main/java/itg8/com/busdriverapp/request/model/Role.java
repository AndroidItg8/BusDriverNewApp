
package itg8.com.busdriverapp.request.model;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Role implements Parcelable
{

    @SerializedName("RoleID")
    @Expose
    private String roleID;
    @SerializedName("RoleName")
    @Expose
    private String roleName;
    @SerializedName("users")
    @Expose
    private Object users = null;
    private List<User> roleUser;

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    private boolean isChecked;

    public Role() {
    }

    public String getRoleID() {
        return roleID;
    }

    public void setRoleID(String roleID) {
        this.roleID = roleID;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Object getUsers() {
        return users;
    }

    public void setUsers(Object  users) {
        this.users = users;
    }


    public void setRoleUser(List<User> roleUser) {
        this.roleUser = roleUser;
    }

    public List<User> getRoleUser() {
        return roleUser;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.roleID);
        dest.writeString(this.roleName);
        dest.writeTypedList(this.roleUser);
        dest.writeByte(this.isChecked ? (byte) 1 : (byte) 0);
    }

    protected Role(Parcel in) {
        this.roleID = in.readString();
        this.roleName = in.readString();
        this.roleUser = in.createTypedArrayList(User.CREATOR);
        this.isChecked = in.readByte() != 0;
    }

    public static final Creator<Role> CREATOR = new Creator<Role>() {
        @Override
        public Role createFromParcel(Parcel source) {
            return new Role(source);
        }

        @Override
        public Role[] newArray(int size) {
            return new Role[size];
        }
    };
}
