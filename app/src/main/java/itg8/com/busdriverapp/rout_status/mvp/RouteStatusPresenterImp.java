package itg8.com.busdriverapp.rout_status.mvp;

import java.util.List;

import itg8.com.busdriverapp.common.BaseWeakPresenter;
import itg8.com.busdriverapp.home.model.Checkpoint;
import itg8.com.busdriverapp.home.model.CheckpointData;
import itg8.com.busdriverapp.home.model.User;

public class RouteStatusPresenterImp extends BaseWeakPresenter<RouteStatusMVP.RouteStatusView>
implements RouteStatusMVP.RouteStatusPresenter,RouteStatusMVP.RouteStatusListener {


    RouteStatusMVP.RouteStatusModule module;

    public RouteStatusPresenterImp() {
        super(null);
        module=new RouteStatusModuleImp(this);
    }

    @Override
    public void onViewCreated(RouteStatusMVP.RouteStatusView view) {
        super.weakViewCreated(view);
    }

    @Override
    public void onViewDestroyed() {
        if(hasView())
            detactView();
    }

    @Override
    public void onStartBiffurgatingData(CheckpointData data) {
        if(hasView()){
            getView().showProgress();
            module.onStartBiffergeting(data);
        }
    }

    @Override
    public void onCheckpointListAvail(List<Checkpoint> checkpoints) {
        if(hasView()){
            getView().hideProgress();
            getView().onCheckpointAvail(checkpoints);
        }
    }

    @Override
    public void onListOfAddressForMapViewAvail(RouteStatusMVP.AllMapRelData navigation) {
        if(hasView()){
            getView().hideProgress();
            getView().onListOfAddressForMapViewAvail(navigation);
        }
    }

    @Override
    public void onListOfLeaveUserAvail(List<User> usersOnLeave) {
        if(hasView()){
            getView().hideProgress();
            getView().onLeaveUserAvail(usersOnLeave);
        }
    }

    @Override
    public void onUsersListAvail(List<User> allUsers) {
        if(hasView()){
            getView().hideProgress();
            getView().onUsersListAvail(allUsers);
        }
    }
}
