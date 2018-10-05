package itg8.com.busdriverapp.request.mvp;

import android.view.View;

import itg8.com.busdriverapp.common.BaseWeakPresenter;

import itg8.com.busdriverapp.leave_request.mvp.RequestMVP;
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



    public void onSuccess(String response) {
        if (hasView()) {
            getRequestView().hideProgress();
            getRequestView().onSuccess(response);
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

    @Override
    public void onCategoryDownloaded(ResponseBody responseBody) {

    }

    private RequestOtherMVP.RequestView getRequestView() {
        return getView();
    }
}
