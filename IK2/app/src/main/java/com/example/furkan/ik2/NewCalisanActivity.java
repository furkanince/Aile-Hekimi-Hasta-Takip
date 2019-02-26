package com.example.furkan.ik2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class NewCalisanActivity extends AppCompatActivity {

    private EditText mAd,mSoyad,mDepartman;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_calisan);
        final String cinsiyet = "E";
        mAd=(EditText)findViewById(R.id.ad_edtTxt);
        mSoyad=(EditText)findViewById(R.id.soyad_edtTxt);
        mDepartman=(EditText)findViewById(R.id.departman_edtTxt);
        button=(Button)findViewById(R.id.calisan_add_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calisan calisan=new Calisan();
                calisan.setAd(mAd.getText().toString());
                calisan.setSoyad(mSoyad.getText().toString());
                calisan.setDepartman(mDepartman.getText().toString());
                new FirebaseDatabaseHelper2().addCalisan(calisan, new FirebaseDatabaseHelper2.DataStatus() {
                    @Override
                    public void DataIsLoaded(List<Calisan> calisans, List<String> keys) {

                    }

                    @Override
                    public void DataIsInserted() {
                        Toast.makeText(NewCalisanActivity.this,"Calisan Ekleme işi başarıylar Gerçekleşti",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void DataIsUpdated() {

                    }

                    @Override
                    public void DataIsDeleted() {

                    }
                });
            }
        });






    }
}
