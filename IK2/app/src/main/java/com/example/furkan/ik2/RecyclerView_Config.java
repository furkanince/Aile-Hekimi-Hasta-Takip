package com.example.furkan.ik2;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class RecyclerView_Config {
    private Context mContext;
    private BooksAdapter mBooksAdapter;

    public void setConfig (RecyclerView recyclerView,Context context,List<Book> books,List<String> keys)
    {
        mContext =context;
        mBooksAdapter = new BooksAdapter(books,keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(mBooksAdapter);
    }


    class BookItemView extends RecyclerView.ViewHolder{

        private TextView mTitle;
        private TextView mAuthor;
        private TextView mISBN;
        private TextView mCategory;

        private String key;
        public BookItemView (ViewGroup parent)
        {
            super(LayoutInflater.from(mContext).inflate(R.layout.book_list_item,parent,false));

            mTitle =(TextView)itemView.findViewById(R.id.title_txtView);
            mAuthor = (TextView)itemView.findViewById(R.id.author_txtView);
            mCategory =(TextView)itemView.findViewById(R.id.category_txtView);
            mISBN =(TextView)itemView.findViewById(R.id.isbn_txtView);
        }

        public void bind (Book book,String key)
        {
            mTitle.setText(book.getTitle());
            mAuthor.setText(book.getAuthor());
            mCategory.setText(book.getCategory_name());
            mISBN.setText(book.getIsbn());
            this.key = key;
        }
    }

    class BooksAdapter extends RecyclerView.Adapter<BookItemView>{

        private List<Book> mBookList;
        private List<String> mKeys;

        public BooksAdapter(List<Book> mBookList, List<String> mKeys) {
            this.mBookList = mBookList;
            this.mKeys = mKeys;
        }

        @Override
        public void onBindViewHolder(BookItemView holder, int position) {
            holder.bind(mBookList.get(position),mKeys.get(position));
        }

        @Override
        public int getItemCount() {
            return mBookList.size();
        }


        @Override
        public BookItemView onCreateViewHolder(ViewGroup parent, int ViewType) {
            return new BookItemView(parent);
        }
    }

}