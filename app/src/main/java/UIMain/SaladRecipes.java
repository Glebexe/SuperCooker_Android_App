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
import Salads.IngredientsSalad1;
import Salads.IngredientsSalad2;
import Salads.IngredientsSalad3;
import Salads.IngredientsSalad4;
import com.example.supercooker.R;
import Adapters.RecipeHeader;

import java.util.ArrayList;

public class SaladRecipes extends AppCompatActivity {

    private ArrayList<RecipeHeader> recipeHeaders = new ArrayList();
    private ListView headersList;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setTheme(Cook.textColorId);
        setTheme(Cook.themeId);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salad_recipes);
        findViewById(R.id.layout).setBackground(getDrawable(Cook.backgroundId));

        printInfo();
    }

    public void printInfo() {
        ArrayList<Intent> intents = new ArrayList<>();
        intents.add(new Intent(this, IngredientsSalad1.class));
        intents.add(new Intent(this, IngredientsSalad2.class));
        intents.add(new Intent(this, IngredientsSalad3.class));
        intents.add(new Intent(this, IngredientsSalad4.class));

        IngredientsSalad1.whereWasCalled = SaladRecipes.class;
        IngredientsSalad2.whereWasCalled = SaladRecipes.class;
        IngredientsSalad3.whereWasCalled = SaladRecipes.class;
        IngredientsSalad4.whereWasCalled = SaladRecipes.class;

        recipeHeaders.add(new RecipeHeader("Тайский салат"));
        recipeHeaders.add(new RecipeHeader("Салат с помидорами и голубым сыром"));
        recipeHeaders.add(new RecipeHeader("Салат Нисуаз"));
        recipeHeaders.add(new RecipeHeader("Итальянский салат с сухариками"));

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
                overridePendingTransition(R.anim.left_out, R.anim.my_anim);
            }
        });
    }
}