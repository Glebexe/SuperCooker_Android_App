package UISteak;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.supercooker.Cook;
import com.example.supercooker.R;

import UISteak.PutSteakOnPan;
import UISteak.SprinkleWithSpices;

public class LubricateWithOil extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setTheme(Cook.textColorId);
        setTheme(Cook.themeId);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lubricate_with_oil);
        findViewById(R.id.layout).setBackground(getDrawable(Cook.backgroundId));

        addListenerOnButton();

        TextView textView = findViewById(R.id.textView);
        textView.setText("2) Облейте стейк маслом. Затем проделай все тоже самое с другой стороны стейка.");
    }
    public void addListenerOnButton(){
        Button buttonnext = findViewById(R.id.buttonnext);
        Button buttonprev = findViewById(R.id.buttonprev);
        final ImageView imgCook = findViewById(R.id.cook);
        final TextView textView1 = findViewById(R.id.textView2);
        final boolean[] pressed1 = {false};

        buttonnext.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(v.getContext(), PutSteakOnPan.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.right_in,android.R.anim.slide_out_right);
                    }
                }
        );

        buttonprev.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(v.getContext(), SprinkleWithSpices.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.left_out,R.anim.my_anim);
                    }
                }
        );

        imgCook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!pressed1[0]) {
                    pressed1[0] = true;
                    Cook.speak("Лучше всего подойдет рафинированное оливковое или подсолнечное масло." +
                                    " Налей его много, если у тебя плита жарит как ад, а если нет, то достаточно смазать стейк.     ",
                            findViewById(R.id.textView2));
                }
                else {
                    imgCook.setClickable(false);
                    Cook.timer.cancel();
                    textView1.setText("Лучше всего подойдет рафинированное оливковое или подсолнечное масло." +
                            " Налей его много, если у тебя плита жарит как ад, а если нет, то достаточно смазать стейк.     ");
                }
            }
        });
    }

}
