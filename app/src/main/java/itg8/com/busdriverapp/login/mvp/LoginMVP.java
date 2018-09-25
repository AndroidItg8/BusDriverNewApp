package itg8.com.busdriverapp.login.mvp;

import android.view.View;

/**
 * Created by itg_Android on 9/6/2017.
 */

public interface LoginMVP {

    public interface LoginView{
        String getUsername();
        String getPassword();
        void onSuccess();
        void onFail(String message);
        void onError(Object t);

        void onUsernameInvalid(String err);

        void onPasswordInvalid(String err);

        void showProgress();

        void hideProgress();

        void setTokenAvail(boolean isAvail);
    }

    public interface LoginPresenter{
        void onDestroy();
        void onLoginClicked(View view);

        void startGettingToken();
    }

    public interface LoginListener{
        void onSuccess();
        void onFail(String message);
        void onError(Object t);

        void onTokenAvail();
    }

    public interface LoginModule{
        void onStartCall(String url, String username, String password, LoginListener listener);
        void onDestroy();

        void startGettingTokenSession(LoginMVP.LoginListener listener);
    }

}
