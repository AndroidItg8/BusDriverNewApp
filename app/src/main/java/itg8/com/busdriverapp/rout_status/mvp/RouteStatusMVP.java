package itg8.com.busdriverapp.rout_status.mvp;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

import itg8.com.busdriverapp.common.BaseView;
import itg8.com.busdriverapp.home.model.Checkpoint;
import itg8.com.busdriverapp.home.model.CheckpointData;
import itg8.com.busdriverapp.home.model.User;
import itg8.com.busdriverapp.map.MapLatLngAddressModel;

public class RouteStatusMVP {
    public interface RouteStatusView extends BaseView{
        void onCheckpointsWithUserAvail();
        void onListOfAddressForMapViewAvail(AllMapRelData navigation);
        void ListOfLeaveAvail();
        void onCheckpointAvail(List<Checkpoint> checkpoints);
        void onUsersListAvail(List<User> allUsers);

        void onLeaveUserAvail(List<User> usersOnLeave);
    }

    public interface RouteStatusPresenter{
        void onStartBiffurgatingData(CheckpointData data);
        void onViewCreated(RouteStatusView view);
        void onViewDestroyed();
    }

    public interface RouteStatusListener{
        void onCheckpointListAvail(List<Checkpoint> checkpoints);
        void onListOfAddressForMapViewAvail(AllMapRelData navigation);
        void onListOfLeaveUserAvail(List<User> usersOnLeave);
        void onUsersListAvail(List<User> allUsers);
    }

    static class RouteStatusModel{
        List<Checkpoint> checkpoints;
        AllMapRelData navigation;
        List<User> usersOnLeave;
        List<User> allUsers;
    }

    public static class AllMapRelData{
        List<ArrayList<LatLng>> allRouteNavigation;
        List<MapLatLngAddressModel> allMarkers;
    }

    public interface RouteStatusModule{
        void onStartBiffergeting(CheckpointData data);
    }
}
