package com.example.hp.braintrainer;

import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.CountDownTimer;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static java.lang.Math.random;

public class MainActivity extends AppCompatActivity {
    String strings[]={" evaluate 56/8-4"," evaluate 7*12+6","evaluate 15-6+56"," evaluate 76/2+3","evaluate 7*2","evaluate 76*4*0*45","remainder in 64/3","evaluate 5*4*3*2*1","cos(60)+sin(30)=","x^2+12^2=13^2.find x","which of these is an acute angle"};
    int ans[][]={{3,6,7,8,1},{80,126,90,100,3},{66,67,68,65,4},{42,32,41,40,3},{11,14,13,15,2},{565,2562,0,56,3,},{2,1,0,3,2},{24,120,130,125,2},{0,1,2,-1,2},{1,5,3,4,2},{90,135,45,120,3}};
    int i=0,s=0,correct=0,incorrect=0,bonus=0;
    TextView name,instruc,op1,op2,op3,op4,que,score,finalscore,next,result,time;
    Button pa,button,help;
    MediaPlayer m1,m2,m3,m4;
    CountDownTimer timer;
    public void work(View view){
        TextView op=(TextView) view;

        int x=Integer.parseInt(op.getTag().toString());
        result=(TextView)findViewById(R.id.result);
        if(x==ans[i][4]){
            result.setText("CORRECT");
            result.setTextColor(Color.GREEN);
            result.animate().alpha(1);
            m3.start();
            s+=10;
            correct++;

            }
         else{
            result.setText("WRONG");
            result.setTextColor(Color.RED);
            result.animate().alpha(1);
            m4.start();
            s-=5;
            incorrect++;
        }

        score.setText(Integer.toString(s));
        op1.setEnabled(false);
        op2.setEnabled(false);
        op3.setEnabled(false);
        op4.setEnabled(false);

    }
    public void update(int i){
        que.setText(strings[i]);
        op1.setText(Integer.toString(ans[i][0]));
        op2.setText(Integer.toString(ans[i][1]));
        op3.setText(Integer.toString(ans[i][2]));
        op4.setText(Integer.toString(ans[i][3]));

    }
    public void nextwork(View view){
        if(i==10){

            timer.cancel();
            if(correct==11) {
                bonus += Integer.parseInt(time.getText().toString());
                s += bonus;
                score.setText(Integer.toString(s));
            }
            finish();
        }
        else {
            i++;
            op1.setEnabled(true);
            op2.setEnabled(true);
            op3.setEnabled(true);
            op4.setEnabled(true);
            result.animate().alpha(0);
            update(i);
        }
    }

    public void again(View view){
        pa.setEnabled(false);
        pa.animate().alpha(0);
        i=0;
        correct=0;
        incorrect=0;
        s=0;
        bonus=0;
        op1.setEnabled(true);
        op2.setEnabled(true);
        op3.setEnabled(true);
        op4.setEnabled(true);
        op1.animate().alpha(1);
        op2.animate().alpha(1);
        op3.animate().alpha(1);
        op4.animate().alpha(1);
        que.animate().alpha(1);
        result.animate().alpha(1);
        result.setText("");
        finalscore.setText("");
        finalscore.animate().translationYBy(500);
        next.animate().alpha(1);
        next.setEnabled(true);
        score.setText("0");

        update(i);
        startgame();
    }
    public void finish(){
        m2.start();
        op1.animate().alpha(0);
        op2.animate().alpha(0);
        op3.animate().alpha(0);
        op4.animate().alpha(0);
        que.animate().alpha(0);
        op1.setEnabled(false);
        op2.setEnabled(false);
        op3.setEnabled(false);
        op4.setEnabled(false);
        result.animate().alpha(0);
        next.animate().alpha(0);
        next.setEnabled(false);

        finalscore=(TextView)findViewById(R.id.finalscore);
        finalscore.setText("\t\t\tRESULT\n\nAttempt:"+Integer.toString(correct+incorrect)+"\nCorrect:"+Integer.toString(correct)+"\nIncorrect:"+Integer.toString(incorrect)+"\nBonus:"+Integer.toString(bonus)+"\nScore:"+Integer.toString(s));

        finalscore.animate().translationYBy(-500).setDuration(1000);
        pa.setEnabled(true);
        pa.animate().alpha(1).setDuration(1000);
    }
    public void startgame(){
            pa.setEnabled(false);
            timer=new CountDownTimer(30000,1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                m1.start();
                int y=(int)millisUntilFinished;
                time.setText(Integer.toString(y/1000));
            }

            @Override
            public void onFinish() {
                finish();
            }
        };
        timer.start();
        update(i);
    }
    public void begin(View view){

        button.animate().alpha(0);
        button.setEnabled(false);
        help.animate().alpha(0);
        help.setEnabled(false);
        name.animate().alpha(0);
        instruc.animate().alpha(0);
        op1.animate().alpha(1);
        op2.animate().alpha(1);
        op3.animate().alpha(1);
        op4.animate().alpha(1);
        que.animate().alpha(1);
        result.animate().alpha(1);
        next.animate().alpha(1);
        next.setEnabled(true);
        op1.setEnabled(true);
        op2.setEnabled(true);
        op3.setEnabled(true);
        op4.setEnabled(true);

        ImageView imageView=(ImageView)findViewById(R.id.imageView);
        imageView.animate().alpha(1);
        score.animate().alpha(1);
        TextView text1=(TextView)findViewById(R.id.textView4);
        TextView text2=(TextView)findViewById(R.id.textView5);
        time.animate().alpha(1);
        text1.animate().alpha(1);
        text2.animate().alpha(1);
        startgame();

    }
    public void helpwork(View view){
        instruc.setText("1.You are given 30 sec.\n2.Try to solve maximum problems\n3. +10 for every correct response\n4.-5 for every incorrect response\n5.You can skip problems as well ");
        instruc.animate().alpha(1);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         que=(TextView)findViewById(R.id.que);
         op1=(TextView)findViewById(R.id.op1);
         op2=(TextView)findViewById(R.id.op2);
         op3=(TextView)findViewById(R.id.op3);
         op4=(TextView)findViewById(R.id.op4);
         next=(TextView)findViewById(R.id.next);
         time=(TextView)findViewById(R.id.time);
        score=(TextView)findViewById(R.id.score);
         pa=(Button)findViewById(R.id.pa);
        button=(Button)findViewById(R.id.play);
        help=(Button)findViewById(R.id.help);
        result=(TextView)findViewById(R.id.result);
        name=(TextView)findViewById(R.id.name);
        instruc=(TextView)findViewById(R.id.instruc);
        m1=MediaPlayer.create(this,R.raw.tick);
        m2=MediaPlayer.create(this,R.raw.horn);
        m3=MediaPlayer.create(this,R.raw.correct);
        m4=MediaPlayer.create(this,R.raw.wrong);

        op1.setEnabled(false);
        op2.setEnabled(false);
        op3.setEnabled(false);
        op4.setEnabled(false);
        pa.setEnabled(false);
        next.setEnabled(false);


    }
}
