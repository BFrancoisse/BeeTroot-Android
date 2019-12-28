package com.example.beetrootapp.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.beetrootapp.R;
import com.example.beetrootapp.model.Farm;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerViewProximityAdapter extends RecyclerView.Adapter<RecyclerViewProximityAdapter.ViewHolder> {
    private static final String TAG = "RVProximityAdapter";
    private ArrayList<Farm> farms;
    private Context context;

    CircleImageView imageFarm;
    TextView nameFarm;
    TextView phoneFarm;
    TextView km;
    RatingBar ratingBar;
    RelativeLayout parentLayout;

    public RecyclerViewProximityAdapter(ArrayList<Farm> farms, Context context) {
        super();
        this.farms = farms;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_list_proximity,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d(TAG,"onBindViewHolder appelé");
        imageFarm = (CircleImageView)holder.itemView.findViewById(R.id.image_farm);
        nameFarm = (TextView)holder.itemView.findViewById(R.id.farm_name);
        phoneFarm = (TextView)holder.itemView.findViewById(R.id.farm_phone);
        km = (TextView)holder.itemView.findViewById(R.id.km_proximity);
        ratingBar = (RatingBar)holder.itemView.findViewById(R.id.rating_bar_farm);
        parentLayout = holder.itemView.findViewById(R.id.parent_layout_proximity);

//        Glide.with(context).asBitmap().load("https://resultadosdigitais.com.br/blog/files/2018/01/url-amigavel.jpg").into(holder.imageFarm);


        phoneFarm.setText(farms.get(position).getUser().getPhone());
        nameFarm.setText(farms.get(position).getName());
        km.setText(String.format( "%.1f",farms.get(position).getDistance()) + context.getString(R.string.km));
        parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,farms.get(position).getName(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return farms.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public  View view;
        public ViewHolder(View proximityView){
            super(proximityView);
            view = proximityView;
        }


    }
}
