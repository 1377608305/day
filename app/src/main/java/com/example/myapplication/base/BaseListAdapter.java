package com.example.myapplication.base;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public abstract class BaseListAdapter<T> extends RecyclerView.Adapter {
    protected Context context;
    protected List<T> data;
    public BaseListAdapter(Context context, List<T> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(getLayout(), parent,false);
        BaseViewHolder bh = new BaseViewHolder(inflate);
        return bh;
    }

    protected abstract int getLayout();


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        BaseViewHolder bh=(BaseViewHolder)holder;
        T t = data.get(position);
        initBindData(bh,t,position);
        if (onItemClickListenter!=null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListenter.onItemListenter(v,position);
                }
            });
        }
    }

    protected abstract void initBindData(BaseViewHolder bh, T t, int position);


    @Override
    public int getItemCount() {
        return data.size();
    }



    public void addData(List<T> list){
        if (list!=null&&list.size()>0){
            data.addAll(list);
            notifyDataSetChanged();
        }
    }

    public void updataData(List<T> list){
        if (list!=null&&list.size()>0){
            data.clear();
            data.addAll(list);
            notifyDataSetChanged();
        }

    }
    public class BaseViewHolder extends RecyclerView.ViewHolder{
        SparseArray sparseArray=new SparseArray();
        public BaseViewHolder(@NonNull View itemView) {
            super(itemView);

        }

        public View getViewById(int id){
            View view = (View) sparseArray.get(id);
            if (view==null){
                view = itemView.findViewById(id);
                sparseArray.append(id,view);

            }
            return view;
        }

        public void setText(@IdRes int id, String text){
            TextView tl= (TextView) getViewById(id);
            tl.setText(text);

        }

    }
    public BaseRlvAdapter.OnItemClickListenter onItemClickListenter;

    public void setOnItemClickListenter(OnItemClickListenter onItemClickListenter) {
        this.onItemClickListenter = (BaseRlvAdapter.OnItemClickListenter) onItemClickListenter;
    }

    public interface OnItemClickListenter{
        void onItemListenter(View view, int pos);
    }
}
