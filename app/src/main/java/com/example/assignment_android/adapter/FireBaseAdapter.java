package com.example.assignment_android.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.assignment_android.model.FireBaseModel;
import com.example.assignment_android.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FireBaseAdapter extends RecyclerView.Adapter<FireBaseAdapter.ViewHolder> {

    private List<FireBaseModel>list;
    private Context context;

    public FireBaseAdapter(List<FireBaseModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.list_item_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        FireBaseModel fireBaseModel=list.get(position);
        holder.txtName.setText(fireBaseModel.getPersonName());
        holder.txtdes.setText(fireBaseModel.getPersonDesignation());
        holder.txtteam.setText(fireBaseModel.getPersonTeam());
        Glide.with(context).load(fireBaseModel.getPersonImage()).into(holder.imgIcon);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView imgIcon;
        public TextView txtName,txtdes,txtteam;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgIcon=itemView.findViewById(R.id.imgIcon);
            txtName=itemView.findViewById(R.id.txtName);
            txtdes=itemView.findViewById(R.id.txtdesig);
            txtteam=itemView.findViewById(R.id.txtteam);
        }
    }
}
