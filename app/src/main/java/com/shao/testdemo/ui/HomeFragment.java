package com.shao.testdemo.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.shao.testdemo.R;
import com.shao.testdemo.adapter.HomeRecyclerViewAdapter;
import com.shao.testdemo.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragment {

    private String mParam1;
    private static final String ARG_PARAM1 = "param1";

    @BindView(R.id.recy_home)
    RecyclerView homeRecyclerView;
    private List<String> mData;

    public HomeFragment() {

    }

    public static HomeFragment newInstance(String param1) {
        HomeFragment fragment = new HomeFragment();
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
        return R.layout.fragment_home;
    }

    @Override
    protected void loadData() {
        initData();
    }

    @Override
    protected void initViews(View view, Bundle savedInstanceState) {
        RecyclerView.Adapter adapter = new HomeRecyclerViewAdapter(mData, new ItemClickListener());
        homeRecyclerView.setLayoutManager(new GridLayoutManager(mContext, 3));
        homeRecyclerView.addItemDecoration(new DividerGridItemDecoration(mContext));
        homeRecyclerView.setAdapter(adapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    protected void initData() {
        mData = new ArrayList<>();
        for (int i = 'A'; i < 'z'; i++) {
            mData.add(String.valueOf((char)i));
        }
    }

    class ItemClickListener implements HomeRecyclerViewAdapter.OnItemClickListener {

        @Override
        public void onItemClick(View view, int position) {
            Toast.makeText(mContext, mData.get(position), Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onItemLongClick(View view, int position) {

        }
    }
}