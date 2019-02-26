package com.example.furkan.ik2.components;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.furkan.ik2.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

public class Sirketistatikleri extends AppCompatActivity {

    private DatabaseReference mDatabase;
    TextView textView,textView2,textView3,textView4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sirketistatikleri);

        textView=(TextView)findViewById(R.id.istatistik_calisan_sayisi);
        textView2=(TextView)findViewById(R.id.istatistik_yazilim_departman_sayisi);
        textView3=(TextView)findViewById(R.id.istatistik_satis_departman_sayisi);
        textView4=(TextView)findViewById(R.id.istatistik_ar_ge_departman_sayisi);


        mDatabase = FirebaseDatabase.getInstance().getReference().child("Users");

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                int toplam_calisan_sayisi=0;
                int yazilim_dprt_sayisi=0;
                int satis_dprt_sayisi=0;
                int ar_ge_dprt_sayisi=0;

                for(DataSnapshot ds :dataSnapshot.getChildren())
                {
                    Map<String,Object> map = (Map<String,Object>) ds.getValue();
                    Object dprtman=map.get("departman");


                    if(dprtman.equals("Yazılım"))
                    {
                        yazilim_dprt_sayisi++;
                    }
                    else if (dprtman.equals("Satış"))
                    {
                        satis_dprt_sayisi++;
                    }
                    else if (dprtman.equals("AR_GE"))
                    {
                        ar_ge_dprt_sayisi++;
                    }

                    toplam_calisan_sayisi++;
                    textView.setText(String.valueOf(toplam_calisan_sayisi));
                    textView2.setText(String .valueOf(yazilim_dprt_sayisi));
                    textView3.setText(String.valueOf(satis_dprt_sayisi));
                    textView4.setText(String.valueOf(ar_ge_dprt_sayisi));
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
