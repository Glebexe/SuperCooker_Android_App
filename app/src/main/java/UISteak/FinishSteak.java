package UISteak;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.supercooker.Cook;
import com.example.supercooker.R;

import Datebase.InteractionWithDatabase;

public class FinishSteak extends AppCompatActivity {

    private ImageView imgCook;
    private InteractionWithDatabase interactionWithDatabase;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setTheme(Cook.textColorId);
        setTheme(Cook.themeId);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish_steak);
        findViewById(R.id.layout).setBackground(getDrawable(Cook.backgroundId));

        interactionWithDatabase = new InteractionWithDatabase((ImageView) findViewById(R.id.like), "0", "meatDishes");

        interactionWithDatabase.viewsCount();
        addListenerOnButton();

        TextView textView = findViewById(R.id.startText);
        textView.setText("6) Дайте стейку полежать 3–5 минут.");
    }

    public void addListenerOnButton(){
        Button buttonnext = findViewById(R.id.buttonnext);
        Button buttonprev = findViewById(R.id.buttonprev);
        imgCook = findViewById(R.id.cook);
        final TextView textView1 = findViewById(R.id.description);
        final boolean[] pressed1 = {false};

        interactionWithDatabase.onLikeCLick();

        buttonnext.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        interactionWithDatabase.addLikeStatus();

                        Intent intent = new Intent(v.getContext(), ChoosingGarnishForSteak.whereWasCalled);
                        startActivity(intent);
                        overridePendingTransition(R.anim.right_in,android.R.anim.slide_out_right);
                    }
                }
        );

        buttonprev.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(v.getContext(), RoastingSides.class);
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
                    Cook.speak("При высоких температурах верхние слои мяса сжимаются," +
                            " фиксируя сок внутри. Если разрезать стейк сразу же, сок просто вытечет на тарелку." +
                            " Подождите до 5 минут: этого достаточно, чтобы верхние слои мяса расширились и тоже напитались соком.",
                            findViewById(R.id.description));
                }
                else {
                    imgCook.setClickable(false);
                    Cook.timer.cancel();
                    textView1.setText("При высоких температурах верхние слои мяса сжимаются," +
                            " фиксируя сок внутри. Если разрезать стейк сразу же, сок просто вытечет на тарелку." +
                            " Подождите до 5 минут: этого достаточно, чтобы верхние слои мяса расширились и тоже напитались соком.");
                }
            }
        });
    }
}
