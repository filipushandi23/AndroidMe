package com.example.androidme.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.androidme.R;
import com.example.androidme.data.AndroidImageAssets;

import java.util.ArrayList;
import java.util.List;

public class BodyPartFragment extends Fragment {

    //tag for logging
    private static final String TAG = BodyPartFragment.class.getSimpleName();

    //final strings to store state information about the list
    private static final String IMAGE_ID_LIST = "image_ids";
    private static final String LIST_INDEX = "list_index";

    //variable to store a list of image resources and the index of the image that this fragment display
    private List<Integer> mImageIds;
    private int mListIndex;
    public BodyPartFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        //inflate the AndroidMe fragment layout
        View rootView = inflater.inflate(R.layout.fragment_body_part,container,false);

        //get a reference to the imageview in the fragment layout
        final ImageView imageView = rootView.findViewById(R.id.body_part_image_view);

        //if a list of image ids exists, se the image resource to he correct item in that list
        //otherwise , create a log statement that indicates that the list was not found
        if(mImageIds != null){
            imageView.setImageResource(mImageIds.get(mListIndex));
            //set a click listener on the image view
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //increment position as long as the index remains <= the size of the image ids list
                    if(mListIndex < mImageIds.size()-1){
                        mListIndex++;
                    }
                    else {
                        //the end of the list has been reached, return to the beginning
                        mListIndex = 0;
                    }

                    //set the image resource to the new list item
                    imageView.setImageResource(mImageIds.get(mListIndex));
                }
            });
        }
        else{
            Log.v(TAG,"This fragment has a null list of image id");
        }

        //set te image to the first in our list of head images

        //return the rootView
        return rootView;
    }

    //setter methods for keeping track f the list images this fragment can display and which image
    //in the list is currently being displayed
    public void setImageIds(List<Integer> imageIds){
        mImageIds = imageIds;
    }

    public void setListIndex(int listIndex){
        mListIndex = listIndex;
    }

    //save the current state of this fragment
    @Override
    public void onSaveInstanceState(@NonNull Bundle currentState) {
        currentState.putIntegerArrayList(IMAGE_ID_LIST,(ArrayList<Integer>) mImageIds);
        currentState.putInt(LIST_INDEX,mListIndex);
    }
}
