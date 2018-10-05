package itg8.com.busdriverapp.leave_request.mvp;

import android.text.TextUtils;
import android.view.View;

import java.util.Calendar;

import itg8.com.busdriverapp.R;
import itg8.com.busdriverapp.common.BaseWeakPresenter;
import itg8.com.busdriverapp.common.CommonMethod;

import static itg8.com.busdriverapp.leave_request.LeaveRequestFragment.HALF_DAY;
import static itg8.com.busdriverapp.leave_request.LeaveRequestFragment.MULTI_DAY;
import static itg8.com.busdriverapp.leave_request.LeaveRequestFragment.ONE_DAY;

public class RequestPresenterImp extends BaseWeakPresenter<RequestMVP.RequestView> implements RequestMVP.RequesListener, RequestMVP.RequestPresenter {


    RequestMVP.RequestModule module;

    public RequestPresenterImp(RequestMVP.RequestView view) {
        super(view);
        module = new RequestModuleImp();
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
    public void onSaveClicked(View view, int selectedDay, int radioChecked) {

        if (hasView()) {
            boolean isValid = true;
            String message = getRequestView().getOnMessaged();
            String startDate = getRequestView().getStartDate();
            String endDate = getRequestView().getEndDate();
            String oneDayDate = getRequestView().getOneDayDate();
            Calendar c = getRequestView().getStartCalenderDate();
            Calendar cs = getRequestView().getEndCalenderDate();
            int checkedItem = getRequestView().getCheckItem();
            if (isValid) {
                getRequestView().showProgress();
                if (selectedDay == ONE_DAY ) {

                    if (TextUtils.isEmpty(oneDayDate)) {
                        isValid = false;
                        getRequestView().onOneDayInvalid(view.getContext().getString(R.string.empty_string));
                    } else {
//                        getRequestView().onOneDayInvalid("");
//                        startDate = CommonMethod.getStringServerDateFromStringDateOnly(getRequestView().getOneDayDate());
//                        endDate = startDate;
                        isValid=true;
                    }
                } else if (selectedDay == HALF_DAY) {

                    isValid = checkValidationForHalfDay(view, isValid, startDate, endDate, oneDayDate);



                } else if (selectedDay == MULTI_DAY) {

                    isValid = checkValidationForMultipleDay(view, isValid, startDate, endDate);

                }
                if (TextUtils.isEmpty(message)) {
                    isValid = false;
                    getRequestView().onMessageInvalid(view.getContext().getString(R.string.empty_string));
                }
            }

            if (isValid) {
                getRequestView().showProgress();


                module.onStartCall(view.getContext().getString(R.string.url_login), radioChecked, CommonMethod.getServerStringDateByDate(c.getTime()), CommonMethod.getServerStringDateByDate(cs.getTime()), message, this);
            }


        }

    }



    private String getStartDate(String startDate) {
        final Calendar c = Calendar.getInstance();
        final int mHour = c.get(Calendar.HOUR_OF_DAY);
        final int mMinute = c.get(Calendar.MINUTE);
        String[] splitYear = startDate.split("-");
        String mDay = splitYear[0];
        String mMonth = splitYear[1];
        String mYear = splitYear[2];
        c.set(Calendar.YEAR, Integer.parseInt(mYear));
        c.set(Calendar.MONTH, Integer.parseInt(mMonth));
        c.set(Calendar.DAY_OF_MONTH, Integer.parseInt(mDay));
        c.set(Calendar.HOUR_OF_DAY, mHour);
        c.set(Calendar.MINUTE,mMinute);


        return  CommonMethod.formatServerSend.format(c.getTime());
    }

//    private String getHalfDayDataAsSever(String startDate, String endDate, String oneDayDate) {
//
//        final Calendar c = Calendar.getInstance();
////        final int mHour = c.get(Calendar.HOUR_OF_DAY);
////        final int mMinute = c.get(Calendar.MINUTE);
//        final int mYear = c.get(Calendar.YEAR);
//        final int mMonth = c.get(Calendar.MONTH);
//        final int mDay = c.get(Calendar.DAY_OF_MONTH);
//        String stDate = oneDayDate + startDate;
//        String enDate = oneDayDate + endDate;
//
//        c.set(Calendar.YEAR,mYear);
//        c.set(Calendar.MONTH, mMonth);
//        c.set(Calendar.DAY_OF_MONTH, mDay);
//        c.set(Calendar.HOUR_OF_DAY, startDate);
//        c.set(Calendar.MINUTE,minute);
//
//        Date dateStart = CommonMethod.convertStringDate(stDate);
//        Date dateEnd = CommonMethod.convertStringDate(enDate);
//
//        String datefinal = CommonMethod.convertDateString(dateStart) + " -" + CommonMethod.convertDateString(dateEnd);
//
//
//        return datefinal;
//
//    }

    private boolean checkValidationForMultipleDay(View view, boolean isValid, String startDate, String endDate) {
        if (TextUtils.isEmpty(startDate)) {
            isValid = false;
            getRequestView().onStartDateInvalid(view.getContext().getString(R.string.empty_string));
        }
        if (TextUtils.isEmpty(endDate)) {
            isValid = false;
            getRequestView().onEndDateInvalid(view.getContext().getString(R.string.empty_string));
        }
        return isValid;


    }

    private boolean checkValidationForHalfDay(View view, boolean isValid, String startDate, String endDate, String oneDayDate) {
        if (TextUtils.isEmpty(startDate)) {
            isValid = false;
            getRequestView().onStartDateInvalid(view.getContext().getString(R.string.empty_string));
        }
        if (TextUtils.isEmpty(endDate)) {
            isValid = false;
            getRequestView().onEndDateInvalid(view.getContext().getString(R.string.empty_string));
        }

        if (TextUtils.isEmpty(oneDayDate)) {
            isValid = false;
            getRequestView().onOneDayInvalid(view.getContext().getString(R.string.empty_string));
        }


        return isValid;
    }


    private RequestMVP.RequestView getRequestView() {
        return getView();
    }
}
