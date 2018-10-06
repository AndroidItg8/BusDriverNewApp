package itg8.com.busdriverapp.request.mvp;

import android.view.View;

import java.util.List;

import itg8.com.busdriverapp.common.BaseView;
import itg8.com.busdriverapp.request.model.Role;
import okhttp3.ResponseBody;

public interface RequestOtherMVP {

    public interface RequestView extends BaseView {
        String getCategory();
                String getUser();
        String getMessage();


        void onCategoryInvalid(String err);

        void onUserInvalid(String err);
        void onMessageInvalid(String err);
        void onSuccess(List<Role> response);
        void onFail(String message);
        void onError(Object t);


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


    }

    public interface RequestModule{
        void onRequestSave( String categoryId, String message, String UserId,RequestOtherMVP.RequestListener listener);
        void onDownloadCategory( RequestOtherMVP.RequestListener listener);
        //        void onDownloadUser( RequestMVP.RequestListener listener);
        void onDestroy();

    }
}
