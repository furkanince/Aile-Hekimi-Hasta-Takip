package com.example.furkan.ik2.ik3;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.furkan.ik2.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.FileNotFoundException;
import java.io.IOException;

public class FotoYukle extends AppCompatActivity {

    private StorageReference mStorageRef;
    private DatabaseReference mDatabaseRef;
    private ImageView imageView;
    private EditText txtImageName;
    private Uri imgUri;

    public static  final String FB_STORAGE_PATH= "image/";
    public static final String FB_DATABASE_PATH= "image";
    public static final int REQUEST_CODE =1234;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foto_yukle);
        mStorageRef = FirebaseStorage.getInstance().getReference();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference(FB_DATABASE_PATH);

        imageView=(ImageView)findViewById(R.id.imageView_foto);
        txtImageName = (EditText)findViewById(R.id.txtImageName);

        }
        public void btnBrowse_Click(View v)
        {

            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent,"Select image"),REQUEST_CODE);
        }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE && resultCode == RESULT_OK && data!= null && data.getData() != null){
            imgUri = data.getData();

            try {
                Bitmap bm = MediaStore.Images.Media.getBitmap(getContentResolver(),imgUri);
                imageView.setImageBitmap(bm);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public String getImageExt(Uri uri)
    {
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));

    }
    public void btnUpload_Click(View v)
    {

    if(imgUri != null)
    {
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setTitle("Uploading image");
        dialog.show();

        //Get the storage reference
        StorageReference ref = mStorageRef.child(FB_STORAGE_PATH + System.currentTimeMillis()+"."+getImageExt(imgUri));

        //Add file to reference

        ref.putFile(imgUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                //tasksnapshot.getDownload() özelliği olmadığı için aşağıdaki yerleri ben düzenledim!!
                Task<Uri> urlTask = taskSnapshot.getStorage().getDownloadUrl();
                while (!urlTask.isSuccessful());
                Uri downloadUrl = urlTask.getResult();
                //buraya kadar düzenledim.
                //Dismiss dialog  when succes
                dialog.dismiss();
                Toast.makeText(getApplicationContext(),"Image upload",Toast.LENGTH_SHORT).show();
                ImageUpload ımageUpload = new ImageUpload(txtImageName.getText().toString(),downloadUrl.toString());//Normalde tasksnapshot.getDownload().toString() olmalıydı.Fakat metod olmadığı için yukarıda belirttiğim yolu izledim ve downloadUrl.toString() 'nu yazdım.Çalıştı :D

                //Save  image info  in to firebase database;
                String uploadId = mDatabaseRef.push().getKey();
                mDatabaseRef.child(uploadId).setValue(ımageUpload);

            }
        })
        .addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                //Dismiss dialog  when error
                dialog.dismiss();
                Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
            }
        })
        .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {

                //Show upload Progress
                double progress = (100 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
                dialog.setMessage("Upload " + (int)progress+"%");
            }
        });
    }
    else {
        Toast.makeText(getApplicationContext(),"Please select a photo",Toast.LENGTH_SHORT).show();
    }
    }
    public void btnShowListImage_Click(View v)
    {
        Intent i = new Intent(FotoYukle.this,ImageListActivity.class);
        startActivity(i);

    }



}
