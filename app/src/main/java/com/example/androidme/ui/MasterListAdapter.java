package com.example.androidme.ui;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.List;

//custom adapter clas tat display a list of android me images in a gridview
public class MasterListAdapter extends BaseAdapter {

    //keeps track of the context and list of images to display
    private Context mContext;

    private List<Integer> mImageIds;

    public MasterListAdapter(Context context, List<Integer> imageIds){
        mContext = context;
        mImageIds = imageIds;
    }

    @Override
    public int getCount() {
        return mImageIds.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if(convertView == null){
            //if the view is not recycled, this creates a new image view to hold an image
            imageView = new ImageView(mContext);
            //define the layout paramteres
            imageView.setAdjustViewBounds(true);
            imageView.setScaleType(ImageView.ScaleType.CENTER);
            imageView.setPadding(8,8,8,8);
        }
        else{
            imageView = (ImageView) convertView;
        }

        //set the image resource and return the newly created ImageView
        imageView.setImageResource(mImageIds.get(position));
        return imageView;
    }
}
