package com.example.furkan.ik2;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DatabaseReference;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;


public class SeacrhPerson extends AppCompatActivity {
    private EditText mSearchField;
    private ImageButton mSearchBtn;

    private RecyclerView mResultList;

    private DatabaseReference mUserDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seacrh_person);
        mUserDatabase = FirebaseDatabase.getInstance().getReference("Users");
        mSearchField = (EditText) findViewById(R.id.search_field);
        mSearchBtn = (ImageButton) findViewById(R.id.search_btn);
        mResultList = (RecyclerView) findViewById(R.id.result_list);
        mResultList.setHasFixedSize(true);
        mResultList.setLayoutManager(new LinearLayoutManager(this));

        mSearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String searchText = mSearchField.getText().toString();

                firebaseUserSearch(searchText);

            }
        });
    }
    private void firebaseUserSearch(String searchText){

        Query firebaseSearchQuery = mUserDatabase.orderByChild("ad").startAt(searchText).endAt(searchText + "\uf8ff");



        FirebaseRecyclerAdapter<Users,UsersViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Users, UsersViewHolder>(
                Users.class,
                R.layout.list_layout,
                UsersViewHolder.class,
                firebaseSearchQuery
        ) {
            @Override
            protected void populateViewHolder(UsersViewHolder viewHolder, Users model, int position) {

               // viewHolder.setDetails(getApplicationContext(),model.getAd(),model.getDepartman(),model.getTelefon(),model.getIsebaslamatarihi(),model.getImage());
            }
        };
        mResultList.setAdapter(firebaseRecyclerAdapter);
    }


    public static class  UsersViewHolder extends RecyclerView.ViewHolder{
        View mView;

        public UsersViewHolder(@NonNull View itemView) {
            super(itemView);
            mView=itemView;
        }
        /*
        public void setDetails(Context ctx, String ad, String departman, String telefon, String isegiris, String image){
            TextView user_name =  (TextView)mView.findViewById(R.id.textView2);
            TextView user_departman = (TextView)mView.findViewById(R.id.textView3);
            TextView user_telefon = (TextView)mView.findViewById(R.id.textView4);
            TextView user_isegiris = (TextView)mView.findViewById(R.id.textView5);
            ImageView user_image = (ImageView)mView.findViewById(R.id.imageView);

            user_name.setText(ad);
            user_departman.setText(departman);
            user_telefon.setText(telefon);
            user_isegiris.setText(isegiris);

            Glide.with(ctx).load(image).into(user_image);

        }
        */
    }
}
