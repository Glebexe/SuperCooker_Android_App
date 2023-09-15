package Salads;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.supercooker.Cook;
import com.example.supercooker.R;

import java.io.Serializable;

public class IngredientsSalad3 extends AppCompatActivity implements Serializable {

    public static Class whereWasCalled;
    private ListView listView;
    private String [] ingredients = new String[]{"2 больших яйца, отваренных вкрутую (охлажденных в холодильнике);",
            "300 граммов розового картофеля, отварного;","400 граммов зеленой фасоли;","немного масла;",
            "половина чайной ложки свежемолотого перца черного перца;","половина чайной ложки кошерной соли;",
            "четверть стакана нарезанной свежей петрушки (с плоскими листьями);","1 ст. л. свежих листьев тимьяна;",
            "2 1/2 ст. л. оливкового масла;","2 ст. л. белого винного уксуса;","1 ч. л. дижонской горчицы;",
            "1 небольшой зубец чеснока, тертый;","3 стакана нарезанного салата ромена;","стакан порезанных пополам помидоров черри;",
            "20 маслин."};

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setTheme(Cook.textColorId);
        setTheme(Cook.themeId);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredients_salad3);
        findViewById(R.id.layout).setBackground(getDrawable(Cook.backgroundId));

        list();
    }
    public void list(){
        listView = findViewById(R.id.ingredients);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.ingredients3,ingredients);
        listView.setAdapter(adapter);

        Button buttonNext = findViewById(R.id.button_next);
        Button buttonPrev = findViewById(R.id.button_prev);

        buttonNext.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(v.getContext(), Salad3Finish.class);
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