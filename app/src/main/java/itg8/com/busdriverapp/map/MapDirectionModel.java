
package itg8.com.busdriverapp.map;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class MapDirectionModel implements Parcelable
{

    @SerializedName("geocoded_waypoints")
    @Expose
    private List<GeocodedWaypoint> geocodedWaypoints = new ArrayList<GeocodedWaypoint>();
    @SerializedName("routes")
    @Expose
    private List<Route> routes = new ArrayList<Route>();
    @SerializedName("status")
    @Expose
    private String status;
    public final static Creator<MapDirectionModel> CREATOR = new Creator<MapDirectionModel>() {


        @SuppressWarnings({
            "unchecked"
        })
        public MapDirectionModel createFromParcel(Parcel in) {
            MapDirectionModel instance = new MapDirectionModel();
            in.readList(instance.geocodedWaypoints, (GeocodedWaypoint.class.getClassLoader()));
            in.readList(instance.routes, (Route.class.getClassLoader()));
            instance.status = ((String) in.readValue((String.class.getClassLoader())));
            return instance;
        }

        public MapDirectionModel[] newArray(int size) {
            return (new MapDirectionModel[size]);
        }

    }
    ;

    /**
     * 
     * @return
     *     The geocodedWaypoints
     */
    public List<GeocodedWaypoint> getGeocodedWaypoints() {
        return geocodedWaypoints;
    }

    /**
     * 
     * @param geocodedWaypoints
     *     The geocoded_waypoints
     */
    public void setGeocodedWaypoints(List<GeocodedWaypoint> geocodedWaypoints) {
        this.geocodedWaypoints = geocodedWaypoints;
    }

    /**
     * 
     * @return
     *     The routes
     */
    public List<Route> getRoutes() {
        return routes;
    }

    /**
     * 
     * @param routes
     *     The routes
     */
    public void setRoutes(List<Route> routes) {
        this.routes = routes;
    }

    /**
     * 
     * @return
     *     The status
     */
    public String getStatus() {
        return status;
    }

    /**
     * 
     * @param status
     *     The status
     */
    public void setStatus(String status) {
        this.status = status;
    }


    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(geocodedWaypoints);
        dest.writeList(routes);
        dest.writeValue(status);
    }

    public int describeContents() {
        return  0;
    }

}
