package itg8.com.busdriverapp.rout_status.mvp;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallbacks;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceBuffer;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.model.LatLng;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import itg8.com.busdriverapp.R;
import itg8.com.busdriverapp.common.CommonMethod;
import itg8.com.busdriverapp.common.MyApplication;
import itg8.com.busdriverapp.common.RetroController;
import itg8.com.busdriverapp.home.busModel.User_;
import itg8.com.busdriverapp.home.model.Checkpoint;
import itg8.com.busdriverapp.home.model.CheckpointData;
import itg8.com.busdriverapp.home.model.User;
import itg8.com.busdriverapp.map.GeocodedWaypoint;
import itg8.com.busdriverapp.map.MapDirectionModel;
import itg8.com.busdriverapp.map.MapLatLngAddressModel;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static itg8.com.busdriverapp.common.CommonMethod.getListOfLocations;

public class RouteStatusModuleImp implements RouteStatusMVP.RouteStatusModule {

    private RouteStatusMVP.RouteStatusListener listener;

    public RouteStatusModuleImp(RouteStatusMVP.RouteStatusListener listener) {

        this.listener = listener;
    }

    @Override
    public void onStartBiffergeting(final CheckpointData data) {
        Observable.just(data)
                .flatMap(new Function<CheckpointData, Observable<RouteStatusMVP.RouteStatusModel>>() {
                    @Override
                    public Observable<RouteStatusMVP.RouteStatusModel> apply(CheckpointData data) throws Exception {
                        //For AllCheckpoints
                        RouteStatusMVP.RouteStatusModel model=new RouteStatusMVP.RouteStatusModel();
                        model.checkpoints=data.getCheckpoints().getCheckpoint();
                        return Observable.just(model);
                    }
                })
                .flatMap(new Function<RouteStatusMVP.RouteStatusModel, Observable<RouteStatusMVP.RouteStatusModel>>() {
                    @Override
                    public Observable<RouteStatusMVP.RouteStatusModel> apply(final RouteStatusMVP.RouteStatusModel routeStatusModel) throws Exception {
                       return getAllCheckpointForMapView(routeStatusModel,data);
                    }
                }).flatMap(new Function<RouteStatusMVP.RouteStatusModel, Observable<RouteStatusMVP.RouteStatusModel>>() {
            @Override
            public Observable<RouteStatusMVP.RouteStatusModel> apply(RouteStatusMVP.RouteStatusModel routeStatusModel) throws Exception {
                List<User> user_s=new ArrayList<>();
                for (Checkpoint checkpoints :
                        data.getCheckpoints().getCheckpoint()) {
                    if(checkpoints.getUsers() instanceof List)
                        user_s.addAll((Collection<? extends User>) checkpoints.getUsers());
                }
                routeStatusModel.allUsers=user_s;
                return Observable.just(routeStatusModel);
            }
        })
//                .flatMap(new Function<RouteStatusMVP.RouteStatusModel, Observable<RouteStatusMVP.RouteStatusModel>>() {
//            @Override
//            public Observable<RouteStatusMVP.RouteStatusModel> apply(RouteStatusMVP.RouteStatusModel routeStatusModel) throws Exception {
//                return null;
//            }
//        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Object o) {
                        if(o instanceof RouteStatusMVP.RouteStatusModel){
                            RouteStatusMVP.RouteStatusModel model= (RouteStatusMVP.RouteStatusModel) o;
                            if(model.allUsers!=null)
                                listener.onUsersListAvail(model.allUsers);
                            if(model.navigation!=null)
                                listener.onListOfAddressForMapViewAvail(model.navigation);
                            if(model.checkpoints!=null)
                                listener.onCheckpointListAvail(model.checkpoints);
                            if(model.usersOnLeave!=null)
                                listener.onListOfLeaveUserAvail(model.usersOnLeave);
                        }
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

    private Observable<RouteStatusMVP.RouteStatusModel> getAllCheckpointForMapView(final RouteStatusMVP.RouteStatusModel routeStatusModel, CheckpointData data) {
        List<Checkpoint> checkpoints= (List<Checkpoint>) data.getCheckpoints().getCheckpoint();
        String source=null,destination=null;
        if (!TextUtils.isEmpty(checkpoints.get(0).getCheckpointAddress())) {
            source = checkpoints.get(0).getLatitude() + ","
                    + checkpoints.get(0).getLongitude();
        }
        if (!TextUtils.isEmpty(checkpoints.get(checkpoints.size() - 1).getCheckpointAddress())) {
            Checkpoint checkpoint = checkpoints.get(checkpoints.size() - 1);
            destination = checkpoint.getLatitude() + "," + checkpoint.getLongitude();
        }

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(5, TimeUnit.MINUTES);
        builder.addInterceptor(interceptor);
        builder.readTimeout(5, TimeUnit.MINUTES);

        OkHttpClient client = builder.build();
        Gson gson = new GsonBuilder().setLenient().create();


        Retrofit retrofit;

        retrofit = new Retrofit.Builder()

                .baseUrl("https://maps.googleapis.com")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build();


        RetroController controllerTemp = retrofit.create(RetroController.class);
        StringBuilder addresLines = new StringBuilder();
        for (String address :
                CommonMethod.getAddresses(data)) {
            addresLines.append("|").append(address);
        }
        Observable<ResponseBody> responseBodyObservable = controllerTemp.downloadMapRoute("/maps/api/directions/json", source, destination, addresLines, MyApplication.getInstance().getString(R.string.google_maps_key), "Transit");
        return responseBodyObservable.flatMap(new Function<ResponseBody, Observable<String>>() {
            @Override
            public Observable<String> apply(ResponseBody responseBody) throws Exception {
                String response=responseBody.string();
                return Observable.just(response);
            }
        }).flatMap(new Function<String, Observable<RouteStatusMVP.RouteStatusModel>>() {
            @Override
            public Observable<RouteStatusMVP.RouteStatusModel> apply(final String s) throws Exception {
                return Observable.combineLatest(new Observable<List<ArrayList<LatLng>>>() {
                    @Override
                    protected void subscribeActual(Observer<? super List<ArrayList<LatLng>>> observer) {
                        observer.onNext(getListOfLocations(s));
                    }
                }, new Observable<List<MapLatLngAddressModel>>() {
                    @Override
                    protected void subscribeActual(final Observer<? super List<MapLatLngAddressModel>> observer) {
                        final MapDirectionModel model = new Gson().fromJson(s, MapDirectionModel.class);
                        final GoogleApiClient googleApiClient=MyApplication.getInstance().getGoogleApiClient();
                        if(googleApiClient!=null) {
                           final List<MapLatLngAddressModel> mapLatLngAddressModels=new ArrayList<>();

                           Observable.fromIterable(model.getGeocodedWaypoints()).doOnNext(new Consumer<GeocodedWaypoint>() {
                               @Override
                               public void accept(GeocodedWaypoint route) throws Exception {
                                   if (

                                           route == model.getGeocodedWaypoints().get(model.getGeocodedWaypoints().size() - 1) ||
                                                   route == model.getGeocodedWaypoints().get(0)
                                           ) {

                                       Places.GeoDataApi.getPlaceById(googleApiClient, route.getPlaceId()).setResultCallback(new ResultCallbacks<PlaceBuffer>() {
                                           @Override
                                           public void onSuccess(@NonNull PlaceBuffer places) {

                                               final Place myPlace = places.get(0);
                                               LatLng queriedLocation = myPlace.getLatLng();
                                               Log.v("Latitude is", "" + queriedLocation.latitude);
                                               Log.v("Longitude is", "" + queriedLocation.longitude);
                                               mapLatLngAddressModels.add(new MapLatLngAddressModel(queriedLocation, myPlace.getAddress().toString()));

                                               places.release();
                                           }

                                           @Override
                                           public void onFailure(@NonNull Status status) {

                                           }

                                       });
                                   } else {
                                       Places.GeoDataApi.getPlaceById(googleApiClient, route.getPlaceId()).setResultCallback(new ResultCallbacks<PlaceBuffer>() {
                                           @Override
                                           public void onSuccess(@NonNull PlaceBuffer places) {

                                               final Place myPlace = places.get(0);
                                               LatLng queriedLocation = myPlace.getLatLng();
                                               Log.v("Latitude is", "" + queriedLocation.latitude);
                                               Log.v("Longitude is", "" + queriedLocation.longitude);
                                               mapLatLngAddressModels.add(new MapLatLngAddressModel(queriedLocation, myPlace.getAddress().toString()));

                                               places.release();
                                           }

                                           @Override
                                           public void onFailure(@NonNull Status status) {

                                           }

                                       });
                                   }
                               }
                           }).doOnComplete(new Action() {
                               @Override
                               public void run() throws Exception {
                                   observer.onNext(mapLatLngAddressModels);
                               }
                           }).subscribe();
                        }
                    }
                }, new BiFunction<List<ArrayList<LatLng>>, List<MapLatLngAddressModel>, RouteStatusMVP.RouteStatusModel>() {

                    @Override
                    public RouteStatusMVP.RouteStatusModel apply(List<ArrayList<LatLng>> navigation, List<MapLatLngAddressModel> allMarkers) throws Exception {
                        RouteStatusMVP.AllMapRelData data1=new RouteStatusMVP.AllMapRelData();
                        data1.allRouteNavigation=navigation;
                        data1.allMarkers=allMarkers;
                        routeStatusModel.navigation=data1;
                        return routeStatusModel;
                    }
                }).flatMap(new Function<RouteStatusMVP.RouteStatusModel, Observable<RouteStatusMVP.RouteStatusModel>>() {
                    @Override
                    public Observable<RouteStatusMVP.RouteStatusModel> apply(RouteStatusMVP.RouteStatusModel routeStatusModel) throws Exception {
                        return Observable.just(routeStatusModel);
                    }
                }).subscribeOn(Schedulers.io());
//                RouteStatusMVP.AllMapRelData mMapRelData=new RouteStatusMVP.AllMapRelData();
//                mMapRelData.allRouteNavigation = getListOfLocations(s);
//                routeStatusModel.navigation=mMapRelData;
//
//                return Observable.just(routeStatusModel);
            }
        }).subscribeOn(Schedulers.io());
    }


}
