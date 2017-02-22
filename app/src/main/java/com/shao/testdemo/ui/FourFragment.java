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


public class FourFragment extends BaseFragment {
    private static final String ARG_PARAM1 = "param1";
    @BindView(R.id.tv_fragment_four)
    TextView tvFragmentFour;
    private String mParam1;

    public FourFragment() {
    }

    public static FourFragment newInstance(String param1) {
        FourFragment fragment = new FourFragment();
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
        return R.layout.fragment_four;
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void initViews(View view, Bundle savedInstanceState) {
        tvFragmentFour.setText(mParam1);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }
}
