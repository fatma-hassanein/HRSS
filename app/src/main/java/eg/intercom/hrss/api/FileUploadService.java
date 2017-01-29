package eg.intercom.hrss.api;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Url;

/**
 * Created by Hager.Magdy on 10/13/2016.
 */
public interface FileUploadService {

    // previous code for single file uploads
    @Multipart
    @POST("upload")
    Call<ResponseBody> uploadFile(
            @Part("description") RequestBody description,
            @Part MultipartBody.Part file);

    // new code for multiple files
    @Multipart
    @POST("upload")
    Call<ResponseBody> uploadMultipleFiles(
            @Url String url,
            @Part("description") RequestBody description,
            @Part MultipartBody.Part file1,
            @Part MultipartBody.Part file2);

}
