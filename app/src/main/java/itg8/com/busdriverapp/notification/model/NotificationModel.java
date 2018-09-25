package itg8.com.busdriverapp.notification.model;

import android.os.Parcel;
import android.os.Parcelable;

public class NotificationModel implements Parcelable {
    public static final Creator<NotificationModel> CREATOR = new Creator<NotificationModel>() {
        @Override
        public NotificationModel createFromParcel(Parcel source) {
            NotificationModel var = new NotificationModel();
            var.NotificationMessage = source.readString();
            var.UserID = source.readString();
            var.NotificationMethod = source.readString();
            var.created_At = source.readString();
            var.NotficationMessageID = source.readString();
            var.RoleID = source.readString();
            var.created_by = source.readString();
            return var;
        }

        @Override
        public NotificationModel[] newArray(int size) {
            return new NotificationModel[size];
        }
    };
    private String NotificationMessage;
    private String UserID;
    private String NotificationMethod;
    private String created_At;
    private String NotficationMessageID;
    private String RoleID;
    private String created_by;

    public String getNotificationMessage() {
        return this.NotificationMessage;
    }

    public void setNotificationMessage(String NotificationMessage) {
        this.NotificationMessage = NotificationMessage;
    }

    public String getUserID() {
        return this.UserID;
    }

    public void setUserID(String UserID) {
        this.UserID = UserID;
    }

    public String getNotificationMethod() {
        return this.NotificationMethod;
    }

    public void setNotificationMethod(String NotificationMethod) {
        this.NotificationMethod = NotificationMethod;
    }

    public String getCreated_At() {
        return this.created_At;
    }

    public void setCreated_At(String created_At) {
        this.created_At = created_At;
    }

    public String getNotficationMessageID() {
        return this.NotficationMessageID;
    }

    public void setNotficationMessageID(String NotficationMessageID) {
        this.NotficationMessageID = NotficationMessageID;
    }

    public String getRoleID() {
        return this.RoleID;
    }

    public void setRoleID(String RoleID) {
        this.RoleID = RoleID;
    }

    public String getCreated_by() {
        return this.created_by;
    }

    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.NotificationMessage);
        dest.writeString(this.UserID);
        dest.writeString(this.NotificationMethod);
        dest.writeString(this.created_At);
        dest.writeString(this.NotficationMessageID);
        dest.writeString(this.RoleID);
        dest.writeString(this.created_by);
    }

    @Override
    public int describeContents() {
        return 0;
    }
}
