package com.example.supercooker;

import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;

public class Cook {

    public static int speechVerification;
    public static CountDownTimer timer;
    public static int themeId;
    public static int cookTime;
    public static int backgroundId;
    public static int textColorId;
    public static int talkSpeed;


    public static void speak(final String text, final View view) {

        final TextView textView = view.findViewById(view.getId());
        final char[] charText = text.toCharArray();

        if(charText[0]=='П'){
            speechVerification++;
        }

        timer = new CountDownTimer(text.length()*talkSpeed+800, talkSpeed) {

            String printText = "";
            int count = 0;

            @Override
            public void onTick(long l) {
                if(l <= text.length()*talkSpeed) {
                    try {
                        textView.setText(printText += charText[count]);
                        count++;
                    }
                    catch (Exception e){}
                }
            }

            @Override
            public void onFinish() {
            }
        }.start();
    }


    public static int getSpeechVerification(){
        return speechVerification;
    }
}
