package com.example.androidme.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import com.example.androidme.R;
import com.example.androidme.data.AndroidImageAssets;

public class AndroidMeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_me);

        //only create a new fragments when there is no previously saved state
        if(savedInstanceState == null){
            //create a new head BodyPartFragment
            BodyPartFragment headFragment = new BodyPartFragment();

            //set the list of image id's for the head fragment and set the position to the second image
            //in the list
            headFragment.setImageIds(AndroidImageAssets.getHeads());
            headFragment.setListIndex(1);

            //add the fragment to its container using FragmentManager and a Transaction
            //containernya diisi oleh headFragment
            //transaction hanya digunakan pada fragment yang dinamis (berubah-ubah)
            //fragment statis tidak perlu menggunakan transaction
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .add(R.id.head_container,headFragment)// bisa add, replace, remove
                    .commit();

            //create and display the body and leg bodypart fragments
            BodyPartFragment bodyFragment = new BodyPartFragment();
            bodyFragment.setImageIds(AndroidImageAssets.getBodies());
            fragmentManager.beginTransaction()
                    .add(R.id.body_container,bodyFragment)
                    .commit();

            BodyPartFragment legFragment = new BodyPartFragment();
            legFragment.setImageIds(AndroidImageAssets.getLegs());
            fragmentManager.beginTransaction()
                    .add(R.id.leg_container,legFragment)
                    .commit();
        }
    }
}
