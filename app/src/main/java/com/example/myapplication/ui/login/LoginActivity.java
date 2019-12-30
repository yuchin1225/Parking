package com.example.myapplication.ui.login;

import android.Manifest;
import android.app.Activity;

import androidx.core.app.ActivityCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.ServiceActivity;


public class LoginActivity extends AppCompatActivity {

    LoginViewModel loginViewModel;
    EditText phone;
    EditText car;
    Button login;
    Button anonymous;
    ProgressBar loadingProgressBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        getlocation();
        chicknet();
    }

    private void getlocation(){
            if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED)
                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1);
                return;
    }

    private void chicknet(){
        ConnectivityManager CM = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifi = CM.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo gprs = CM.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            if (wifi != null && wifi.getState() == NetworkInfo.State.CONNECTED) {
                Toast.makeText(getApplicationContext(), "WIFI", Toast.LENGTH_LONG).show();
            } else if (gprs != null && gprs.getState() == NetworkInfo.State.CONNECTED) {
                Toast.makeText(getApplicationContext(), "GPRS", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getApplicationContext(), "NONE", Toast.LENGTH_LONG).show();
            }
    }


    private void initView(){
        phone = findViewById(R.id.ph);
        car = findViewById(R.id.car);
        login = findViewById(R.id.login);
        anonymous = findViewById(R.id.anonymous);
        loadingProgressBar = findViewById(R.id.loading);
        initData();
    }

    private  void initData(){
        loginViewModel = ViewModelProviders.of(this, new LoginViewModelFactory())
                .get(LoginViewModel.class);
        loginViewModel.getLoginFormState().observe(this, new Observer<LoginFormState>() {
            @Override
            public void onChanged(@Nullable LoginFormState loginFormState) {
                if (loginFormState == null) {
                    return;
                }
                login.setEnabled(loginFormState.isDataValid());
                if (loginFormState.getPhoneError() != null) {
                    phone.setError(getString(loginFormState.getPhoneError()));
                }
                if (loginFormState.getPasswordError() != null) {
                    car.setError(getString(loginFormState.getPasswordError()));
                }
            }
        });

        loginViewModel.getLoginResult().observe(this, new Observer<LoginResult>() {
            @Override
            public void onChanged(@Nullable LoginResult loginResult) {
                if (loginResult == null) {
                    return;
                }
                loadingProgressBar.setVisibility(View.GONE);
                if (loginResult.getError() != null) {
                    showLoginFailed(loginResult.getError());
                }
                if (loginResult.getSuccess() != null) {
                    updateUiWithUser(loginResult.getSuccess());
                }
                setResult(Activity.RESULT_OK);

                //Complete and destroy login activity once successful
                Intent login =new Intent();
                login.setClass(LoginActivity.this, ServiceActivity.class);
                startActivity(login);
                finish();
            }
        });

        TextWatcher afterTextChangedListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // ignore
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // ignore
            }

            @Override
            public void afterTextChanged(Editable s) {
                loginViewModel.loginDataChanged(phone.getText().toString(),
                        car.getText().toString());
            }
        };
        phone.addTextChangedListener(afterTextChangedListener);
        car.addTextChangedListener(afterTextChangedListener);
        car.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    loginViewModel.login(phone.getText().toString(),
                            car.getText().toString());
                }
                return false;
            }
        });
        login.setOnClickListener(loginOnClick);
        anonymous.setOnClickListener(anonyOnClick);
    }

    public View.OnClickListener loginOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            loadingProgressBar.setVisibility(View.VISIBLE);
            loginViewModel.login(phone.getText().toString(),
                    car.getText().toString());
        }
    };

    public View.OnClickListener anonyOnClick =new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            loadingProgressBar.setVisibility(View.VISIBLE);
            Intent anonymous = new Intent();
            anonymous.setClass(LoginActivity.this, ServiceActivity.class);
            startActivity(anonymous);
        }
    };

    private void updateUiWithUser(LoggedInUserView model) {
        String welcome = getString(R.string.welcome) + model.getDisplayName();
        // TODO : initiate successful logged in experience
        Toast.makeText(getApplicationContext(), welcome, Toast.LENGTH_LONG).show();
    }

    private void showLoginFailed(@StringRes Integer errorString) {
        Toast.makeText(getApplicationContext(), errorString, Toast.LENGTH_SHORT).show();
    }
}
