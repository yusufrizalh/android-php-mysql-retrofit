package id.inixindo.androidjavaretrofit.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import id.inixindo.androidjavaretrofit.R;
import id.inixindo.androidjavaretrofit.apis.ApiRequestData;
import id.inixindo.androidjavaretrofit.apis.RetrofitServer;
import id.inixindo.androidjavaretrofit.models.ModelResponseUsers;
import id.inixindo.androidjavaretrofit.models.ModelUsers;
import id.inixindo.androidjavaretrofit.utils.Config;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText editTextEmail, editTextPassword;
    private Button btnLogin;

    private int id;
    private String JSON_STRING;
    private List<ModelUsers> usersList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        btnLogin = findViewById(R.id.btnLogin);

        usersList = new ArrayList<>();

        btnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnLogin:
                if (editTextEmail.getText().toString().length() == 0) {
                    Toast.makeText(this, "Email address cannot be blank!", Toast.LENGTH_SHORT).show();
                    editTextEmail.setError("Email address cannot be blank!");
                    return;
                } else if (!Patterns.EMAIL_ADDRESS.matcher(editTextEmail.getText().toString()).matches()) {
                    Toast.makeText(this, "Invalid email address!", Toast.LENGTH_SHORT).show();
                    editTextEmail.setError("Invalid email address!");
                } else if (editTextPassword.getText().toString().length() == 0) {
                    Toast.makeText(this, "Password cannot be blank!", Toast.LENGTH_SHORT).show();
                    editTextPassword.setError("Password cannot be blank!");
                    return;
                } else {
                    showUser();
                }
                break;
        }
    }

    private void showUser() {
        final ProgressDialog progressDialog;
        progressDialog = new ProgressDialog(LoginActivity.this);
        progressDialog.setTitle("Login");
        progressDialog.setMessage("Please wait.....");
        progressDialog.show();

        ApiRequestData apiRequestData = RetrofitServer.connectRetrofit().create(ApiRequestData.class);

        Call<ModelResponseUsers> responseUsersCall = apiRequestData.getAllUsers();

        responseUsersCall.enqueue(new Callback<ModelResponseUsers>() {
            @Override
            public void onResponse(Call<ModelResponseUsers> call, Response<ModelResponseUsers> response) {
                if (response.isSuccessful()) {
                    usersList = response.body().getData();
                    Boolean loginSuccess = false;

                    for (int i = 0; i < usersList.size(); i++) {
                        if (editTextEmail.getText().toString().equals(usersList.get(i).getEmail()) &&
                                editTextPassword.getText().toString().equals(usersList.get(i).getPassword())) {
                            id = usersList.get(i).getId();
                            loginSuccess = true;
                        }
                    }

                    if (loginSuccess) {
                        // Intent intent = new Intent(getApplicationContext(), WelcomeActivity.class);
                        Intent intent = new Intent(getApplicationContext(), OnBoardActivity.class);
                        intent.putExtra(Config.TAG_ID, id);
                        startActivity(intent);
                        finish();
                        Toast.makeText(LoginActivity.this, "Success to Login!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(LoginActivity.this, "Invalid Email or Password!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(LoginActivity.this, "Something Wrong!", Toast.LENGTH_SHORT).show();
                }

                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<ModelResponseUsers> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Failed connect to server!", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });
    }

}