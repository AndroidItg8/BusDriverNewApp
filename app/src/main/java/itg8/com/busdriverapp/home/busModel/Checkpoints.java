
package itg8.com.busdriverapp.home.busModel;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Checkpoints implements Parcelable
{

    @SerializedName("checkpoint")
    @Expose
    private Object checkpoint;
    private List<Checkpoint> checkpointList=new ArrayList<>();


    /**
     * 
     * @return
     *     The checkpoint
     */
    public Object getCheckpoint() {
        return checkpoint;
    }

    /**
     * 
     * @param checkpoint
     *     The checkpoint
     */
    public void setCheckpoint(Object checkpoint) {
        this.checkpoint = checkpoint;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable((Parcelable) this.checkpoint, flags);
        dest.writeTypedList(this.checkpointList);
    }

    public Checkpoints() {
    }

    protected Checkpoints(Parcel in) {
        this.checkpoint = in.readParcelable(Object.class.getClassLoader());
        this.checkpointList = in.createTypedArrayList(Checkpoint.CREATOR);
    }

    public static final Creator<Checkpoints> CREATOR = new Creator<Checkpoints>() {
        @Override
        public Checkpoints createFromParcel(Parcel source) {
            return new Checkpoints(source);
        }

        @Override
        public Checkpoints[] newArray(int size) {
            return new Checkpoints[size];
        }
    };
}
