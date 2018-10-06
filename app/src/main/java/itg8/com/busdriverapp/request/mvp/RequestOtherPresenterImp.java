package itg8.com.busdriverapp.request.mvp;

import android.view.View;

import java.util.List;

import itg8.com.busdriverapp.common.BaseWeakPresenter;

import itg8.com.busdriverapp.leave_request.mvp.RequestMVP;
import itg8.com.busdriverapp.request.model.Role;
import okhttp3.ResponseBody;

public class RequestOtherPresenterImp extends BaseWeakPresenter<RequestOtherMVP.RequestView> implements RequestOtherMVP.RequestListener, RequestOtherMVP.RequestPresenter  {
    RequestOtherMVP.RequestModule module ;



    public RequestOtherPresenterImp(RequestOtherMVP.RequestView requestView) {
        super(requestView);
        module = new  RequestOtherModuleImp();
    }

    @Override
    public void onDestroy() {

    }
    @Override
    public void getCategory() {
        if (hasView()) {
            getRequestView().showProgress();
            module.onDownloadCategory(this);
        }

    }
    @Override
    public void onRequestClicked(View view) {

    }



    public void onSuccess(Object response) {
        if (hasView()) {
            getRequestView().hideProgress();
            getRequestView().onSuccess((List<Role>) response);
        }
    }

    @Override
    public void onFail(String message) {
        if (hasView()) {
            getRequestView().hideProgress();
            getRequestView().onFail(message);
        }
    }

    @Override
    public void onError(Object t) {
        if (hasView()) {
            getRequestView().hideProgress();
            getRequestView().onError(t);
        }
    }



    private RequestOtherMVP.RequestView getRequestView() {
        return getView();
    }
}
