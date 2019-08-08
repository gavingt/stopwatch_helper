package com.monykaushik17.stopwatch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class Home extends AppCompatActivity {

    private static int counter;
    private static boolean running = false;
    private static Handler handler = new Handler();
    private ArrayAdapter<String> arrayAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,LaptimesContainer.lapTimesArrayList);
        ListView listView = (ListView) findViewById(R.id.lapListView);
        listView.setAdapter(arrayAdapter);

        final TextView buttonStopStart = findViewById(R.id.button_stop_start);
        buttonStopStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                running = !running;

                if (running) {
                    buttonStopStart.setText("stop");
                    runner();
                } else {
                    buttonStopStart.setText("start");
                    handler.removeCallbacksAndMessages(null);
                }
            }
        });

    }


    public void onClickLap(View view){
        LaptimesContainer.lapTimesArrayList.add(getTime(counter));
        arrayAdapter.notifyDataSetChanged();

    }

    public void runner(){
        final TextView timeView = (TextView) findViewById(R.id.timeText);

        handler.post(new Runnable() {
            @Override
            public void run() {
                handler.postDelayed(this,23);

                if(running){
                    counter+=25;
                    timeView.setText(getTime(counter));
                }
            }
        });
    }


    public static String getTime(int counter){
        int hours= counter / 3600000;
        int minutes = (counter % 3600000)/60000;
        int second = ((counter % 3600000)%60000)/1000;
        int ms = ((counter % 3600000)%60000)%1000;
        return String.format("%02d:%02d:%02d:%03d", hours, minutes,second,ms);
    }
}
