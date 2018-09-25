
package itg8.com.busdriverapp.map;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Polyline implements Parcelable
{

    @SerializedName("points")
    @Expose
    private String points;
    public final static Creator<Polyline> CREATOR = new Creator<Polyline>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Polyline createFromParcel(Parcel in) {
            Polyline instance = new Polyline();
            instance.points = ((String) in.readValue((String.class.getClassLoader())));
            return instance;
        }

        public Polyline[] newArray(int size) {
            return (new Polyline[size]);
        }

    }
    ;

    /**
     * 
     * @return
     *     The points
     */
    public String getPoints() {
        return points;
    }

    /**
     * 
     * @param points
     *     The points
     */
    public void setPoints(String points) {
        this.points = points;
    }


    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(points);
    }

    public int describeContents() {
        return  0;
    }

}
