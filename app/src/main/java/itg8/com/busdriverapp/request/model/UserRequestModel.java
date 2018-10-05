
package itg8.com.busdriverapp.request.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserRequestModel implements Parcelable
{

    @SerializedName("WSResponse")
    @Expose
    private WSResponse wSResponse;
    public final static Creator<UserRequestModel> CREATOR = new Creator<UserRequestModel>() {


        @SuppressWarnings({
            "unchecked"
        })
        public UserRequestModel createFromParcel(Parcel in) {
            return new UserRequestModel(in);
        }

        public UserRequestModel[] newArray(int size) {
            return (new UserRequestModel[size]);
        }

    }
    ;

    protected UserRequestModel(Parcel in) {
        this.wSResponse = ((WSResponse) in.readValue((WSResponse.class.getClassLoader())));
    }

    public UserRequestModel() {
    }

    public WSResponse getWSResponse() {
        return wSResponse;
    }

    public void setWSResponse(WSResponse wSResponse) {
        this.wSResponse = wSResponse;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(wSResponse);
    }

    public int describeContents() {
        return  0;
    }

}
