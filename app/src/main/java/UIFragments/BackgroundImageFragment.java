package UIFragments;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.example.supercooker.Cook;
import com.example.supercooker.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class BackgroundImageFragment extends Fragment implements View.OnClickListener {
    private View view;
    private TextView strokeForLightBackground,strokeForDarkBackground,strokeForMiddleBackground;
    private ImageView lightBackground,darkBackground,middleBackground;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.background_image,container,false);
        lightBackground = view.findViewById(R.id.lightBackground);
        darkBackground = view.findViewById(R.id.darkBackground);
        middleBackground = view.findViewById(R.id.middleBackground);
        lightBackground.setOnClickListener(this);
        darkBackground.setOnClickListener(this);
        middleBackground.setOnClickListener(this);
        strokeForLightBackground = view.findViewById(R.id.strokeForLightBackground);
        strokeForDarkBackground = view.findViewById(R.id.strokeForDarkBackground);
        strokeForMiddleBackground = view.findViewById(R.id.strokeForMiddleBackground);

        startSettings();
        return view;

    }

    private void startSettings() {
        switch (Cook.backgroundId) {
            case R.drawable.app_background_darck:
                strokeForDarkBackground.setBackgroundColor(Color.GREEN);
                break;
            case R.drawable.app_background_light:
                strokeForLightBackground.setBackgroundColor(Color.GREEN);
                break;
            case R.drawable.app_background_midle:
                strokeForMiddleBackground.setBackgroundColor(Color.GREEN);
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.lightBackground:
                Cook.backgroundId = R.drawable.app_background_light;
                Cook.textColorId = R.style.TextColorForLightBackground;
                strokeForLightBackground.setBackgroundColor(Color.GREEN);
                strokeForDarkBackground.setBackgroundColor(Color.RED);
                strokeForMiddleBackground.setBackgroundColor(Color.RED);
                break;

            case R.id.darkBackground:
                Cook.backgroundId = R.drawable.app_background_darck;
                Cook.textColorId = R.style.TextColorForDarkBackground;
                strokeForDarkBackground.setBackgroundColor(Color.GREEN);
                strokeForLightBackground.setBackgroundColor(Color.RED);
                strokeForMiddleBackground.setBackgroundColor(Color.RED);
                break;
            case R.id.middleBackground:
                Cook.backgroundId = R.drawable.app_background_midle;
                Cook.textColorId = R.style.TextColorForMiddleBackground;
                strokeForMiddleBackground.setBackgroundColor(Color.GREEN);
                strokeForLightBackground.setBackgroundColor(Color.RED);
                strokeForDarkBackground.setBackgroundColor(Color.RED);
                break;
        }
    }
}
