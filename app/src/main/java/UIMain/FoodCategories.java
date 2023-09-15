package UIMain;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import com.example.supercooker.Cook;
import com.example.supercooker.R;

public class FoodCategories extends AppCompatActivity {


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setTheme(Cook.textColorId);
        setTheme(Cook.themeId);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_categories);
        findViewById(R.id.layout).setBackground(getDrawable(Cook.backgroundId));
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button1:
                Intent intent2 = new Intent(v.getContext(), MeatRecipes.class);
                startActivity(intent2);
                overridePendingTransition(R.anim.right_in, android.R.anim.slide_out_right);
                break;
            case R.id.button2:
                Intent intent1 = new Intent(v.getContext(), SaladRecipes.class);
                startActivity(intent1);
                overridePendingTransition(R.anim.right_in, android.R.anim.slide_out_right);
                break;
            case R.id.toMainMenu:
                Intent intent = new Intent(v.getContext(), HomeScreen.class);
                startActivity(intent);
                overridePendingTransition(R.anim.left_out,R.anim.my_anim);
                finishAfterTransition();
                break;
        }
    }
}