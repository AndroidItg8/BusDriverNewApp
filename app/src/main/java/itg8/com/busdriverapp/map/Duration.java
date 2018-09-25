
package itg8.com.busdriverapp.map;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Duration implements Parcelable
{

    @SerializedName("text")
    @Expose
    private String text;
    @SerializedName("value")
    @Expose
    private int value;
    public final static Creator<Duration> CREATOR = new Creator<Duration>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Duration createFromParcel(Parcel in) {
            Duration instance = new Duration();
            instance.text = ((String) in.readValue((String.class.getClassLoader())));
            instance.value = ((int) in.readValue((int.class.getClassLoader())));
            return instance;
        }

        public Duration[] newArray(int size) {
            return (new Duration[size]);
        }

    }
    ;

    /**
     * 
     * @return
     *     The text
     */
    public String getText() {
        return text;
    }

    /**
     * 
     * @param text
     *     The text
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * 
     * @return
     *     The value
     */
    public int getValue() {
        return value;
    }

    /**
     * 
     * @param value
     *     The value
     */
    public void setValue(int value) {
        this.value = value;
    }


    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(text);
        dest.writeValue(value);
    }

    public int describeContents() {
        return  0;
    }

}
