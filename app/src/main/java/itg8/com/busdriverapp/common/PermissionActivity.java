package itg8.com.busdriverapp.common;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.NonNull;


import com.google.android.gms.common.api.GoogleApiClient;

import java.util.List;


public class PermissionActivity extends BaseActivity{

    private static final int RC_SMS = 101;
    private static String[] SMS_PERMISSION={Manifest.permission.RECEIVE_SMS};
    private boolean hasSMSPermission;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public GoogleApiClient.ConnectionCallbacks getConnectionCallback() {
        return null;
    }

//


}
