package com.example.rakapermanaputra.datamahasiswa.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rakapermanaputra.datamahasiswa.R;
import com.example.rakapermanaputra.datamahasiswa.activity.ReviewActivity;
import com.example.rakapermanaputra.datamahasiswa.model.Data;
import com.squareup.picasso.Picasso;

import java.util.List;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {

    private List<Data> dataList;
    private Context context;

    public DataAdapter(List<Data> data, Context context) {
        this.dataList = data;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final Data data = dataList.get(position);

        holder.textId.setText(data.getId());
        holder.textNama.setText(data.getNama());
        holder.textNpm.setText(data.getNpm());
        holder.textKelas.setText(data.getKelas());

        Picasso.get()
                .load(data.getFoto())
                .resize(300,300)
                .into(holder.foto);

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ReviewActivity.class);
                intent.putExtra("id", dataList.get(position).getId());
                intent.putExtra("id", dataList.get(position).getId());
                intent.putExtra("nama", dataList.get(position).getNama());
                intent.putExtra("npm", dataList.get(position).getNpm());
                intent.putExtra("kelas", dataList.get(position).getKelas());
                intent.putExtra("foto", dataList.get(position).getFoto());
                intent.putExtra("email", dataList.get(position).getEmail());
                intent.putExtra("uts", dataList.get(position).getUts());
                intent.putExtra("uas", dataList.get(position).getUas());
                intent.putExtra("keterangan", dataList.get(position).getKeterangan());

                Toast.makeText(context,"Masuk " + data.getNama(), Toast.LENGTH_SHORT).show();
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView textId, textNama, textNpm, textKelas;
        TextView textEmail, textUts, textUas, textKeterangan;
        ImageView foto;
        LinearLayout linearLayout;

        public ViewHolder(View itemView) {
            super(itemView);

            textId = itemView.findViewById(R.id.textViewId);
            textNama = itemView.findViewById(R.id.textViewNama);
            textNpm = itemView.findViewById(R.id.textViewNpm);
            textKelas = itemView.findViewById(R.id.textViewKelas);

            textEmail = itemView.findViewById(R.id.textReviewEmail);
            textUts = itemView.findViewById(R.id.textReviewUts);
            textUas = itemView.findViewById(R.id.textReviewUas);
            textKeterangan = itemView.findViewById(R.id.textReviewKeterangan);

            linearLayout = itemView.findViewById(R.id.linearLayout);

            foto = itemView.findViewById(R.id.imgProfile);
        }
    }
}
