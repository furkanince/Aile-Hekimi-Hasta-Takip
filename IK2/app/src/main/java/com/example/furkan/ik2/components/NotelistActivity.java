package com.example.furkan.ik2.components;

import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.furkan.ik2.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class NotelistActivity extends AppCompatActivity {

    private RecyclerView mNotesList;
    private GridLayoutManager gridLayoutManager;
    private DatabaseReference fNotesDatabase;
    private FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notelist);

        gridLayoutManager=new GridLayoutManager(this,3, GridLayoutManager.VERTICAL,false);
        mNotesList=findViewById(R.id.main_notes_list);
        mNotesList.setLayoutManager(gridLayoutManager);
        mNotesList.setHasFixedSize(true);


        fAuth=FirebaseAuth.getInstance();
        if(fAuth.getCurrentUser() != null){
            fNotesDatabase = FirebaseDatabase.getInstance().getReference().child("Notes").child(fAuth.getCurrentUser().getUid());
        }



    }

    @Override

    public void onStart(){
        super.onStart();
        FirebaseRecyclerAdapter<NoteModel,NoteViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<NoteModel, NoteViewHolder>(

                NoteModel.class,
                R.layout.single_note_layout,
                NoteViewHolder.class,
                fNotesDatabase

        ) {
            @Override
            protected void populateViewHolder(final NoteViewHolder viewHolder, NoteModel model, int position) {

            String noteId = getRef(position).getKey();
            fNotesDatabase.child(noteId).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    String title =dataSnapshot.child("title").getValue().toString();
                    String timestap = dataSnapshot.child("timestamp").getValue().toString();

                    viewHolder.setNoteTitle(title);
                    viewHolder.setNoteTime(timestap);

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }

            });

            }

        };

        mNotesList.setAdapter(firebaseRecyclerAdapter);

    }
    

}
