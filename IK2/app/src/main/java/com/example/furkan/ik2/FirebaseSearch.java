package com.example.furkan.ik2;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FirebaseSearch extends AppCompatActivity {
//Bu nodülüde kullanacağım
    private EditText mSearchField;
    private ImageButton mSearchBtn;
    private RecyclerView mResultList;
    private ProgressDialog progressDialog;




    private DatabaseReference mUserDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_search);

        mUserDatabase = FirebaseDatabase.getInstance().getReference("Users");

        mSearchBtn=(ImageButton)findViewById(R.id.search_btn);
        mSearchField=(EditText)findViewById(R.id.search_field);
        mResultList=(RecyclerView)findViewById(R.id.result_list);

        mResultList.setHasFixedSize(true);

        mResultList.setLayoutManager(new LinearLayoutManager(this));

        ProgressDialog progressDialog = new ProgressDialog(FirebaseSearch.this);
        progressDialog.setMessage("Kişiler Getirliyor..");

        firebaseUserSearch2();
        progressDialog.dismiss();

        mSearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String searchText = mSearchField.getText().toString();
                firebaseUserSearch(searchText);
            }
        });

    }
    private void firebaseUserSearch(String searchTex){



        Query firebaseSearchQuery = mUserDatabase.orderByChild("ad").startAt(searchTex).endAt(searchTex + "\uf8ff");
        FirebaseRecyclerAdapter<Users,UsersViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Users, UsersViewHolder>(
                Users.class,
                R.layout.list_layout,
                UsersViewHolder.class,
                firebaseSearchQuery

        ) {
            @Override
            protected void populateViewHolder(UsersViewHolder viewHolder, Users model, int position) {

                viewHolder.setDetails(getApplicationContext(),model.getAd(),model.getDepartman(),model.getTelefon(),model.getEmail(),model.getIsebaslamatarihi(),model.getImage());

            }
        };
        mResultList.setAdapter(firebaseRecyclerAdapter);



    }

    private void firebaseUserSearch2(){


        FirebaseRecyclerAdapter<Users,UsersViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Users, UsersViewHolder>(
                Users.class,
                R.layout.list_layout,
                UsersViewHolder.class,
                mUserDatabase

        ) {
            @Override
            protected void populateViewHolder(UsersViewHolder viewHolder, Users model, int position) {

                viewHolder.setDetails(getApplicationContext(),model.getAd(),model.getDepartman(),model.getTelefon(),model.getEmail(),model.getIsebaslamatarihi(),model.getImage());

            }
        };
        mResultList.setAdapter(firebaseRecyclerAdapter);

    }

    public static class UsersViewHolder extends RecyclerView.ViewHolder{

        View mView;

        public UsersViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
        }

        public void setDetails(Context ctx, String name, String departman, String telefon, String email, String isegiristarihi, String userImage){

            TextView user_name = (TextView)mView.findViewById(R.id.name_text);
            TextView user_departman =(TextView)mView.findViewById(R.id.departman_text);
            TextView user_telefon = (TextView)mView.findViewById(R.id.telefon_text);
            TextView user_email = (TextView)mView.findViewById(R.id.email_text);
            TextView user_isegiristarihi = (TextView)mView.findViewById(R.id.isegiristarihi_text);
            ImageView user_image = (ImageView)mView.findViewById(R.id.profile_image);

            user_name.setText(name);
            user_departman.setText(departman);
            user_telefon.setText(telefon);
            user_email.setText(email);
            user_isegiristarihi.setText(isegiristarihi);

            Glide.with(ctx).load(userImage).into(user_image);


        }


    }








}
