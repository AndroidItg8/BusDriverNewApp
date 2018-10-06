
package itg8.com.busdriverapp.home.busModel;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WSResponse implements Parcelable
{

    @SerializedName("Buses")
    @Expose
    private Object Buses;

    /**
     *
     * @return
     *     The Buses
     */
    public Object getBuses() {
        return Buses;
    }

    /**
     *
     * @param Buses
     *     The Buses
     */
    public void setBuses(Object Buses) {
        this.Buses = Buses;
    }


    public WSResponse() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList((List<Buses>) this.Buses);
    }
    protected WSResponse(Parcel in) {
        this.Buses = in.readParcelableArray(Object.class.getClassLoader());
    }

    public static final Creator<WSResponse> CREATOR = new Creator<WSResponse>() {
        @Override
        public WSResponse createFromParcel(Parcel source) {
            return new WSResponse(source);
        }

        @Override
        public WSResponse[] newArray(int size) {
            return new WSResponse[size];
        }
    };
}
