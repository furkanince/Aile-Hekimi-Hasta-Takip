package com.example.furkan.ik2;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;


public class RecyclerView_Config2 {
    private Context mContext;
    private CalisansAdapter mCalisanAdapter;
    public void setConfig(RecyclerView recyclerView,Context context,List<Calisan> calisans,List<String> keys )
    {
        mContext = context;
        mCalisanAdapter = new CalisansAdapter(calisans,keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(mCalisanAdapter);
     }

    class CalisanItemView extends RecyclerView.ViewHolder{
        private TextView mAd , mSoyad , mDepartman;
        private String key;

        public CalisanItemView(ViewGroup parent){
            super(LayoutInflater.from(mContext).inflate(R.layout.calisan_list_item,parent,false));

            mAd =(TextView)itemView.findViewById(R.id.ad_txtView);
            mSoyad =(TextView)itemView.findViewById(R.id.soyad_txtView);
            mDepartman=(TextView)itemView.findViewById(R.id.departman_txtView);

        }
        public void bind(Calisan calisan,String key)
        {
            mAd.setText(calisan.getAd());
            mSoyad.setText(calisan.getSoyad());
            mDepartman.setText(calisan.getDepartman());
            this.key = key;
        }


        }
    class CalisansAdapter extends RecyclerView.Adapter<CalisanItemView>
    {
        private List<Calisan> mCalisanList;
        private List<String> mKeys;

        public CalisansAdapter(List<Calisan> mCalisanList, List<String> mKeys) {
            this.mCalisanList = mCalisanList;
            this.mKeys = mKeys;
        }

        @Override
        public CalisanItemView onCreateViewHolder( ViewGroup parent, int viewType) {
            return new CalisanItemView(parent);
        }

        @Override
        public void onBindViewHolder(CalisanItemView holder, int position) {
            holder.bind(mCalisanList.get(position),mKeys.get(position));
        }

        @Override
        public int getItemCount() {
            return mCalisanList.size();
        }
    }
}
