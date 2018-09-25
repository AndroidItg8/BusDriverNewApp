
package itg8.com.busdriverapp.map;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;


public class Route implements Parcelable
{

    @SerializedName("bounds")
    @Expose

    private Bounds bounds;
    @SerializedName("copyrights")
    @Expose
    private String copyrights;
    @SerializedName("legs")
    @Expose

    private List<Leg> legs = new ArrayList<Leg>();
    @SerializedName("overview_polyline")
    @Expose

    private OverviewPolyline overviewPolyline;
    @SerializedName("summary")
    @Expose
    private String summary;
    @SerializedName("warnings")
    @Expose

    private List<Object> warnings = new ArrayList<Object>();
    @SerializedName("waypoint_order")
    @Expose

    private List<Integer> waypointOrder = new ArrayList<Integer>();
    public final static Creator<Route> CREATOR = new Creator<Route>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Route createFromParcel(Parcel in) {
            Route instance = new Route();
            instance.bounds = ((Bounds) in.readValue((Bounds.class.getClassLoader())));
            instance.copyrights = ((String) in.readValue((String.class.getClassLoader())));
            in.readList(instance.legs, (Leg.class.getClassLoader()));
            instance.overviewPolyline = ((OverviewPolyline) in.readValue((OverviewPolyline.class.getClassLoader())));
            instance.summary = ((String) in.readValue((String.class.getClassLoader())));
            in.readList(instance.warnings, (Object.class.getClassLoader()));
            in.readList(instance.waypointOrder, (Integer.class.getClassLoader()));
            return instance;
        }

        public Route[] newArray(int size) {
            return (new Route[size]);
        }

    }
    ;

    /**
     * 
     * @return
     *     The bounds
     */
    public Bounds getBounds() {
        return bounds;
    }

    /**
     * 
     * @param bounds
     *     The bounds
     */
    public void setBounds(Bounds bounds) {
        this.bounds = bounds;
    }

    /**
     * 
     * @return
     *     The copyrights
     */
    public String getCopyrights() {
        return copyrights;
    }

    /**
     * 
     * @param copyrights
     *     The copyrights
     */
    public void setCopyrights(String copyrights) {
        this.copyrights = copyrights;
    }

    /**
     * 
     * @return
     *     The legs
     */
    public List<Leg> getLegs() {
        return legs;
    }

    /**
     * 
     * @param legs
     *     The legs
     */
    public void setLegs(List<Leg> legs) {
        this.legs = legs;
    }

    /**
     * 
     * @return
     *     The overviewPolyline
     */
    public OverviewPolyline getOverviewPolyline() {
        return overviewPolyline;
    }

    /**
     * 
     * @param overviewPolyline
     *     The overview_polyline
     */
    public void setOverviewPolyline(OverviewPolyline overviewPolyline) {
        this.overviewPolyline = overviewPolyline;
    }

    /**
     * 
     * @return
     *     The summary
     */
    public String getSummary() {
        return summary;
    }

    /**
     * 
     * @param summary
     *     The summary
     */
    public void setSummary(String summary) {
        this.summary = summary;
    }

    /**
     * 
     * @return
     *     The warnings
     */
    public List<Object> getWarnings() {
        return warnings;
    }

    /**
     * 
     * @param warnings
     *     The warnings
     */
    public void setWarnings(List<Object> warnings) {
        this.warnings = warnings;
    }

    /**
     * 
     * @return
     *     The waypointOrder
     */
    public List<Integer> getWaypointOrder() {
        return waypointOrder;
    }

    /**
     * 
     * @param waypointOrder
     *     The waypoint_order
     */
    public void setWaypointOrder(List<Integer> waypointOrder) {
        this.waypointOrder = waypointOrder;
    }


    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(bounds);
        dest.writeValue(copyrights);
        dest.writeList(legs);
        dest.writeValue(overviewPolyline);
        dest.writeValue(summary);
        dest.writeList(warnings);
        dest.writeList(waypointOrder);
    }

    public int describeContents() {
        return  0;
    }

}
