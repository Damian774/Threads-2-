package com.example.pwsz.myapplication;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Handler hand;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView tv1 = findViewById(R.id.TV_show_thread);
        hand = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                tv1.append((String)msg.obj);
            }
        };

        final int start_ = 1;
        final int stop_ = 100;
        final int step_ = 1000;

        final Thread w1 = new Thread(new Runnable() {
            @Override
            public void run() {
                int i ;
                for(i=start_;i<stop_;i++){
                    //tv1.append(" "+i);
                    Message msg1 = new Message();
                    msg1.obj=" "+i;
                    hand.sendMessage(msg1);
                    try {
                        Thread.sleep(step_);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        Button b = findViewById(R.id.button1);
        b.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                w1.start();
            }
        });

    }

    }


/*
PD
1.stworzyc nowe wątki
2.button tworzy nowy wątek

button clear screen i button
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