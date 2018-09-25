
package itg8.com.busdriverapp.home.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RouteModel implements Parcelable
{

    @SerializedName("CheckpointData")
    @Expose
    private List<itg8.com.busdriverapp.home.model.CheckpointData> CheckpointData;
    protected final static Object NOT_FOUND_VALUE = new Object();

    /**
     * 
     * @return
     *     The CheckpointData
     */
    public List<itg8.com.busdriverapp.home.model.CheckpointData> getCheckpointData() {
        return CheckpointData;
    }

    /**
     * 
     * @param CheckpointData
     *     The CheckpointData
     */
    public void setCheckpointData(List<itg8.com.busdriverapp.home.model.CheckpointData> CheckpointData) {
        this.CheckpointData = CheckpointData;
    }






    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.CheckpointData);
    }

    public RouteModel() {
    }

    protected RouteModel(Parcel in) {
        this.CheckpointData = in.createTypedArrayList(itg8.com.busdriverapp.home.model.CheckpointData.CREATOR);
    }

    public static final Creator<RouteModel> CREATOR = new Creator<RouteModel>() {
        @Override
        public RouteModel createFromParcel(Parcel source) {
            return new RouteModel(source);
        }

        @Override
        public RouteModel[] newArray(int size) {
            return new RouteModel[size];
        }
    };
}
