package UISteak;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.supercooker.Cook;
import com.example.supercooker.R;

public class PreparingForCooking extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setTheme(Cook.textColorId);
        setTheme(Cook.themeId);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preparing_for_cooking);
        findViewById(R.id.layout).setBackground(getDrawable(Cook.backgroundId));

        addListener();
    }

    public void addListener(){
        Button buttonnext = findViewById(R.id.buttonnext);
        Button buttonprev = findViewById(R.id.buttonprev);
        final ImageView cook1 = findViewById(R.id.cookStand1);
        final ImageView cook2 = findViewById(R.id.cookStand2);
        final TextView textView1 = findViewById(R.id.paragraph1);
        final TextView textView2 = findViewById(R.id.paragraph2);
        final boolean[] pressed1 = {false};
        final boolean[] pressed2 = {false};

        final TextView textView = findViewById(R.id.Header);
        textView.setText("С начала небольшая подготовка:");
        buttonnext.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(v.getContext(), ChoosingRecipe.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.right_in,android.R.anim.slide_out_right);
                    }
                }
        );

        buttonprev.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(v.getContext(), Ingredients.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.left_out,R.anim.my_anim);
                    }
                }
        );

        cook1.setOnClickListener(new View.OnClickListener() {
            CountDownTimer timer;
                @Override
                public void onClick(View v) {
                    if(!pressed1[0]) {
                        pressed1[0] = true;
                        final TextView textView = findViewById(R.id.paragraph1);
                        final char[] charText = ("1. Перед началом готовки разогрей сководродку, обычно это занимает около 5 минут. ").toCharArray();

                        timer = new CountDownTimer(charText.length * 100 + 800, 100) {

                            String printText = "";

                            @Override
                            public void onTick(long l) {
                                if (l <= charText.length * 100) {
                                    printText += charText[(int) (charText.length * 100 - l) / 100];
                                    textView.setText(printText);
                                }
                            }

                            @Override
                            public void onFinish() {
                                cook1.setVisibility(View.GONE);
                                cook2.setVisibility(View.VISIBLE);
                            }
                        }.start();
                    }
                    else {
                        cook1.setClickable(false);
                        timer.onFinish();
                        timer.cancel();
                        textView1.setText("1. Перед началом готовки разогрей сководродку, обычно это занимает около 5 минут. ");
                    }
                }
            }
        );
        cook2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!pressed2[0]) {
                        pressed2[0] = true;
                        Cook.speak("2. Включи вытяжку или открой окна, иначе вся твоя кухня будет заполнена дымом. ", findViewById(R.id.paragraph2));
                    }
                    else {
                        cook2.setClickable(false);
                        Cook.timer.cancel();
                        textView2.setText("2. Включи вытяжку или открой окна, иначе вся твоя кухня будет заполнена дымом.");
                    }
                }
            }
        );
    }

}
