
package itg8.com.busdriverapp.map;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;


public class GeocodedWaypoint implements Parcelable
{

    @SerializedName("geocoder_status")
    @Expose
    private String geocoderStatus;
    @SerializedName("partial_match")
    @Expose
    private boolean partialMatch;
    @SerializedName("place_id")
    @Expose
    private String placeId;
    @SerializedName("types")
    @Expose

    private List<String> types = new ArrayList<String>();
    public final static Creator<GeocodedWaypoint> CREATOR = new Creator<GeocodedWaypoint>() {


        @SuppressWarnings({
            "unchecked"
        })
        public GeocodedWaypoint createFromParcel(Parcel in) {
            GeocodedWaypoint instance = new GeocodedWaypoint();
            instance.geocoderStatus = ((String) in.readValue((String.class.getClassLoader())));
            instance.partialMatch = ((boolean) in.readValue((boolean.class.getClassLoader())));
            instance.placeId = ((String) in.readValue((String.class.getClassLoader())));
            in.readList(instance.types, (String.class.getClassLoader()));
            return instance;
        }

        public GeocodedWaypoint[] newArray(int size) {
            return (new GeocodedWaypoint[size]);
        }

    }
    ;

    /**
     * 
     * @return
     *     The geocoderStatus
     */
    public String getGeocoderStatus() {
        return geocoderStatus;
    }

    /**
     * 
     * @param geocoderStatus
     *     The geocoder_status
     */
    public void setGeocoderStatus(String geocoderStatus) {
        this.geocoderStatus = geocoderStatus;
    }

    /**
     * 
     * @return
     *     The partialMatch
     */
    public boolean isPartialMatch() {
        return partialMatch;
    }

    /**
     * 
     * @param partialMatch
     *     The partial_match
     */
    public void setPartialMatch(boolean partialMatch) {
        this.partialMatch = partialMatch;
    }

    /**
     * 
     * @return
     *     The placeId
     */
    public String getPlaceId() {
        return placeId;
    }

    /**
     * 
     * @param placeId
     *     The place_id
     */
    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    /**
     * 
     * @return
     *     The types
     */
    public List<String> getTypes() {
        return types;
    }

    /**
     * 
     * @param types
     *     The types
     */
    public void setTypes(List<String> types) {
        this.types = types;
    }


    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(geocoderStatus);
        dest.writeValue(partialMatch);
        dest.writeValue(placeId);
        dest.writeList(types);
    }

    public int describeContents() {
        return  0;
    }

}
