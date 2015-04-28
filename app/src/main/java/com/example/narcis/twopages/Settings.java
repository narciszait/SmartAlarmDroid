package com.example.narcis.twopages;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by narcis on 14/04/15.
 */
public class Settings extends Fragment {
    View rootView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        rootView = inflater.inflate(R.layout.settings_layout, container, false);

        Button button = (Button)rootView.findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                /* Perform action on click */
                Log.v("", "Settings set");

                AlertDialog alertDialog1 = new AlertDialog.Builder(
                        getActivity()).create();
                // Setting Dialog Title
                alertDialog1.setTitle("Settings");
                // Setting Dialog Message
                alertDialog1.setMessage("You have modified settings");
                // Setting OK Button
                alertDialog1.setButton("OK", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        // Write your code here to execute after dialog
                        // closed
                        Toast.makeText(getActivity(),
                                "You have modified settings", Toast.LENGTH_SHORT).show();
                    }
                });

                // Showing Alert Message
                alertDialog1.show();
            }
        });

        return rootView;
    }
}
