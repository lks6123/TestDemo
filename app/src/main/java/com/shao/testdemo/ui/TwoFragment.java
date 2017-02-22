package com.shao.testdemo.ui;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shao.testdemo.R;
import com.shao.testdemo.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;


public class TwoFragment extends BaseFragment {

    private static final String ARG_PARAM1 = "param1";
    @BindView(R.id.tv_fragment_two)
    TextView tvFragmentTwo;
    private String mParam1;

    public TwoFragment() {
    }

    public static TwoFragment newInstance(String param1) {
        TwoFragment fragment = new TwoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_two;
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void initViews(View view, Bundle savedInstanceState) {
        tvFragmentTwo.setText(mParam1);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }
}
