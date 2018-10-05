package itg8.com.busdriverapp.leave_request.mvp;

import android.view.View;

import java.util.Calendar;

import itg8.com.busdriverapp.common.BaseView;


public interface RequestMVP {
    public interface RequestView extends BaseView {


        String getStartDate();
        String getEndDate();
        String getOneDayDate();
        String getOnMessaged();
       Calendar getStartCalenderDate();
       Calendar getEndCalenderDate();

        void onSuccess(String response);
        void onFail(String message);
        void onError(Object t);



        void onStartDateInvalid(String err);
        void onEndDateInvalid(String err);
        void onOneDayInvalid(String err);
        void onMessageInvalid(String err);


        void showProgress();

        void hideProgress();

        int getCheckItem();
    }

    public interface RequestPresenter{
        void onSaveClicked(View view, int daySelected, int radioChecked);


    }

    public interface RequesListener{
        void onSuccess(String response);
        void onFail(String message);
        void onError(Object t);
    }

    public interface RequestModule{
        void onStartCall(String url, int checkedItem,  String startDate, String  endDate,String message, RequestMVP.RequesListener listener);
        void onDestroy();

    }

}
