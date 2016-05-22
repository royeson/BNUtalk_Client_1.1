package com.bnutalk.socket;

import android.graphics.Bitmap;

/**
 * Created by huangtianyous on 2016/4/9.
 */
public class Msg {
    public static final int TYPE_RECEIVED =0;
    public static final int TYPE_SENT=1;
    private String content;
    private int type;
    private Bitmap receiver_image;
    private Bitmap sender_image;

    public Msg(String content, int type){
        this.content=content;
        this.type=type;
    }
    public String getContent(){
        return content;
    }
    public int getType(){
        return type;
    }
    public Bitmap getReceiver()
    {//get receiver's photo from server here
        return receiver_image;
    }
    public Bitmap getSender()
    {//get sender's photo from server here
        return sender_image;
    }

}
