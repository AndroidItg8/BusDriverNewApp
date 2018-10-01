package itg8.com.busdriverapp.home;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallbacks;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceBuffer;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.net.ConnectException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import itg8.com.busdriverapp.R;
import itg8.com.busdriverapp.admin_map.AdminMapFragment;
import itg8.com.busdriverapp.admin_map.ChildCheckinDialogFragment;
import itg8.com.busdriverapp.admin_map.Type;
import itg8.com.busdriverapp.bus.fragment.BusFragment;
import itg8.com.busdriverapp.bus.fragment.RequestFragment;
import itg8.com.busdriverapp.bus.fragment.RouteMapFragment;
import itg8.com.busdriverapp.common.BaseActivity;
import itg8.com.busdriverapp.common.CommonMethod;
import itg8.com.busdriverapp.common.MyApplication;
import itg8.com.busdriverapp.common.Prefs;
import itg8.com.busdriverapp.common.UtilSnackbar;
import itg8.com.busdriverapp.home.busModel.BusModel;
import itg8.com.busdriverapp.home.model.Checkpoint;
import itg8.com.busdriverapp.home.model.CheckpointData;
import itg8.com.busdriverapp.home.model.RouteModel;
import itg8.com.busdriverapp.home.model.User;
import itg8.com.busdriverapp.home.mvp.HomeMvp;
import itg8.com.busdriverapp.home.mvp.HomePresenterImp;
import itg8.com.busdriverapp.login.LoginActivity;
import itg8.com.busdriverapp.map.GeocodedWaypoint;
import itg8.com.busdriverapp.map.MapDirectionModel;
import itg8.com.busdriverapp.map.MapLatLngAddressModel;
import itg8.com.busdriverapp.rout_status.RouteStatusAdapter;

import static android.view.Gravity.RIGHT;
import static itg8.com.busdriverapp.common.CommonMethod.IS_LOGIN;

public class HomeActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener, HomeFragmentInteractor, BaseActivity.GrantLocationPermissionListener, HomeMvp.HomeView, GoogleApiClient.ConnectionCallbacks {

    private static final String TAG = "HomeActivity";
    private static final int RC_VAL = 102;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.frame_container)
    FrameLayout frameContainer;
    @BindView(R.id.fab)
    FloatingActionButton fab;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.nav_view)
    NavigationView navView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.lbl_type)
    TextView lblType;
    @BindView(R.id.view)
    View view;
    @BindView(R.id.img_cross)
    ImageView imgCross;
    @BindView(R.id.rl_top)
    RelativeLayout rlTop;
    @BindView(R.id.progress)
    ProgressBar progress;
    @BindView(R.id.navigation)
    BottomNavigationView navigation;
    private Type type;
    private String currentFragment = "";


    private View.OnClickListener toolbarBackListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            onBackPressed();
        }
    };
    private int count = 1;
    private FusedLocationProviderClient mFusedLocationProviderClient;
    private boolean locationGranted;
    // The geographical location where the device is currently located. That is, the last-known
    // location retrieved by the Fused Location Provider.
    private Location mLastKnownLocation;


    private HomeMvp.HomePresenter presenter;
    private CheckpointData mCheckpointData;
    private boolean isGCConnected;
    private List<Checkpoint> listOfChildrens;
    private List<Checkpoint> listOfHalts;
    private ChildCheckinDialogFragment dialog;
    private RouteStatusAdapter routeStatusAdapter;
    private FragmentManager fm;
    String title;
    sendBusesInfoListener listener;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.nav_bottom_home:
                    title= "Home";
                    presenter.setFragmentAsPerUser();
                    return true;
                case R.id.nav_bottom_request:
                    title= "Request";
                    callFragment(RequestFragment.newInstance("",""));

                    return true;
                case R.id.nav_bottom_track:
                    title= "Track";
                   callFragment(RouteMapFragment.newInstance("",""));
                    return true;



            }


            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);
        ButterKnife.bind(this);
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        setListener(this);
        loadMap();

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        presenter = new HomePresenterImp(this);
        presenter.setFragmentAsPerUser();
        presenter.sendToken(getString(R.string.url_token), FirebaseInstanceId.getInstance().getToken());
        init();
        //        callFragment(HomeFragment.newInstance(null));
        askForPermission();
        // Construct a FusedLocationProviderClient.
        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
    }



    private void loadMap() {
        // Fixing Later Map loading Delay
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    MapView mv = new MapView(getApplicationContext());
                    mv.onCreate(null);
                    mv.onPause();
                    mv.onDestroy();
                } catch (Exception ignored) {

                }
            }
        }).start();
    }
   public void  removeUPButton(){
       getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

    }
    private void init() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.setDrawerIndicatorEnabled(false);
        toggle.syncState();

        toggle.setDrawerIndicatorEnabled(false);
        toggle.setToolbarNavigationClickListener(toolbarBackListener);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        setOnClickedListner();

        setRecyclerView(new ArrayList<User>());

    }

    private void setOnClickedListner() {
        imgCross.setOnClickListener(this);
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public void callFragment(Fragment fragment) {
        if (fm == null)
            fm = getSupportFragmentManager();
        FragmentTransaction ft = fm
                .beginTransaction();
        ft.replace(R.id.frame_container, fragment, fragment.getClass().getSimpleName());
        if (!fragment.getClass().getSimpleName().equalsIgnoreCase(RouteListFragment.TAG))
            ft.addToBackStack(fragment.getClass().getSimpleName());
        ft.commit();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);

        MenuItem item = menu.findItem(R.id.action_child_notify_badge);
        TextView textView = item.getActionView().findViewById(R.id.txtCount);
        textView.setText(String.valueOf(count));
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_child_notify_badge) {
            count++;
            invalidateOptionsMenu();
            return false;
        } else if (id == R.id.action_logout) {
            logoutFromActivity();
        }

        return super.onOptionsItemSelected(item);
    }

    private void logoutFromActivity() {
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case DialogInterface.BUTTON_POSITIVE:
                        Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                        Prefs.remove(IS_LOGIN);
                        Prefs.remove(CommonMethod.TOKEN);
                        startActivity(intent);
                        finish();
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        //No button clicked
                        dialog.dismiss();
                        break;
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Do you want to Logout?").setPositiveButton("Yes", dialogClickListener)
                .setNegativeButton("No", dialogClickListener).show();

    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void openDrawerLayout() {
        drawerLayout.openDrawer(RIGHT);
        getList();
    }

    private void getList() {
        List<Object> objects = new ArrayList<>();
    }

    private void setRecyclerView(List<User> list) {
        recyclerView.setLayoutManager(new LinearLayoutManager(HomeActivity.this));
        DividerItemDecoration itemDecoration = new DividerItemDecoration(HomeActivity.this, RecyclerView.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);
        routeStatusAdapter = new RouteStatusAdapter(HomeActivity.this, type, list);
        recyclerView.setAdapter(routeStatusAdapter);

    }


    @Override
    public void onClick(View view) {
        drawerLayout.closeDrawer(RIGHT);

    }

    @Override
    public void onAttach(String tag) {
        Log.i(TAG, "onAttach: " + tag);
        currentFragment = tag;
        if (currentFragment.equalsIgnoreCase(AdminMapFragment.TAG)) {
            toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
            isGCConnected = true;
        } else {
            toolbar.setNavigationIcon(null);
        }
    }

    @Override
    public void onStartMapFragment(String children, String halt, String skip, CheckpointData mCheckpointData) throws JSONException {
        if (this.mCheckpointData != null) {
            if (mCheckpointData.getCheckpoints().getCheckpoint().get(0) != null) {
                Checkpoint checkpoint = ((List<Checkpoint>) mCheckpointData.getCheckpoints().getCheckpoint()).get(0);
                mLastKnownLocation = new Location("");
                mLastKnownLocation.setLatitude(Double.parseDouble(checkpoint.getLatitude()));
                mLastKnownLocation.setLongitude(Double.parseDouble(checkpoint.getLongitude()));
            }
        }

        callFragment(AdminMapFragment.newInstance(mLastKnownLocation, mCheckpointData, children, halt, skip));
    }

    @Override
    public void onDetach(String tag) {

    }

    @Override
    public void startLocationUpdate(OnLocationUpdateListener listener) {
        if (isGCConnected) {
            updateLocation(listener);
        }
    }

    private void updateLocation(final OnLocationUpdateListener listener) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mFusedLocationProviderClient.requestLocationUpdates(getLocationRequest(), new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                if (locationResult == null) {
                    return;
                }
                for (Location location : locationResult.getLocations()) {
                    listener.onLocationUpdate(location);
                }
            }

            ;
        }, null);
        Log.d(TAG, "Location update started ..............: ");
    }


    @Override
    public void startDownloadingAddressForMap(CheckpointData mCheckpointData) {
        this.mCheckpointData = mCheckpointData;
    }

    @Override
    public void stopLocationUpdate() {
        isGCConnected = false;
    }

    @Override
    public void grantedLocationPermission() {
        locationGranted = true;
        checkForLocationEnable();
    }

    @Override
    public void onLocationEnabled() {
        getBestLocation();
    }

    private void getBestLocation() {
        /*
         * Get the best and most recent location of the device, which may be null in rare
         * cases when a location is not available.
         */
        try {
            if (locationGranted) {
                Task locationResult = mFusedLocationProviderClient.getLastLocation();
                locationResult.addOnCompleteListener(this, new OnCompleteListener<Location>() {
                    @Override
                    public void onComplete(@NonNull Task<Location> task) {
                        if (task.isSuccessful()) {
                            // Set the map's camera position to the current location of the device.
                            mLastKnownLocation = task.getResult();

                        } else {
                            Log.d(TAG, "Current location is null. Using defaults.");
                            Log.e(TAG, "Exception: %s", task.getException());
                        }
                    }
                });
            }
        } catch (SecurityException e) {
            Log.e("Exception: %s", e.getMessage());
        }
    }

    @Override
    public void notGrantedLocationPermission() {
        locationGranted = false;
    }

    public void collectPlaces(MapDirectionModel model, final MarkerAvailableListener listener) {


        for (GeocodedWaypoint route :
                model.getGeocodedWaypoints()) {
            if (
                    route == model.getGeocodedWaypoints().get(model.getGeocodedWaypoints().size() - 1) ||
                            route == model.getGeocodedWaypoints().get(0)
                    ) {
                Places.GeoDataApi.getPlaceById(getGoogleClient(), route.getPlaceId()).setResultCallback(new ResultCallbacks<PlaceBuffer>() {
                    @Override
                    public void onSuccess(@NonNull PlaceBuffer places) {

                        final Place myPlace = places.get(0);
                        LatLng queriedLocation = myPlace.getLatLng();
                        Log.v("Latitude is", "" + queriedLocation.latitude);
                        Log.v("Longitude is", "" + queriedLocation.longitude);
                        listener.onLatlangAvail(new MapLatLngAddressModel(queriedLocation, myPlace.getAddress().toString()));

                        places.release();
                    }

                    @Override
                    public void onFailure(@NonNull Status status) {

                    }

                });
            } else {
                Places.GeoDataApi.getPlaceById(getGoogleClient(), route.getPlaceId()).setResultCallback(new ResultCallbacks<PlaceBuffer>() {
                    @Override
                    public void onSuccess(@NonNull PlaceBuffer places) {

                        final Place myPlace = places.get(0);
                        LatLng queriedLocation = myPlace.getLatLng();
                        Log.v("Latitude is", "" + queriedLocation.latitude);
                        Log.v("Longitude is", "" + queriedLocation.longitude);
                        listener.onAllLatlangAvail(new MapLatLngAddressModel(queriedLocation, myPlace.getAddress().toString()));

                        places.release();
                    }

                    @Override
                    public void onFailure(@NonNull Status status) {

                    }

                });
            }
        }


    }


    public <T> void showDialog(List<T> childList) {
        if (dialog == null)
            dialog = ChildCheckinDialogFragment.newInstance(childList);
        if (!dialog.isVisible())
            dialog.show(getSupportFragmentManager(), ChildCheckinDialogFragment.class.getSimpleName());
    }

    @Override
    public void onRouteAvail(RouteModel routeModels) {
        Log.i(TAG, "onRouteAvail: " + new Gson().toJson(routeModels));
//        if (routeModels != null
//                && routeModels.getCheckpointData() != null
//                && routeModels.getCheckpointData().getCheckpoints() != null)
//            listOfHalts = routeModels.getCheckpointData().getCheckpoints().getCheckpoint();
        callFragment(RouteListFragment.newInstance(routeModels));
    }

    @Override
    public void showBusAdminDetails() {
        presenter.startGettingBusInfo();
        navigation.setVisibility(View.VISIBLE);
    }

    @Override
    public void showBusDriverDetails() {
        presenter.startGettingRouteInfo();
        navigation.setVisibility(View.GONE);
    }

    @Override
    public void onRouteDownloadFail(Object e) {
        UtilSnackbar.showSnakbarTypeFail(recyclerView, e.toString(), new UtilSnackbar.OnSnackbarActionClickListener() {
            @Override
            public void onRetryClicked() {
                presenter.startGettingRouteInfo();
            }
        });
    }

    @Override
    public void onNoInternet() {
        UtilSnackbar.showSnakbarTypeThree(recyclerView, new UtilSnackbar.OnSnackbarActionClickListener() {
            @Override
            public void onRetryClicked() {
                presenter.startGettingRouteInfo();
            }
        });
    }

    @Override
    public void showProgress() {
        //  progress.setVisibility(View.VISIBLE);
        //  frameContainer.setVisibility(View.GONE);
    }

    @Override
    public void hideProgress() {
        //  progress.setVisibility(View.GONE);
        //  frameContainer.setVisibility(View.VISIBLE);
    }

    @Override
    public void onRetroError(Object obj) {
        if (obj instanceof HttpException)
            showErrorByCode(((HttpException) obj).code());
        else if (obj instanceof ConnectException)
            UtilSnackbar.showSnakbarTypeTwo(recyclerView, "Fail to connect...");
    }


    @Override
    public GoogleApiClient.ConnectionCallbacks getConnectionCallback() {
        return this;
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        isGCConnected = true;
        MyApplication.getInstance().setGoogleApiClient(getGoogleClient());
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onBusesAvailable(BusModel busModel) {

   callFragment(BusFragment.newInstance(busModel));

    }

    public void setListOfChildrens(List<Checkpoint> listOfChildrens) {
        this.listOfChildrens = listOfChildrens;
        List<User> users = new ArrayList<>();
        for (Checkpoint checkpoint :
                listOfChildrens) {
            if (checkpoint.getUsers() == null)
                continue;

            try {
                String jsonString = new Gson().toJson(checkpoint.getUsers());
                Object json = new JSONTokener(jsonString).nextValue();
                if (json instanceof JSONObject) {
                    User user = new Gson().fromJson(json.toString(), User.class);
                    users.add(user);
                } else if (json instanceof JSONArray) {
                    List<User> users1 = new Gson().fromJson(json.toString(), new TypeToken<List<User>>() {
                    }.getType());
                    users.addAll(users1);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            checkpoint.setUsers(users);
        }

        routeStatusAdapter.setAllUsers(users);
    }

    public void setAndOpenChildrenList() {
        if (listOfChildrens != null) {
            drawerLayout.openDrawer(RIGHT);
//            setRecyclerView(new ArrayList<Object>(listOfChildrens));
        }
    }

    public void setAndHaltList() {
        if (listOfHalts != null) {
            drawerLayout.openDrawer(RIGHT);
//            setRecyclerView(new ArrayList<Object>(listOfHalts));
        }
    }

    public void changeUsersStatus(List<String> userIDS) {
        routeStatusAdapter.setUserIDs(userIDS);
    }

    public void attachedListener(RouteMapFragment routeMapFragment) {

    }

    public interface MarkerAvailableListener {
        void onAllLatlangAvail(MapLatLngAddressModel model);

        void onLatlangAvail(MapLatLngAddressModel latLng);

        void onListComplete();
    }

    public interface sendBusesInfoListener{
        void onBusesAvailable(BusModel busModel);
    }
}
