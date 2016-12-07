package me.maodev.gankioproject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by maoyu on 16/12/5.
 */

public class RetrofitInterface {

    public interface RequestServes{
        @GET("{type}/{number}/{page}")
        Call<ApiDate> getApiDate(@Path("type") String type,@Path("number") String number,@Path("page") int page);
    };
}
