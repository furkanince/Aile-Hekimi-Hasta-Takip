package com.example.furkan.ik2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import java.util.List;

public class CalisanListActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calisan_list);

        mRecyclerView=(RecyclerView)findViewById(R.id.recyclerview_calisans);
        new FirebaseDatabaseHelper2().readCalisan(new FirebaseDatabaseHelper2.DataStatus() {
            @Override
            public void DataIsLoaded(List<Calisan> calisans, List<String> keys) {
                new RecyclerView_Config2().setConfig(mRecyclerView,CalisanListActivity.this,calisans,keys);
            }

            @Override
            public void DataIsInserted() {

            }

            @Override
            public void DataIsUpdated() {

            }

            @Override
            public void DataIsDeleted() {

            }
        });



    }
}
