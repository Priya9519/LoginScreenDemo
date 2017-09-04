package com.example.priya.loginscreendemo.backend;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Created by priya on 25/8/17.
 */

public interface SendDataInterface {
    @FormUrlEncoded
    @POST("auth/login")
    Call<ResponseBody>columnInfo(@Header("X-APP-TOKEN") String header,
                                 @Field("username") String username,
                                 @Field("password") String password,
                                 @Field("device_type") String device_type,
                                 @Field("device_token") String device_token

                                 );

}
