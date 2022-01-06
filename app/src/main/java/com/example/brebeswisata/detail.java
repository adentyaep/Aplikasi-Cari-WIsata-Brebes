package com.example.brebeswisata;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class detail extends AppCompatActivity {
    TextView txtNama, txtDeskripsi, txtHarga;
    ImageView gambar;
    String nama_wista;
    String deskripsi;
    String harga;
    String pathImage;
    double latitut, longitut;
    Button btnLokasi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        txtNama = findViewById(R.id.txtNama_wisata);
        txtDeskripsi = findViewById(R.id.txtDeskripsi);
        txtHarga = findViewById(R.id.txtHarga);
        gambar = findViewById(R.id.gambarDetail);
        btnLokasi = findViewById(R.id.btnLokasi);

        Bundle extras = getIntent().getExtras();
        nama_wista = extras.getString("nama_wisata");
        deskripsi = extras.getString("deskripsi");
        harga = extras.getString("harga");

        txtNama.setText(nama_wista);
        txtDeskripsi.setText(deskripsi);
        txtHarga.setText("Tiket Masuk : Rp. "+harga);

        pathImage = extras.getString("gambar");
        Glide.with(gambar)
                .load(pathImage)
                .apply(new RequestOptions().fitCenter())
                .into(gambar);

        latitut = extras.getDouble("latitut");
        longitut = extras.getDouble("longitut");

        btnLokasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(detail.this, MapsActivity.class);
                intent.putExtra("latitut", latitut);
                intent.putExtra("longitut", longitut);
                intent.putExtra("nama_wisata", nama_wista);
                startActivity(intent);
            }
        });

    }
}