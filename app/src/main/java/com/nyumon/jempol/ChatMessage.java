package com.nyumon.jempol;

import android.widget.ImageView;

/**
 * Created by ridho on 07/04/16.
 */
public class ChatMessage {
    public boolean left;
    public String message;
    public ImageView tet;

    public ChatMessage(boolean left, String message,ImageView tet) {
        super();
        this.left = left;
        this.tet = tet;
        this.message = message;
    }
}
