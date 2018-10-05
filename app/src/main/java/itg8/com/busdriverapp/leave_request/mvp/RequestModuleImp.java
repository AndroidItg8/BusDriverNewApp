package itg8.com.busdriverapp.leave_request.mvp;

import android.util.Log;

import com.google.gson.Gson;

import itg8.com.busdriverapp.common.NetworkUtility;
import itg8.com.busdriverapp.leave_request.model.LeaveRequestModel;

public class RequestModuleImp implements RequestMVP.RequestModule {

    private static final String TAG = "RequestModuleImp";

    @Override
    public void onStartCall(String url, int checkedItem, String startDate, String endDate, String message, final RequestMVP.RequesListener listener) {
        int type = 1;
        String userId = null;
        Log.i(TAG, "onStartCall: " + checkedItem + " startDate " + startDate + " endDate " + endDate);
        LeaveRequestModel model = new LeaveRequestModel();
        model.setUserId(userId);
        model.setDescription(message);
        model.setStartDate(startDate);
        model.setEndDate(endDate);
        model.setType(type);
        model.setFulldayleave(String.valueOf(checkedItem));

        new NetworkUtility.NetworkBuilder().setHeader().build().sendLeaveRequest(model, new NetworkUtility.ResponseListener() {
            @Override
            public void onSuccess(Object message) {
                String response = (String) message;
                if (response != null) {
                    listener.onSuccess(response);
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
