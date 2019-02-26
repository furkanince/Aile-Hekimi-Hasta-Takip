package com.example.furkan.ik2.ik3;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.furkan.ik2.R;
import com.google.firebase.database.DatabaseReference;

import java.util.List;

import static com.example.furkan.ik2.R.id.tvImageName;

import android.app.ProgressDialog;
import android.content.Context;
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
import com.example.furkan.ik2.FirebaseSearch;
import com.example.furkan.ik2.R;
import com.example.furkan.ik2.Users;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class ImageListAdapter extends ArrayAdapter<ImageUpload> {

    DatabaseReference db;
    private Activity context;
    private int resource;
    private List<ImageUpload> listImage;
    private DatabaseReference mUserDatabase;


    public ImageListAdapter(@NonNull Activity context, int resource, @NonNull List<ImageUpload> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        listImage = objects;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View v = inflater.inflate(resource,null);
        TextView tvname = (TextView)v.findViewById(tvImageName);
        ImageView img = v.findViewById(R.id.imgView);

        tvname.setText(listImage.get(position).getName());
        Glide.with(context).load(listImage.get(position).getUrl()).into(img);
        return v;
    }
}
