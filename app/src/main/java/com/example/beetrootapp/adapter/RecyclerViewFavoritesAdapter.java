package com.example.beetrootapp.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.beetrootapp.R;
import com.example.beetrootapp.model.Like;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerViewFavoritesAdapter extends RecyclerView.Adapter<RecyclerViewFavoritesAdapter.ViewHolder> {
    private static final String TAG = "RVFavoritesAdapter";
    private ArrayList<Like> likes;
    private Context context;

    CircleImageView imageFarm;
    TextView nameFarm;
    TextView phoneFarm;
    ImageView fav;
    RelativeLayout parentLayout;

    public RecyclerViewFavoritesAdapter(ArrayList<Like> likes, Context context) {
        super();
        this.likes = likes;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_list_likes,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d(TAG,"onBindViewHolder appelé");
        imageFarm = (CircleImageView)holder.itemView.findViewById(R.id.image_farm);
        nameFarm = (TextView)holder.itemView.findViewById(R.id.farm_name);
        phoneFarm = (TextView)holder.itemView.findViewById(R.id.farm_phone);
        fav = (ImageView)holder.itemView.findViewById(R.id.fav);
        parentLayout = holder.itemView.findViewById(R.id.parent_layout_proximity);

//        Glide.with(context).asBitmap().load("https://resultadosdigitais.com.br/blog/files/2018/01/url-amigavel.jpg").into(holder.imageFarm);

        phoneFarm.setText(likes.get(position).getUser().getPhone());
        nameFarm.setText(likes.get(position).getFarm().getName());

        //Onclick sur fav
    }

    @Override
    public int getItemCount() {
        return likes.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        public View view;

        public ViewHolder(View proximityView) {
            super(proximityView);
            view = proximityView;
        }
    }
}
