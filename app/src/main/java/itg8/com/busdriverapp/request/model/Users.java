package itg8.com.busdriverapp.request.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public  class Users {
    @SerializedName("User")
    @Expose
private  String User;

    public Users(String User) {

        this.User = User;
    }

    public String getUser() {
        return User;
    }

    public void setUser(String user) {
        User = user;
    }


}

