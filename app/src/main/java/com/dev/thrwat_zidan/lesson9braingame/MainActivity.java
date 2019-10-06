package com.dev.thrwat_zidan.lesson9braingame;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button btn_start,button0,button1,button2,button3,btn_palyAgain;
    ArrayList<Integer> answers=new ArrayList<Integer>();
    int location_of_correct_answer;
    int incorrect_answer;

    int score=0;
    int numberofQuiestion = 0;

    Boolean isGameRun = true;

    TextView txt_sum,txt_Questions_points,txt_result, txt_timer;

    RelativeLayout gameRelativeLayout;
    MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_start = findViewById(R.id.btn_start);
        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        txt_sum = findViewById(R.id.sumTextview);
        txt_timer = findViewById(R.id.txt_timer);
        txt_result = findViewById(R.id.txt_result);
        txt_Questions_points = findViewById(R.id.txt_Questions);
        btn_palyAgain = findViewById(R.id.palyAgain);
        gameRelativeLayout = findViewById(R.id.gameRelativeLayout);
        mediaPlayer = MediaPlayer.create(MainActivity. this, R.raw.horn);




    }

    public void start(View view) {
        btn_start.setVisibility(view.INVISIBLE);
        gameRelativeLayout.setVisibility(view.VISIBLE);
        playAgain(findViewById(R.id.palyAgain));
        mediaPlayer.stop();
        isGameRun = true;

    }

    public void choseAnsewer(View view) {
if (isGameRun){



if (view.getTag().toString().equals(Integer.toString(location_of_correct_answer))){
    score++;
    txt_result.setText("Correct");

    //Log.i("Correct", "correct");

}else{
    txt_result.setText("Wrong");
}
        numberofQuiestion++;
txt_Questions_points.setText(Integer.toString(score)+"/"+Integer.toString(numberofQuiestion ));
GeneratQuestions();
}
    }

    public void  GeneratQuestions(){
        Random rand = new Random();

        int a = rand.nextInt(21);
        int b = rand.nextInt(21);

        txt_sum.setText(Integer.toString(a)+"+"+Integer.toString(b));

        location_of_correct_answer = rand.nextInt(4);

        answers.clear();


        for (int i= 0; i <4  ; i++) {

            if (i==location_of_correct_answer){
                answers.add(a + b);
            }else{
                incorrect_answer=(rand.nextInt(41));

                while (incorrect_answer==a+b){

                    incorrect_answer=(rand.nextInt(41));
                }
                answers.add(incorrect_answer);

            }

        }

        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));
    }

    public void  playAgain(final View view) {
        score = 0;
        numberofQuiestion = 0;
        txt_timer.setText("30s");
        txt_Questions_points.setText("0/0");
        txt_result.setText("");
        btn_palyAgain.setVisibility(view.INVISIBLE);
        GeneratQuestions();
        mediaPlayer.stop();
        isGameRun = true;

        new CountDownTimer(30100, 1000) {

            @Override
            public void onTick(long l) {
                txt_timer.setText(String.valueOf(l / 1000)+"s");

            }

            @Override
            public void onFinish() {
                txt_timer.setText("0s");
                txt_result.setText("Your Score: "+Integer.toString(score)+"/"+Integer.toString(numberofQuiestion ));
                btn_palyAgain.setVisibility(view.VISIBLE);
                mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.horn);
                mediaPlayer.start();
                isGameRun = false;

            }
        }.start();


    }
}
