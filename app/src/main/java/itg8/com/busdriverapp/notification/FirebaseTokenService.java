package itg8.com.busdriverapp.notification;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import itg8.com.busdriverapp.R;
import itg8.com.busdriverapp.common.NetworkUtility;

public class FirebaseTokenService extends FirebaseInstanceIdService {
    private static final String TAG = "FirebaseTokenService";
    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
        String refresh= FirebaseInstanceId.getInstance().getToken();
        sendRegistrationToServer(refresh);
    }

    private void sendRegistrationToServer(String refresh) {
        //TODO send to server
        new NetworkUtility.NetworkBuilder().setHeader().build().sendToken(getString(R.string.url_token),refresh, new NetworkUtility.ResponseListener() {
            @Override
            public void onSuccess(Object message) {
                Log.d(TAG, "onSuccess: "+message.toString());
            }

            @Override
            public void onFailure(Object err) {

            }

            @Override
            public void onSomethingWrong(Object e) {

            }
        });
    }
}
