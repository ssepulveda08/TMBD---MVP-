package com.example.tmdb.ListMovie.ui.list;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.tmdb.Data.Model.MovieDto;
import com.example.tmdb.Detail.DetailMovieActivity;
import com.example.tmdb.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static com.example.tmdb.Utilities.Constants.RUTA_IMG_MINI;


public class AdapterListMovie extends RecyclerView.Adapter<AdapterListMovie.RecyclerViewHolder>
        implements View.OnClickListener, Filterable {


    private ArrayList<MovieDto> dataSet;
    private ArrayList<MovieDto> mFilteredList;
    private Activity activity;

    public AdapterListMovie(ArrayList<MovieDto> array, Activity activity) {
        this.dataSet = array;
        this.mFilteredList = array;
        this.activity = activity;

    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list_movie, viewGroup, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int i) {
        MovieDto item = mFilteredList.get(i);

        Picasso.get()
                .load(RUTA_IMG_MINI+ item.getPoster_path())
                .error(R.drawable.ic_default_image)
                .into(holder.img);

        holder.text_title.setText(item.getTitle());
        String []  fecha = item.getRelease_data().split("-");
        if (fecha.length >0){
            holder.text_year.setText(fecha[0]);
        }

        holder.itemView.setOnClickListener(view -> {
            Intent startIntent = new Intent(activity, DetailMovieActivity.class);
            startIntent.putExtra("ItemDetail", item);
            activity.startActivity(startIntent,
                    ActivityOptions.makeSceneTransitionAnimation(activity).toBundle());
        });

    }

    @Override
    public int getItemCount() {
        return mFilteredList.size();
    }

    @Override
    public void onClick(View v) {

    }

    static  class RecyclerViewHolder extends RecyclerView.ViewHolder {

        TextView text_title;
        TextView text_year;
        ImageView img;

        RecyclerViewHolder(View view) {
            super(view);
            img = view.findViewById(R.id.imageView_item);
            text_title = view.findViewById(R.id.textView_title_item);
            text_year = view.findViewById(R.id.textview_year_item);
        }

    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                String charString = charSequence.toString();

                if (charString.isEmpty()) {
                    mFilteredList = dataSet;
                } else {

                    ArrayList<MovieDto> filteredList = new ArrayList<>();
                    for (MovieDto row : dataSet) {
                        if (row.getTitle().toLowerCase().contains(charString) ){ //|| androidVersion.getName().toLowerCase().contains(charString) || androidVersion.getVer().toLowerCase().contains(charString)) {
                            filteredList.add(row);
                        }
                    }
                    mFilteredList = filteredList;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = mFilteredList;
                return filterResults;
            }
            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mFilteredList = (ArrayList<MovieDto>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
}

