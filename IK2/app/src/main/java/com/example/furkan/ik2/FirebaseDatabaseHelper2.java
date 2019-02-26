package com.example.furkan.ik2;

import android.provider.Settings;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FirebaseDatabaseHelper2 {

    private FirebaseDatabase mDatabase;
    private DatabaseReference mReferenceCalisans;
    private List<Calisan> calisans = new ArrayList<>();


    public interface DataStatus{

        void DataIsLoaded(List<Calisan> calisans, List<String> keys);
        void DataIsInserted();
        void DataIsUpdated();
        void DataIsDeleted();
    }

    public FirebaseDatabaseHelper2() {
    mDatabase = FirebaseDatabase.getInstance();
    mReferenceCalisans = mDatabase.getReference("calisan");
    }
    public void readCalisan(final DataStatus dataStatus){
        mReferenceCalisans.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int sayac = 0;
            calisans.clear();
            List<String> keys = new ArrayList<>();
            for(DataSnapshot keyNode : dataSnapshot.getChildren())
            {
                keys.add(keyNode.getKey());
                Calisan calisan = keyNode.getValue(Calisan.class);
                calisans.add(calisan);
                sayac++;
                Log.d("Toplam",String.valueOf(sayac));
            }
            dataStatus.DataIsLoaded(calisans,keys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void addCalisan(Calisan calisan, final FirebaseDatabaseHelper2.DataStatus dataStatus){
        String key = mReferenceCalisans.push().getKey();
        mReferenceCalisans.child(key).setValue(calisan)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        dataStatus.DataIsInserted();
                    }
                });
    }

}
