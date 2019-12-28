package com.example.beetrootapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.beetrootapp.R;
import com.example.beetrootapp.ViewModel.LikeVM;
import com.example.beetrootapp.model.Like;

import java.util.List;

public class FavoritesFragment extends Fragment {
    private List<Like> likedFarm;
    private LikeVM farmVM;
    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_favorites,container,false);
    }
}
