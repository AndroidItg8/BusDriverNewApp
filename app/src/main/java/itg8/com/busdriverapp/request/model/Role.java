
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
    private List<User> users = null;
    public final static Creator<Role> CREATOR = new Creator<Role>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Role createFromParcel(Parcel in) {
            return new Role(in);
        }

        public Role[] newArray(int size) {
            return (new Role[size]);
        }

    }
    ;

    protected Role(Parcel in) {
        this.roleID = ((String) in.readValue((String.class.getClassLoader())));
        this.roleName = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.users, (itg8.com.busdriverapp.request.model.User.class.getClassLoader()));
    }

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

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(roleID);
        dest.writeValue(roleName);
        dest.writeList(users);
    }

    public int describeContents() {
        return  0;
    }

}
