package com.example.stylesanddrawables;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ImageButton incWifiLevel, decWifiLevel;
    private ImageButton turnOffWifi;
    private ImageView wifiIndicator;
    private TextView textlevel;
    private int level = 4;
    private int images[] = {R.drawable.ic_baseline_wifi_lvl0, R.drawable.ic_baseline_wifi_lvl1, R.drawable.ic_baseline_wifi_lvl2, R.drawable.ic_baseline_wifi_lvl3, R.drawable.ic_baseline_signal_wifi_4_bar_24, R.drawable.ic_baseline_signal_wifi_off_24};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        incWifiLevel = (ImageButton) findViewById(R.id.add);
        decWifiLevel = (ImageButton) findViewById(R.id.remove);
        turnOffWifi = (ImageButton) findViewById(R.id.wifi_off);
        wifiIndicator = (ImageView) findViewById(R.id.wifi_indicator);
        textlevel = (TextView) findViewById(R.id.text_level);

       // Log.d("Change Level", "hlo");

        if (savedInstanceState != null)
            onRestoreInstanceState(savedInstanceState);

        incWifiLevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (level == 4)
                    textlevel.setText("Maximum level reached");
                else if (level == 5)
                    level = 0;
                else if (level >= -1 && level <= 3)
                    level++;
               // Log.d("Change Level", "hlo" + level);
                wifiIndicator.setImageResource(images[level]);
                textlevel.setText("( Level : " + level + " )");
            }
        });
        decWifiLevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (level == 0)
                    textlevel.setText("Minimum level reached");
                else if (level > 0 && level <= 4)
                    level--;
                //Log.d("Change Level", "hlo" + level);
                wifiIndicator.setImageResource(images[level]);
                textlevel.setText("( Level : " + level + " )");
            }
        });
        turnOffWifi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (level != 5) {
                    level = 5;
                    textlevel.setText("Wi-fi turned off");
                } else {
                    level = 4;
                    textlevel.setText("( Level : " + level + " )");
                }
                wifiIndicator.setImageResource(images[level]);

            }
        });
    }

    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        level = savedInstanceState.getInt("Level");
        CharSequence t = savedInstanceState.getCharSequence("txt");
        textlevel.setText(t);
        int i = savedInstanceState.getInt("imgView");
        wifiIndicator.setImageResource(i);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("Level", level);
        outState.putCharSequence("txt", textlevel.getText());
        outState.putInt("imgView", wifiIndicator);
    }

    /*@Override
    public void onStart()
    {
        super.onStart();
        wifiIndicator.setImageResource(images[level]);
    }*/
}