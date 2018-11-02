package com.example.pwsz.myapplication;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Handler hand;
     int start_;
     int stop_;
     int step_;
     EditText ET_start;
     EditText ET_end;
     EditText ET_step;
     ArrayList<Thread> threadList = new ArrayList<>();
    boolean stopAllThreads;
    TextView tv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ET_start = findViewById(R.id.editText_Start);
        ET_end = findViewById(R.id.editText_End);
        ET_step = findViewById(R.id.editText_Step);
        tv1 = findViewById(R.id.TV_show_thread);
    }



    public void startThread(View view) {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                int i ;
                for(i=start_;i<stop_;i++){

                    Message msg = new Message();
                    msg.obj=" "+i;
                    hand.sendMessage(msg);
                    try {
                        Thread.sleep(step_);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        Log.d("INTERRUPTEDEXCEPTION","Thread interrupted");
                        return;
                    }
                }

            }
        });
        threadList.add(thread);


        hand = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                tv1.append((String)msg.obj);
            }
        };



        try {
            start_ = Integer.parseInt(ET_start.getText().toString());
            stop_ = Integer.parseInt(ET_end.getText().toString());
            step_ = Integer.parseInt(ET_step.getText().toString());
            threadList.get(threadList.size()-1) .start();
        }catch(NumberFormatException e){
            e.printStackTrace();


        }



    }

    public void clearTV(View view) {
        tv1.setText("");
    }

    public void stopAllThreads(View view) {
        for (Thread t: threadList) {
            t.interrupt();
        }
    }
}


/*
HW
1.stworzyc nowe wątki
2.button tworzy nowy wątek

button clear screen i button kasuj wątki

b.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                w1.start();
            }
        });
 */


/*
//jeden z elementow programowych wykonywany na proc
//watek to proces bez wlasnej pamieci - ma pamiec wspoldzielona

handler  - dostaje sie do elementow graficznych - korzysta sie w wątkach - ma metode abstract handleMessage

1.Tworz obiekt Thread lub 1.dodaj interfejs Runnable
2.nadpisac metode abstract Run()
3.Metody beda wykonywane rownolegle
Thread musi byc w try catch InterruptedException printstacktrace
Thread p = new Thread(){
public void Run(){
for(;;) pętla nieskonczonego kodu
Thread.sleep(1000 ( ms )) // usypia wątek
}
}

// period  start  end
  1000      1     100
  2000      1     100
     [start watku]

     wątek1: 1
     wątek1: 2
     wątek2: 1
     wątek1: 3
     wątek1: 4
     wątek2: 2

*/