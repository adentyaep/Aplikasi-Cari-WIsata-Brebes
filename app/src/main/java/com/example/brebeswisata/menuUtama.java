package com.example.brebeswisata;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.brebeswisata.adapter.WisataAdapter;
import com.example.brebeswisata.apiclient.APIClient;
import com.example.brebeswisata.apiclient.Wisata;
import com.example.brebeswisata.apiclient.WisataInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class menuUtama extends AppCompatActivity {
    DatabaseHelper db;
    Button btnLogout;
    TextView txtUser;
    ImageView profile;
    WisataInterface wisataInterface;
    RecyclerView recWisata;
    WisataAdapter adapter;
    EditText pencarian;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_utama);

        db = new DatabaseHelper(this);

//        btnLogout = (Button) findViewById(R.id.btnLogout);
        profile = (ImageView) findViewById(R.id.profile);
        wisataInterface = APIClient.getClient().create(WisataInterface.class);

        recWisata = findViewById(R.id.rec_wisata);
        recWisata.setHasFixedSize(true);
        recWisata.setLayoutManager(new LinearLayoutManager(this));

        pencarian = findViewById(R.id.edtCari);
        pencarian.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());

            }
        });



        Boolean checkSession = db.checkSession("ada");

        if (checkSession == false){
            Intent loginIntent = new Intent(menuUtama.this, login.class);
            startActivity(loginIntent);
            finish();
        }

        getAllWisata();


        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean updtSession = db.upgradeSession("kosong", 1);
                if(updtSession == true){
                    Toast.makeText(getApplicationContext(),"Berhasil Keluar", Toast.LENGTH_SHORT).show();
                    Intent loginIntent =  new Intent(menuUtama.this, login.class);
                    startActivity(loginIntent);
                    finish();
                }

            }
        });
    }




    private void getAllWisata(){
        Call<List<Wisata>> getWisata = wisataInterface.getWisata();
        getWisata.enqueue(new Callback<List<Wisata>>() {
            @Override
            public void onResponse(Call<List<Wisata>> call, Response<List<Wisata>> response) {
                ArrayList<Wisata> listWisata = (ArrayList<Wisata>) response.body();
                Log.d("list_wisata: ", response.raw().body().toString());
                Log.d("list_wisata: ", listWisata.toString());

                adapter = new WisataAdapter(listWisata);
                recWisata.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<List<Wisata>> call, Throwable t) {
                Log.d("error_wisata: ", t.getMessage());

            }
        });
    }

    private void filter(String text){
        Call<List<Wisata>> getWisata = wisataInterface.getWisata();
        ArrayList<Wisata> filterWisata = new ArrayList<>();
        getWisata.enqueue(new Callback<List<Wisata>>() {
            @Override
            public void onResponse(Call<List<Wisata>> call, Response<List<Wisata>> response) {
                ArrayList<Wisata> listWisata = (ArrayList<Wisata>) response.body();
                Log.d("list_wisata: ", response.raw().body().toString());
                Log.d("list_wisata: ", listWisata.toString());

                adapter = new WisataAdapter(listWisata);
                recWisata.setAdapter(adapter);

                for (Wisata item : listWisata){
                    if (item.getNama_wisata().toLowerCase().contains(text.toLowerCase())){
                        filterWisata.add(item);
                    }
                }

                adapter.filter(filterWisata);

            }

            @Override
            public void onFailure(Call<List<Wisata>> call, Throwable t) {
                Log.d("error_wisata: ", t.getMessage());

            }

        });



}
}