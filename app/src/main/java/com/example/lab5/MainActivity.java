package com.example.lab5;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import org.w3c.dom.Text;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{
    TextView lblCounter;
    Button btnStart, btnStop,btn5sec,btn10sec,btn15sec;
    int counter = 0;
    boolean running = false;
    int last=0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lblCounter = (TextView) findViewById(R.id.textView);
        btnStart = (Button) findViewById(R.id.buttonstart);
        btnStop = (Button) findViewById(R.id.buttonstop);
        btn5sec = (Button) findViewById(R.id.sec5);
        btn10sec = (Button) findViewById(R.id.sec10);
        btn15sec = (Button) findViewById(R.id.sec15);
        btnStop.setOnClickListener(this);
        btnStart.setOnClickListener(this);
        btn5sec.setOnClickListener(this);
        btn10sec.setOnClickListener(this);
        btn15sec.setOnClickListener(this);
    }

    public void onClick(View v) {
        if (v.equals(btnStart)) {
            counter = 0;
            running = true;
            new MyCounter().start();
        }
        else if(v.equals(btn5sec)){
            btn15sec.setSelected(false);
            btn10sec.setSelected(false);
            last=5;
        }
        else if(v.equals(btn10sec)){
            btn5sec.setSelected(false);
            btn15sec.setSelected(false);
            last=10;
        }
        else if(v.equals(btn15sec)){
            btn5sec.setSelected(false);
            btn10sec.setSelected(false);
            last=15;
        }
        else if (v.equals(btnStop)) {
            running = false;
        }
    }
    Handler handler = new Handler()
    {
        public void handleMessage(Message m)
        {
            lblCounter.setText(String.valueOf(m.what));
        }
    };
    class MyCounter extends Thread
    {
        public void run()
        {
            while (running&&counter<last)
            {
                counter++;
                handler.sendEmptyMessage(counter);
                try {
                    Thread.sleep(1000);
                }
                catch (Exception e) {}
            }
        }
    }
}