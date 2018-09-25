package itg8.com.busdriverapp.common;

import org.json.JSONObject;

import io.reactivex.Observable;
import itg8.com.busdriverapp.login.LoginModel;
import itg8.com.busdriverapp.notification.model.NotificationModel;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface RetroController {

    @POST("WS.ashx?op=UserLogin")
    Observable<ResponseBody> checkLogin(@Body LoginModel object);

    Observable<ResponseBody> checkOtp();

    @POST("WS.ashx?op=GetRequestToken")
    Observable<ResponseBody> getRequestToken(@Body JSONObject object);


    @GET
    Observable<ResponseBody> downloadMapRoute(@Url String s, @Query("origin") String source,@Query("destination") String destination,@Query("waypoints") StringBuilder addresLines, @Query("key") String key,@Query("mode") String mode);

    @POST("WS.ashx?op=NotificationMSG")
    Observable<ResponseBody> mapNotificationToLog(@Body NotificationModel model);


    @POST("WS.ashx?op=AllCheckPointForDriver")
    Observable<ResponseBody> getRoute(@Body JSONObject object);

    @FormUrlEncoded
    @POST
    Observable<ResponseBody> sendToken(@Url String url, @Field("Token") String token);

}
