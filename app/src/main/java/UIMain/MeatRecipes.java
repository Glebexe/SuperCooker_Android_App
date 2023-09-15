package UIMain;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.supercooker.Cook;

import Adapters.ProductAdapter;
import UISteak.ChoosingGarnishForSteak;

import com.example.supercooker.R;
import Adapters.RecipeHeader;

import java.util.ArrayList;

public class MeatRecipes extends AppCompatActivity {

    private ArrayList<RecipeHeader> recipeHeaders = new ArrayList();
    private ListView headersList;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setTheme(Cook.textColorId);
        setTheme(Cook.themeId);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meat_recipes);
        findViewById(R.id.layout).setBackground(getDrawable(Cook.backgroundId));

        printInfo();
    }

    public void printInfo(){
        ArrayList<Intent> intents = new ArrayList<>();
        intents.add(new Intent(this, ChoosingGarnishForSteak.class));

        recipeHeaders.add(new RecipeHeader("Стейк"));

        ChoosingGarnishForSteak.whereWasCalled = MeatRecipes.class;

        headersList = findViewById(R.id.HeadersList);
        ProductAdapter adapter = new ProductAdapter(this, R.layout.list_item, recipeHeaders,intents);
        headersList.setAdapter(adapter);


        Button button = findViewById(R.id.set);
        button.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), FoodCategories.class);
                startActivity(intent);
                overridePendingTransition(R.anim.left_out,R.anim.my_anim);
            }
        });
    }
}
