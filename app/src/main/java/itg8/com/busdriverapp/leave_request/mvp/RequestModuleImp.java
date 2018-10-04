package itg8.com.busdriverapp.leave_request.mvp;

import android.util.Log;

import itg8.com.busdriverapp.common.NetworkUtility;

public class RequestModuleImp implements RequestMVP.RequestModule {

    private static final String TAG = "RequestModuleImp";

    @Override
    public void onStartCall(String url, int checkedItem, String startDate, String endDate, String message, final RequestMVP.RequesListener listener) {
        int type = 1;
        String userId = null;
        Log.i(TAG, "onStartCall: "+checkedItem+" startDate "+startDate+" endDate "+endDate);
        new NetworkUtility.NetworkBuilder().build().sendLeaveRequest(url, userId, checkedItem, startDate, endDate, message, type, new NetworkUtility.ResponseListener() {
            @Override
            public void onSuccess(Object message) {
                String response = (String) message;
                if (response != null) {
                    listener.onSuccess();
                }
            }

            @Override
            public void onFailure(Object err) {
                listener.onFail(err.toString());

            }

            @Override
            public void onSomethingWrong(Object e) {
                listener.onError(e);
            }
        });
    }

    @Override
    public void onDestroy() {

    }
}
