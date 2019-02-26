package com.example.furkan.ik2.tıbbi_istatistik;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.furkan.ik2.R;
import com.google.android.gms.common.data.ObjectExclusionFilterable;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.Map;

public class Istatistik extends AppCompatActivity {
    private DatabaseReference mDatabase;
    private TextView textView_hasta_sayisi,textView_memur_sayisi,textView_yazilimci_sayisi,textView_muhendis_sayisi,
    textView_erkek_sayisi,textView_kadin_sayisi,
    textView_parol_oran,textView_ceppra_oran,textView_emadine_oran;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_istatistik);
        textView_hasta_sayisi=(TextView)findViewById(R.id.istatistik_hasta_sayisi);
        textView_memur_sayisi=(TextView)findViewById(R.id.istatistik_memur_sayisi);
        textView_yazilimci_sayisi=(TextView)findViewById(R.id.istatistik_yazilim_sayisi);
        textView_muhendis_sayisi=(TextView)findViewById(R.id.istatistik_muhendis_sayisi);
        textView_erkek_sayisi=(TextView)findViewById(R.id.istatistik_erkek_sayisi);
        textView_kadin_sayisi=(TextView)findViewById(R.id.istatistik_kadın_sayisi);
        textView_parol_oran=(TextView)findViewById(R.id.istatistik_parol_sayisi);
        textView_ceppra_oran=(TextView)findViewById(R.id.istatistik_ceppra_sayisi);
        textView_emadine_oran=(TextView)findViewById(R.id.istatistik_emadine_sayisi);


        mDatabase = FirebaseDatabase.getInstance().getReference().child("Hasta");

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int yazilimci_sayisi=0;
                int memur_sayisi=0;
                int muhendis_sayisi=0;
                int toplam_hasta_sayisi=0;

                int erkek_sayisi=0;
                int kadin_sayisi=0;
                int erkek_oran=0;
                int kadin_oran=0;

                int parol_sayisi=0;
                int ceppra_sayisi=0;
                int emadine_sayisi=0;
                int parol_oran=0;
                int ceppra_oran=0;
                int emadine_oran=0;

                for(DataSnapshot ds:dataSnapshot.getChildren())
            {
                Map<String,Object> map = (Map<String,Object>) ds.getValue();
                Object dprtman=map.get("meslek");
                Object cinsiyet=map.get("image");
                Object ilac=map.get("ilac");

                if(ilac.equals("Parol"))
                {
                    parol_sayisi++;
                }
                else if (ilac.equals("Ceppra"))
                {
                    ceppra_sayisi++;
                }
                else if (ilac.equals("Emadine"))
                {
                    emadine_sayisi++;
                }

                if(cinsiyet.equals("https://firebasestorage.googleapis.com/v0/b/ikprojem-e2870.appspot.com/o/person-female.png?alt=media&token=ff691915-f88f-43e6-bd13-b08c2ab4cbcc")){
                    kadin_sayisi++;
                }
                if(cinsiyet.equals("https://firebasestorage.googleapis.com/v0/b/ikprojem-e2870.appspot.com/o/person-male.png?alt=media&token=3dd572aa-8745-445f-b749-93c865ef9438"))
                {
                    erkek_sayisi++;
                }

                if(dprtman.equals("Yazilim"))
                {
                    yazilimci_sayisi++;
                }
                else if (dprtman.equals("Memur"))
                {
                    memur_sayisi++;
                }
                else if (dprtman.equals("Mühendis"))
                {
                    muhendis_sayisi++;
                }
                toplam_hasta_sayisi++;

                erkek_oran=100*erkek_sayisi/toplam_hasta_sayisi;
                kadin_oran=100-erkek_oran;

                parol_oran=100*parol_sayisi/toplam_hasta_sayisi;
                ceppra_oran=100*ceppra_sayisi/toplam_hasta_sayisi;
                emadine_oran=100*emadine_sayisi/toplam_hasta_sayisi;


                textView_hasta_sayisi.setText(String.valueOf(toplam_hasta_sayisi));
                textView_yazilimci_sayisi.setText(String.valueOf(yazilimci_sayisi));
                textView_memur_sayisi.setText(String.valueOf(memur_sayisi));
                textView_muhendis_sayisi.setText(String.valueOf(muhendis_sayisi));
                textView_erkek_sayisi.setText(String.valueOf("%"+erkek_oran));
                textView_kadin_sayisi.setText(String.valueOf("%"+kadin_oran));
                textView_parol_oran.setText(String.valueOf("%"+parol_oran));
                textView_ceppra_oran.setText(String.valueOf("%"+ceppra_oran));
                textView_emadine_oran.setText(String.valueOf("%"+emadine_oran));

            }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
