package itg8.com.busdriverapp.leave_request.model;

import android.os.Parcel;
import android.os.Parcelable;

public class LeaveRequestModel implements Parcelable {
     private String id;
     private String userid;
      private String description;
      private String startdate;
      private String enddate;
      private int type;
      private String fulldayleave;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userid;
    }

    public void setUserId(String userId) {
        this.userid = userId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStartDate() {
        return startdate;
    }

    public void setStartDate(String startDate) {
        this.startdate = startDate;
    }

    public String getEndDate() {
        return enddate;
    }

    public void setEndDate(String endDate) {
        this.enddate = endDate;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getFulldayleave() {
        return fulldayleave;
    }

    public void setFulldayleave(String fulldayleave) {
        this.fulldayleave = fulldayleave;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.userid);
        dest.writeString(this.description);
        dest.writeString(this.startdate);
        dest.writeString(this.enddate);
        dest.writeInt(this.type);
        dest.writeString(this.fulldayleave);
    }

    public LeaveRequestModel() {
    }

    protected LeaveRequestModel(Parcel in) {
        this.id = in.readString();
        this.userid = in.readString();
        this.description = in.readString();
        this.startdate = in.readString();
        this.enddate = in.readString();
        this.type = in.readInt();
        this.fulldayleave = in.readString();
    }

    public static final Parcelable.Creator<LeaveRequestModel> CREATOR = new Parcelable.Creator<LeaveRequestModel>() {
        @Override
        public LeaveRequestModel createFromParcel(Parcel source) {
            return new LeaveRequestModel(source);
        }

        @Override
        public LeaveRequestModel[] newArray(int size) {
            return new LeaveRequestModel[size];
        }
    };
}
