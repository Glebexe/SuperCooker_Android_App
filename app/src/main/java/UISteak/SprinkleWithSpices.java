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

public class SprinkleWithSpices extends AppCompatActivity {

    private Button buttonnext,buttonprev;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setTheme(Cook.textColorId);
        setTheme(Cook.themeId);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sprinkle_with_spices);
        findViewById(R.id.layout).setBackground(getDrawable(Cook.backgroundId));

        addListenerOnButton();

        TextView textView = findViewById(R.id.textView);
        textView.setText("1) Посыпте стейк солью и специями.");
    }

    public void addListenerOnButton(){
        buttonnext = findViewById(R.id.buttonnext);
        buttonprev = findViewById(R.id.buttonprev);
        final ImageView imgCook = findViewById(R.id.cook);
        final TextView textView1 = findViewById(R.id.cookAdvise);
        final boolean[] pressed1 = {false};
        buttonnext.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(v.getContext(), LubricateWithOil.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.right_in,android.R.anim.slide_out_right);
                    }
                }
        );

        buttonprev.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(v.getContext(), ChoosingRecipe.class);
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
                    Cook.speak("Самые лучшие приправы, которые подходят для стейка, на мой взгляд:" +
                                    " красный и черный перец, чеснок, розмарин, тимьян, орегано.    ",
                            findViewById(R.id.cookAdvise));
                }
                else {
                    imgCook.setClickable(false);
                    Cook.timer.cancel();
                    textView1.setText("Самые лучшие приправы, которые подходят для стейка, на мой взгляд:" +
                            " красный и черный перец, чеснок, розмарин, тимьян, орегано.    ");
                }

            }
        });
    }
}
