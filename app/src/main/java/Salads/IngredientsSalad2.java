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

public class IngredientsSalad2 extends AppCompatActivity implements Serializable {

    public static Class whereWasCalled;
    private ListView listView;
    private String [] ingredients = new String[]{"2 ст. л. цельнозерновой горчицы;","3 капли вустерширского соуса;","1 ч. л. бальзамического уксуса;",
            "четверть чайной ложки кошерной соли;","четверть чайной ложки перца;","500 граммов помидоров черри, нарезанных половинками;",
            "3/4 стакана измельченного сельдерея;","2 пучка зеленого лука, нарезанных мелко;","100 граммов голубого сыра, измельченного (раскрошенного);",
            "2 ст. л. лимонного сока;","четверть стакана оливкового масла."};

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setTheme(Cook.textColorId);
        setTheme(Cook.themeId);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredients_salad2);
        findViewById(R.id.layout).setBackground(getDrawable(Cook.backgroundId));

        list();
    }
    public void list(){
        listView = findViewById(R.id.ingredients);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.ingredients2,ingredients);
        listView.setAdapter(adapter);

        Button buttonNext = findViewById(R.id.button_next);
        Button buttonPrev = findViewById(R.id.button_prev);

        buttonNext.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(v.getContext(), Salad2Finish.class);
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