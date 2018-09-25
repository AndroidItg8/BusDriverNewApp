
package itg8.com.busdriverapp.home.model;

import java.util.ArrayList;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Checkpoints implements Parcelable
{

//    @SerializedName("checkpoint")
//    @Expose
//    private List<Checkpoint> checkpoint = new ArrayList<Checkpoint>();
    @SerializedName("checkpoint")
    @Expose
    public Object checkpoint;

    private List<Checkpoint> checkpoints=new ArrayList<>();

    protected final static Object NOT_FOUND_VALUE = new Object();

    /**
     * 
     * @return
     *     The checkpoint
     */
    public List<Checkpoint> getCheckpoint() {
        return checkpoints;
    }

    public void setCheckpoints(List<Checkpoint> checkpoints) {
        this.checkpoints = checkpoints;
    }

    /**
     * 
     * @param checkpoint
     *     The checkpoint
     */
    public void setCheckpoint(Object checkpoint) {
        this.checkpoint = checkpoint;
    }


    public Checkpoints() {
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.checkpoints);
    }

    protected Checkpoints(Parcel in) {
        this.checkpoints = in.createTypedArrayList(Checkpoint.CREATOR);
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
