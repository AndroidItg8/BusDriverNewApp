package itg8.com.busdriverapp.home.mvp;

import android.util.Log;

import java.util.List;

import itg8.com.busdriverapp.R;
import itg8.com.busdriverapp.common.MyApplication;
import itg8.com.busdriverapp.common.NetworkUtility;
import itg8.com.busdriverapp.home.model.RouteModel;

class HomeModuleImp implements HomeMvp.HomeModule {
    private static final String TAG = "HomeModuleImp";
    private HomeMvp.HomeListener listener;

    public HomeModuleImp(HomeMvp.HomeListener listener) {
        this.listener = listener;
    }

    @Override
    public void onStartGettingRouteInfo() {
        Log.i(TAG, "onStartGettingRouteInfo: ");
        new NetworkUtility.NetworkBuilder().setHeader().build().getRoute(
                MyApplication.getInstance().getString(R.string.url_route),
                new NetworkUtility.ResponseListener() {
                    @Override
                    public void onSuccess(Object message) {
                        Log.i(TAG, "onSuccess: ");
                        listener.onRouteAvail((RouteModel) message);
                    }

                    @Override
                    public void onFailure(Object err) {
                        Log.i(TAG, "onFailure: ");
                        listener.onFailToGetRoute(err);
                    }

                    @Override
                    public void onSomethingWrong(Object e) {
                        Log.i(TAG, "onSomethingWrong: ");
                    }
                }
        );
    }

    @Override
    public void onStartGettingBusInfo() {
        new NetworkUtility.NetworkBuilder().setHeader().build().getBuses(
                new NetworkUtility.ResponseListener(){

                    @Override
                    public void onSuccess(Object message) {

                    }

                    @Override
                    public void onFailure(Object err) {

                    }

                    @Override
                    public void onSomethingWrong(Object e) {

                    }
                }
        );
    }

    @Override
    public void sendTokenToServer(String url, String token) {
        new NetworkUtility.NetworkBuilder().setHeader().build().sendToken(url,token, new NetworkUtility.ResponseListener() {
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
