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

public class PutSteakOnPan extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setTheme(Cook.textColorId);
        setTheme(Cook.themeId);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_put_steak_on_pan);
        findViewById(R.id.layout).setBackground(getDrawable(Cook.backgroundId));

        addListenerOnButton();

        TextView textView = findViewById(R.id.textView);
        textView.setText("3) Положите стейк на сковородку.");
    }

    public void addListenerOnButton(){
        Button buttonnext = findViewById(R.id.buttonnext);
        Button buttonprev = findViewById(R.id.buttonprev);
        final ImageView imgCook = findViewById(R.id.imageView8);
        final TextView textView1 = findViewById(R.id.cookSpeak);
        final boolean[] pressed1 = {false};

        buttonnext.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(v.getContext(), Fry.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.right_in,android.R.anim.slide_out_right);
                    }
                }
        );

        buttonprev.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(v.getContext(), LubricateWithOil.class);
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
                        Cook.speak("Если вы жарите на гриле или на сковородке гриль, то немного прижмите его.    ",
                                findViewById(R.id.cookSpeak));
                    }
                    else {
                        imgCook.setClickable(false);
                        Cook.timer.cancel();
                        textView1.setText("Если вы жарите на гриле или на сковородке гриль, то немного прижмите его.    ");
                    }
                }
            }
        );
    }

}

