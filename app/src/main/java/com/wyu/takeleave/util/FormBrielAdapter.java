package com.wyu.takeleave.util;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.wyu.takeleave.R;
import com.wyu.takeleave.student.Student;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;


public class FormBrielAdapter extends RecyclerView.Adapter<FormBrielAdapter.ViewHolder> implements View.OnClickListener{

    private Context mContext;

    private List<FormBrief> formBriefs;

    private OnItemClickListener mOnItemClickListener;


    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    @Override
    public void onClick(View view) {

    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView time;
        TextView status;
        TextView auditor;
        TextView duration;
        TextView reply;
        RelativeLayout item;

        public ViewHolder(View view) {
            super(view);
            time = (TextView) view.findViewById(R.id.time);
            status = (TextView) view.findViewById(R.id.status);
            auditor = (TextView) view.findViewById(R.id.auName);
            duration = (TextView) view.findViewById(R.id.duration);
            reply = (TextView) view.findViewById(R.id.reply);
            item = (RelativeLayout) view.findViewById(R.id.applyItem);
        }
    }

    public FormBrielAdapter(ArrayList<FormBrief> formBriefs){
        this.formBriefs = formBriefs;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null) {
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.apply_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        FormBrief formBrief = formBriefs.get(position);
        if(formBrief.getTime() != null){
            holder.time.setText(formBrief.getTime().toString());
        }
        if(formBrief.getAuditor() != null){
            holder.auditor.setText(formBrief.getAuditor());
        }
        if(formBrief.getDuration() != null){
            holder.duration.setText(formBrief.getDuration());
        }
        if(formBrief.getReply() != null){
            holder.reply.setText(formBrief.getReply());
        }
        switch (formBrief.getStatus()){
            case 0 :{
                holder.status.setTextColor(Color.parseColor("#ff0000"));
                holder.status.setText("已拒绝");
                break;
            }
            case 1 :{
                holder.status.setTextColor(Color.parseColor("#b3c8e5"));
                holder.status.setText("已通过");
                break;
            }
            case 2 :{
                holder.status.setTextColor(Color.parseColor("#eecf7e"));
                holder.status.setText("班导审核");
                break;
            }
            case 3 :{
                holder.status.setTextColor(Color.parseColor("#eecf7e"));
                holder.status.setText("辅导员审核");
                break;
            }
            case 4 :{
                holder.status.setTextColor(Color.parseColor("#eecf7e"));
                holder.status.setText("院长审核");
                break;
            }
            case 999:{
                holder.status.setTextColor(Color.parseColor("#eecf7e"));
                holder.status.setText("待审核");
                break;
            }
        }
        holder.item.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mOnItemClickListener.onItemClick(v, position);
                    }
                });
    }

    @Override
    public int getItemCount() {
        return this.formBriefs.size();
    }
}
