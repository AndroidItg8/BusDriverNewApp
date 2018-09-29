
package itg8.com.busdriverapp.home.busModel;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BusModel implements Parcelable
{

    @SerializedName("WSResponse")
    @Expose
    private itg8.com.busdriverapp.home.busModel.WSResponse WSResponse;
    public final static Parcelable.Creator<BusModel> CREATOR = new Creator<BusModel>() {


        @SuppressWarnings({
            "unchecked"
        })
        public BusModel createFromParcel(Parcel in) {
            BusModel instance = new BusModel();
            instance.WSResponse = ((itg8.com.busdriverapp.home.busModel.WSResponse) in.readValue((WSResponse.class.getClassLoader())));
            return instance;
        }

        public BusModel[] newArray(int size) {
            return (new BusModel[size]);
        }

    }
    ;

    /**
     * 
     * @return
     *     The WSResponse
     */
    public itg8.com.busdriverapp.home.busModel.WSResponse getWSResponse() {
        return WSResponse;
    }

    /**
     * 
     * @param WSResponse
     *     The WSResponse
     */
    public void setWSResponse(itg8.com.busdriverapp.home.busModel.WSResponse WSResponse) {
        this.WSResponse = WSResponse;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(WSResponse);
    }

    public int describeContents() {
        return  0;
    }

}
