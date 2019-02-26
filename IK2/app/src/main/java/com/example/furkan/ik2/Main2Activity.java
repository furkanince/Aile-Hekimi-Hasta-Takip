package com.example.furkan.ik2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;

import com.example.furkan.ik2.components.NotelistActivity;
import com.example.furkan.ik2.components.Sirketistatikleri;
import com.example.furkan.ik2.ik3.FotoYukle;
import com.example.furkan.ik2.tıbbi_istatistik.Hasta_Ara;
import com.example.furkan.ik2.tıbbi_istatistik.Hasta_Ekle;
import com.example.furkan.ik2.tıbbi_istatistik.Istatistik;
import com.google.firebase.auth.FirebaseAuth;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener{

    private FirebaseAuth fAuth;

    private CardView bankCard,ideasCard,addCard,linksCard,wifiCard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        bankCard=(CardView)findViewById(R.id.bank_card);
        ideasCard=(CardView)findViewById(R.id.ideas_card);
        addCard=(CardView)findViewById(R.id.add_card);
        linksCard=(CardView)findViewById(R.id.links_card);
        wifiCard=(CardView)findViewById(R.id.wifi_card);

        //Add click listener to the cards
        bankCard.setOnClickListener(this);
        ideasCard.setOnClickListener(this);
        addCard.setOnClickListener(this);
        linksCard.setOnClickListener(this);
        wifiCard.setOnClickListener(this);

        fAuth = FirebaseAuth.getInstance();

        updateUI();

    }

    private void updateUI() {
        if(fAuth.getCurrentUser() != null)
        {
            Log.i("Main2Activity","fAuth != null");
        }else{
            Intent startIntent = new Intent(Main2Activity.this,StartActivity.class);
            startActivity(startIntent);
            Log.i("Main2Activity","fAuth == null");
        }
    }

    public void onClick(View v) {

        Intent i;

        switch (v.getId()) {

            case R.id.bank_card : i = new Intent(this,Hasta_Ekle.class);startActivity(i); break;
            case R.id.ideas_card : i = new Intent(this,Hasta_Ara.class);startActivity(i); break;
            case R.id.add_card : i = new Intent(this,NewNoteActivity.class);startActivity(i); break;
            case R.id.links_card : i = new Intent(this,NotelistActivity.class);startActivity(i); break;
            case R.id.wifi_card : i = new Intent(this,FotoYukle.class);startActivity(i); break;
            default:break;

        }
    }
}
