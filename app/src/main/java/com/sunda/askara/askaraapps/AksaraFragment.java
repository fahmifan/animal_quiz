package com.sunda.askara.askaraapps;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class AksaraFragment extends Fragment {
    private  ArrayList<Aksara> listAksara = new ArrayList<>();
    private  AssetManager assetManager;

    // default value to avoid null pointer
    String aksaraType = "Baku_Ngalagena_Tambahan";

    public AksaraFragment() {
        super();
    }

    // Set the desired aksara types
    public void setAksaraType(String aksaraType) {
        this.aksaraType = aksaraType;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.aksara_list, container, false);

        assetManager = getActivity().getAssets();

        // Load aksara files from assets folder
        try {
            String[] files = this.getListFilesName(aksaraType);
            for (String file:files) {
                this.loadAksara(
                    assetManager,
                    aksaraType + "/" + file,
                    // remove the file extention before load it
                    file.replace(".png", "")
                );
            }

            ListView listView = (ListView) rootView.findViewById(R.id.aksara_list);

            AksaraAdapter itemsAdapter = new AksaraAdapter(getActivity(), listAksara);
            listView.setAdapter(itemsAdapter);

        } catch (IOException e) {e.printStackTrace();}

        return rootView;
    }

    // Return the array of String file name
    private String[] getListFilesName(String path) throws IOException {
        return assetManager.list(path);
    }

    // Load aksara image file to listAksara
    private void loadAksara(
        AssetManager assetManager, String imageResource, String imageText) throws IOException {
        InputStream is = assetManager.open(imageResource);
        Bitmap bitmap = BitmapFactory.decodeStream(is);
        listAksara.add(new Aksara(imageText, bitmap));
    }
}
