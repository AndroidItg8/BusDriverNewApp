package itg8.com.busdriverapp.home.mvp;

import java.util.List;

import itg8.com.busdriverapp.common.BaseView;
import itg8.com.busdriverapp.home.model.RouteModel;

public interface HomeMvp {
    public interface HomeView extends BaseView{
        void onRouteAvail(RouteModel routeModels);
        void onRouteDownloadFail(Object e);
    }

    public interface HomePresenter{
        void startGettingRouteInfo();

        void sendToken(String url, String token);
    }

    public interface HomeListener{
        void onRouteAvail(RouteModel routeModels);
        void onFailToGetRoute(Object e);
        void onNoInternetAccess();
    }

    public interface HomeModule{
        void onStartGettingRouteInfo();

        void sendTokenToServer(String url, String token);
    }
}
