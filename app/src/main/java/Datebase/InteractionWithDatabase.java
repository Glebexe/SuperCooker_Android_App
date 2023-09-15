package Datebase;

import android.os.Build;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.example.supercooker.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;


public class InteractionWithDatabase {

    private DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
    private ImageView like;
    private boolean isLikePressed = false;
    private boolean startLikeStatus = false;
    private String dishNumber;
    private String groupOfDishes;

    public InteractionWithDatabase(ImageView like, String dishNumber, String groupOfDishes){
        this.like = like;
        this.dishNumber = dishNumber;
        this.groupOfDishes = groupOfDishes;
        likeStartStatus();
    }

    private void likeStartStatus(){
        if(FirebaseAuth.getInstance().getCurrentUser() != null) {
            mDatabase.child("user" + FirebaseAuth.getInstance().getUid()).child(groupOfDishes + "Likes").addListenerForSingleValueEvent(
                    new ValueEventListener() {
                        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if (dataSnapshot.child(dishNumber).getValue(Boolean.class) != null && dataSnapshot.child(dishNumber).getValue(Boolean.class)) {
                                like.setImageResource(R.drawable.ic_baseline_favorite_24);
                                isLikePressed = true;
                                startLikeStatus = true;
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            Toast.makeText(like.getContext(),"Ошибка", Toast.LENGTH_SHORT).show();
                        }
                    }
            );
        }
    }

    public void viewsCount(){
        final Map<String, Object> recipe = new HashMap<>();
        mDatabase.child("recipes").child(groupOfDishes).addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        recipe.put(dishNumber,dataSnapshot.child("views").child(dishNumber).getValue(Long.class).intValue() + 1);
                        mDatabase.child("recipes").child(groupOfDishes).child("views").updateChildren(recipe);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Toast.makeText(like.getContext(),"Ошибка", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void addLikeStatus(){
        mDatabase.child("user" + FirebaseAuth.getInstance().getUid()).child(groupOfDishes + "Likes").addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        final Map<String, Object> recipe = new HashMap<>();
                        mDatabase.child("recipes").child(groupOfDishes).addListenerForSingleValueEvent(
                                new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        if(!startLikeStatus && isLikePressed)
                                            recipe.put(dishNumber, dataSnapshot.child("likes").child(dishNumber).getValue(Long.class).intValue() + 1);
                                        else if(startLikeStatus && !isLikePressed)
                                            recipe.put(dishNumber, dataSnapshot.child("likes").child(dishNumber).getValue(Long.class).intValue() - 1);

                                        mDatabase.child("recipes").child(groupOfDishes).child("likes").updateChildren(recipe);
                                    }
                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {
                                        Toast.makeText(like.getContext(),"Ошибка", Toast.LENGTH_SHORT).show();
                                    }
                                });
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Toast.makeText(like.getContext(),"Ошибка", Toast.LENGTH_SHORT).show();
                    }
                });

        if(FirebaseAuth.getInstance().getCurrentUser() != null) {
            Map<String, Object> like = new HashMap<>();
            like.put(dishNumber, isLikePressed);
            mDatabase.child("user" + FirebaseAuth.getInstance().getUid()).child(groupOfDishes + "Likes").updateChildren(like);
        }
    }

    public void onLikeCLick(){
        like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (FirebaseAuth.getInstance().getCurrentUser() != null) {
                    if (!isLikePressed) {
                        like.setImageResource(R.drawable.ic_baseline_favorite_24);
                        isLikePressed = true;
                    } else {
                        like.setImageResource(R.drawable.ic_baseline_favorite_border_24);
                        isLikePressed = false;
                    }
                } else {
                    Toast.makeText(v.getContext(),
                            "Для того чтобы оценивать рецепты, вы должны зарегестрироваться",
                            Toast.LENGTH_SHORT).show();
                }
            }

        });
    }
}
