package com.example.priya.loginscreendemo.requests;

import android.util.Log;

import com.example.priya.loginscreendemo.backend.ApiClient;
import com.example.priya.loginscreendemo.listeners.ApiListeners;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.priya.loginscreendemo.utility.AppContants.X_APP_TOKEN;
import static com.example.priya.loginscreendemo.utility.AppContants.device_token;
import static com.example.priya.loginscreendemo.utility.AppContants.device_type;

/**
 * Created by priya on 4/9/17.
 */

public class RequestApi {
    public static RequestApi getInstance(){
        return new RequestApi();
    }
    public  void requestApi(String username, String password, final ApiListeners listeners) {
        ApiClient.getApiService().columnInfo(X_APP_TOKEN, username, password, device_type, device_token).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody>response) {
              Log.e("status",response.body()+"");
                listeners.onSuccess("Login Successfully");

            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("Message", "Request Fails");
                listeners.onError("Failed to Processes Request");

            }
        });
    }
}
