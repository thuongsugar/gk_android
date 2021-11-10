package com.example.giuaky.model;

public class Music {
    private int srcImg;
    private String nameMusic;
    private String nameAuthor;
    private boolean isLove;
    private int srcMp3;

    private boolean isActive;

    public Music(int srcImg, String nameMusic, String nameAuthor, boolean isLove, int srcMp3) {
        this.srcImg = srcImg;
        this.nameMusic = nameMusic;
        this.nameAuthor = nameAuthor;
        this.isLove = isLove;
        this.srcMp3 = srcMp3;
        this.isActive = false;
    }

    public int getSrcImg() {
        return srcImg;
    }

    public void setSrcImg(int srcImg) {
        this.srcImg = srcImg;
    }

    public String getNameMusic() {
        return nameMusic;
    }

    public void setNameMusic(String nameMusic) {
        this.nameMusic = nameMusic;
    }

    public String getNameAuthor() {
        return nameAuthor;
    }

    public void setNameAuthor(String nameAuthor) {
        this.nameAuthor = nameAuthor;
    }

    public boolean isLove() {
        return isLove;
    }

    public void setLove(boolean love) {
        isLove = love;
    }

    public int getSrcMp3() {
        return srcMp3;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public void setSrcMp3(int srcMp3) {
        this.srcMp3 = srcMp3;
    }
}
