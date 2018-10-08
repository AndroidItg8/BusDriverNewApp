package itg8.com.busdriverapp.request.mvp;

import itg8.com.busdriverapp.common.NetworkUtility;
import itg8.com.busdriverapp.request.model.OtherUserRequestModel;

public class RequestOtherModuleImp implements RequestOtherMVP.RequestModule {


    @Override
    public void onRequestSave(OtherUserRequestModel model, final RequestOtherMVP.RequestListener listener) {
        new NetworkUtility.NetworkBuilder().setHeader().build().sendOtherRequest(model,new NetworkUtility.ResponseListener() {
            @Override
            public void onSuccess(Object message) {
                listener.onSuccessSave((Boolean) message);

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
    public void onDownloadCategory(final RequestOtherMVP.RequestListener listener) {

        new NetworkUtility.NetworkBuilder().setHeader().build().getCategory(new NetworkUtility.ResponseListener() {
            @Override
            public void onSuccess(Object message) {
                listener.onSuccess(message);

            }

            @Override
            public void onFailure(Object err) {
                listener.onFail(err.toString());

            }

            @Override
            public void onSomethingWrong(Object e) {
                listener.onError(e.toString());

            }
        });

    }

    @Override
    public void onDestroy() {

    }
}
