package com.rakaadinugroho.msuryanusatara.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.rakaadinugroho.msuryanusatara.R;
import com.rakaadinugroho.msuryanusatara.models.Category;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Raka Adi Nugroho on 5/27/17.
 *
 * @Github github.com/rakaadinugroho
 * @Contact nugrohoraka@gmail.com
 */

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {
    private Context context;
    private List<Category> categories;
    private int rowLayout;

    public CategoryAdapter(Context context, List<Category> categories, int rowLayout) {
        this.context = context;
        this.categories = categories;
        this.rowLayout = rowLayout;
    }

    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view   = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        CategoryViewHolder  viewHolder  = new CategoryViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CategoryViewHolder holder, int position) {
        Category category   = categories.get(position);
        try {
            Glide.with(context)
                    .load(category.getCategoryThumbnail().toString())
                    .into(holder.category_pict);
            holder.category_title.setText(category.getCategoryName().toString());
        }catch (NullPointerException e){
            Toast.makeText(context, "Terjadi Kesalahan", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    class CategoryViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.item_category_pict)
        ImageView category_pict;
        @BindView(R.id.item_category_title)
        TextView category_title;
        public CategoryViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
