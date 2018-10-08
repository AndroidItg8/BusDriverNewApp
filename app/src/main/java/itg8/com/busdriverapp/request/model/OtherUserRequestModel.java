package itg8.com.busdriverapp.request.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;


public class OtherUserRequestModel  {
    @SerializedName("NotificationMessage")
    private String NotificationMessage;
    @SerializedName("NotificationType")
    private  String NotificationType;
    @SerializedName("type")
    private int type;
    @SerializedName("Users")
    private List<Users> users= new ArrayList<>();


    public String getNotificationMessage() {
        return NotificationMessage;
    }

    public void setNotificationMessage(String notificationMessage) {
        NotificationMessage = notificationMessage;
    }

    public String getNotificationType() {
        return NotificationType;
    }

    public void setNotificationType(String notificationType) {
        NotificationType = notificationType;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List<Users> getUsers() {
        return users;
    }

    public void setUsers(List<Users> users) {
       this.users = users;
    }





    public OtherUserRequestModel() {
    }




}
