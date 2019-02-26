package com.example.furkan.ik2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseSearchAdd extends AppCompatActivity {

//Bu modulü projemde ekleyeceğim
    EditText editText_ad_soyad,editText_departman,editText_telefon,editText_email,editText_isegiris;
    Button buttonAdd;
    Spinner spinnerGender;
    DatabaseReference databaseUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_search_add);

        databaseUser = FirebaseDatabase.getInstance().getReference("Users");

        editText_ad_soyad=(EditText)findViewById(R.id.ad_fs);
        editText_departman=(EditText)findViewById(R.id.departman_fs);
        editText_telefon=(EditText)findViewById(R.id.telefon_fs);
        editText_email=(EditText)findViewById(R.id.email_fs);
        editText_isegiris=(EditText)findViewById(R.id.isegiris_fs);

        buttonAdd=(Button)findViewById(R.id.button_add_fs);
        spinnerGender=(Spinner)findViewById(R.id.spinner_fs);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            addUser();

            }
        });
    }
    private void addUser(){
        String name=editText_ad_soyad.getText().toString().trim();
        String dep=editText_departman.getText().toString().trim();
        String tel=editText_telefon.getText().toString().trim();
        String emi=editText_email.getText().toString().trim();
        String isgiristarih=editText_isegiris.getText().toString().trim();

        String image=spinnerGender.getSelectedItem().toString();

        if(image.equals("Kadın")){
            image="https://firebasestorage.googleapis.com/v0/b/ikprojem-e2870.appspot.com/o/person-female.png?alt=media&token=ff691915-f88f-43e6-bd13-b08c2ab4cbcc";
        }
        else if(image.equals("Erkek")){
            image="https://firebasestorage.googleapis.com/v0/b/ikprojem-e2870.appspot.com/o/person-male.png?alt=media&token=3dd572aa-8745-445f-b749-93c865ef9438";
        }


        if(!TextUtils.isEmpty(name)){

            String id =databaseUser.push().getKey();

            Users users = new Users(name,dep,emi,image,isgiristarih,tel);
            databaseUser.child(id).setValue(users);

            Toast.makeText(FirebaseSearchAdd.this,"Ekeleme işlemi başarili",Toast.LENGTH_SHORT).show();

        }else{
            Toast.makeText(FirebaseSearchAdd.this,"Doldurmadığınız alan var!!!",Toast.LENGTH_SHORT).show();
        }


    }

}
