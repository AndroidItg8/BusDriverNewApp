
package itg8.com.busdriverapp.home.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StudentList implements Parcelable
{

    @SerializedName("pkid")
    @Expose
    private int pkid;
    @SerializedName("Fname")
    @Expose
    private String Fname;
    @SerializedName("StudentAdd")
    @Expose
    private String StudentAdd;
    public final static Parcelable.Creator<StudentList> CREATOR = new Creator<StudentList>() {


        @SuppressWarnings({
            "unchecked"
        })
        public StudentList createFromParcel(Parcel in) {
            StudentList instance = new StudentList();
            instance.pkid = ((int) in.readValue((int.class.getClassLoader())));
            instance.Fname = ((String) in.readValue((String.class.getClassLoader())));
            instance.StudentAdd = ((String) in.readValue((String.class.getClassLoader())));
            return instance;
        }

        public StudentList[] newArray(int size) {
            return (new StudentList[size]);
        }

    }
    ;

    /**
     * 
     * @return
     *     The pkid
     */
    public int getPkid() {
        return pkid;
    }

    /**
     * 
     * @param pkid
     *     The pkid
     */
    public void setPkid(int pkid) {
        this.pkid = pkid;
    }

    /**
     * 
     * @return
     *     The Fname
     */
    public String getFname() {
        return Fname;
    }

    /**
     * 
     * @param Fname
     *     The Fname
     */
    public void setFname(String Fname) {
        this.Fname = Fname;
    }

    /**
     * 
     * @return
     *     The StudentAdd
     */
    public String getStudentAdd() {
        return StudentAdd;
    }

    /**
     * 
     * @param StudentAdd
     *     The StudentAdd
     */
    public void setStudentAdd(String StudentAdd) {
        this.StudentAdd = StudentAdd;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(pkid);
        dest.writeValue(Fname);
        dest.writeValue(StudentAdd);
    }

    public int describeContents() {
        return  0;
    }

}
