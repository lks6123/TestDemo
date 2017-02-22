package com.shao.testdemo.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.shao.testdemo.R;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity extends AppCompatActivity {
    protected static String TAG_LOG = null;// Log tag
    protected Context mContext = null;//context
    private Unbinder mUnbinder ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
//        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        init(savedInstanceState);

    }

    private void init(Bundle savedInstanceState) {
        mContext = this;
        TAG_LOG = this.getClass().getSimpleName();
        BaseAppManager.getInstance().addActivity(this);
        if (getContentViewID() != 0) {
            this.setContentView(getContentViewID());
//            LayoutInflater.from(this).inflate(getContentViewID(), null);
            mUnbinder = ButterKnife.bind(this);
            Log.d(TAG_LOG,"Binder" + this.getContentViewID()+this.getClass().getSimpleName());
        }
        initViews(savedInstanceState);
    }


    /**
     * @return 当前要设置给activity的contentViewId
     */
    public abstract int getContentViewID();

    protected abstract void initViews(Bundle savedInstanceState);

    @Override
    public void finish() {
        super.finish();
        BaseAppManager.getInstance().removeActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mUnbinder != null) {
            mUnbinder.unbind();
            mUnbinder = null;
        }
    }

    /**
     * @param tarActivity 要打开的activity
     */
    public void startActivity(Class<? extends Activity> tarActivity) {
        Intent intent = new Intent(this, tarActivity);
        startActivity(intent);
    }

    /**
     * 通过intent打开一个activity
     *
     * @param tarActivity 要打开的activity
     * @param options     options
     */
    //
    public void startActivity(Class<? extends Activity> tarActivity, Bundle options) {
        Intent intent = new Intent(this, tarActivity);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            startActivity(intent, options);
        } else {
            startActivity(intent);
        }
    }


    /**
     * 重新载入当前的activity
     */
    public void reload() {
        Intent intent = getIntent();
        overridePendingTransition(0, 0);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        finish();
        overridePendingTransition(0, 0);
        startActivity(intent);
    }


    /**
     * 设置Toolbar
     *
     * @param toolbar 要设置的toolbar
     * @param title   toolbar标题
     */
    protected void setToolBar(Toolbar toolbar, String title) {
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationIcon(R.mipmap.ic_arrow_back_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                onBackPressedSupport();
                onBackPressed();
            }
        });
    }
}
