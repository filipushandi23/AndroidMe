package com.example.androidme.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.example.androidme.R;
import com.example.androidme.data.AndroidImageAssets;

public class MainActivity  extends AppCompatActivity
        implements MasterListFragment.OnImageClickListener {

    //variables to store the values for the list index of the selected images
    //teh default value will be index = 0
    private int mHeadIndex;
    private int mBodyIndex;
    private int mLegIndex;

    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //determine if you're creating a two-pane or single pane display
        //android_me_linear_layout is a layout for tablet
        if(findViewById(R.id.android_me_linear_layout) != null){
            //this linear layout will only initially exist in the two pane table case
            mTwoPane = true;

            //chage the GridView to space out the images on tablet
            GridView gridView = (GridView) findViewById(R.id.images_grid_view);
            gridView.setNumColumns(2);

            //getting rid of the "next" button that appears on phones for launching separate activity
            Button nextButton = findViewById(R.id.next_button);
            nextButton.setVisibility(View.GONE);

            if(savedInstanceState == null){
                // two pane mode, add initial BodyPartFragment to the screen
                FragmentManager fragmentManager = getSupportFragmentManager();

                //creating an new head fragment
                BodyPartFragment headFragment = new BodyPartFragment();
                headFragment.setImageIds(AndroidImageAssets.getHeads());

                //add the fragment to its container using a transaction
                fragmentManager.beginTransaction()
                        .add(R.id.head_container,headFragment)
                        .commit();

                //new body fragment
                BodyPartFragment bodyFragment = new BodyPartFragment();
                bodyFragment.setImageIds(AndroidImageAssets.getBodies());

                //add the fragment to its container using a transaction
                fragmentManager.beginTransaction()
                        .add(R.id.body_container,bodyFragment)
                        .commit();

                //new body fragment
                BodyPartFragment legFragment = new BodyPartFragment();
                legFragment.setImageIds(AndroidImageAssets.getLegs());

                //add the fragment to its container using a transaction
                fragmentManager.beginTransaction()
                        .add(R.id.leg_container,legFragment)
                        .commit();
            }
        }
        else{
            //we're in single pane mode and displaying fragments on a phone in separate activities
            mTwoPane = false;
        }
    }

    //define the behaviour for onImageSelected
    @Override
    public void onImageSelected(int position) {
        //based on where a user has clicked
        //store the selected list index for the head, body, and leg BodyPartFragments

        //bodyPartNumber will be = 0 for the head fragment, 1 for the body, and 2 for the leg fragment
        //dividing by 12 gives us these integer values because each list of images resources has a
        //size of 12

        int bodyPartNumber = position/12;

        //store the correct list index no matter where in the image list has been clicked
        //this ensures that the index will always be a value betwee 0-11

        int listIndex = position - 12 * bodyPartNumber;

        if(mTwoPane){
            //create two pane interaction

            BodyPartFragment newFragment = new BodyPartFragment();

            //set the currently displayed item for the correct body part fragment
            switch (bodyPartNumber){
                case 0:
                    //a head image has been clicked
                    //give the correct image resources to the new fragment
                    newFragment.setImageIds(AndroidImageAssets.getHeads());
                    newFragment.setListIndex(listIndex);

                    //replace the old heat fragment with a new one
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.head_container,newFragment)
                            .commit();
                    break;
                case 1:
                    //a body image has been clicked
                    //give the correct image resources to the new fragment
                    newFragment.setImageIds(AndroidImageAssets.getBodies());
                    newFragment.setListIndex(listIndex);

                    //replace the old heat fragment with a new one
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.body_container,newFragment)
                            .commit();

                    break;
                case 2:
                    //a leg image has been clicked
                    //give the correct image resources to the new fragment
                    newFragment.setImageIds(AndroidImageAssets.getLegs());
                    newFragment.setListIndex(listIndex);

                    //replace the old heat fragment with a new one
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.leg_container,newFragment)
                            .commit();
                    break;
                default:break;
            }

        }
        else{
            //set the currently dispalyed item for the correct body part fragment
            switch (bodyPartNumber){
                case 0:
                    mHeadIndex = listIndex;
                    break;
                case 1:
                    mBodyIndex = listIndex;
                    break;
                case 2:
                    mLegIndex = listIndex;
                    break;
                default:break;
            }

            //put this information in a BUndle and attach it to an Intent that will launch an AndroidMeActivity
            Bundle b = new Bundle();
            b.putInt("headIndex",mHeadIndex);
            b.putInt("bodyIndex",mBodyIndex);
            b.putInt("legIndex",mLegIndex);

            //attach the Bundle to Intent
            final Intent intent = new Intent(this,AndroidMeActivity.class);
            intent.putExtras(b);

            //the "Next" button launches a new AndroidMeActivity
            Button nextButton = findViewById(R.id.next_button);
            nextButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(intent);
                }
            });
        }
    }
}
