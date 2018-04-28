package com.example.mortezasaadat.animalquiz;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

class AksaraAdapter extends ArrayAdapter<NewAksara> {
    private List<NewAksara> aksaraList = new ArrayList<>();

    public AksaraAdapter(@NonNull Context context, ArrayList<NewAksara> aksaras) {
        super(context, 0, aksaras);
        aksaraList = aksaras;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Check if an existing view is being used
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false
                );
        }

        NewAksara currentAksara = this.aksaraList.get(position);

        TextView aksaraTextView = (TextView) listItemView.findViewById(R.id.aksara_text_view);
        aksaraTextView.setText(currentAksara.getmAksaraWord());

        ImageView aksaraImageView = (ImageView) listItemView.findViewById(R.id.image_askara);
        if(currentAksara.isHasImage()) {
            // Inject the R.id to the current ImageView
            aksaraImageView.setImageBitmap(currentAksara.getmImageBitmap());
            aksaraImageView.setVisibility(View.VISIBLE);
        } else {
            aksaraImageView.setVisibility(View.GONE);
        }

        return listItemView;
    }
}
