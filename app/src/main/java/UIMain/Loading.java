package UIMain;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import com.example.supercooker.Cook;
import com.example.supercooker.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Loading extends AppCompatActivity {

    private DatabaseReference myRef = FirebaseDatabase.getInstance().getReference();

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            myRef.child("user" + FirebaseAuth.getInstance().getUid()).addListenerForSingleValueEvent(
                    new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            try {
                                Cook.textColorId = dataSnapshot.child("textColorId").getValue(Long.class).intValue();
                                Cook.themeId = dataSnapshot.child("themeId").getValue(Long.class).intValue();
                                Cook.backgroundId = dataSnapshot.child("backgroundId").getValue(Long.class).intValue();
                                Cook.talkSpeed = dataSnapshot.child("talkSpeed").getValue(Long.class).intValue();
                                Cook.speechVerification = dataSnapshot.child("speechVerification").getValue(Long.class).intValue();
                                startActivity(new Intent(Loading.this, HomeScreen.class));
                                finishAfterTransition();
                            }
                            catch (Exception e){
                                startInitialization();
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            Toast.makeText(Loading.this,"Ошибка", Toast.LENGTH_SHORT).show();
                        }
                    });
        }
        else {
            startInitialization();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void startInitialization(){
        Cook.themeId = R.style.GreenAppTheme;
        Cook.backgroundId = R.drawable.app_background_darck;
        Cook.textColorId = R.style.TextColorForDarkBackground;
        Cook.talkSpeed = 100;
        Cook.speechVerification = 0;
        startActivity(new Intent(Loading.this,HomeScreen.class));
        finishAfterTransition();
    }
}