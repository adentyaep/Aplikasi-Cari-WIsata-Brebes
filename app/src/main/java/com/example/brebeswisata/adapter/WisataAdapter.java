package com.example.brebeswisata.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.brebeswisata.R;
import com.example.brebeswisata.apiclient.Wisata;
import com.example.brebeswisata.detail;

import java.util.ArrayList;

public class WisataAdapter extends RecyclerView.Adapter<WisataAdapter.ViewHolder> {

    private ArrayList<Wisata> listWisata;
    private Context context;
    private String pathImage;


    public WisataAdapter(ArrayList<Wisata> listWisata){
        this.listWisata = listWisata;
    }

    @NonNull
    @Override
    public WisataAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        ViewHolder holder = new ViewHolder(inflater.inflate(R.layout.wisata, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull WisataAdapter.ViewHolder holder, int position) {
        Wisata wisata = listWisata.get(position);
        holder.nama_wisata.setText(wisata.getNama_wisata());
        //holder.deskripsi.setText(wisata.getDeskripsi());
        holder.alamat.setText(wisata.getAlamat());
        holder.harga_tiket.setText("Tiket Masuk : Rp. " + wisata.getHarga_tiket());

        pathImage = wisata.getGambar();
        Glide.with(holder.itemView.getContext())
                .load(pathImage)
                .apply(new RequestOptions().fitCenter())
                .into(holder.gambar);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, detail.class);
                intent.putExtra("nama_wisata", wisata.getNama_wisata());
                intent.putExtra("deskripsi", wisata.getDeskripsi());
                intent.putExtra("harga", wisata.getHarga_tiket());
                intent.putExtra("gambar", wisata.getGambar());
                intent.putExtra("latitut", wisata.getLatitut());
                intent.putExtra("longitut", wisata.getLongitut());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listWisata.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView nama_wisata, deskripsi, alamat, harga_tiket;
        public ImageView gambar;

        public ViewHolder(View itemView){
            super(itemView);
            nama_wisata = itemView.findViewById(R.id.nama_wisata);
            //deskripsi = itemView.findViewById(R.id.deskripsi);
            alamat = itemView.findViewById(R.id.alamat);
            harga_tiket = itemView.findViewById(R.id.harga_tiket);
            gambar = itemView.findViewById(R.id.gambar);
        }
    }

    public void filter(ArrayList<Wisata> filterWisata){


        listWisata = filterWisata;
        notifyDataSetChanged();

    }


}
