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

import java.math.*;
import java.sql.Time;
import java.util.Calendar;


/**
 * Created by narcis on 14/04/15.
 */
public class SetAlarm extends Fragment {
    View rootView;
//    AlarmManager alarmManager;
//    private PendingIntent pendingIntent;
    private TimePicker alarmTimePicker;
//    private static SetAlarm inst;
//    private TextView alarmTextView;
    private int hourPicked;
    private int minutePicked;
    private int counter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.setalarm_layout, container, false);
        alarmTimePicker = (TimePicker) rootView.findViewById(R.id.timePicker);
        alarmTimePicker.setIs24HourView(true);
        Button button = (Button) rootView.findViewById(R.id.button);

        alarmTimePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                 hourPicked = alarmTimePicker.getCurrentHour();
                 minutePicked = alarmTimePicker.getCurrentMinute();
                Log.v("hour picked1", Integer.toString(hourPicked));
                Log.v("minute picked1: ", Integer.toString(minutePicked));
            }
         });

         button.setOnClickListener(new View.OnClickListener() {
             public void onClick(View v) {
             /* Perform action on click */
             Log.v("","Alarm set");
             Log.v("hour picked", Integer.toString(hourPicked));
             Log.v("minute picked: ", Integer.toString(minutePicked));

             final Calendar c2 = Calendar.getInstance();
             int mHour2 = c2.get(Calendar.HOUR_OF_DAY);
             int mMinute2 = c2.get(Calendar.MINUTE);
             int mSecond2 = c2.get(Calendar.SECOND);

             Log.v("current hour: ", Integer.toString(mHour2));
             Log.v("current minute: ", Integer.toString(mMinute2));

             long hoursRemaining = hourPicked - mHour2;
             long minutesRemaining = minutePicked - mMinute2;

             Log.v("remaning hours: ", Long.toString(Math.abs(hoursRemaining)));
             Log.v("remaining minutes: ", Long.toString(Math.abs(minutesRemaining)));


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
                     "Alarm set", Toast.LENGTH_SHORT).show();
                        }
                    });
                    // Showing Alert Message
                  alertDialog1.show();
             }
        });
        return rootView;
    }
}
