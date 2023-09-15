package UIMain;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.supercooker.Cook;
import com.example.supercooker.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Settings extends AppCompatActivity {

    private DatabaseReference myRef = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setTheme(R.style.SettingAppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(FirebaseAuth.getInstance().getCurrentUser() != null){
                    Map<String, Object> defaultSettings = new HashMap<>();
                    defaultSettings.put("textColorId", Cook.textColorId);
                    defaultSettings.put("themeId",Cook.themeId);
                    defaultSettings.put("backgroundId",Cook.backgroundId);
                    defaultSettings.put("talkSpeed",Cook.talkSpeed);
                    defaultSettings.put("speechVerification",Cook.speechVerification);
                    myRef.child("user" + FirebaseAuth.getInstance().getUid()).updateChildren(defaultSettings);
                }
                Intent intent2 = new Intent(v.getContext(), HomeScreen.class);
                startActivity(intent2);
                overridePendingTransition(R.anim.left_out,R.anim.my_anim);
            }
        });
    }
}
