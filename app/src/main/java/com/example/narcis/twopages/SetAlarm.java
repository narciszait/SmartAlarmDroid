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
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.widget.TimePicker;
import android.widget.ToggleButton;


/**
 * Created by narcis on 14/04/15.
 */
public class SetAlarm extends Fragment {
    View rootView;
//    AlarmManager alarmManager;
//    private PendingIntent pendingIntent;
//    private TimePicker alarmTimePicker;
//    private static SetAlarm inst;
//    private TextView alarmTextView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        rootView = inflater.inflate(R.layout.setalarm_layout, container, false);


        Button button = (Button)rootView.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                /* Perform action on click */
                Log.v("","Alarm set");


                AlertDialog alertDialog1 = new AlertDialog.Builder(
                        getActivity()).create();
                // Setting Dialog Title
                alertDialog1.setTitle("Set Alarm");
                // Setting Dialog Message
                alertDialog1.setMessage("You have set your alarm");
                // Setting OK Button
                alertDialog1.setButton("OK", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        // Write your code here to execute after dialog
                        // closed
                        Toast.makeText(getActivity(),
                                "You set your alarm", Toast.LENGTH_SHORT).show();
                    }
                });

                // Showing Alert Message
                alertDialog1.show();
            }
        });

        return rootView;
    }
}
