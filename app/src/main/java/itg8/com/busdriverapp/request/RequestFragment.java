package itg8.com.busdriverapp.request;


import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import itg8.com.busdriverapp.R;
import itg8.com.busdriverapp.common.CommonMethod;
import itg8.com.busdriverapp.request.model.Role;
import itg8.com.busdriverapp.request.model.User;
import itg8.com.busdriverapp.request.mvp.RequestOtherMVP;
import itg8.com.busdriverapp.request.mvp.RequestOtherPresenterImp;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RequestFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RequestFragment extends Fragment implements View.OnClickListener, RequestOtherMVP.RequestView {
    public static final String TAG = "RequestFragment";
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final int RC_CODE = 23;
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
    List<Role> roleList = new ArrayList<>();
    List<User> userList = new ArrayList<>();
    @BindView(R.id.progress)
    ProgressBar mProgress;
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
        switch (v.getId()) {
            case R.id.lbl_category:
                if (userList.size() > 0) {
                    Intent intents = new Intent(getActivity(), RequestActivity.class);
                    intents.putParcelableArrayListExtra(CommonMethod.ROLE_USER, (ArrayList<? extends Parcelable>) userList);
                    startActivity(intents);
                }
                break;

            case R.id.lbl_user:
                if (roleList.size() > 0) {
                    Intent intent = new Intent(getActivity(), RequestActivity.class);
                    intent.putParcelableArrayListExtra(CommonMethod.ROLE, (ArrayList<? extends Parcelable>) roleList);
                    startActivityForResult(intent, RC_CODE);
                }
                break;

            case R.id.btnSave:

                break;

            case R.id.btnClear:
                break;

        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }


    //    private void OpenCategoryDialogue() {
////        final String[] singleChoiceItems = {"Bus Admin", "Bus Driver"};
//
//        int itemSelected = 0;
//        new AlertDialog.Builder(getActivity())
//                .setTitle("Select  User")
//                .setSingleChoiceItems(roleList, itemSelected, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int selectedIndex) {
//
//                        mLblUser.setText();
//
//
//                    }
//                })
//                .setPositiveButton("Ok", null)
//                .setNegativeButton("Cancel", null)
//                .show();
//
//    }

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
    public void onSuccess(List<Role> response) {
        roleList.addAll(response);
        for (Role role :roleList){
            userList.addAll(role.getRoleUser());
        }


    }





    private void convertObjectToModel(List<Role> response) {
        Observable.just(response).flatMap(new Function<List<Role>, ObservableSource<List<User>>>() {
            @Override
            public ObservableSource<List<User>> apply(List<Role> attendanceAdmin) throws Exception {

                for (Role role : attendanceAdmin) {


                    String jsonString = new Gson().toJson(role.getUsers());
                    Object json = new JSONTokener(jsonString).nextValue();

                    if (json instanceof JSONObject) {
                        User bus = new Gson().fromJson(json.toString(), User.class);
                        userList.add(bus);

                    } else if (json instanceof JSONArray) {
                        List<User> bus = new Gson().fromJson(json.toString(), new TypeToken<List<User>>() {
                        }.getType());
                        userList.addAll(bus);
                        Log.d(TAG, "apply: listBuses" + roleList.size());
                    }
                }

                return Observable.just(userList);
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<User>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<User> roles) {
                        userList.addAll(roles);


                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();

                    }

                    @Override
                    public void onComplete() {

                    }
                });


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
        mProgress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        mProgress.setVisibility(View.GONE);

    }

    @Override
    public void onRetroError(Object obj) {

    }
}
