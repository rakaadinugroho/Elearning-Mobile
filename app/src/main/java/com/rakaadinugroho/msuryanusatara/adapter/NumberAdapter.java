package com.rakaadinugroho.msuryanusatara.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rakaadinugroho.msuryanusatara.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Raka Adi Nugroho on 5/31/17.
 *
 * @Github github.com/rakaadinugroho
 * @Contact nugrohoraka@gmail.com
 */

public class NumberAdapter extends RecyclerView.Adapter<NumberAdapter.NumberViewHolder>{
    private List<Integer> listnumber;
    private Context context;
    private int rowLayout;

    public NumberAdapter(List<Integer> listnumber, Context context, int rowLayout) {
        this.listnumber = listnumber;
        this.context = context;
        this.rowLayout = rowLayout;
    }

    @Override
    public NumberViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view   = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        NumberViewHolder viewHolder = new NumberViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(NumberViewHolder holder, int position) {
        Integer number  = (listnumber.get(position)+1);
        holder.number_label.setText(number.toString());
    }

    @Override
    public int getItemCount() {
        return listnumber.size();
    }

    class NumberViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.item_number_label)
        TextView number_label;
        public NumberViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
