package UISteak;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.supercooker.Cook;
import Salads.IngredientsSalad1;
import Salads.IngredientsSalad2;
import Salads.IngredientsSalad3;
import Salads.IngredientsSalad4;
import com.example.supercooker.R;

public class ChoosingGarnishForSteak extends AppCompatActivity {

    private ImageView imgCook;
    private TextView textView1;
    public static Class whereWasCalled;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setTheme(Cook.textColorId);
        setTheme(Cook.themeId);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choosing_garnish_for_steak);
        findViewById(R.id.layout).setBackground(getDrawable(Cook.backgroundId));

        imgCook = findViewById(R.id.cook);
        textView1 = findViewById(R.id.cookSpeak);
        Cook.speak("Перед тем как начать, я хочу предложить тебе эти салаты. Они хорошо сочетаются со стейком. " +
                        "Если ты захочешь мы можем сначала сделать один из них, а потом приступить к стейку.    ",
                    findViewById(R.id.cookSpeak));

    }

    public void onClick(View v){
        switch (v.getId()){
            case R.id.cook:
                imgCook.setClickable(false);
                Cook.timer.cancel();
                textView1.setText("Перед тем как начать, я хочу предложить тебе эти салаты. Они хорошо сочетаются со стейком." +
                        "Если ты захочешь мы можем сначала сделать один из них, а потом приступить к стейку.    ");
                break;
            case R.id.button1:
                Intent intent1 = new Intent(v.getContext(), IngredientsSalad1.class);
                IngredientsSalad1.whereWasCalled = ChoosingGarnishForSteak.class;
                startActivity(intent1);
                overridePendingTransition(R.anim.right_in,android.R.anim.slide_out_right);
                break;
            case R.id.button2:
                Intent intent2 = new Intent(v.getContext(), IngredientsSalad2.class);
                IngredientsSalad2.whereWasCalled = ChoosingGarnishForSteak.class;
                startActivity(intent2);
                overridePendingTransition(R.anim.right_in,android.R.anim.slide_out_right);
                break;
            case R.id.button3:
                Intent intent3 = new Intent(v.getContext(), IngredientsSalad3.class);
                IngredientsSalad3.whereWasCalled = ChoosingGarnishForSteak.class;
                startActivity(intent3);
                overridePendingTransition(R.anim.right_in,android.R.anim.slide_out_right);
                break;
            case R.id.button4:
                Intent intent4 = new Intent(v.getContext(), IngredientsSalad4.class);
                IngredientsSalad4.whereWasCalled = ChoosingGarnishForSteak.class;
                startActivity(intent4);
                overridePendingTransition(R.anim.right_in,android.R.anim.slide_out_right);
                break;
            case R.id.buttonnext:
                Intent intent0 = new Intent(v.getContext(), Ingredients.class);
                startActivity(intent0);
                overridePendingTransition(R.anim.right_in,android.R.anim.slide_out_right);
                break;
            case R.id.buttonprev:
                Intent intent = new Intent(v.getContext(), whereWasCalled);
                startActivity(intent);
                overridePendingTransition(R.anim.left_out,R.anim.my_anim);
        }
    }
}