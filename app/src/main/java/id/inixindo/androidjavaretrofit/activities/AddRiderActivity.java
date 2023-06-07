package id.inixindo.androidjavaretrofit.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import id.inixindo.androidjavaretrofit.R;
import id.inixindo.androidjavaretrofit.apis.ApiRequestData;
import id.inixindo.androidjavaretrofit.apis.RetrofitServer;
import id.inixindo.androidjavaretrofit.models.ModelResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddRiderActivity extends AppCompatActivity {
    private EditText editTextNama, editTextNomor, editTextSponsor, editTextNegara;
    private Button btnSave;
    private String nama, nomor, sponsor, negara;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_rider);

        // actionbar
        Toolbar toolbarAddRiderActivity = findViewById(R.id.toolbarAddRiderActivity);
        toolbarAddRiderActivity.setTitle("Add New Rider");
        toolbarAddRiderActivity.setTitleTextColor(Color.WHITE);
        toolbarAddRiderActivity.setNavigationIcon(getResources().getDrawable(R.drawable.ic_back_arrow));
        toolbarAddRiderActivity.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        setSupportActionBar(toolbarAddRiderActivity);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initView();

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nama = editTextNama.getText().toString();
                nomor = editTextNomor.getText().toString();
                sponsor = editTextSponsor.getText().toString();
                negara = editTextNegara.getText().toString();
                // cek tidak boleh kosong
                if (nama.trim().equals("")) {
                    editTextNama.setError("Nama Rider tidak boleh kosong!");
                } else if (nomor.trim().equals("")) {
                    editTextNomor.setError("Nomor Rider tidak boleh kosong!");
                } else if (sponsor.trim().equals("")) {
                    editTextSponsor.setError("Sponsor Rider tidak boleh kosong!");
                } else if (negara.trim().equals("")) {
                    editTextNegara.setError("Negara Rider tidak boleh kosong!");
                } else {
                    createNewRider();
                }
            }
        });
    }

    private void initView() {
        editTextNama = findViewById(R.id.editTextNama);
        editTextNomor = findViewById(R.id.editTextNomor);
        editTextSponsor = findViewById(R.id.editTextSponsor);
        editTextNegara = findViewById(R.id.editTextNegara);
        btnSave = findViewById(R.id.btnSave);
    }

    private void createNewRider() {
        ApiRequestData apiRequestData = RetrofitServer.connectRetrofit().create(ApiRequestData.class);
        Call<ModelResponse> responseCall = apiRequestData.insertData(nama, nomor, sponsor, negara);
        responseCall.enqueue(new Callback<ModelResponse>() {
            @Override
            public void onResponse(Call<ModelResponse> call, Response<ModelResponse> response) {
                int code = response.body().getCode();
                String message = response.body().getMessage();

                if (code == 1) {
                    Toast.makeText(AddRiderActivity.this, message, Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(AddRiderActivity.this, message, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ModelResponse> call, Throwable t) {
                Toast.makeText(AddRiderActivity.this, "Failed connect to server!", Toast.LENGTH_SHORT).show();
            }
        });

        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent back = new Intent(AddRiderActivity.this, MainActivity.class);
        back.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        back.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return true;
    }
}
