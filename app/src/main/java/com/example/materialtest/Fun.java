package com.example.materialtest;

public class Fun extends BaseActivity{

    private String name;

    private int imageId;

    private int Star;
    private int StarNumber;

    public Fun(String name, int imageId,int Star,int StarNumber) {
        this.name = name;
        this.imageId = imageId;
        this.Star=Star;
        this.StarNumber=StarNumber;
    }


    public String getName() {
        return name;
    }

    public int getImageId() {
        return imageId;
    }

    public int getStar(){
        return Star;
    }
    public int getStarNumber(){
        return StarNumber;
    }
}