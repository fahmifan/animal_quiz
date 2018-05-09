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

import com.example.mortezasaadat.askaraapps.R;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

// TODO: Implement the dummy project here

public class AksaraFragment extends Fragment {
    private  ArrayList<Aksara> listAksara = new ArrayList<>();
    private  AssetManager assetManager;
    String aksaraType = "Baku_Ngalagena_Tambahan";

    public AksaraFragment() {
        super();
    }

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

        try {
            String[] files = this.getListFileName(aksaraType);
            for (String res:files) {
                this.loadAksara(
                        assetManager,
                        aksaraType + "/" + res,
                        res.replace(".png", "")
                );
            }


        ListView listView = (ListView) rootView.findViewById(R.id.aksara_list);

        AksaraAdapter itemsAdapter = new AksaraAdapter(getActivity(), listAksara);
        listView.setAdapter(itemsAdapter);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return rootView;
    }

        private String[] getListFileName(String path) throws IOException {
            return assetManager.list(path);
        }

        private void loadAksara(AssetManager assetManager, String imageRes, String imageText) throws IOException {
            InputStream is = assetManager.open(imageRes);
            Bitmap bitmap = BitmapFactory.decodeStream(is);
            listAksara.add(new Aksara(imageText, bitmap));
        }
}
