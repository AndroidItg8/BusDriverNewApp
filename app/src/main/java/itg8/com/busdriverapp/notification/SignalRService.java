package itg8.com.busdriverapp.notification;

import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import itg8.com.busdriverapp.common.CommonMethod;
import itg8.com.busdriverapp.common.Prefs;
import itg8.com.busdriverapp.notification.model.MessageEvent;
import itg8.com.busdriverapp.notification.model.NotificationModel;
import microsoft.aspnet.signalr.client.ErrorCallback;
import microsoft.aspnet.signalr.client.LogLevel;
import microsoft.aspnet.signalr.client.Logger;
import microsoft.aspnet.signalr.client.MessageReceivedHandler;
import microsoft.aspnet.signalr.client.SignalRFuture;
import microsoft.aspnet.signalr.client.hubs.HubConnection;
import microsoft.aspnet.signalr.client.hubs.SubscriptionHandler2;
import microsoft.aspnet.signalr.client.transport.ClientTransport;
import microsoft.aspnet.signalr.client.transport.ServerSentEventsTransport;


public class SignalRService extends Service {

    private static final String TAG = "SignalRService";
    private microsoft.aspnet.signalr.client.hubs.HubConnection mHubConnection;
    private microsoft.aspnet.signalr.client.hubs.HubProxy mHubProxy;
    private Handler mHandler; // to display Toast message
    private final IBinder mBinder = new LocalBinder(); // Binder given to clients

    public static final String BROADCAST_ACTION = "com.android.com.simplechatwithsignalr";

    public SignalRService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mHandler = new Handler(Looper.getMainLooper());
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        int result = super.onStartCommand(intent, flags, startId);
        startSignalR();
        return result;
    }

    @Override
    public void onDestroy() {
        mHubConnection.stop();
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // Return the communication channel to the service.
        startSignalR();
        return mBinder;
    }

    /**
     * Class used for the client Binder.  Because we know this service always
     * runs in the same process as its clients, we don't need to deal with IPC.
     */
    public class LocalBinder extends Binder {
        public SignalRService getService() {
            // Return this instance of SignalRService so clients can call public methods
            return SignalRService.this;
        }
    }

    /**
     * method for clients (activities)
     */
    public void sendMessage(String message) {
        String SERVER_METHOD_SEND = "notificationHub";
        mHubProxy.invoke(SERVER_METHOD_SEND, message);
    }

    private void startSignalR() {

//        String serverUrl = "http://192.168.10.11:8082/signalr";
        String serverUrl = CommonMethod.BASE_URL+"signalr";
//        String serverUrl = CommonMethod.WS_BASE_URL+"/signalr";
        mHubConnection = new HubConnection(serverUrl, "userName=" + Prefs.getString(CommonMethod.TOKEN), false, new Logger() {
            @Override
            public void log(String s, LogLevel logLevel) {
               // Log.d(TAG, "logTTT: "+s+" LgLvl:"+logLevel.name());
                if(s.contains("Invoking event: notifyuser with arguments")){

                }
            }
        });
        mHubConnection.error(new ErrorCallback() {
            @Override
            public void onError(Throwable throwable) {
               // Log.d(TAG, "logTTT onError: "+throwable.getMessage());
                throwable.printStackTrace();
            }
        });
        String SERVER_HUB_CHAT = "notificationHub";
        mHubProxy = mHubConnection.createHubProxy(SERVER_HUB_CHAT);
        ClientTransport clientTransport = new ServerSentEventsTransport(mHubConnection.getLogger());
        SignalRFuture<Void> signalRFuture = mHubConnection.start(clientTransport);

        try {
            signalRFuture.get();

        } catch (InterruptedException | ExecutionException e) {
            Log.e(TAG, e.toString());
            return;
        }

        /* ****new codes here**** */
        /* ****seems useless but should be here!**** */
//        mHubProxy.subscribe(this);
//        mHubProxy.subscribe(new Object() {
//            @SuppressWarnings("unused")
//            public void newMessage(final String message, final String messageId, final String chatId,
//                                   final String senderUserId, final String fileUrl, final String replyToMessageId) {
//
//
//            }
//        });



        /* ********************** */


        mHubProxy.on("notify", new SubscriptionHandler2<Object, Object>() {
            @Override
            public void run(Object msg,Object msg2) {
               // Log.d(TAG, "run: notificationHub Bus Driver: "+msg2.toString());

                String notificationBody=msg2.toString();
                try {
                    JSONObject object=new JSONObject(notificationBody);
                    if(object.has("FullData")){
                        List<NotificationModel> modelList;
                        if(object.get("FullData") instanceof JSONObject){
                            NotificationModel m=new Gson().fromJson(object.getJSONObject("FullData").toString(),NotificationModel.class);
                            modelList=new ArrayList<>();
                            modelList.add(m);
                        }else{
                            modelList=new Gson().fromJson(object.getJSONArray("FullData").toString(),new TypeToken<List<NotificationModel>>(){}.getType());
                        }
                        createBroadcastNotification(modelList);
                    }else if(object.has("LocationInfo")){

                        JSONObject array=object.getJSONObject("LocationInfo");

                        if(array.get("ttp") instanceof JSONObject){
                            passLatLngData(array.getJSONObject("ttp"));
                        }else {
                            JSONObject arr=array.getJSONArray("ttp").getJSONObject(0);
                            passLatLngData(arr);
                        }
//                        .getJSONObject("ttp").getJSONObject("tt");
////                        if(array.length()>0){
//                            JSONObject tt=array.getJSONObject("row");
//                            if(tt!=null){
//                                if(tt.has("Latitude")){
//                                    EventBus.getDefault().post(new MessageEvent(tt.toString()));
//                                }
//                            }
////                        }

                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
                //                sendBroadcastAndNotification(msg);

            }
        }, Object.class,Object.class);

            mHubConnection.received(new MessageReceivedHandler() {
            @Override
            public void onMessageReceived(final JsonElement json) {

              //  Log.d(TAG, "run: notificationHub: "+json.toString());
                return;

//                sendBroadcastAndNotification(json.getAsJsonObject().getAsJsonArray("A").get(0).getAsString());


//                runOnUiThread(new Runnable() {
//                    public void run() {
//                        JsonObject jsonObject = json.getAsJsonObject();
//                        Log.e("<Debug>", "response = " + jsonObject.toString());
//
//                    }
//                });

            }
        });

//        mHubProxy.on("notificationHub", new SubscriptionHandler1<Object>() {
//            @Override
//            public void run(Object msg) {
////                sendBroadcastAndNotification(msg);
//
//            }
//        }, Object.class);
    }

    private void passLatLngData(JSONObject array1) throws JSONException {
        JSONObject array=array1.getJSONObject("tt");
        JSONObject tt=array.getJSONObject("row");
        if(tt!=null){
            if(tt.has("Latitude")){
                EventBus.getDefault().post(new MessageEvent(tt.toString()));
            }
        }
    }

    private void createBroadcastNotification(List<NotificationModel> modelList) {
        for (NotificationModel model :
                modelList) {
            if(!isNotificationVisible(Integer.parseInt(model.getNotficationMessageID()))){
                if(Prefs.getBoolean(CommonMethod.DND,false))
                    createNotification(model);
            }
        }
    }
    private boolean isNotificationVisible(int MY_ID) {
        Intent notificationIntent = new Intent(this, PopupActivity.class);
        PendingIntent test = PendingIntent.getActivity(this, MY_ID, notificationIntent, PendingIntent.FLAG_NO_CREATE);
        return test != null;
    }


    private void sendBroadcastAndNotification(String msg) {
      //  Log.d(TAG, "MyMessageFromServer :  "+msg);

        Intent intent = new Intent(BROADCAST_ACTION);
        intent.putExtra("message", msg);
        sendBroadcast(intent);

//        createNotification(msg);
    }

    private void createNotification(NotificationModel msg) {
        Intent intent=new Intent(CommonMethod.ACTION_START_STATIC_NOTIFICATION);
        sendBroadcast(intent);
    }

}

