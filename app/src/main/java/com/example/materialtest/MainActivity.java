package com.example.materialtest;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends BaseActivity {

    private DrawerLayout mDrawerLayout;
    private static final String TAG = "MainActivity";
    private int choose_curse;
    private Fun[] fruits = {new Fun("打球", R.drawable.ball,R.drawable.star0,0), new Fun("爬山", R.drawable.climb,R.drawable.star1,1),
            new Fun("角色扮演", R.drawable.cosplay,R.drawable.star3,3), new Fun("抓螃蟹", R.drawable.crab,R.drawable.star8,8),
            new Fun("美食", R.drawable.delicious,R.drawable.star3,3), new Fun("公园", R.drawable.flower,R.drawable.star8,8),
            new Fun("自由活动", R.drawable.free,R.drawable.star5,5), new Fun("找小朋友", R.drawable.friend,R.drawable.star9,9),
            new Fun("模拟打仗", R.drawable.gunfight,R.drawable.star6,6), new Fun("看电影", R.drawable.movie,R.drawable.star10,10),
            new Fun("读书", R.drawable.read,R.drawable.star7,7), new Fun("骑车", R.drawable.ride,R.drawable.star3,3),
            new Fun("滑冰", R.drawable.skate,R.drawable.star4,4), new Fun("玩手机", R.drawable.smarthphone,R.drawable.star5,5),
            new Fun("石头公园", R.drawable.stonepark,R.drawable.star5,5), new Fun("游泳", R.drawable.swim,R.drawable.star9,9),
            new Fun("平板电脑", R.drawable.tablet,R.drawable.star1,6), new Fun("玩具", R.drawable.toy,R.drawable.star8,8),
            new Fun("玩水", R.drawable.water,R.drawable.star2,7), new Fun("暂定", R.drawable.pend1,R.drawable.star0,0),
            new Fun("暂定", R.drawable.pend2,R.drawable.star4,4),new Fun("暂定", R.drawable.pend5,R.drawable.star0,0),
            new Fun("暂定", R.drawable.pend3,R.drawable.star10,10),new Fun("暂定", R.drawable.pend6,R.drawable.star7,7),
            new Fun("暂定", R.drawable.pend4,R.drawable.star9,9),};

    private List<Fun> fruitList = new ArrayList<>();

    private FunAdapter adapter;

    private SwipeRefreshLayout swipeRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navView = (NavigationView) findViewById(R.id.nav_view);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        }
        navView.setCheckedItem(R.id.nav_maths);
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                mDrawerLayout.closeDrawers();
                saveData(QuestionDatabase.code_maths);
                Toast.makeText(MainActivity.this,"选中数学",Toast.LENGTH_SHORT).show();

                return true;
            }
        });
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                choose_curse=loadScore();
                String choose_item="请选择课程";
                switch(choose_curse){
                    case 0:
                        choose_item="请选择课程";
                        break;
                    case 1:
                        choose_item="当前已选择 生物";
                        break;
                    case 2:
                        choose_item="当前已选择 化学";
                        break;
                    case 3:
                        choose_item="当前已选择 语文";
                        break;
                    case 4:
                        choose_item="当前已选择 地理";
                        break;
                    case 5:
                        choose_item="当前已选择 历史";
                        break;
                    case 6:
                        choose_item="当前已选择 数学";
                        break;
                    case 7:
                        choose_item="当前已选择 物理";
                        break;
                    case 8:
                        choose_item="当前已选择 政治";
                        break;
                    case 9:
                        choose_item="当前已选择 英文";
                        break;
                    case 10:
                        choose_item="当前已选择 音乐";
                        break;


                }



                Snackbar.make(view, choose_item, Snackbar.LENGTH_SHORT).show();


            }
        });
        initFruits();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new FunAdapter(fruitList);
        recyclerView.setAdapter(adapter);
        swipeRefresh = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh);
        swipeRefresh.setColorSchemeResources(R.color.colorPrimary);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshFruits();
            }
        });
    }

    private void refreshFruits() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        initFruits();
                        adapter.notifyDataSetChanged();
                        swipeRefresh.setRefreshing(false);
                    }
                });
            }
        }).start();
    }

    private void initFruits() {
        fruitList.clear();
        for (int i = 0; i < 50; i++) {
            Random random = new Random();
            int index = random.nextInt(fruits.length);
            fruitList.add(fruits[index]);
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.backup:
                Toast.makeText(this, "You clicked Backup", Toast.LENGTH_SHORT).show();
                break;
            case R.id.delete:
                Toast.makeText(this, "You clicked Delete", Toast.LENGTH_SHORT).show();
                break;
            case R.id.settings:
                Toast.makeText(this, "You clicked Settings", Toast.LENGTH_SHORT).show();
                break;
            default:
        }
        return true;
    }
    private void saveData(int choose_code){  //保存分数

        SharedPreferences.Editor editor=getSharedPreferences("data",MODE_PRIVATE).edit();

        editor.putInt("curse",choose_code);

        editor.commit();
    }
    private int loadScore(){   //加载分数
        SharedPreferences pref=getSharedPreferences("data",MODE_PRIVATE);


        return pref.getInt("curse",0);
    }
}
