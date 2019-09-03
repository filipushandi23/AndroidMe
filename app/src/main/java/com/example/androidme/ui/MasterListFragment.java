package com.example.androidme.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.androidme.R;
import com.example.androidme.data.AndroidImageAssets;

public class MasterListFragment extends Fragment {
    //this fragment displays all of the android me images in one large list
    //the list appears as a grid of images

    //mandatory empty constructor
    public MasterListFragment(){}

    //inflates the gridview of all androidme images

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_master_list, container, false);

        //get a reference to the GridView in the fragment_master_list xml layout file
        GridView gridView = rootView.findViewById(R.id.images_grid_view);

        //create the adapter
        //this adapter takes in the context and an ArrayList of ALL the image resources to display
        MasterListAdapter adapter = new MasterListAdapter(getContext(), AndroidImageAssets.getAll());
        gridView.setAdapter(adapter);

        //return the rootview
        return rootView;
    }
}
