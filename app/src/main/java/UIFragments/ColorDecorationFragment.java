package UIFragments;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class ColorDecorationFragment extends Fragment implements View.OnClickListener {
    View view;
    TextView yellow,green,blue,red;
    TextView yellowStrokePanel,greenStrokePanel,blueStrokePanel,redStrokePanel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.color_decoration,container,false);
        yellow = view.findViewById(R.id.yellowColor);
        green = view.findViewById(R.id.greenColor);
        blue = view.findViewById(R.id.blueColor);
        red = view.findViewById(R.id.redColor);
        yellow.setOnClickListener(this);
        green.setOnClickListener(this);
        blue.setOnClickListener(this);
        red.setOnClickListener(this);
        yellowStrokePanel = view.findViewById(R.id.yellowStrokePanel);
        greenStrokePanel = view.findViewById(R.id.greenStrokePanel);
        blueStrokePanel = view.findViewById(R.id.blueStrokePanel);
        redStrokePanel = view.findViewById(R.id.redStrokePanel);

        startSettings();

        return view;
    }

    private void startSettings() {
        switch (Cook.themeId) {
            case R.style.YellowAppTheme:
                yellowStrokePanel.setBackgroundColor(Color.GREEN);
                break;
            case R.style.GreenAppTheme:
                greenStrokePanel.setBackgroundColor(Color.GREEN);
                break;
            case R.style.BlueAppTheme:
                blueStrokePanel.setBackgroundColor(Color.GREEN);
                break;
            case R.style.RedAppTheme:
                redStrokePanel.setBackgroundColor(Color.GREEN);
                break;
        }
    }

    public void onClick(View v){
        switch (v.getId()) {

            case R.id.yellowColor:
                Cook.themeId = R.style.YellowAppTheme;
                yellowStrokePanel.setBackgroundColor(Color.GREEN);
                redStrokePanel.setBackgroundColor(Color.RED);
                greenStrokePanel.setBackgroundColor(Color.RED);
                blueStrokePanel.setBackgroundColor(Color.RED);
                break;
            case R.id.greenColor:
                Cook.themeId = R.style.GreenAppTheme;
                greenStrokePanel.setBackgroundColor(Color.GREEN);
                redStrokePanel.setBackgroundColor(Color.RED);
                yellowStrokePanel.setBackgroundColor(Color.RED);
                blueStrokePanel.setBackgroundColor(Color.RED);
                break;
            case R.id.blueColor:
                Cook.themeId = R.style.BlueAppTheme;
                blueStrokePanel.setBackgroundColor(Color.GREEN);
                redStrokePanel.setBackgroundColor(Color.RED);
                greenStrokePanel.setBackgroundColor(Color.RED);
                yellowStrokePanel.setBackgroundColor(Color.RED);
                break;
            case R.id.redColor:
                Cook.themeId = R.style.RedAppTheme;
                redStrokePanel.setBackgroundColor(Color.GREEN);
                blueStrokePanel.setBackgroundColor(Color.RED);
                greenStrokePanel.setBackgroundColor(Color.RED);
                yellowStrokePanel.setBackgroundColor(Color.RED);
                break;
        }

    }
}
