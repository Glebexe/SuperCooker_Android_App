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

public class IngredientsSalad4 extends AppCompatActivity implements Serializable {

    public static Class whereWasCalled;
    private ListView listView;
    private String [] ingredients = new String[]{"2 ст. л. красного винного уксуса;","2 стакана молодой рукколы; полстакана тонко нарезанного красного лука;",
            "полстакана измельченного свежего базилика;","500 граммов разноцветных помидоров (желтых, розовых и красных);","1 средний огурец; соль кошерная и перец;",
            "100-граммовый цельнозерновой багет, нарезанный кубиками и поджаренный."};

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setTheme(Cook.textColorId);
        setTheme(Cook.themeId);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredients_salad4);
        findViewById(R.id.layout).setBackground(getDrawable(Cook.backgroundId));

        list();
    }
    public void list(){
        listView = findViewById(R.id.ingredients);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.ingredients4,ingredients);
        listView.setAdapter(adapter);

        Button buttonNext = findViewById(R.id.button_next);
        Button buttonPrev = findViewById(R.id.button_prev);

        buttonNext.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(v.getContext(), Salad4Finish.class);
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