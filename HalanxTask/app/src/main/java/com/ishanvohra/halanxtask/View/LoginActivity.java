package com.ishanvohra.halanxtask.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;
import com.ishanvohra.halanxtask.Model.LoginResponse;
import com.ishanvohra.halanxtask.R;
import com.ishanvohra.halanxtask.ViewModel.LoginViewModel;

public class LoginActivity extends AppCompatActivity {

    private LoginViewModel loginViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final TextInputEditText usernameEt = findViewById(R.id.login_username_et);
        final TextInputEditText passwordEt = findViewById(R.id.login_password_et);

        loginViewModel = new ViewModelProvider(LoginActivity.this).get(LoginViewModel.class);
        loginViewModel.init();

        Button loginBtn = findViewById(R.id.login_btn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginViewModel.login("o" + usernameEt.getText().toString(), passwordEt.getText().toString())
                        .observe(LoginActivity.this, new Observer<LoginResponse>() {
                            @Override
                            public void onChanged(LoginResponse loginResponse) {
                                if(loginResponse != null){
                                    SharedPreferences pref = getSharedPreferences("MyPref", MODE_PRIVATE);
                                    SharedPreferences.Editor editor = pref.edit();
                                    editor.putString("key", loginResponse.getKey());
                                    editor.putString("username", "o" + usernameEt.getText().toString());
                                    editor.putString("password", passwordEt.getText().toString());
                                    editor.apply();

                                    startActivity(new Intent(LoginActivity.this, ViewBillsActivity.class));
                                    finish();
                                }
                            }
                        });
            }
        });

    }
}