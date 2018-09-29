
package itg8.com.busdriverapp.home.busModel;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WSResponse implements Parcelable
{

    @SerializedName("Buses")
    @Expose
    private itg8.com.busdriverapp.home.busModel.Buses Buses;
    public final static Parcelable.Creator<WSResponse> CREATOR = new Creator<WSResponse>() {


        @SuppressWarnings({
            "unchecked"
        })
        public WSResponse createFromParcel(Parcel in) {
            WSResponse instance = new WSResponse();
            instance.Buses = ((itg8.com.busdriverapp.home.busModel.Buses) in.readValue((Buses.class.getClassLoader())));
            return instance;
        }

        public WSResponse[] newArray(int size) {
            return (new WSResponse[size]);
        }

    }
    ;

    /**
     * 
     * @return
     *     The Buses
     */
    public itg8.com.busdriverapp.home.busModel.Buses getBuses() {
        return Buses;
    }

    /**
     * 
     * @param Buses
     *     The Buses
     */
    public void setBuses(itg8.com.busdriverapp.home.busModel.Buses Buses) {
        this.Buses = Buses;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(Buses);
    }

    public int describeContents() {
        return  0;
    }

}
