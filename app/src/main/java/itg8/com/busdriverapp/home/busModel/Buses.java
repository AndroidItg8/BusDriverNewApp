
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

    @SerializedName("users")
    @Expose
    private List<User> users = new ArrayList<User>();
    public final static Parcelable.Creator<Buses> CREATOR = new Creator<Buses>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Buses createFromParcel(Parcel in) {
            Buses instance = new Buses();
            in.readList(instance.users, (itg8.com.busdriverapp.home.busModel.User.class.getClassLoader()));
            return instance;
        }

        public Buses[] newArray(int size) {
            return (new Buses[size]);
        }

    }
    ;

    /**
     * 
     * @return
     *     The users
     */
    public List<User> getUsers() {
        return users;
    }

    /**
     * 
     * @param users
     *     The users
     */
    public void setUsers(List<User> users) {
        this.users = users;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(users);
    }

    public int describeContents() {
        return  0;
    }

}
