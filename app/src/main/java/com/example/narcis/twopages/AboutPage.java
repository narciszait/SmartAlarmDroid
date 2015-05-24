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
//    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        rootView = inflater.inflate(R.layout.about_layout, container, false);

        Button button = (Button)rootView.findViewById(R.id.button3);
        final ImageView usImageView = (ImageView)rootView.findViewById(R.id.usImageView);



        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {



                /* Perform action on click */
                Log.v("", "I clicked");
                Log.v("counter: ", Integer.toString(counter));

                AlertDialog alertDialog1 = new AlertDialog.Builder(
                        getActivity()).create();
                // Setting Dialog Title
                alertDialog1.setTitle("About Page");
                // Setting Dialog Message
                alertDialog1.setMessage("That is who we are");
                // Setting OK Button
                alertDialog1.setButton("OK", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        // Write your code here to execute after dialog
                        // closed
                        Toast.makeText(getActivity(),
                                "You looked who made the app", Toast.LENGTH_SHORT).show();
                    }
                });

                // Showing Alert Message
                alertDialog1.show();
            }
        });

        return rootView;
    }
}
