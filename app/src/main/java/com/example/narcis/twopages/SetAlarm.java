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


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.setalarm_layout, container, false);
        alarmTimePicker = (TimePicker) rootView.findViewById(R.id.timePicker);
        alarmTimePicker.setIs24HourView(true);

        Button button = (Button) rootView.findViewById(R.id.button);
//        hour = alarmTimePicker.getCurrentHour();
//        minute = alarmTimePicker.getCurrentMinute();

        alarmTimePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {

            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                hourPicked = alarmTimePicker.getCurrentHour();
                minutePicked = alarmTimePicker.getCurrentMinute();
//                String hourString = Integer.toString(hourPicked);
//                String minuteString = Integer.toString(minutePicked);
//                Log.v("hour changed: ", hourString);
//                Log.v("minute changed: ", minuteString);
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

                Log.v("current hour: ", Integer.toString(mHour2));
                Log.v("current minute: ", Integer.toString(mMinute2));

                long hoursRemaining = hourPicked - mHour2;
                long minutesRemaining = minutePicked - mMinute2;
                Log.v("remaning hours: ", Long.toString(hoursRemaining));
                Log.v("remaining minutes: ", Long.toString(minutesRemaining));


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
