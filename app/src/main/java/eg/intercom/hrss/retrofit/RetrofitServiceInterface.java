package eg.intercom.hrss.retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import eg.intercom.hrss.api.ServerConfig;
import eg.intercom.hrss.helpers.Constants;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Url;

/**
 * Created by Hager.Magdy on 9/26/2016.
 */
public interface RetrofitServiceInterface {


    //here we use client to add dynamic header paramters
    //OkHttpClient.Builder  httpClient = new OkHttpClient.Builder();
    //OkHttpClient client = httpClient.build();

//    OkHttpClient client = Constants.httpClient.readTimeout(90000, TimeUnit.MILLISECONDS).connectTimeout(90000, TimeUnit.MILLISECONDS).build();
//    Gson gson = new GsonBuilder()
//            .setLenient()
//            .create();

    OkHttpClient client = Constants.httpClient.readTimeout(90000, TimeUnit.MILLISECONDS).connectTimeout(90000, TimeUnit.MILLISECONDS).build();
    Gson gson = new GsonBuilder()
            .setLenient()
            .create();

    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(ServerConfig.SERVER_URl)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();

//post methode to send parameters in body
    @FormUrlEncoded
    @POST
    Call<JsonObject> postMethode(@Url String url,

                                 @FieldMap Map<String, String> options);



    @GET
    Call<JsonObject> getMethode(@Url String url);



}
