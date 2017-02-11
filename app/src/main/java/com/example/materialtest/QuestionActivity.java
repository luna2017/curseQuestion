package com.example.materialtest;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.EventLogTags;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Random;

public class QuestionActivity extends AppCompatActivity {
    private TextView mshow_answer;
    private Button mconfirm_button;
    private TextView mshow_question;
    private TextView mshow_score;
    private ArrayList<MathQuestion> mqList=new ArrayList<>();
    private int optionNumberA;
    private Random rand=new Random();
    private MathQuestion mq;
    private EditText minput_question;
    private String strShowAnswer;
    private String strInputquestion;
    private int manswer_number=1;
    private Button mnext_button;
    private int score;   //显示分数

    private static final String TAG = "QuestionActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        mshow_answer=(TextView)findViewById(R.id.show_answer);
        mconfirm_button=(Button)findViewById(R.id.comfirm_button);
        minput_question=(EditText)findViewById(R.id.input_question);  //输入框
        mshow_question=(TextView)findViewById(R.id.show_question);   //显示问题
        mshow_score=(TextView)findViewById(R.id.show_score);
        loadScore();
        show_score();
        show_querstion();


        mconfirm_button.setOnClickListener(new View.OnClickListener(){


            @Override
            public void onClick(View v) {

                strInputquestion=minput_question.getText().toString();
                if(String.valueOf(mq.getResult()).equals(strInputquestion)){
                    score++;
                    show_score();
                    Toast.makeText(QuestionActivity.this,"正确",Toast.LENGTH_SHORT).show();
                    //mshow_question.setText("答案正确"+String.valueOf(mq.getResult()));

                }else{
                    Toast.makeText(QuestionActivity.this,"错误",Toast.LENGTH_SHORT).show();
                    //mshow_question.setText("答案错误"+String.valueOf(mq.getResult()));
                }
                mshow_question.setText("正确答案是 "+String.valueOf(mq.getResult()));

            }
        });
        mnext_button=(Button)findViewById(R.id.next_button);
        mnext_button.setOnClickListener(new View.OnClickListener(){


            @Override
            public void onClick(View v) {
                mshow_question.setText("显示答案");
                if (minput_question.getText().toString().trim().equals(""))
                    Toast.makeText(QuestionActivity.this,"请输入答案",Toast.LENGTH_SHORT).show();
                else {
                    manswer_number++;

                    show_querstion();
                }
            }
        });



    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
        saveData();
    }


    private void show_querstion(){
        mq = new MathQuestion("+", rand.nextInt(20), rand.nextInt(20));
        strShowAnswer = mq.getOptionNumberA() + "  +  " + mq.getOptionNumberB();
        mshow_answer.setText("第 " + manswer_number + " 题" +"\r\n"+ strShowAnswer);



    }
    private void show_score(){
        mshow_score.setText("分数: "+score);
    }
    private void saveData(){  //保存分数
        SharedPreferences.Editor editor=getSharedPreferences("data",MODE_PRIVATE).edit();

        editor.putInt("score",score);
        Log.d(TAG, "saveData: "+score);
        editor.commit();
    }
    private void loadScore(){   //加载分数
        SharedPreferences pref=getSharedPreferences("data",MODE_PRIVATE);

        score=pref.getInt("score",0);

    }

}
