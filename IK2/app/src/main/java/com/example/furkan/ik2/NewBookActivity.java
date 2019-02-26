package com.example.furkan.ik2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

public class NewBookActivity extends AppCompatActivity {

    private EditText mAuthor_editTxt,mTitle_editTxt,mISBN_editTXT;
    private Spinner mBook_categories_spinner;
    private Button mAdd_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_book);

        mAuthor_editTxt = (EditText)findViewById(R.id.author_edtTxt);
        mTitle_editTxt = (EditText)findViewById(R.id.title_edtText);
        mISBN_editTXT = (EditText)findViewById(R.id.isbn_edtText);
        mBook_categories_spinner = (Spinner)findViewById(R.id.book_categories_spinner);
        mAdd_btn=(Button)findViewById(R.id.add_btn);

        mAdd_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Book book = new Book();
                book.setAuthor(mAuthor_editTxt.getText().toString());
                book.setTitle(mTitle_editTxt.getText().toString());
                book.setIsbn(mISBN_editTXT.getText().toString());
                book.setCategory_name(mBook_categories_spinner.getSelectedItem().toString());
                new FirebaseDatabaseHelper().addBook(book, new FirebaseDatabaseHelper.DataStatus() {
                    @Override
                    public void DataIsLoaded(List<Book> books, List<String> keys) {

                    }

                    @Override
                    public void DataIsInserted() {

                        Toast.makeText(NewBookActivity.this,"Kitap Ekleme işi başarıylar Gerçekleşti",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void DataIsUpdated() {

                    }

                    @Override
                    public void DataIsDeleted() {

                    }
                });
            }
        });






    }
}
