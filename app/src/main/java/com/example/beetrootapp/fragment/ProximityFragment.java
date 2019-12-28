package com.example.beetrootapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.beetrootapp.R;
import com.example.beetrootapp.ViewModel.FarmVM;
import com.example.beetrootapp.adapter.RecyclerViewProximityAdapter;
import com.example.beetrootapp.model.Farm;
import com.example.beetrootapp.model.User;

import java.util.ArrayList;
import java.util.List;

public class ProximityFragment extends Fragment {

    private ArrayList<Farm> farmsProximity;
    private FarmVM farmVM;
    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_proximity,container,false);
        recyclerView = (RecyclerView)view.findViewById(R.id.recycler_view_proximity);

        farmsProximity = new ArrayList<>();

        farmVM = ViewModelProviders.of(this).get(FarmVM.class);
        farmVM.getFarms().observe(this,farms -> {
            farmsProximity = (ArrayList<Farm>) farms;
            initRecyclerView();

        });
        return view;
    }

    private void initRecyclerView(){
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this.getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        RecyclerViewProximityAdapter recyclerViewProximityAdapter = new RecyclerViewProximityAdapter(farmsProximity,this.getContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(recyclerViewProximityAdapter);
    }


}
