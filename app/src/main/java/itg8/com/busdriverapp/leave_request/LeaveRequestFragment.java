package itg8.com.busdriverapp.leave_request;


import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import itg8.com.busdriverapp.R;
import itg8.com.busdriverapp.common.CommonMethod;
import itg8.com.busdriverapp.common.UtilSnackbar;
import itg8.com.busdriverapp.leave_request.mvp.RequestMVP;
import itg8.com.busdriverapp.leave_request.mvp.RequestPresenterImp;

import static itg8.com.busdriverapp.home.HomeFragment.TAG;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LeaveRequestFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LeaveRequestFragment extends Fragment implements RadioGroup.OnCheckedChangeListener, View.OnClickListener,RequestMVP.RequestView {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public static final int ONE_DAY = 0;
    public static final int HALF_DAY = 1;
    public static final int MULTI_DAY = 2;
    private static final int START = 5;
    private static final int END= 6;
    @BindView(R.id.rd_oneDay)
    RadioButton mRdOneDay;
    @BindView(R.id.rd_halfDay)
    RadioButton mRdHalfDay;
    @BindView(R.id.rd_multipleDay)
    RadioButton mRdMultipleDay;
    @BindView(R.id.rdDayGroup)
    RadioGroup mRdDayGroup;
    @BindView(R.id.lbl_oneDay_date)
    TextView mLblOneDayDate;
    @BindView(R.id.lbl_start_Date)
    TextView mLblStartDate;
    @BindView(R.id.lbl_end_Date)
    TextView mLblEndDate;
    @BindView(R.id.ll_multipleDay)
    LinearLayout mLlMultipleDay;
    @BindView(R.id.frame)
    FrameLayout mFrame;
    @BindView(R.id.txt_message)
    EditText mTxtMessage;
    @BindView(R.id.btnSave)
    Button mBtnSave;
    @BindView(R.id.btnClear)
    Button mBtnClear;
    Unbinder unbinder;

    Calendar startDate=Calendar.getInstance();
    Calendar endDate=Calendar.getInstance();


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private boolean isFromHalfDay = false;
    private int daySelected = 0;

    private RequestMVP.RequestPresenter presenter;
    private int radioChecked=0;
    private int clickedItem=-1;


    public LeaveRequestFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LeaveRequestFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LeaveRequestFragment newInstance(String param1, String param2) {
        LeaveRequestFragment fragment = new LeaveRequestFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public Calendar getStartCalenderDate() {
        return startDate;
    }

    @Override
    public Calendar getEndCalenderDate() {
        return endDate;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_leave_request, container, false);
        unbinder = ButterKnife.bind(this, view);
        init();
        return view;
    }

    private void init() {
        presenter =  new RequestPresenterImp(this);
        mRdDayGroup.setOnCheckedChangeListener(this);
        mLblOneDayDate.setOnClickListener(this);
        mLblEndDate.setOnClickListener(this);
        mLblStartDate.setOnClickListener(this);
        mBtnSave.setOnClickListener(this);
        mBtnClear.setOnClickListener(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rd_oneDay:
                resetCalender();
                oneDaySelection();
                daySelected = ONE_DAY;
                radioChecked=1;
                break;

            case R.id.rd_halfDay:
                clearAllTextView(mLblOneDayDate, mLblStartDate, mLblEndDate, mTxtMessage);
                halfDaySelection(true);
                daySelected = HALF_DAY;
                resetCalender();

                radioChecked = 0;
                break;




            case R.id.rd_multipleDay:
                clearAllTextView(mLblOneDayDate, mLblStartDate, mLblEndDate, mTxtMessage);
                MultipleDaySelection(false);
                daySelected = MULTI_DAY;
                radioChecked = 1;
                resetCalender();

                break;
        }
    }

    private void resetCalender() {
        startDate=Calendar.getInstance();
        endDate=Calendar.getInstance();
    }

    private void oneDaySelection() {

        showHideView(mLblOneDayDate, mLlMultipleDay);
    }

    private void halfDaySelection(boolean b) {

//        showHideView(mLlMultipleDay, mLblOneDayDate);
        mLlMultipleDay.setVisibility(View.VISIBLE);
        mLblOneDayDate.setVisibility(View.VISIBLE);
        isFromHalfDay = b;
        setLabelHint(isFromHalfDay);
    }

    private void MultipleDaySelection(boolean b) {

        showHideView(mLlMultipleDay, mLblOneDayDate);
        isFromHalfDay = b;
        setLabelHint(isFromHalfDay);
    }

    private void setLabelHint(boolean isFromHalfDay) {

        if (isFromHalfDay) {
            mLblEndDate.setHint(getString(R.string.start_time));
            mLblStartDate.setHint(getString(R.string.end_time));
            mLblOneDayDate.setHint(getString(R.string.select_date));

        } else {
            mLblEndDate.setHint(getString(R.string.start_Date));
            mLblStartDate.setHint(getString(R.string.end_Date));
        }
        mTxtMessage.setHint("Message");



    }

    private void showHideView(View show, View hide) {
        show.setVisibility(View.VISIBLE);
        hide.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.lbl_oneDay_date:
                mLblOneDayDate.setTag(START);
                clickedItem = START;
                openCalender(mLblOneDayDate, clickedItem);
                break;
                case R.id.lbl_start_Date:
                 mLblOneDayDate.setTag(START);
                    clickedItem = START;
                lableClicked(mLblStartDate,clickedItem);
                break;
                case R.id.lbl_end_Date:
                    mLblEndDate.setTag(END);
                    clickedItem = END;
                lableClicked(mLblEndDate, clickedItem);
                break;
                case R.id.btnClear:
                clearAllTextView(mLblOneDayDate, mLblStartDate, mLblEndDate, mTxtMessage);
                break;
                case R.id.btnSave:
                    presenter.onSaveClicked(v,daySelected,radioChecked);
                break;

        }

    }



    private void lableClicked(TextView mLblEndDate, int clickedItem) {
        if (isFromHalfDay)
            openTimeCalender(mLblEndDate, clickedItem);
        else
            openCalender(mLblEndDate, clickedItem);


    }


    private void clearAllTextView(TextView... textView) {
        for (TextView view : textView) {
            view.setText(null);

        }
        mTxtMessage.setText(null);
    }

    private void openTimeCalender(final TextView mLblStartTime, final int clickedItem) {

        final Calendar c = Calendar.getInstance();
         final int mHour = c.get(Calendar.HOUR_OF_DAY);
        final int mMinute = c.get(Calendar.MINUTE);
        final int mYear = c.get(Calendar.YEAR);
        final int mMonth = c.get(Calendar.MONTH);
        final int mDay = c.get(Calendar.DAY_OF_MONTH);

        // Launch Time Picker Dialog
        TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(),
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay,
                                          int minute) {

                        c.set(Calendar.YEAR,mYear);
                        c.set(Calendar.MONTH, mMonth);
                        c.set(Calendar.DAY_OF_MONTH, mDay);
                        c.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        c.set(Calendar.MINUTE,minute);



                        String[]  entireDate = CommonMethod.formatServerSend.format(c.getTime()).split(" ");
                        String date = entireDate[0];
                        String time = entireDate[1];




                        setTimeAccordingly(c,clickedItem);
                        mLblStartTime.setText(CommonMethod.getApPmTime(c));
                    }
                }, mHour, mMinute, false);
        timePickerDialog.show();

    }

    private void openCalender(final TextView mLblOneDate, final int clickedItem) {
        final Calendar c = Calendar.getInstance();
//        final int mYear = c.get(Calendar.YEAR);
//        final int mMonth = c.get(Calendar.MONTH);
//        final int mDay = c.get(Calendar.DAY_OF_MONTH);
//        final int mHour = c.get(Calendar.HOUR_OF_DAY);
//        final int mMinute = c.get(Calendar.MINUTE);



        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

                        c.set(Calendar.YEAR,year);
                        c.set(Calendar.MONTH, monthOfYear);
                        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
//                        c.set(Calendar.HOUR_OF_DAY, mHour);
//                        c.set(Calendar.MINUTE,mMinute);
//                       String[]  entireDate = CommonMethod.formatServerSend.format(c.getTime()).split(" ");
//                        String date = entireDate[0];
//                        String time = entireDate[1];

                        setDateAccordingly(c,clickedItem);
                        mLblOneDate.setText(CommonMethod.getDDMMMYYYYfromDate(c));


                    }
                }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    private void setDateAccordingly(Calendar c, int tag) {

        if(daySelected==ONE_DAY || daySelected==HALF_DAY){
            startDate.setTime(c.getTime());
            endDate.setTime(c.getTime());
        }else if(daySelected==MULTI_DAY){
            if(tag==START)
                startDate.setTime(c.getTime());
            else
                endDate.setTime(c.getTime());
        }
    }

    private void setTimeAccordingly(Calendar c, int tag) {
        Calendar selectedDate=tag==START?startDate:endDate;
            selectedDate.set(Calendar.HOUR_OF_DAY,c.get(Calendar.HOUR_OF_DAY));
            selectedDate.set(Calendar.MINUTE,c.get(Calendar.MINUTE));
            selectedDate.set(Calendar.SECOND,c.get(Calendar.SECOND));
    }


    @Override
    public String getStartDate() {
        Log.d(TAG, "getStartDate: " + mLblStartDate.getText().toString());
        return mLblStartDate.getText().toString();
    }

    @Override
    public String getEndDate() {
        Log.d(TAG, "getEndDate: " + mLblEndDate.getText().toString());

        return mLblEndDate.getText().toString();
    }

    @Override
    public String getOneDayDate() {
        return mLblOneDayDate.getText().toString();
    }

    @Override
    public int getCheckItem() {
        return radioChecked;
    }

    @Override
    public String getOnMessaged() {
        return mTxtMessage.getText().toString();

    }

    @Override
    public void onSuccess(String response) {
        UtilSnackbar.showSnakbarTypeTwo(mBtnSave, "response");

    }

    @Override
    public void onFail(String message) {
        UtilSnackbar.showSnakbarTypeTwo(mBtnSave,message);
    }

    @Override
    public void onError(Object t) {

        UtilSnackbar.showSnakbarTypeTwo(mBtnSave, t.toString());
    }


    @Override
    public void onStartDateInvalid(String err) {
        showError(err,mLblStartDate);

    }

    private void showError(String err, TextView view) {
        view.setError(err);
    }

    @Override
    public void onEndDateInvalid(String err) {
        showError(err,mLblEndDate);

    }

    @Override
    public void onOneDayInvalid(String err) {
        showError(err,mLblOneDayDate);

    }

    @Override
    public void onMessageInvalid(String err) {
        showError(err,mTxtMessage);

    }

    @Override
    public void onNoInternet() {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void onRetroError(Object obj) {

    }



}
