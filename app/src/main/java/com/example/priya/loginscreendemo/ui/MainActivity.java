package com.example.priya.loginscreendemo.ui;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.priya.loginscreendemo.R;
import com.example.priya.loginscreendemo.listeners.ApiListeners;
import com.example.priya.loginscreendemo.requests.RequestApi;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,ApiListeners{
    Button btnLogin;
    EditText etUserName, etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        btnLogin = (Button) findViewById(R.id.btn_login);
        etUserName = (EditText) findViewById(R.id.et_username);
        etPassword = (EditText) findViewById(R.id.et_password);
        btnLogin.setOnClickListener(this);

    }
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo!= null && activeNetworkInfo.isConnected();
    }

    private boolean validateLoginDetails() {
        if (TextUtils.isEmpty(etUserName.getText().toString())) {
            etUserName.setError("Please Enter Your UserName");
        } else if (TextUtils.isEmpty(etPassword.getText().toString())) {
            etPassword.setError("Please Enter Your Password");
        } else if (etPassword.getText().toString().length() < 6)
            etPassword.setError("Password must be of atleast");
        else
            return true;

        return true;
    }
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_login) {
            Log.e("Clicked","Clicked");
            v.setBackgroundColor(getColor(R.color.colorAccent));
            if (validateLoginDetails()) {
                if (isNetworkAvailable()) {
                    Toast.makeText(this, "Connected", Toast.LENGTH_SHORT).show();
                    RequestApi.getInstance().requestApi(etUserName.getText().toString(), etPassword.getText().toString(),this);
                } else
                    Toast.makeText(this, "Fails to Connect", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onSuccess(String success) {
        Toast.makeText(this,success, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError(String errorMessage) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();

    }
}
