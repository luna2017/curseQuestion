package com.example.materialtest;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class FunAdapter extends RecyclerView.Adapter<FunAdapter.ViewHolder>{

    private static final String TAG = "FunAdapter";

    private Context mContext;

    private List<Fun> mFunList;

    static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        ImageView funImage;
        TextView funName;
        ImageView funStar;

        public ViewHolder(View view) {
            super(view);
            cardView = (CardView) view;
            funImage = (ImageView) view.findViewById(R.id.fun_image);
            funName = (TextView) view.findViewById(R.id.fun_name);
            funStar=(ImageView)view.findViewById(R.id.fun_star);
        }
    }

    public FunAdapter(List<Fun> funList) {
        mFunList = funList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null) {
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.fun_item, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Fun fun = mFunList.get(position);
                Intent intent = new Intent(mContext, FunActivity.class);
                intent.putExtra(FunActivity.FUN_NAME, fun.getName());
                intent.putExtra(FunActivity.FUN_IMAGE_ID, fun.getImageId());
                intent.putExtra(FunActivity.FUN_STAR_ID,fun.getStarNumber());
                mContext.startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Fun fun = mFunList.get(position);
        holder.funName.setText(fun.getName());

        Glide.with(mContext).load(fun.getImageId()).into(holder.funImage);
        Glide.with(mContext).load(fun.getStar()).into(holder.funStar);
    }

    @Override
    public int getItemCount() {
        return mFunList.size();
    }

}
