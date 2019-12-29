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
import com.example.beetrootapp.ViewModel.LikeVM;
import com.example.beetrootapp.adapter.RecyclerViewFavoritesAdapter;
import com.example.beetrootapp.adapter.RecyclerViewProximityAdapter;
import com.example.beetrootapp.model.Farm;
import com.example.beetrootapp.model.Like;

import java.util.ArrayList;
import java.util.List;

public class FavoritesFragment extends Fragment {
    //private List<Like> likedFarm;
    //private LikeVM likeVM; problème API au niveau de l'ajout donc on va afficher les fermes de la DB
    private FarmVM farmVM;
    private ArrayList<Farm> likedFarm;
    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_favorites,container,false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_favorites);


        likedFarm = new ArrayList<>();

        farmVM = ViewModelProviders.of(this).get(FarmVM.class);
        farmVM.getFarms(getContext()).observe(this, farms -> {
            likedFarm = (ArrayList<Farm>) farms;
            initRecyclerView();

        });
        return view;
    }
    private void initRecyclerView(){
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this.getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        RecyclerViewFavoritesAdapter recyclerViewProximityAdapter = new RecyclerViewFavoritesAdapter(likedFarm,this.getContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(recyclerViewProximityAdapter);
    }
}
