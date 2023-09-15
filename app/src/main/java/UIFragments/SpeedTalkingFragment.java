package UIFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;

import androidx.fragment.app.Fragment;

import com.example.supercooker.Cook;
import com.example.supercooker.R;

public class SpeedTalkingFragment extends Fragment implements View.OnClickListener  {

    private View view;
    private SeekBar seekBar;
    private Button button;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.speed_talking,container,false);
        seekBar = view.findViewById(R.id.seekBar);
        button = view.findViewById(R.id.set);
        button.setOnClickListener(this);

        startSettings();

        return view;
    }

    private void startSettings() {
        seekBar.setProgress(Cook.talkSpeed);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.set:
                Cook.talkSpeed = 450 - seekBar.getProgress();
        }
    }
}
