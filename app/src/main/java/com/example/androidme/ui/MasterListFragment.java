package com.example.androidme.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.androidme.R;
import com.example.androidme.data.AndroidImageAssets;

public class MasterListFragment extends Fragment {
    //this fragment displays all of the android me images in one large list
    //the list appears as a grid of images

    OnImageClickListener mCallback;

    //define a new interface OnImageClickListener that triggers a callback in the host activity
    public interface OnImageClickListener{
        void onImageSelected(int position);
    }

    //Override onAttach to make sure that the container activity has implemented the callback
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        //this make sure that the host activity has implemented the callback interface
        //if not, it throws an exception
        try{
            mCallback = (OnImageClickListener) context;
        }catch (ClassCastException e){
            throw new ClassCastException(context.toString()+" must implement OnImageClickListener");
        }
    }

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

        //set a click listener on the gridView and trigger the callback onImageSelected when an item is clicked
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //trigger the callback method and pass in the position that was clicked
                mCallback.onImageSelected(position);
            }
        });

        //return the rootview
        return rootView;
    }

}
