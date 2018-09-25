package itg8.com.busdriverapp.login.mvp;


import itg8.com.busdriverapp.common.NetworkUtility;

class LoginModuleImp implements LoginMVP.LoginModule {
    @Override
    public void onStartCall(String url, String username, String password, final LoginMVP.LoginListener listener) {
        new NetworkUtility.NetworkBuilder().setHeader().build().login(url,username, password, new NetworkUtility.ResponseListener() {
            @Override
            public void onSuccess(Object message) {
                    String response= (String) message;
                if(response!=null){
                    listener.onSuccess();
                }
            }

            @Override
            public void onFailure(Object err) {

                listener.onFail((String) err);

            }

            @Override
            public void onSomethingWrong(Object e) {
                listener.onError(e);
            }
        });
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void startGettingTokenSession(final LoginMVP.LoginListener listener) {
        new NetworkUtility.NetworkBuilder().setHeader().build().getRequestToken(new NetworkUtility.ResponseListener() {
            @Override
            public void onSuccess(Object message) {
                listener.onTokenAvail();
            }

            @Override
            public void onFailure(Object err) {
                listener.onFail((String) err);
            }

            @Override
            public void onSomethingWrong(Object e) {
                listener.onError(e);
            }
        });
    }
}
