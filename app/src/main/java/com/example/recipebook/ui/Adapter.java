package com.example.recipebook.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.recipebook.R;
import com.example.recipebook.data.RecipeDetails;
import com.example.recipebook.data.RecipeListItem;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    List<RecipeListItem> recipeListItems;
    Context context;
    OnItemClickListener listener;

    public Adapter(Context context, OnItemClickListener listener) {
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (recipeListItems != null) {
            RecipeListItem item = recipeListItems.get(position);

            holder.title.setText(item.getTitle());
            if (item.getImage() != null) {
                Glide.with(context)
                        .load(item.getImage())
                        .placeholder(R.drawable.ic_baseline_search_24)
                        .into(holder.imageView);
            } else {
                Glide.with(context).clear(holder.imageView);
                holder.imageView.setImageDrawable(null);
            }

            holder.itemView.setOnClickListener(v -> {
                listener.onClick(item);
            });
        }
    }

    @Override
    public int getItemCount() {
        if (recipeListItems == null) return 0;
        return recipeListItems.size();
    }

    public void setRecipeListItems(List<RecipeListItem> recipeListItems) {
        this.recipeListItems = recipeListItems;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView title;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image);
            title = itemView.findViewById(R.id.title);

        }

    }

    public interface OnItemClickListener {
        void onClick(RecipeListItem item);
    }
}
