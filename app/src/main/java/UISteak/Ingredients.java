package UISteak;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.supercooker.Cook;
import com.example.supercooker.R;

import java.util.ArrayList;

import UIMain.MeatRecipes;


public class Ingredients extends AppCompatActivity {

    private ListView ingredientslist;
    private ArrayList<String[]> infAboutIng;
    private Button buttonnext,buttonprev;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setTheme(Cook.textColorId);
        setTheme(Cook.themeId);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredients);
        findViewById(R.id.layout).setBackground(getDrawable(Cook.backgroundId));

        printInfo();
        addListenerOnButton();
    }

    private void printInfo() {

        infAboutIng = new ArrayList<>();
        infAboutIng.add(new String[]{"1. Стейк","2. Черный перец","3. Соль","4. Подсолнечное масло"});

        ingredientslist = findViewById(R.id.ingredients);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this,R.layout.ingredients,infAboutIng.get(0));
        ingredientslist.setAdapter(adapter1);

        TextView textView = findViewById(R.id.cookWords);
        textView.setText("Для приготовления стейка тебе понадобится:");
    }

    public void addListenerOnButton(){
        buttonnext = findViewById(R.id.buttonnext);
        buttonprev = findViewById(R.id.buttonprev);
        buttonnext.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Ingredients.this, PreparingForCooking.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.right_in,android.R.anim.slide_out_right);
                    }
                }
        );

        buttonprev.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(v.getContext(), MeatRecipes.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.left_out,R.anim.my_anim);
                    }
                }
        );
    }

}
