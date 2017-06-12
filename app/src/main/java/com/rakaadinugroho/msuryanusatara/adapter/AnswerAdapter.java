package com.rakaadinugroho.msuryanusatara.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rakaadinugroho.msuryanusatara.R;
import com.rakaadinugroho.msuryanusatara.models.Answer;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Raka Adi Nugroho on 6/1/17.
 *
 * @Github github.com/rakaadinugroho
 * @Contact nugrohoraka@gmail.com
 */

public class AnswerAdapter extends RecyclerView.Adapter<AnswerAdapter.AnswerViewHolder>{
    private List<Answer> answers;
    private Context context;
    private int rowLayout;

    public AnswerAdapter(List<Answer> answers, Context context, int rowLayout) {
        this.answers = answers;
        this.context = context;
        this.rowLayout = rowLayout;
    }

    @Override
    public AnswerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view   = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        AnswerViewHolder viewHolder = new AnswerViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(AnswerViewHolder holder, int position) {
        Answer answer   = answers.get(position);
    }

    @Override
    public int getItemCount() {
        return answers.size();
    }

    class AnswerViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.answer_label)
        TextView anwer_label;
        public AnswerViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
