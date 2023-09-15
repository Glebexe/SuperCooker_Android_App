package UISteak;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.supercooker.Cook;
import com.example.supercooker.R;

import UISteak.PreparingForCooking;
import UISteak.SprinkleWithSpices;

public class ChoosingRecipe extends AppCompatActivity {

    private Button btn1, btn2, btn3, btn4, btn5, btn6, next;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setTheme(Cook.textColorId);
        setTheme(Cook.themeId);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choosing_recipe);
        findViewById(R.id.layout).setBackground(getDrawable(Cook.backgroundId));

        next = findViewById(R.id.next);
        next.setVisibility(View.GONE);

        final ImageView imgCook = findViewById(R.id.cook);
        final TextView textView1 = findViewById(R.id.textView);
        Cook.speak("Какой степени прожарки ты хочешь добиться: ",findViewById(R.id.textView));

        imgCook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgCook.setClickable(false);
                Cook.timer.cancel();
                textView1.setText("Какой степени прожарки ты хочешь добиться:");
            }
        });
    }

    public void onClick(View v) {
        btn1 = findViewById(R.id.button1);
        btn2 = findViewById(R.id.button2);
        btn3 = findViewById(R.id.button3);
        btn4 = findViewById(R.id.button4);
        btn5 = findViewById(R.id.button5);
        btn6 = findViewById(R.id.button6);
        switch (v.getId())
        {
            case R.id.button1:
                changColor(btn2,btn3,btn4,btn5,btn6);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    btn1.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#4a6b14")));
                    Cook.cookTime = 30000;
                    next.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.button2:
                changColor(btn1,btn3,btn4,btn5,btn6);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    btn2.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#4a6b14")));
                    Cook.cookTime = 60000;
                    next.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.button3:
                changColor(btn2,btn1,btn4,btn5,btn6);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    btn3.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#4a6b14")));
                    Cook.cookTime = 120000;
                    next.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.button4:
                changColor(btn2,btn3,btn1,btn5,btn6);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    btn4.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#4a6b14")));
                    Cook.cookTime = 180000;
                    next.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.button5:
                changColor(btn2,btn3,btn1,btn4,btn6);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    btn5.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#4a6b14")));
                    Cook.cookTime = 240000;
                    next.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.button6:
                changColor(btn2,btn3,btn4,btn5,btn1);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    btn6.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#4a6b14")));
                    Cook.cookTime = 300000;
                    next.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.next:
                Intent intent2 = new Intent(v.getContext(), SprinkleWithSpices.class);
                startActivity(intent2);
                overridePendingTransition(R.anim.right_in,android.R.anim.slide_out_right);
                break;
            case R.id.prev:
                Intent intent = new Intent(v.getContext(), PreparingForCooking.class);
                startActivity(intent);
                overridePendingTransition(R.anim.left_out,R.anim.my_anim);
                break;

        }
    }

    public void changColor(Button btn1, Button btn2, Button btn3, Button btn4, Button btn5){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            btn1.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#f72027")));
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            btn2.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#f72027")));
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            btn3.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#f72027")));
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            btn4.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#f72027")));
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            btn5.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#f72027")));
        }
    }
}
