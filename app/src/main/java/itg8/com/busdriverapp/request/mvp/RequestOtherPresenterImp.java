package itg8.com.busdriverapp.request.mvp;

import android.text.TextUtils;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import itg8.com.busdriverapp.R;
import itg8.com.busdriverapp.common.BaseWeakPresenter;
import itg8.com.busdriverapp.common.CommonMethod;
import itg8.com.busdriverapp.request.model.OtherUserRequestModel;
import itg8.com.busdriverapp.request.model.Role;
import itg8.com.busdriverapp.request.model.User;
import itg8.com.busdriverapp.request.model.Users;


public class RequestOtherPresenterImp extends BaseWeakPresenter<RequestOtherMVP.RequestView> implements RequestOtherMVP.RequestListener, RequestOtherMVP.RequestPresenter {
    RequestOtherMVP.RequestModule module;


    public RequestOtherPresenterImp(RequestOtherMVP.RequestView requestView) {
        super(requestView);
        module = new RequestOtherModuleImp();
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
        if (hasView()) {
            String role = getRequestView().getCategory();
            String message = getRequestView().getMessage();
            List<User> users = getRequestView().getUser();
            boolean isValid = true;
            if (isValid) {
                if (TextUtils.isEmpty(message)) {
                    isValid = false;
                    getRequestView().onMessageInvalid(view.getContext().getString(R.string.empty_string));
                } else if (TextUtils.isEmpty(role)) {
                    isValid = false;
                    getRequestView().onCategoryInvalid(view.getContext().getString(R.string.empty_string));
                } else if (users.size() > 0)
                    isValid = true;
                else
                    isValid = false;
                if (isValid) {

                    OtherUserRequestModel model = new OtherUserRequestModel();
                    model.setNotificationMessage(message);
                    model.setNotificationType(CommonMethod.NOTIFICATION_TYPE);
                    model.setType(9);
                    List<Users> list = new ArrayList<>();
                    for (User r : users
                            ) {
                        if (r.getChecked()) {
                            list.add(new Users(r.getUserID()));
                        }
                    }
                    model.setUsers(list);
                    getRequestView().showProgress();
                    module.onRequestSave(model, this);
                }


            }

        }


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
    public void onSuccessSave(Boolean message) {
        if (hasView()) {
            getRequestView().hideProgress();
            getRequestView().onSaveSuccessfully(message);
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
