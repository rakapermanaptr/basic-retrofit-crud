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

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostActivity extends AppCompatActivity {
    private List<Data> dataList;
    private Button btnPost;
    private EditText textNama, textNpm, textKelas, textEmail;

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        textNama = (EditText) findViewById(R.id.edtPostNama);
        textNpm = (EditText) findViewById(R.id.edtPostNpm);
        textKelas = (EditText) findViewById(R.id.edtPostKelas);
        textEmail = (EditText) findViewById(R.id.edtPostEmail);

        btnPost = (Button) findViewById(R.id.btnPost);
        btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postData();
                textNama.setText("");
                textNpm.setText("");
                textKelas.setText("");
                textEmail.setText("");
                Toast.makeText(PostActivity.this,"Posted !", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void postData() {

        ApiService service = RetrofitClientInstance.retrofit().create(ApiService.class);
        Call<Data> call = service.postData(textNama.getText().toString(), textNpm.getText().toString(),
                textKelas.getText().toString(), textEmail.getText().toString());
        call.enqueue(new Callback<Data>() {
            @Override
            public void onResponse(Call<Data> call, Response<Data> response) {

            }

            @Override
            public void onFailure(Call<Data> call, Throwable t) {

            }
        });
    }

}
