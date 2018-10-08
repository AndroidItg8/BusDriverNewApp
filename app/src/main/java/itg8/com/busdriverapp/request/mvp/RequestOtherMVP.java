package itg8.com.busdriverapp.request.mvp;

import android.view.View;

import java.util.List;

import itg8.com.busdriverapp.common.BaseView;
import itg8.com.busdriverapp.request.model.OtherUserRequestModel;
import itg8.com.busdriverapp.request.model.Role;
import itg8.com.busdriverapp.request.model.User;
import okhttp3.ResponseBody;

public interface RequestOtherMVP {

    public interface RequestView extends BaseView {
        String getCategory();
                List<User> getUser();
        String getMessage();


        void onCategoryInvalid(String err);

        void onUserInvalid(String err);
        void onMessageInvalid(String err);
        void onSuccess(List<Role> response);
        void onFail(String message);
        void onError(Object t);


        void onSaveSuccessfully(Boolean message);
    }

    public interface RequestPresenter{
        void onDestroy();
        void onRequestClicked(View view);
        void getCategory();
//        void DownloadUserType(ResponseBody responseBody);


    }

    public interface RequestListener{
        void onSuccess(Object message);
        void onFail(String message);
        void onError(Object t);
        void onSuccessSave(Boolean message);
    }

    public interface RequestModule{
        void onRequestSave(OtherUserRequestModel model, RequestOtherMVP.RequestListener listener);
        void onDownloadCategory( RequestOtherMVP.RequestListener listener);
        //        void onDownloadUser( RequestMVP.RequestListener listener);
        void onDestroy();

    }
}
