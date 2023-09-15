package Salads;


import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.supercooker.Cook;
import com.example.supercooker.R;

import Datebase.InteractionWithDatabase;

public class Salad3Finish extends AppCompatActivity {

    private InteractionWithDatabase interactionWithDatabase;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setTheme(Cook.textColorId);
        setTheme(Cook.themeId);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salad3_finish);
        findViewById(R.id.layout).setBackground(getDrawable(Cook.backgroundId));

        interactionWithDatabase = new InteractionWithDatabase((ImageView) findViewById(R.id.like), "2", "salads");

        interactionWithDatabase.viewsCount();
        ListenerOnButton();
    }

    public void ListenerOnButton(){
        Button buttonNext = findViewById(R.id.button_next);
        Button buttonPrev = findViewById(R.id.button_prev);

        interactionWithDatabase.onLikeCLick();

        buttonNext.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        interactionWithDatabase.addLikeStatus();

                        Intent intent = new Intent(v.getContext(), IngredientsSalad3.whereWasCalled);
                        startActivity(intent);
                        overridePendingTransition(R.anim.right_in,android.R.anim.slide_out_right);
                    }
                }
        );

        buttonPrev.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(v.getContext(), IngredientsSalad3.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.left_out,R.anim.my_anim);
                    }
                }
        );
    }
}