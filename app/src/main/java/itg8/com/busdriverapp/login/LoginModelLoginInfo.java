package itg8.com.busdriverapp.login;

import android.os.Parcel;
import android.os.Parcelable;

public class LoginModelLoginInfo implements Parcelable {
    public static final Creator<LoginModelLoginInfo> CREATOR = new Creator<LoginModelLoginInfo>() {
        @Override
        public LoginModelLoginInfo createFromParcel(Parcel source) {
            LoginModelLoginInfo var = new LoginModelLoginInfo();
            var.userName = source.readString();
            var.password = source.readString();
            return var;
        }

        @Override
        public LoginModelLoginInfo[] newArray(int size) {
            return new LoginModelLoginInfo[size];
        }
    };



    private String userName;
    private String password;

    public LoginModelLoginInfo(String username, String password) {
        this.userName = username;
        this.password = password;
    }

    public LoginModelLoginInfo() {
    }

    public String getUsername() {
        return this.userName;
    }

    public void setUsername(String Username) {
        this.userName = Username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String Password) {
        this.password = Password;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.userName);
        dest.writeString(this.password);
    }

    @Override
    public int describeContents() {
        return 0;
    }
}
