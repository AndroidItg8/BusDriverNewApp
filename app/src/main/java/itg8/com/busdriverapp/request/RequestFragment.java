package itg8.com.busdriverapp.request;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import itg8.com.busdriverapp.R;
import itg8.com.busdriverapp.leave_request.mvp.RequestMVP;
import itg8.com.busdriverapp.leave_request.mvp.RequestPresenterImp;
import itg8.com.busdriverapp.request.mvp.RequestOtherMVP;
import itg8.com.busdriverapp.request.mvp.RequestOtherPresenterImp;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RequestFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RequestFragment extends Fragment implements View.OnClickListener , RequestOtherMVP.RequestView{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.lbl_category)
    TextView mLblCategory;
    @BindView(R.id.lbl_user)
    TextView mLblUser;
    @BindView(R.id.txt_message)
    EditText mTxtMessage;
    @BindView(R.id.btnSave)
    Button mBtnSave;
    @BindView(R.id.btnClear)
    Button mBtnClear;
    Unbinder unbinder;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private RequestOtherMVP.RequestPresenter presenter;


    public RequestFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RequestFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RequestFragment newInstance(String param1, String param2) {
        RequestFragment fragment = new RequestFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_request, container, false);
        unbinder = ButterKnife.bind(this, view);
presenter = new RequestOtherPresenterImp(this);
presenter.getCategory();
        setClickListener();
        return view;

    }

    private void setClickListener() {
        mLblCategory.setOnClickListener(this);
        mLblUser.setOnClickListener(this);
        mBtnClear.setOnClickListener(this);
        mBtnSave.setOnClickListener(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.lbl_category:
                OpenCustomDialogue();
                break;

            case R.id.lbl_user:
                OpenCategoryDialogue();
                break;

            case R.id.btnSave:

                break;

            case R.id.btnClear:
                break;

        }

    }

    private void OpenCustomDialogue() {

    }

    private void OpenCategoryDialogue() {
        final String[] singleChoiceItems = {"Bus Admin", "Bus Driver"};
        int itemSelected = 0;
        new AlertDialog.Builder(getActivity())
                .setTitle("Select  User")
                .setSingleChoiceItems(singleChoiceItems, itemSelected, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int selectedIndex) {

                                mLblUser.setText(singleChoiceItems[selectedIndex]);


                    }
                })
                .setPositiveButton("Ok", null)
                .setNegativeButton("Cancel", null)
                .show();

    }

    @Override
    public String getCategory() {
        return mLblCategory.getText().toString();
    }

    @Override
    public String getUser() {
        return mLblCategory.getText().toString();
    }

    @Override
    public String getMessage() {
        return mTxtMessage.getText().toString();
    }

    @Override
    public void onCategoryInvalid(String err) {
        mLblCategory.setError(err);
    }

    @Override
    public void onUserInvalid(String err) {
        mLblUser.setError(err);

    }

    @Override
    public void onMessageInvalid(String err) {
        mTxtMessage.setError(err);


    }

    @Override
    public void onSuccess(String response) {

    }

    @Override
    public void onFail(String message) {

    }

    @Override
    public void onError(Object t) {

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
