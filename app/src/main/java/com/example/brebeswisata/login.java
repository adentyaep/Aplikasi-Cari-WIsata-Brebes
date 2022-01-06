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

public class login extends AppCompatActivity {

    TextView daftar;
    Button btnlogin;
    EditText username, password;
    DatabaseHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        daftar = (TextView) findViewById(R.id.daftar);
        btnlogin = (Button) findViewById(R.id.btnLogin);
        username = (EditText) findViewById(R.id.username);
        password = (EditText)findViewById(R.id.password);
        db = new DatabaseHelper(this);




        daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(login.this, daftar.class));
            }
        });

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });

        cek_form(username);
        cek_form(password);


    }

    void login(){
        if(username.getText().length()<1){
            username.setBackgroundResource(R.drawable.form_error);
        }

        if(password.getText().length()<1){
            password.setBackgroundResource(R.drawable.form_error);
        }

        String strUsername = username.getText().toString();
        String strPassword = password.getText().toString();

        Boolean login = db.checkLogin(strUsername, strPassword);
        if (login == true){
            Boolean updateSession = db.upgradeSession("ada", 1);
            if (updateSession == true){
                Toast.makeText(getApplicationContext(),"Berhasil Login", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(com.example.brebeswisata.login.this, menuUtama.class);
                startActivity(intent);
                finish();
            }
        }else{
            Toast.makeText(getApplicationContext(),"Login Gagal",Toast.LENGTH_SHORT).show();
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