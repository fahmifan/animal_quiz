package com.example.mortezasaadat.animalquiz;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

public class AksaraSundaKunoFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.aksara_list, container, false);

        // Create a list of aksara
        final ArrayList<Aksara> aksaras = new ArrayList<>();
        aksaras.add(new Aksara("Ka"));
        aksaras.add(new Aksara("Ga"));
        aksaras.add(new Aksara("Nga"));
        aksaras.add(new Aksara("Ca"));
        aksaras.add(new Aksara("Ja"));
        aksaras.add(new Aksara("Nya"));

        // inject the arraylist to listview
        ListView listView = (ListView) rootView.findViewById(R.id.aksara_list);
        AksaraAdapter itemsAdapter = new AksaraAdapter(getActivity(), aksaras);
        listView.setAdapter(itemsAdapter);

        return rootView;
    }

}
