package itg8.com.busdriverapp.request.mvp;

import itg8.com.busdriverapp.common.NetworkUtility;

public class RequestOtherModuleImp implements RequestOtherMVP.RequestModule {
    @Override
    public void onRequestSave(String categoryId, String message, String UserId, RequestOtherMVP.RequestListener listener) {

    }

    @Override
    public void onDownloadCategory(RequestOtherMVP.RequestListener listener) {

        new NetworkUtility.NetworkBuilder().setHeader().build().getCategory(new NetworkUtility.ResponseListener() {
            @Override
            public void onSuccess(Object message) {

            }

            @Override
            public void onFailure(Object err) {

            }

            @Override
            public void onSomethingWrong(Object e) {

            }
        });

    }

    @Override
    public void onDestroy() {

    }
}
