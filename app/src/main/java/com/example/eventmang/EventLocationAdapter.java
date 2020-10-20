package com.example.eventmang;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.flaviofaria.kenburnsview.KenBurnsView;
import com.squareup.picasso.Picasso;

import java.util.List;

public class EventLocationAdapter  extends RecyclerView.Adapter<EventLocationAdapter.EventLocationViewHolder> {

    private List<EventLocation> eventLocations;

    public EventLocationAdapter(List<EventLocation> eventLocations) {
        this.eventLocations = eventLocations;
    }

    @NonNull
    @Override
    public EventLocationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new EventLocationViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.item_container_location,
                        parent,
                        false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull EventLocationViewHolder holder, int position) {

        holder.setLocationData(eventLocations.get(position));

    }

    @Override
    public int getItemCount() {
        return eventLocations.size();
    }

    static class EventLocationViewHolder extends RecyclerView.ViewHolder{

        private KenBurnsView kbvLocation;
        private TextView text_title,text_location,text_star_rating;

         EventLocationViewHolder(@NonNull View itemView) {
            super(itemView);

            kbvLocation=itemView.findViewById(R.id.kbv_location);
            text_title=itemView.findViewById(R.id.text_title);
            text_location=itemView.findViewById(R.id.text_location);
            text_star_rating=itemView.findViewById(R.id.text_star_rating);


        }
        void setLocationData(EventLocation eventLocation){
            Picasso.get().load(eventLocation.imageUrl).into(kbvLocation);
            text_title.setText(eventLocation.title);
            text_location.setText(eventLocation.location);
            text_star_rating.setText(String.valueOf(eventLocation.starRating));
        }
    }
}
