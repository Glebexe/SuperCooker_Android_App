package UISteak;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.supercooker.Cook;
import com.example.supercooker.R;

import pl.droidsonroids.gif.GifImageView;

public class Fry extends AppCompatActivity {

    private TextView vtimer;
    private int fryTime;
    private Button buttonnext,buttonprev;
    private GifImageView steak;
    private ImageView imgCook;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setTheme(Cook.textColorId);
        setTheme(Cook.themeId);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fry);
        findViewById(R.id.layout).setBackground(getDrawable(Cook.backgroundId));

        timer();
    }

    public void timer(){
        buttonnext = findViewById(R.id.buttonnext);
        buttonnext.setVisibility(View.GONE);

        fryTime = Cook.cookTime;
        vtimer = findViewById(R.id.timer);
        steak = findViewById(R.id.gif);
        imgCook = findViewById(R.id.cook);

        final TextView textView = findViewById(R.id.textView);
        textView.setText("4) Жарьте стейк " + fryTime/1000 + " секунд.");

        CountDownTimer timer = new CountDownTimer(fryTime*2+8000,1000){
            int vich = fryTime/1000+8;
            @Override
            public void onTick(long l) {
                if(l > fryTime+8000 || l < fryTime)
                    vtimer.setText("" + (l/1000-vich));
                if(l < fryTime+8000 && l > fryTime+7000){
                    steak.setImageResource(R.drawable.flip_steak);
                    notification("Переверните Стейк!");
                    textView.setText("4) Теперь переверните стейк.  ");
                    imgCook.setImageResource(R.drawable.cook_with_ringing_alarm_clock);
                    vich = 0;
                }
                if(l < fryTime+500 && l > fryTime-500) {
                    steak.setImageResource(R.drawable.fry2);
                    imgCook.setImageResource(R.drawable.cook_with_alarm_clock);
                    textView.setText("4) Жарьте его с другой стороны тоже " + fryTime/1000 + " секунд.  ");
                }
            }

            @Override
            public void onFinish() {
                notification("Перейдите к следующему шагу!");
                imgCook.setImageResource(R.drawable.cook_with_ringing_alarm_clock);
                buttonnext.setVisibility(View.VISIBLE);
            }
        }.start();

        addListenerOnButton(timer);
    }

    public void notification(String title){
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("1", "My channel",
                    NotificationManager.IMPORTANCE_HIGH);
            channel.setDescription("Дополнтельные настройки");
            channel.enableLights(true);
            channel.setLightColor(Color.RED);
            channel.enableVibration(false);
            notificationManager.createNotificationChannel(channel);
        }
        Intent intent = new Intent(getApplicationContext(),Fry.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(),0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this,"1")
                        .setAutoCancel(true)
                        .setContentTitle(title)
                        .setTimeoutAfter(10000)
                        .setSmallIcon(R.mipmap.steak_icon_round)
                        .setContentIntent(pendingIntent);

        Notification notification = builder.build();

        notificationManager.notify(1, notification);
    }


    public void addListenerOnButton(final CountDownTimer timer){
        buttonprev = findViewById(R.id.buttonprev);
        buttonnext.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(v.getContext(), RoastingSides.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.right_in,android.R.anim.slide_out_right);
                    }
                }
        );

        buttonprev.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        timer.cancel();
                        Intent intent = new Intent(v.getContext(), PutSteakOnPan.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.left_out,R.anim.my_anim);
                    }
                }
        );
    }
}
