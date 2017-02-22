package com.shao.testdemo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.shao.testdemo.base.BaseActivity;
import com.shao.testdemo.ui.FourFragment;
import com.shao.testdemo.ui.HomeFragment;
import com.shao.testdemo.ui.ThreeFragment;
import com.shao.testdemo.ui.TwoFragment;

import java.util.ArrayList;

import butterknife.BindView;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    public static final String SELECTED_TEXT_VIEW = "SelectedTextView";

    @BindView(R.id.txt_deal)
    TextView tabDeal;
    @BindView(R.id.txt_poi)
    TextView tabPoi;
    @BindView(R.id.txt_user)
    TextView tabUser;
    @BindView(R.id.txt_more)
    TextView tabMore;

    private ArrayList<Fragment> fragments;
    private ArrayList<TextView> textViewList;
    //被选中的TextView在textViewList中的位置
    private int selected;
    private FragmentManager mFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getContentViewID() {
        return R.layout.activity_main;
    }
    //遍历TextView集合，将选中的TextView设置为未选中状态
    public void unSelectedAll() {
        for (TextView textView : textViewList) {
            if (textView.isSelected()) {
                textView.setSelected(false);
            }
        }
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        //初始化数据
        initData();
        setDefaultFragment();
        initTextViewClickListener();
    }
    //初始化所有的textView的单击监听
    private void initTextViewClickListener() {
        tabDeal.setOnClickListener(this);
        tabMore.setOnClickListener(this);
        tabUser.setOnClickListener(this);
        tabPoi.setOnClickListener(this);
    }

    private void initData() {
        initTextViewLists();
        initFragments();
    }

    //初始化Fragment集合
    private void initFragments() {
        fragments = new ArrayList<>();
        fragments.add(HomeFragment.newInstance("FragmentOne"));
        fragments.add(TwoFragment.newInstance("FragmentTwo"));
        fragments.add(ThreeFragment.newInstance("FragmentThree"));
        fragments.add(FourFragment.newInstance("FragmentFour"));
        //初始化FragmentManager
        if (mFragmentManager == null){
            mFragmentManager = getSupportFragmentManager();
        }
    }

    //初始化TextView集合
    private void initTextViewLists() {
        Log.d(TAG_LOG,"创建TextViewLists");
        textViewList = new ArrayList<>();
        textViewList.add(tabDeal);
        textViewList.add(tabPoi);
        textViewList.add(tabUser);
        textViewList.add(tabMore);
    }

    //初始化刚进入时的Fragment
    private void setDefaultFragment() {
        Log.d(TAG_LOG,"加载默认的Fragment");
        Fragment fragment = mFragmentManager.findFragmentById(R.id.fram_main_activity);
        //防止旋转屏幕之后重复加载
        if(fragment == null ) {
            tabDeal.setSelected(true);
            fragment = fragments.get(0);
            mFragmentManager.beginTransaction().add(R.id.fram_main_activity,fragment).commit();
            Log.d(TAG_LOG,"检查屏幕是否重复加载");
        }
    }

    //保存屏幕旋转时被选中的TextView在List中的位置，以便Activity重建之后知道恢复TextView被选中状态
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(SELECTED_TEXT_VIEW,selected);
    }

    //恢复被选中的TextView状态
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        int anInt = savedInstanceState.getInt(SELECTED_TEXT_VIEW);
        Log.d(TAG_LOG,"被选中的要保存的TextView的序号是"+anInt);
        TextView view = textViewList.get(anInt);
        if (view != null) {
            view.setSelected(true);
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.txt_deal:
                switchFragment(0);
                break;
            case R.id.txt_poi:
                switchFragment(1);
                break;
            case R.id.txt_user:
                switchFragment(2);
                break;
            case R.id.txt_more:
                switchFragment(3);
                break;
        }
    }

    //切换Fragment
    private void switchFragment(int index) {
        unSelectedAll();
        textViewList.get(index).setSelected(true);
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        transaction.replace(R.id.fram_main_activity, fragments.get(index)).commit();
        selected = index;
    }
}
