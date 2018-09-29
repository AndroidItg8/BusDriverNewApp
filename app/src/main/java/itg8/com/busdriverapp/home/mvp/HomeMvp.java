package itg8.com.busdriverapp.home.mvp;

import java.util.List;

import itg8.com.busdriverapp.common.BaseView;
import itg8.com.busdriverapp.home.busModel.BusModel;
import itg8.com.busdriverapp.home.model.RouteModel;

public interface HomeMvp {
    public interface HomeView extends BaseView{
        void onRouteAvail(RouteModel routeModels);
        void onRouteDownloadFail(Object e);

        void showBusAdminDetails();

        void showBusDriverDetails();

        void onBusesAvailable(BusModel busModel);
    }

    public interface HomePresenter{
        void startGettingRouteInfo();

        void sendToken(String url, String token);

        void setFragmentAsPerUser();

        void startGettingBusInfo();
    }

    public interface HomeListener{
        void onRouteAvail(RouteModel routeModels);
        void onFailToGetRoute(Object e);
        void onNoInternetAccess();
        void onBusesAvailable(BusModel busModel);
        void onFailToGetBuses(Object e);
    }

    public interface HomeModule{
        void onStartGettingRouteInfo();

        void sendTokenToServer(String url, String token);

        void onStartGettingBusInfo();
    }
}
