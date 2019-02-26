package com.example.furkan.ik2.tıbbi_istatistik;

import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.furkan.ik2.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class HastaDetails extends AppCompatActivity {

    private String mPost_key=null;
    private DatabaseReference mDatabase;

    private ImageView image;
    private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hasta_details);

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Hasta");
        mPost_key = getIntent().getExtras().getString("blog_id");

        //Toast.makeText(HastaDetails.this,post_key,Toast.LENGTH_SHORT).show();

        image=(ImageView)findViewById(R.id.hasta_image);
        text=(TextView)findViewById(R.id.hasta_name);

        mDatabase.child(mPost_key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String hasta_adi = (String) dataSnapshot.child("ad").getValue();
                String hasta_foto = (String) dataSnapshot.child("image").getValue();

                text.setText(hasta_adi);
                Picasso.with(HastaDetails.this).load(hasta_foto).into(image);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });





    }
}
