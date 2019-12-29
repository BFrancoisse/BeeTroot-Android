package com.example.beetrootapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.beetrootapp.R;
import com.example.beetrootapp.activity.FarmActivity;
import com.example.beetrootapp.model.Farm;

import java.util.ArrayList;

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
    RelativeLayout farmItem;

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
        farmItem = holder.itemView.findViewById(R.id.farm_item);

//      Glide.with(context).asBitmap().load("https://resultadosdigitais.com.br/blog/files/2018/01/url-amigavel.jpg").into(holder.imageFarm);

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                Toast.makeText(context,farms.get(position).getName() +context.getString(R.string.doubleDots)+ rating,Toast.LENGTH_LONG).show();
            }
        });
        phoneFarm.setText(farms.get(position).getUser().getPhone());
        nameFarm.setText(farms.get(position).getName());
        km.setText(String.format( "%.1f",farms.get(position).getDistance()) + context.getString(R.string.km));

        ImageView fav = (ImageView)holder.itemView.findViewById(R.id.fav);

        fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //if(exist pas encore )
                Toast.makeText(context, R.string.addToFavoris, Toast.LENGTH_LONG).show();
                //else
                //Toast.makeText(context, "Retiré des favoris !", Toast.LENGTH_LONG).show();

            }
        });

        farmItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openFarmActivity = new Intent(v.getContext(), FarmActivity.class);
                openFarmActivity.putExtra("farmerId", farms.get(position).getUserId());
                v.getContext().startActivity(openFarmActivity);
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
