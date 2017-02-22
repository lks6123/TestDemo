package com.shao.testdemo.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;



public abstract class BaseFragment extends Fragment {
    protected static String TAG_LOG = null;
    protected Context mContext;
    protected Activity mActivityContext;
    private Unbinder mUnBinder;

    @Override
    public void onAttach(Context context) {
        //获取Context对象
        mActivityContext = (Activity) context;
        mContext = context;
        super.onAttach(context);
        Log.d(TAG_LOG,"Fragment_onAttach");
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TAG_LOG = this.getClass().getSimpleName();
        loadData();
        Log.d(TAG_LOG,"Fragment_onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG_LOG,"Fragment_onCreateView");
        if (getLayoutView() != null) {
            return getLayoutView();
        }
        if (getLayoutId() != 0) {
            return inflater.inflate(getLayoutId(), null);
        } else {
            return super.onCreateView(inflater, container, savedInstanceState);
        }

    }

    /**
     * @return 重写这个方法的话,可以直接把一个View作为布局加载给Fragment
     */
    public View getLayoutView() {
        return null;
    }

    /**
     * @return Fragment的布局文件id
     */
    protected abstract int getLayoutId();

    /**
     * 在onCreate时会回调这个方法初始化数据
     */
    protected abstract void loadData();


    /**
     * 在onViewCreated后调用这个方法来初始化 View控件
     * @param view
     * @param savedInstanceState
     */
    protected abstract void initViews(View view, Bundle savedInstanceState);

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        //设置状态栏透明
//        setStatusBarColor();
        super.onViewCreated(view, savedInstanceState);
        //初始化绑定ButterKnife
        mUnBinder = ButterKnife.bind(this, view);
        initViews(view, savedInstanceState);
        Log.d(TAG_LOG,"Fragment_onViewCreated");
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mUnBinder != null) {
            mUnBinder.unbind();
            mUnBinder = null;
        }
        Log.d(TAG_LOG,"Fragment_onDestroyView");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mActivityContext = null;
        Log.d(TAG_LOG,"Fragment_onDetach");
    }


}
