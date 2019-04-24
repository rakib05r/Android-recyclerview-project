package com.example.assignment_android.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.assignment_android.databinding.ListItemLayoutBinding;
import com.example.assignment_android.model.PersonModel;
import com.example.assignment_android.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

public class ProgrammingAdapter extends RecyclerView.Adapter<ProgrammingAdapter.ProgrammingViewHolder> {

    private List<PersonModel> list=new ArrayList<>();
    public ProgrammingAdapter() {
    }
    @Override
    public ProgrammingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ListItemLayoutBinding listItemLayoutBinding= DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),R.layout.list_item_layout,parent,false);
        ProgrammingViewHolder programmingViewHolder=new ProgrammingViewHolder(listItemLayoutBinding);
        return programmingViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProgrammingViewHolder holder, int position) {

        PersonModel personModel=list.get(position);
        holder.listItemLayoutBinding.setLocalmodel(personModel);
        //Glide.with(context).load(personModel.getImage()).into(holder.imgIcon);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setList(List<PersonModel>list){
        this.list=list;
        notifyDataSetChanged();
    }

    public class ProgrammingViewHolder extends RecyclerView.ViewHolder{

        ListItemLayoutBinding listItemLayoutBinding;
        public ProgrammingViewHolder(ListItemLayoutBinding itemView) {
            super(itemView.getRoot());
            listItemLayoutBinding=itemView;
        }
    }
}
