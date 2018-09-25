package itg8.com.busdriverapp.notification;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import itg8.com.busdriverapp.R;
import itg8.com.busdriverapp.common.CommonMethod;
import itg8.com.busdriverapp.common.MyApplication;
import itg8.com.busdriverapp.common.NetworkUtility;
import itg8.com.busdriverapp.notification.model.NotificationModel;


public class PopupActivity extends Activity {

    private static final String NOTIFICATION_ID = "MYNotificationID";
    private int notificationID;
    private NotificationModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        if(getIntent().hasExtra(CommonMethod.NOTIFICATION_MSG)){
        setContentView(R.layout.dialog_popup_notification_received);
            String message=getIntent().getStringExtra(CommonMethod.NOTIFICATION_MSG);
            notificationID=getIntent().getIntExtra(NOTIFICATION_ID,0);
            model=getIntent().getParcelableExtra(CommonMethod.NOTIFICATION_MODEL);
            Button ok= findViewById(R.id.btnOk);

            try {
                Uri notification = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE
                        + "://" + getPackageName() + "/raw/high_alert");
                Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), notification);

                r.play();
            } catch (Exception e) {
                e.printStackTrace();
            }

//           final MediaPlayer mPlayer = MediaPlayer.create(PopupActivity.this, R.raw.high_alert);
//            mPlayer.setLooping(true);
//            mPlayer.start();

            ok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    MyApplication.getInstance().deleteNotificationID(notificationID);
                    if(model!=null){
                        removeNotification(model);
                    }
                    finish();
//                    mPlayer.stop();
                }
            });
            Button cancel=(Button)  findViewById(R.id.btnStart);
            cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    MyApplication.getInstance().deleteNotificationID(notificationID);
//                    mPlayer.stop();
                    finish();
                }
            });

            TextView desc=(TextView)  findViewById(R.id.txtDescription);
            desc.setText(" "+message);

            //  displayAlert();
        }
    }

    private void removeNotification(NotificationModel model) {
        new NetworkUtility.NetworkBuilder().setHeader().build().mapNotificationToLog(model, new NetworkUtility.ResponseListener() {
            @Override
            public void onSuccess(Object message) {

            }

            @Override
            public void onFailure(Object err) {

            }

            @Override
            public void onSomethingWrong(Object e) {

            }
        });
    }

    private void displayAlert()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to exit?").setCancelable(
                false).setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                }).setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

    @Override
    protected void onDestroy() {
        MyApplication.getInstance().deleteNotificationID(notificationID);
        super.onDestroy();

    }
}
