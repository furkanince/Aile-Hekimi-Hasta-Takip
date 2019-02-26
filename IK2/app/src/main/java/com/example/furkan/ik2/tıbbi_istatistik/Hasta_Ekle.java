package com.example.furkan.ik2.tıbbi_istatistik;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.furkan.ik2.FirebaseSearchAdd;
import com.example.furkan.ik2.R;
import com.example.furkan.ik2.Users;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class Hasta_Ekle extends AppCompatActivity {


    EditText editText_ad_soyad,editText_meslek,editText_ilac_baslama_tarihi,editText_ilac;
    Button buttonAdd;
    Spinner spinnerGender;
    DatabaseReference databaseUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hasta__ekle);

        databaseUser = FirebaseDatabase.getInstance().getReference("Hasta");

        editText_ad_soyad=(EditText)findViewById(R.id.ad_hasta);
        editText_meslek=(EditText)findViewById(R.id.meslek_hasta);
        editText_ilac=(EditText)findViewById(R.id.ilac_hasta);
        editText_ilac_baslama_tarihi=(EditText)findViewById(R.id.ilac_baslama_tarihi);

        buttonAdd=(Button)findViewById(R.id.button_add_hasta);
        spinnerGender=(Spinner)findViewById(R.id.spinner_hasta);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addUser();

            }
        });

    }
    private void addUser(){

        String name=editText_ad_soyad.getText().toString().trim();
        String dep=editText_meslek.getText().toString().trim();
        String ilac=editText_ilac.getText().toString().trim();
        String ilac_baslamatarih=editText_ilac_baslama_tarihi.getText().toString().trim();

        String image=spinnerGender.getSelectedItem().toString();

        if(image.equals("Kadın")){
            image="https://firebasestorage.googleapis.com/v0/b/ikprojem-e2870.appspot.com/o/female.png?alt=media&token=b9647827-67ed-442c-b92f-246c02b69aff";
        }
        else if(image.equals("Erkek")){
            image="https://firebasestorage.googleapis.com/v0/b/ikprojem-e2870.appspot.com/o/male.png?alt=media&token=04db5f2d-0ba6-4cad-95cf-c9130158528f";
        }


        if(!TextUtils.isEmpty(name)){

            String id =databaseUser.push().getKey();
            Hasta hasta=new Hasta(name,dep,ilac,ilac_baslamatarih,image);
            databaseUser.child(id).setValue(hasta);

            Toast.makeText(Hasta_Ekle.this,"Ekeleme işlemi başarili",Toast.LENGTH_SHORT).show();

        }else{
            Toast.makeText(Hasta_Ekle.this,"Doldurmadığınız alan var!!!",Toast.LENGTH_SHORT).show();
        }



    }
}
