package itg8.com.busdriverapp.common;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.google.android.gms.common.api.GoogleApiClient;

import java.lang.ref.WeakReference;
import java.util.HashSet;
import java.util.Set;

import itg8.com.busdriverapp.R;
import itg8.com.busdriverapp.db.AppDatabase;
import itg8.com.busdriverapp.utils.UserType;


/**
 * Created by swapnilmeshram on 15/03/18.
 */

public class MyApplication extends Application {
    private static final String PREF_NAME="PARENT_APP";
    private static final String DB_NAME = "ParentApp";
    private static MyApplication mInstance;
    public boolean isLoggingNeeded;
    Set<Integer> notificationIDS=new HashSet<>();
    WeakReference<GoogleApiClient> googleApiClientWeakReference;

    public static MyApplication getInstance() {
        return mInstance;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        isLoggingNeeded=true;

        mInstance=this;
        mInstance.initPref();
        initUserType();

    }

    public AppDatabase getDatabase(){
        return Room.databaseBuilder(this,AppDatabase.class,DB_NAME).build();
    }

    private void initPref() {
        new Prefs.Builder()
                .setContext(this)
                .setMode(MODE_PRIVATE)
                .setPrefsName(PREF_NAME)
                .setUseDefaultSharedPreference(false)
                .build();
    }


    public void initUserType(){
        new UserType.Builder().setType().build();
    }


    public String getAppToken() {
        return getString(R.string.app_token);
    }

    public void addNotificationID(int id){
        notificationIDS.add(id);
    }

    public void deleteNotificationID(int id){
        notificationIDS.remove(id);
    }

    public boolean hasNotificationID(int id){
        return notificationIDS.contains(id);
    }

    public void setGoogleApiClient(GoogleApiClient googleClient) {
        googleApiClientWeakReference=new WeakReference<GoogleApiClient>(googleClient);
    }

    public GoogleApiClient getGoogleApiClient(){
        if(googleApiClientWeakReference!=null)
            if(googleApiClientWeakReference.get()!=null){
                return googleApiClientWeakReference.get();
            }
            return null;
    }

}
