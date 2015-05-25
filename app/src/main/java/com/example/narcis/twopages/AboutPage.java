package com.example.narcis.twopages;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by narcis on 14/04/15.
 */
public class AboutPage extends Fragment {
    View rootView;
    int counter = 0;
    int imageAlpha = 0;
    private AlphaAnimation alphaDown;
    private AlphaAnimation alphaUp;
    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        rootView = inflater.inflate(R.layout.about_layout, container, false);

        Button button = (Button)rootView.findViewById(R.id.button3);
        final ImageView usImageView = (ImageView)rootView.findViewById(R.id.usImageView);
        usImageView.setVisibility(View.INVISIBLE);

        final Animation a = new AlphaAnimation(0.00f, 1.00f);

        a.setDuration(5000);
        a.setAnimationListener(new Animation.AnimationListener() {

            public void onAnimationStart(Animation animation) {

            }

            public void onAnimationRepeat(Animation animation) {

            }

            public void onAnimationEnd(Animation animation) {
                usImageView.setVisibility(View.VISIBLE);
            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                counter++;
                if (counter % 5 == 0) {

                    usImageView.startAnimation(a);

                    Log.v("", "I clicked");
                    Log.v("counter: ", Integer.toString(counter));


                    Toast.makeText(getActivity(),
                            "This is a part of us", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return rootView;
    }
}
