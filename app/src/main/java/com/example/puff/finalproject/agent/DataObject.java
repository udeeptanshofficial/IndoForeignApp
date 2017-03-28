package com.example.puff.finalproject.agent;

/**
 * Created by deeptansh on 20/3/17.
 */
public class DataObject {
    private String mText1;
    private String mText2;
    private String mtext3;
    private String image;



    DataObject (String text1, String text2, String text3, String text4){
        mText1 = text1;
        mText2 = text2;
        mtext3 = text3;
        image = text4;
    }

    public String getmText1() {
        return mText1;
    }

    public void setmText1(String mText1) {
        this.mText1 = mText1;
    }

    public String getmText2() {
        return mText2;
    }

    public void setmText2(String mText2) {
        this.mText2 = mText2;
    }
    public String getMtext3() {
        return mtext3;
    }

    public void setMtext3(String mtext3) {
        this.mtext3 = mtext3;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
