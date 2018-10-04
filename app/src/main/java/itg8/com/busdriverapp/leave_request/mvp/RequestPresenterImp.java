package itg8.com.busdriverapp.leave_request.mvp;

import android.text.TextUtils;
import android.view.View;


import itg8.com.busdriverapp.R;
import itg8.com.busdriverapp.common.BaseWeakPresenter;

public class RequestPresenterImp extends BaseWeakPresenter<RequestMVP.RequestView> implements RequestMVP.RequesListener, RequestMVP.RequestPresenter {


    RequestMVP.RequestModule module;

    public RequestPresenterImp(RequestMVP.RequestView view) {
        super(view);
        module = new RequestModuleImp();
    }


    public void onSuccess() {
        if (hasView()) {
            getRequestView().hideProgress();
            getRequestView().onSuccess();
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
    public void onSaveClicked(View view, int selectedDay) {

        if (hasView()) {
            boolean isValid = true;
            String message = getRequestView().getOnMessaged();
            String startDate = getRequestView().getStartDate();
            String endDate = getRequestView().getEndDate();
            String oneDayDate = getRequestView().getOneDayDate();
            int checkedItem = getRequestView().getCheckItem();
            if (isValid) {
                getRequestView().showProgress();
                if (selectedDay == 0) {

                    if (TextUtils.isEmpty(oneDayDate)) {
                        isValid = false;
                        getRequestView().onOneDayInvalid(view.getContext().getString(R.string.empty_string));
                    } else {
                        getRequestView().onOneDayInvalid("");
                        startDate = getRequestView().getOneDayDate();
                        endDate = getRequestView().getOneDayDate();
                    }
                } else if (selectedDay == 1 || selectedDay ==2 ) {

                    if (TextUtils.isEmpty(startDate)) {
                        isValid = false;
                        getRequestView().onStartDateInvalid(view.getContext().getString(R.string.empty_string));
                    } else {
                        getRequestView().onStartDateInvalid("");
                        }
                    if (TextUtils.isEmpty(endDate)) {
                        isValid = false;
                        getRequestView().onEndDateInvalid(view.getContext().getString(R.string.empty_string));
                    } else
                        getRequestView().onEndDateInvalid("");
                    }
                    if (TextUtils.isEmpty(message)) {
                    isValid = false;
                    getRequestView().onMessageInvalid(view.getContext().getString(R.string.empty_string));
                } else
                    getRequestView().onMessageInvalid("");
            }

            if (isValid) {
                getRequestView().showProgress();
                module.onStartCall(view.getContext().getString(R.string.url_login), checkedItem, startDate, endDate, message, this);
            }


        }

    }

    private boolean checkValidationForHalfDay(View view, boolean isValid, String startDate, String endDate) {
        if (TextUtils.isEmpty(startDate)) {
            isValid = false;
            getRequestView().onStartDateInvalid(view.getContext().getString(R.string.empty_string));
        } else {
            getRequestView().onStartDateInvalid("");

        }


        if (TextUtils.isEmpty(endDate)) {
            isValid = false;
            getRequestView().onEndDateInvalid(view.getContext().getString(R.string.empty_string));
        } else
            getRequestView().onEndDateInvalid("");
        return isValid;
    }


    private RequestMVP.RequestView getRequestView() {
        return getView();
    }
}
