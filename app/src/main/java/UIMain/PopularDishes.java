package UIMain;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ListView;

import com.example.supercooker.Cook;
import com.example.supercooker.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import Adapters.ProductAdapter;
import Adapters.RecipeHeader;
import Salads.IngredientsSalad1;
import Salads.IngredientsSalad2;
import Salads.IngredientsSalad3;
import Salads.IngredientsSalad4;
import UISteak.ChoosingGarnishForSteak;


public class PopularDishes extends AppCompatActivity {

    private DatabaseReference myRef;
    private List<String> saladsNames;
    private List<String> meatDishesNames;
    private List<Integer> saladsViews;
    private List<Integer> saladsRating;
    private List<Integer> meatDishesViews;
    private ArrayList<RecipeHeader> sortedNames = new ArrayList<>();
    private ArrayList<Intent> saladIntents = new ArrayList<>();
    private ArrayList<Intent> meatDishesIntents = new ArrayList<>();
    private ArrayList<Intent> sortedIntents = new ArrayList<>();
    private ListView listDishes;
    private CheckBox sortingByPopularity;
    private CheckBox sortingByRating;
    private CheckBox showMostPopularDish;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setTheme(Cook.textColorId);
        setTheme(Cook.themeId);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popular_dishes);
        findViewById(R.id.layout).setBackground(getDrawable(Cook.backgroundId));

        saladIntents.add(new Intent(this,IngredientsSalad1.class));
        saladIntents.add(new Intent(this,IngredientsSalad2.class));
        saladIntents.add(new Intent(this,IngredientsSalad3.class));
        saladIntents.add(new Intent(this,IngredientsSalad4.class));

        IngredientsSalad1.whereWasCalled = PopularDishes.class;
        IngredientsSalad2.whereWasCalled = PopularDishes.class;
        IngredientsSalad3.whereWasCalled = PopularDishes.class;
        IngredientsSalad4.whereWasCalled = PopularDishes.class;

        meatDishesIntents.add(new Intent(this, ChoosingGarnishForSteak.class));

        ChoosingGarnishForSteak.whereWasCalled = PopularDishes.class;

        getSaladDishes(1);
    }

    public void onClick(View view){
        sortingByPopularity = findViewById(R.id.sorting_by_popularity_checkBox);
        sortingByRating = findViewById(R.id.sorting_by_rating_checkBox);
        showMostPopularDish = findViewById(R.id.show_most_popular_dish_checkBox);
        switch (view.getId()){
            case R.id.sorting_by_popularity_checkBox:
                if(sortingByPopularity.isChecked()){
                    sortingByRating.setChecked(false);
                    showMostPopularDish.setChecked(false);
                    getSaladDishes(1);
                }
                else{
                    sortingByPopularity.setChecked(true);
                }
                break;
            case R.id.sorting_by_rating_checkBox:
                if(sortingByRating.isChecked()){
                    sortingByPopularity.setChecked(false);
                    showMostPopularDish.setChecked(false);
                    getSaladDishes(1);
                }
                else{
                    sortingByRating.setChecked(true);
                }
                getSaladDishes(2);
                break;
            case R.id.show_most_popular_dish_checkBox:
                if(showMostPopularDish.isChecked()){
                    sortingByRating.setChecked(false);
                    sortingByPopularity.setChecked(false);
                    getSaladDishes(1);
                }
                else{
                    showMostPopularDish.setChecked(true);
                }
                getSaladDishes(3);
                break;
            case R.id.toMainMenu:
                Intent intent = new Intent(view.getContext(), HomeScreen.class);
                startActivity(intent);
                overridePendingTransition(R.anim.left_out,R.anim.my_anim);
                break;
        }
    }

    private void getSaladDishes(final int method) {
        myRef = FirebaseDatabase.getInstance().getReference();
        listDishes = findViewById(R.id.listDishes);
            myRef.child("recipes").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    GenericTypeIndicator<List<String>> t1 = new GenericTypeIndicator<List<String>>() {};
                    GenericTypeIndicator<List<Integer>> t2 = new GenericTypeIndicator<List<Integer>>() {};
                    GenericTypeIndicator<List<Integer>> t3 = new GenericTypeIndicator<List<Integer>>() {};
                    GenericTypeIndicator<List<String>> t4 = new GenericTypeIndicator<List<String>>() {};
                    GenericTypeIndicator<List<Integer>> t5 = new GenericTypeIndicator<List<Integer>>() {};
                    saladsNames = dataSnapshot.child("salads").child("names").getValue(t1);
                    saladsViews = dataSnapshot.child("salads").child("views").getValue(t2);
                    saladsRating = dataSnapshot.child("salads").child("likes").getValue(t3);
                    meatDishesNames = dataSnapshot.child("meatDishes").child("names").getValue(t4);
                    meatDishesViews = dataSnapshot.child("meatDishes").child("views").getValue(t5);

                    switch (method){
                        case 1:
                            sortByPopularity();
                            break;
                        case 2:
                            sortByRating();
                            break;
                        case 3:
                            showMostPopularDish();
                            break;
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {}
            });
    }

    private void sortByPopularity(){
        listDishes.setAdapter(null);
        sortedNames.clear();
        sortedIntents.clear();
        for (int i = 0; i < saladsNames.size(); i++) {
            int maxPos = 0, max = -1;
            for (int j = 0; j < saladsNames.size(); j++) {
                if (saladsViews.get(j) > max) {
                    max = saladsViews.get(j);
                    maxPos = j;
                }
            }
            sortedNames.add(new RecipeHeader(saladsNames.get(maxPos)));
            sortedIntents.add(saladIntents.get(maxPos));
            saladsViews.set(maxPos, -1);
        }

        ProductAdapter adapter = new ProductAdapter(this, R.layout.list_item, sortedNames, sortedIntents);
        listDishes.setAdapter(adapter);
    }

    private void sortByRating(){
        listDishes.setAdapter(null);
        sortedNames.clear();
        sortedIntents.clear();
        for (int i = 0; i < saladsNames.size(); i++) {
            int maxPos = 0, max = -1;
            for (int j = 0; j < saladsNames.size(); j++) {
                if (saladsRating.get(j) > max) {
                    max = saladsRating.get(j);
                    maxPos = j;
                }
            }
            sortedNames.add(new RecipeHeader(saladsNames.get(maxPos)));
            sortedIntents.add(saladIntents.get(maxPos));
            saladsRating.set(maxPos, -1);
        }

        ProductAdapter adapter = new ProductAdapter(this, R.layout.list_item, sortedNames, sortedIntents);
        listDishes.setAdapter(adapter);

    }

    private void showMostPopularDish(){
        listDishes.setAdapter(null);
        sortedNames.clear();
        sortedIntents.clear();
        int maxPos = 0, max = -1;
        for (int i = 0; i < saladsNames.size(); i++) {
            if (saladsViews.get(i) > max) {
                max = saladsViews.get(i);
                maxPos = i;
            }
        }
        sortedNames.add(new RecipeHeader(saladsNames.get(maxPos)));
        sortedIntents.add(saladIntents.get(maxPos));

        maxPos = 0;
        max = -1;
        for (int i = 0; i < meatDishesNames.size(); i++) {
            if (meatDishesViews.get(i) > max) {
                max = meatDishesViews.get(i);
                maxPos = i;
            }
        }
        sortedNames.add(new RecipeHeader(meatDishesNames.get(maxPos)));
        sortedIntents.add(meatDishesIntents.get(maxPos));

        ProductAdapter adapter = new ProductAdapter(this, R.layout.list_item, sortedNames, sortedIntents);
        listDishes.setAdapter(adapter);
    }
}