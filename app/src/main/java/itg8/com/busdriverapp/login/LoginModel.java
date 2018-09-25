package itg8.com.busdriverapp.login;

import android.os.Parcel;
import android.os.Parcelable;

public class LoginModel implements Parcelable {
    public static final Creator<LoginModel> CREATOR = new Creator<LoginModel>() {
        @Override
        public LoginModel createFromParcel(Parcel source) {
            LoginModel var = new LoginModel();
            var.LoginInfo = source.readParcelable(LoginModelLoginInfo.class.getClassLoader());
            return var;
        }

        @Override
        public LoginModel[] newArray(int size) {
            return new LoginModel[size];
        }
    };
    private LoginModelLoginInfo LoginInfo;

    public LoginModelLoginInfo getLoginInfo() {
        return this.LoginInfo;
    }

    public void setLoginInfo(LoginModelLoginInfo LoginInfo) {
        this.LoginInfo = LoginInfo;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.LoginInfo, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }
}
