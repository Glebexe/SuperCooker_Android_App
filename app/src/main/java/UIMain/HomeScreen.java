package UIMain;

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
import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

import UIMain.FoodCategories;
import UIMain.Settings;

public class HomeScreen extends AppCompatActivity {

    private ImageView imgCook;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setTheme(Cook.textColorId);
        setTheme(Cook.themeId);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        findViewById(R.id.layout).setBackground(getDrawable(Cook.backgroundId));

        imgCook = findViewById(R.id.cook);
        if(Cook.getSpeechVerification() == 0) {
            Cook.speak("Привет! Я шеф-повар Кастрюлькин. Я буду помогать тебе готовить. Если нажмешь на меня, я могу дать тебе полезные советы.  ",
                    findViewById(R.id.cookWords));
        }
        else{
            imgCook.setVisibility(View.GONE);
        }

        addListenerOnButton();

    }

    public void addListenerOnButton(){
        Button goToRecipes = findViewById(R.id.goToRecipes);
        Button settings = findViewById(R.id.settings);
        Button sorting = findViewById(R.id.sorting);
        Button aboutApp = findViewById(R.id.aboutApp);
        final TextView textView1 = findViewById(R.id.cookWords);

        goToRecipes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), FoodCategories.class);
                startActivity(intent);
                overridePendingTransition(R.anim.right_in,android.R.anim.slide_out_right);
            }
        });

        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), Settings.class);
                startActivity(intent);
                overridePendingTransition(R.anim.right_in,android.R.anim.slide_out_right);
            }
        });

        sorting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(),PopularDishes.class));
                overridePendingTransition(R.anim.right_in,android.R.anim.slide_out_right);
            }
        });

        aboutApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(),AboutApp.class));
                overridePendingTransition(R.anim.right_in,android.R.anim.slide_out_right);
            }
        });

        imgCook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgCook.setClickable(false);
                Cook.timer.cancel();
                textView1.setText("Привет! Я шеф-повар Кастрюлькин. Я буду помогать тебе готовить. Если нажмешь на меня, я могу дать тебе полезные советы.");
            }
        });
    }
}
