package com.example.giuaky.adapter;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.giuaky.R;
import com.example.giuaky.model.Music;

import java.util.List;

public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.MusicViewHolder> {
    private List<Music> musicList;
    private iItemClick iItemClick;

    private int indexOld = -1;

    public void setDataList(List<Music> l){
        musicList = l;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MusicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_music,parent,false);
        return new MusicViewHolder(view);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull MusicViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Music music = musicList.get(position);
        if(music == null){
            return;
        }
        holder.imvAvt.setImageResource(music.getSrcImg());
        holder.tvMusicName.setText(music.getNameMusic());
        holder.tvMusicAuthor.setText(music.getNameAuthor());
        if(music.isLove()){
            holder.imvFav.setImageResource(R.drawable.ic_favorite);
        }
        else {
            holder.imvFav.setImageResource(R.drawable.ic_unfavorite);

        }

        holder.imvFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                music.setLove(!music.isLove());



                notifyItemChanged(position);
            }
        });


        holder.layoutParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(iItemClick != null){

                    iItemClick.onClick(position);
                    if(indexOld != -1){
                        musicList.get(indexOld).setActive(false);
                        music.setActive(true);

                    }
                    else {
                        music.setActive(true);


                    }
                    notifyDataSetChanged();
                    indexOld = position;

                }
            }
        });

        holder.layoutParent.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                iItemClick.onLongClick(position);

                return false;
            }
        });

        if(music.isActive() != true){
            holder.tvMusicName.setTextColor(Color.WHITE);
        }else {
            holder.tvMusicName.setTextColor(Color.GREEN);
        }

    }

    @Override
    public int getItemCount() {
        if(musicList == null){
            return 0;
        }
        return musicList.size();
    }

    public void setCLickItem(iItemClick iItemClick){
        this.iItemClick = iItemClick;
    }



    class MusicViewHolder extends RecyclerView.ViewHolder{
        private ImageView imvAvt;
        private TextView tvMusicName;
        private TextView tvMusicAuthor;
        private ImageView imvFav;

        private ConstraintLayout layoutParent;
        public MusicViewHolder(@NonNull View itemView) {
            super(itemView);
            imvAvt = itemView.findViewById(R.id.imv_avt);
            tvMusicName = itemView.findViewById(R.id.tv_musicName);
            tvMusicAuthor = itemView.findViewById(R.id.tv_author);
            imvFav = itemView.findViewById(R.id.imv_fav);

            layoutParent = itemView.findViewById(R.id.layout_parent);
        }
    }

    public interface iItemClick{
        void onClick(int position);
        void onLongClick(int position);
    }
}
