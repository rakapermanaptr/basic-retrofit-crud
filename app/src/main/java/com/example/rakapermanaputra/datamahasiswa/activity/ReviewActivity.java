package com.example.rakapermanaputra.datamahasiswa.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rakapermanaputra.datamahasiswa.R;
import com.example.rakapermanaputra.datamahasiswa.model.Data;
import com.example.rakapermanaputra.datamahasiswa.network.ApiService;
import com.example.rakapermanaputra.datamahasiswa.network.RetrofitClientInstance;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReviewActivity extends AppCompatActivity {

    private List<Data> dataList;
    private Button btnUpdate, btnDelete;
    private TextView textNama, textNpm, textKelas, textEmail;
    private TextView textId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        textId = (TextView) findViewById(R.id.textReviewId);
        textNama = (TextView) findViewById(R.id.textReviewNama);
        textNpm = (TextView) findViewById(R.id.textReviewNpm);
        textKelas = (TextView) findViewById(R.id.textReviewKelas);
        textEmail = (TextView) findViewById(R.id.textReviewEmail);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        btnDelete = (Button) findViewById(R.id.btnDelete);



        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ReviewActivity.this, UpdateActivity.class);

                intent.putExtra("nama",textNama.getText().toString());
                intent.putExtra("npm",textNpm.getText().toString());
                intent.putExtra("kelas",textKelas.getText().toString());
                intent.putExtra("email",textEmail.getText().toString());

                intent.putExtra("id",textId.getText().toString());

                startActivity(intent);
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteData();

                Toast.makeText(ReviewActivity.this, "Deleted !", Toast.LENGTH_SHORT).show();
            }
        });

        getIncomingIntent();

    }

    private void getIncomingIntent(){
        if (getIntent().hasExtra("nama")){
            String id = getIntent().getStringExtra("id");
            String nama = getIntent().getStringExtra("nama");
            String npm = getIntent().getStringExtra("npm");
            String kelas = getIntent().getStringExtra("kelas");
            String foto = getIntent().getStringExtra("foto");
            String email = getIntent().getStringExtra("email");
            int uts = getIntent().getIntExtra("uts",0);
            int uas = getIntent().getIntExtra("uas",0);
            String keterangan = getIntent().getStringExtra("keterangan");

            setData(id, nama, npm, kelas, foto, email, uts, uas, keterangan);

        }
    }

    private void setData(String id, String nama, String npm, String kelas, String foto, String email,
                         int uts, int uas, String keterangan) {
        TextView textId = findViewById(R.id.textReviewId);
        textId.setText(id);

        TextView textNama = findViewById(R.id.textReviewNama);
        textNama.setText(nama);

        TextView textNpm = findViewById(R.id.textReviewNpm);
        textNpm.setText(npm);

        TextView textKelas = findViewById(R.id.textReviewKelas);
        textKelas.setText(kelas);

        TextView textEmail = findViewById(R.id.textReviewEmail);
        textEmail.setText(email);

        TextView textUts = findViewById(R.id.textReviewUts);
        textUts.setText("UTS : "+uts);

        TextView textUas = findViewById(R.id.textReviewUas);
        textUas.setText("UAS : " +uas);

        TextView textKeterangan = findViewById(R.id.textReviewKeterangan);
        textKeterangan.setText(keterangan);

        ImageView fotoProfile = findViewById(R.id.imageReviewProfile);
        Picasso.get()
                .load(foto)
                .resize(200,200)
                .into(fotoProfile);
    }

    private void deleteData() {
        ApiService service = RetrofitClientInstance.retrofit().create(ApiService.class);

        Call<Data> call = service.deleteData(textId.getText().toString());
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
