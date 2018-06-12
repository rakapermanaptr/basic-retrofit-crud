package com.example.rakapermanaputra.datamahasiswa.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rakapermanaputra.datamahasiswa.R;
import com.example.rakapermanaputra.datamahasiswa.model.Data;
import com.example.rakapermanaputra.datamahasiswa.network.ApiService;
import com.example.rakapermanaputra.datamahasiswa.network.RetrofitClientInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateActivity extends AppCompatActivity {

    EditText textUpdateNama, textUpdateNpm, textUpdateKelas, textUpdateEmail, textId;
    Button btnUpdate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        textId = (EditText) findViewById(R.id.edtUpdateId);
        textUpdateNama = (EditText) findViewById(R.id.edtUpdateNama);
        textUpdateNpm = (EditText) findViewById(R.id.edtUpdateNpm);
        textUpdateKelas = (EditText) findViewById(R.id.edtUpdateKelas);
        textUpdateEmail = (EditText) findViewById(R.id.edtUpdateEmail);

        getIncomingIntent();

        
        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateData();
                textUpdateNama.setText("");
                textUpdateNpm.setText("");
                textUpdateKelas.setText("");
                textUpdateEmail.setText("");

                Toast.makeText(UpdateActivity.this, "Updated !", Toast.LENGTH_SHORT).show();
                
            }
        });

    }

    private void updateData() {

        ApiService service = RetrofitClientInstance.retrofit().create(ApiService.class);

        Call<Data> call = service.updateData(textId.getText().toString(),textUpdateNama.getText().toString(), textUpdateNpm.getText().toString(),
                textUpdateKelas.getText().toString(), textUpdateEmail.getText().toString());
        call.enqueue(new Callback<Data>() {
            @Override
            public void onResponse(Call<Data> call, Response<Data> response) {

            }

            @Override
            public void onFailure(Call<Data> call, Throwable t) {

            }
        });
    }


    private void getIncomingIntent(){
        if (getIntent().hasExtra("nama")) {
            String nama = getIntent().getStringExtra("nama");
            String npm = getIntent().getStringExtra("npm");
            String kelas = getIntent().getStringExtra("kelas");
            String email = getIntent().getStringExtra("email");
            String id = getIntent().getStringExtra("id");


            seData(nama,npm,kelas,email,id);
            Log.i("data", "npm : " + npm);
        }

    }

    private void seData(String nama, String npm, String kelas, String email, String id){
        EditText textNama = findViewById(R.id.edtUpdateNama);
        textNama.setText(nama);

        EditText textNpm = findViewById(R.id.edtUpdateNpm);
        textNpm.setText(npm);

        EditText textKelas = findViewById(R.id.edtUpdateKelas);
        textKelas.setText(kelas);

        EditText textEmail = findViewById(R.id.edtUpdateEmail);
        textEmail.setText(email);

        EditText textId = findViewById(R.id.edtUpdateId);
        textId.setText(id);

    }
}
