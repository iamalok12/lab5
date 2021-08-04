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
    Button btnStart, btnStop,btnReset;
    int counter = 0;
    boolean running = false;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lblCounter = (TextView) findViewById(R.id.textView);
        btnStart = (Button) findViewById(R.id.buttonstart);
        btnReset=(Button)findViewById(R.id.button_reset);
        btnStop = (Button) findViewById(R.id.buttonstop);
        btnStop.setOnClickListener(this);
        btnStart.setOnClickListener(this);
    }

    public void onClick(View v) {
        if (v.equals(btnStart)) {
            counter = 0;
            running = true;
            new MyCounter().start();
        } else if (v.equals(btnStop)) {
            running = false;
        }
        else if(v.equals(btnReset)){
            counter=0;
            running=false;
            handler.sendEmptyMessage();
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
            while (running)
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
