package com.example.beetrootapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import com.example.beetrootapp.R;
import com.example.beetrootapp.model.Picture;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewPicturesAdapter extends RecyclerView.Adapter<RecyclerViewPicturesAdapter.ViewHolder>{

    private List<Picture> mPictures = new ArrayList<>();
    private Context mContext;
    private ImageView mImage;

    public RecyclerViewPicturesAdapter(Context context, List<Picture> pictures) {
        mPictures = pictures;
        mContext = context;
        System.out.println("plooop");
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_list_pictures, viewGroup, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        mImage = (ImageView) viewHolder.itemView.findViewById(R.id.image);
        RequestOptions defaultOptions = new RequestOptions()
                .error(R.drawable.ic_launcher_background);
        Glide.with(mContext)
                .setDefaultRequestOptions(defaultOptions)
                .load(mPictures.get(i).getPictureURL())
                .into(mImage);
    }

    @Override
    public int getItemCount() {
        return mPictures.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public View view;
        public ViewHolder(View picturesView) {
            super(picturesView);
            view = picturesView;
        }
    }
}

