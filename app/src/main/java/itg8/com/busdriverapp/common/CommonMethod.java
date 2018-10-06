package itg8.com.busdriverapp.common;

import android.location.Address;
import android.location.Geocoder;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;
import com.google.gson.Gson;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import itg8.com.busdriverapp.home.HomeActivity;
import itg8.com.busdriverapp.home.model.Checkpoint;
import itg8.com.busdriverapp.home.model.CheckpointData;
import itg8.com.busdriverapp.map.Leg;
import itg8.com.busdriverapp.map.MapDirectionModel;
import itg8.com.busdriverapp.map.MapLatLngAddressModel;
import itg8.com.busdriverapp.map.Route;
import itg8.com.busdriverapp.map.Step;

/**
 * Created by swapnilmeshram on 15/03/18.
 */

public class CommonMethod {
    public static final String TYPE_DATA = "commonMethodUserType";
    public static final String MARKER_CLICKED = "0";
    public static final String ROLE = "ROLE";
    public static final String ROLE_USER = "ROLE_USER";

    private static final String TAG = "CommonMethod";

    public static final String TOKEN = "TOKEN";
//    public static final String BASE_URL = "http://sdkfjskdjfkjsdkjfjskdjfkjsk";
//    public static final String BASE_URL = "http://192.168.1.54:8088";
    public static final String NOTIFICATION_COUNT = "NOTIFICATION_COUNT";
    public static final int NOT_READ = 0;
    public static final String BASE_URL = "http://dev.steponebeacon.com/";
//    public static final String BASE_URL = "http://13.71.4.162:8082/";

//    public static final String BASE_URL = "http://103.229.24.249:8082/";


    public static final String ADDRESS_1="21.1170227, 79.0468798";
    public static final String ADDRESS_2="Jugal Tea Stall, 9, Gayatri Nagar Rd, Lokhande Nagar, Parsodi, Subhash Nagar, Trimurtee Nagar, Nagpur, Maharashtra 440022";
    public static final String ADDRESS_3="Aayushi Paratha Center, LGF/25, S Ambazari Rd, Gopal Nagar, Gayatri Nagar, Pratap Nagar, Nagpur, Maharashtra 440022";
    public static final String ADDRESS_4="Mate Chowk, Pratap Nagar, Nagpur, Maharashtra";
    public static final String ADDRESS_5="Durga Mandir, Pratap Nagar Main Rd, Gopal Nagar, Pratap Nagar, Nagpur, Maharashtra 440022";
    public static final String ADDRESS_6="CHAUDHARI LIFESTYLE, 69, Pratap Nagar Main Rd, Atrey Layout, Kotwal Nagar, Pratap Nagar, Nagpur, Maharashtra 440022";
    public static final String ADDRESS_7="Tatya Tope Nagar, Pratap Nagar, Nagpur, Maharashtra";
    public static final String ADDRESS_8="Dev Nager Hanuman Temple, 999, Khamla Rd, Opp. M.S. Co-Operative Bank, Vivekanand Nagar, Nagpur, Maharashtra 440015";
    public static final String GRANT_TYPE = "password";
    public static final String IS_LOGIN = "LoginAvail";
    public static final String NOTIFICATION_MSG = "NotificationMessage";
    public static final String NOTIFICATION_MODEL = "MyNotificationModel";
    public static final String DND = "DoNotDisturb";
    public static final String ACTION_START_STATIC_NOTIFICATION = getPackageName()+"_ACTION_START_STATIC_NOTIFICATION";
    public static String CHILD_CLICKED="1";

    public static SimpleDateFormat formatServerSend = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.getDefault());

    public static String getPackageName(){
        return MyApplication.getInstance().getPackageName();
    }

    public static String[] getAddresses(){
        return new String[]{ADDRESS_2,ADDRESS_3,ADDRESS_4,ADDRESS_5,ADDRESS_6,ADDRESS_7};
    }
    public static String[] getAddresses(CheckpointData model){
        List<Checkpoint> checkpoints= (List<Checkpoint>) model.getCheckpoints().getCheckpoint();
        String[] strings=new String[checkpoints.size()];
        for (int i=0; i<checkpoints.size(); i++){
            strings[i]=checkpoints.get(i).getLatitude()+","+checkpoints.get(i).getLongitude();
        }
        return strings;
    }


    public static String getApPmTime(Calendar c){
        SimpleDateFormat dateFormat=new SimpleDateFormat("hh:mm a",Locale.getDefault());
        return dateFormat.format(c.getTime());
    }


    public static void getLatLngFromAddress(final HomeActivity.MarkerAvailableListener listener, String address){
        Observable.just(address)
                .flatMap(new Function<String, Observable<MapLatLngAddressModel>>() {
                    @Override
                    public Observable<MapLatLngAddressModel> apply(String s) throws Exception {
                        return Observable.just(s).map(new Function<String, MapLatLngAddressModel>() {
                            @Override
                            public MapLatLngAddressModel apply(String s) throws Exception {
                                return getLocationFromAddress(s);
                            }
                        }).subscribeOn(Schedulers.io());
                    }
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MapLatLngAddressModel>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(MapLatLngAddressModel latLng) {
                        listener.onLatlangAvail(latLng);
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                        listener.onListComplete();
                    }
                });
    }

    public static MapLatLngAddressModel getLocationFromAddress(String strAddress){

        Geocoder coder = new Geocoder(MyApplication.getInstance().getApplicationContext());
        List<Address> address;

        try {
            address = coder.getFromLocationName(strAddress,5);
            if (address==null || address.size()<=0) {
                return null;
            }
            Address location=address.get(0);
            Log.i(TAG, "getLocationFromAddress: "+location.getLatitude()+" longitude: "+location.getLongitude());;
            location.getLongitude();

            LatLng latLng = new LatLng((double) (location.getLatitude()),
                    (double) (location.getLongitude()));

            return new MapLatLngAddressModel(latLng,strAddress);
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }


    public static List<ArrayList<LatLng>> getListOfLocations(String response){
        ArrayList<ArrayList<LatLng>> routes=new ArrayList<>();
        MapDirectionModel model=new Gson().fromJson(response,MapDirectionModel.class);
        if(model==null){
            throw new NullPointerException("Model is null");
        }

        for (Route ro :
                model.getRoutes()) {

            ArrayList<LatLng> path = new ArrayList<>();
            for (Leg leg :
                    ro.getLegs()) {
                for (Step step :
                        leg.getSteps()) {

                    ArrayList<LatLng> poly=decodePoly(step.getPolyline().getPoints());
                    path.addAll(poly);
                }
                routes.add(path);
            }
        }
        return routes;
    }

    /**
     * Method to decode polyline points
     * Courtesy : jeffreysambells.com/2010/05/27/decoding-polylines-from-google-maps-direction-api-with-java
     * */
    private static ArrayList<LatLng> decodePoly(String encoded) {

        ArrayList<LatLng> poly = new ArrayList<LatLng>();
        int index = 0, len = encoded.length();
        int lat = 0, lng = 0;

        while (index < len) {
            int b, shift = 0, result = 0;
            do {
                b = encoded.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlat = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lat += dlat;

            shift = 0;
            result = 0;
            do {
                b = encoded.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlng = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lng += dlng;

            LatLng p = new LatLng((((double) lat / 1E5)),
                    (((double) lng / 1E5)));
            poly.add(p);
        }

        return poly;
    }


    /** calculates the distance between two locations in MILES */
    public static double distance(double lat1, double lng1, double lat2, double lng2) {

        double earthRadius = 3958.75; // in miles, change to 6371 for kilometer output

        double dLat = Math.toRadians(lat2-lat1);
        double dLng = Math.toRadians(lng2-lng1);

        double sindLat = Math.sin(dLat / 2);
        double sindLng = Math.sin(dLng / 2);

        double a = Math.pow(sindLat, 2) + Math.pow(sindLng, 2)
                * Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2));

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));

        double dist = earthRadius * c;

        return dist; // output distance, in MILES
    }


    public static boolean isLogin() {
        return Prefs.contains(CommonMethod.TOKEN);
    }


    public static Date convertStringDate(String startDate) {

        Date date = null;
        try {
         date = formatServerSend.parse(startDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        return date;
    }

    public static String convertDateString(Date startDate) {

        String date = " ";

           date = formatServerSend.format(startDate);



        return date;
    }

    public static String getServerStringDateByDate(Date date){
        return formatServerSend.format(date);
    }

    public static String getDDMMMYYYYfromDate(Calendar c) {



        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd-MMM-yyyy",Locale.getDefault());
        return simpleDateFormat.format(c.getTime());
    }

    public static String getStringServerDateFromStringDateOnly(String oneDayDate) {
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd-MMM-yyyy",Locale.getDefault());
        Calendar calendar=Calendar.getInstance();
        try {
            calendar.setTime(simpleDateFormat.parse(oneDayDate));
            return getServerStringDateByDate(calendar.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }
}
