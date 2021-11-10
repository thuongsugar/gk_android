package com.example.giuaky;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;

import com.example.giuaky.adapter.MusicAdapter;
import com.example.giuaky.model.Music;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rcvMusic;
    private List<Music> musicList;
    private MusicAdapter musicAdapter;

    private MediaPlayer mediaPlayer;
    private  int indexMusicPlaying = -1;
    private  int indexOld;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();
        addEvents();
    }

    private void addEvents() {
        musicAdapter.setCLickItem(new MusicAdapter.iItemClick() {
            @Override
            public void onClick(int position) {
                handleMedia(position);
            }

            @Override
            public void onLongClick(int position) {
                if(position == indexMusicPlaying){
                    mediaPlayer.stop();
                    mediaPlayer.release();
                }
                musicList.remove(position);
                musicAdapter.notifyDataSetChanged();
            }
        });
    }

    private void handleMedia(int position) {

        if(mediaPlayer != null){
            mediaPlayer.release();
        }
        Music music = musicList.get(position);
        mediaPlayer = MediaPlayer.create(this,music.getSrcMp3());
        mediaPlayer.start();

    }

    private void initUI() {
        rcvMusic = findViewById(R.id.rcv_music);
        musicList = new ArrayList<>();
        musicAdapter = new MusicAdapter();
        musicAdapter.setDataList(mookData());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL,false);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);

        rcvMusic.setLayoutManager(linearLayoutManager);
        rcvMusic.addItemDecoration(itemDecoration);
        rcvMusic.setAdapter(musicAdapter);

        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

    }
    private List<Music> mookData(){
        musicList.add(new Music(R.drawable.lactroi, "Lạc trôi", "Sơn Tùng MTP", true, R.raw.lactroi));
        musicList.add(new Music(R.drawable.huong, "Hương", "Văn Mai Hương", false, R.raw.huong));
        musicList.add(new Music(R.drawable.muoinam, "Mười năm", "Đen Vâu", true, R.raw.muoinam));
        musicList.add(new Music(R.drawable.muonroimasaocon, "Muộn rồi mà sao còn", "Sơn Tùng MTP", false, R.raw.muonroimasaocon));
        musicList.add(new Music(R.drawable.songgio, "Sóng gió", "Jack & K-ICM", false, R.raw.songgio));
        musicList.add(new Music(R.drawable.trontim, "Trốn tìm", "Đen Vâu", true, R.raw.trontim));


        return musicList;

    }
}