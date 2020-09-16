package com.ishanvohra.halanxtask.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.ishanvohra.halanxtask.Model.LoginResponse;
import com.ishanvohra.halanxtask.R;
import com.ishanvohra.halanxtask.ViewModel.LoginViewModel;

public class LoginActivity extends AppCompatActivity {

    private LoginViewModel loginViewModel;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final TextInputEditText usernameEt = findViewById(R.id.login_username_et);
        final TextInputEditText passwordEt = findViewById(R.id.login_password_et);
        progressBar = findViewById(R.id.progress_bar);

        loginViewModel = new ViewModelProvider(LoginActivity.this).get(LoginViewModel.class);
        loginViewModel.init();

        SharedPreferences pref = getSharedPreferences("MyPref", MODE_PRIVATE);

        if(pref.contains("username")){
            progressBar.setVisibility(View.VISIBLE);
            loginUser(pref.getString("username", ""), pref.getString("password", ""));
        }

        Button loginBtn = findViewById(R.id.login_btn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                loginUser(usernameEt.getText().toString(), passwordEt.getText().toString());
            }
        });
    }

    private void loginUser(String username, String password){
        loginViewModel.login("o" + username, password)
                .observe(LoginActivity.this, new Observer<LoginResponse>() {
                    @Override
                    public void onChanged(LoginResponse loginResponse) {
                        progressBar.setVisibility(View.GONE);
                        if(loginResponse != null){
                            SharedPreferences pref = getSharedPreferences("MyPref", MODE_PRIVATE);
                            SharedPreferences.Editor editor = pref.edit();
                            editor.putString("key", loginResponse.getKey());
                            editor.putString("username", username);
                            editor.putString("password", password);
                            editor.apply();

                            startActivity(new Intent(LoginActivity.this, ViewBillsActivity.class));
                            finish();
                        }
                    }
                });
    }
}