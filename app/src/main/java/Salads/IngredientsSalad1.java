package Salads;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.supercooker.Cook;
import com.example.supercooker.R;

import java.io.Serializable;

import UISteak.PutSteakOnPan;
import UISteak.SprinkleWithSpices;

public class IngredientsSalad1 extends AppCompatActivity{

    public static Class whereWasCalled;
    private ListView listView;
    private String [] ingredients = new String[]{"1 зубец чеснока;","четверть стакана свежего сока лайма;","2 ст. л. рыбного соуса;","1 ст. л. темно-коричневого сахара;",
            "2 небольших свежих красных тайских чили, тонко нарезанных (или 1 перец серрано);","180 граммов разорванного руками салата ромен;",
            "половинка огурца без косточек, мелко нарезанная;","2 средних лука-шалота, порезанного тонкими кольцами;","полстакана крупно порванной свежей мяты;",
            "треть стакана крупно порванной свежей кинзы;","треть стакана крупно порезанного арахиса жареного;"};

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setTheme(Cook.textColorId);
        setTheme(Cook.themeId);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingrdients_salat1);
        findViewById(R.id.layout).setBackground(getDrawable(Cook.backgroundId));

        list();
    }

    public void list(){
        listView = findViewById(R.id.ingredients);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.ingredients1,ingredients);
        listView.setAdapter(adapter);

        Button buttonNext = findViewById(R.id.button_next);
        Button buttonPrev = findViewById(R.id.button_prev);

        buttonNext.setOnClickListener(
            new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), Salad1Finish.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.right_in,android.R.anim.slide_out_right);
                }
            }
        );

        buttonPrev.setOnClickListener(
            new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), whereWasCalled);
                    startActivity(intent);
                    overridePendingTransition(R.anim.left_out,R.anim.my_anim);
                }
            }
        );
    }
}