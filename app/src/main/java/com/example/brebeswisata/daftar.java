package com.example.brebeswisata;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class daftar extends AppCompatActivity {

    TextView txtlogin;
    Button daftar;
    EditText edtUsername, edtEmail, edtPassword, edtNama;
    DatabaseHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar);

        txtlogin = (TextView) findViewById(R.id.txtlogin);
        daftar = (Button) findViewById(R.id.btndaftar);
        edtUsername = (EditText) findViewById(R.id.edtUsername);
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtPassword = (EditText) findViewById(R.id.edtPassword);
        edtNama = (EditText) findViewById(R.id.edtNama);
        db = new DatabaseHelper(this);

        txtlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(daftar.this, login.class));
            }
        });

        daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                daftar();
            }
        });

        cek_form(edtNama);
        cek_form(edtUsername);
        cek_form(edtEmail);
        cek_form(edtPassword);
    }

    void daftar(){
        if(edtUsername.getText().length()<1){
            edtUsername.setBackgroundResource(R.drawable.form_error);
        }

        if(edtPassword.getText().length()<1){
            edtPassword.setBackgroundResource(R.drawable.form_error);
        }

        if(edtEmail.getText().length()<1){
            edtEmail.setBackgroundResource(R.drawable.form_error);
        }

        if(edtNama.getText().length()<1){
            edtNama.setBackgroundResource(R.drawable.form_error);
        }

        String strUsername = edtUsername.getText().toString();
        String strPassword = edtPassword.getText().toString();
        String strEmail = edtEmail.getText().toString();
        String strNama = edtNama.getText().toString();

        Boolean daftar = db.insertUser(strNama, strUsername, strEmail, strPassword);
        if (daftar == true){
            Toast.makeText(getApplicationContext(),"Berhasil Daftar", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(daftar.this, menuUtama.class);
            startActivity(intent);
            finish();
        }else{
            Toast.makeText(getApplicationContext(),"Daftar Gagal",Toast.LENGTH_SHORT).show();
        }

    }

    void cek_form(EditText editText){
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int count) {
                if(count<1){
                    editText.setBackgroundResource(R.drawable.form_error);
                }else {
                    editText.setBackgroundResource(R.drawable.form);
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}