package com.example.materialtest;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class FunActivity extends BaseActivity {

    public static final String FUN_NAME = "fun_name";

    public static final String FUN_IMAGE_ID = "fun_image_id";

    public static final String FUN_STAR_ID="fun_star";

   private Button mrun_mathQuestion;
     private int curse;
    private static final String TAG = "FunActivity";
    String mSetCurse="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fun);
        Intent intent = getIntent();
        String funName = intent.getStringExtra(FUN_NAME);
        int funImageId = intent.getIntExtra(FUN_IMAGE_ID, 0);
        int funStarId=intent.getIntExtra(FUN_STAR_ID,0);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        ImageView funImageView = (ImageView) findViewById(R.id.fun_image_view);
        TextView funContentText = (TextView) findViewById(R.id.fun_content_text);
        mrun_mathQuestion=(Button)findViewById(R.id.run_mathQuestion);
        mrun_mathQuestion.setOnClickListener(new View.OnClickListener(){


            @Override
            public void onClick(View v) {

                if(curse==6){
                    Intent intent = new Intent(FunActivity.this, QuestionActivity.class);
                    startActivity(intent);
                }

            }
        });
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        collapsingToolbar.setTitle(funName);

        Glide.with(this).load(funImageId).into(funImageView);




        curse=loadScore();
        mSetCurse=getCurse(curse);
        String funContent = generatefunContent(funName,funStarId);
        funContentText.setText(funContent);
    }

    private String generatefunContent(String funName,int funStarId) {

        StringBuilder funContent = new StringBuilder();
        funContent.append(mSetCurse);
        funContent.append("\r\n"+funName+"启动需要\r\n"+"积攒"+funStarId+"个星星");

        return funContent.toString();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;


        }
        return super.onOptionsItemSelected(item);
    }

    private int loadScore(){   //加载分数
        SharedPreferences pref=getSharedPreferences("data",MODE_PRIVATE);


        return pref.getInt("curse",0);
    }

    private String getCurse(int curse){
        switch(curse){
            case 0:
                mSetCurse="请选择课程";
                break;
            case 1:
                mSetCurse="当前课程 生物";
                break;
            case 2:
                mSetCurse="当前课程 化学";
                break;
            case 3:
                mSetCurse="当前课程 语文";
                break;
            case 4:
                mSetCurse="当前课程 地理";
                break;
            case 5:
                mSetCurse="当前课程 历史";
                break;
            case 6:
                mSetCurse="当前课程 数学";
                break;
            case 7:
                mSetCurse="当前课程 物理";
                break;
            case 8:
                mSetCurse="当前课程 政治";
                break;
            case 9:
                mSetCurse="当前课程 英文";
                break;
            case 10:
                mSetCurse="当前课程 音乐";
                break;


        }
        return mSetCurse;

    }
}
