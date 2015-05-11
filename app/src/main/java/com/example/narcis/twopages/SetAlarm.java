package com.example.narcis.twopages;

import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.os.Handler;
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

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.Set;
import java.util.UUID;
import java.math.*;


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
    private int hoursRemaining;
    private int minutesRemaining;
    private String timer;
    private String hoursRemainingString;
    private String minutesRemainingString;
    //Bluetooth variable declarations
    BluetoothAdapter mBluetoothAdapter;
    BluetoothDevice mDevice;
    ConnectThread mConnectThread;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.setalarm_layout, container, false);
        alarmTimePicker = (TimePicker) rootView.findViewById(R.id.timePicker);
        alarmTimePicker.setIs24HourView(true);
        Button button = (Button) rootView.findViewById(R.id.button);

        //Bluetooth stuff here
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        if (mBluetoothAdapter == null){
            Log.v("no bluetooth device", "");
        }

        if (!mBluetoothAdapter.isEnabled()){
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, 1);
            Log.v("bluetooth enabled", "");
        }

        Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();
        if (pairedDevices.size() > 0){
            for (BluetoothDevice device : pairedDevices){
                mDevice = device;
                Toast.makeText(getActivity(), "Smart Alarm found", Toast.LENGTH_SHORT).show();
            }
        }



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
             long timeCurrent=mHour2*3600+mMinute2*60;
             long timeSet= hourPicked*3600+minutePicked*60;
             Log.v("time current", Long.toString(timeCurrent));
             Log.v("time set: ", Long.toString(timeSet));
             if (timeSet<timeCurrent) timeSet=timeSet+(3600*24);
             Log.v("time set new: ", Long.toString(timeSet));
             long timeRemaining=timeSet-timeCurrent+5;
             timer= Long.toString(timeRemaining);
             Log.v("remaning hours: ", Long.toString(timeRemaining / 3600));
             Log.v("remaning hours: ", timer);
            // hoursRemaining = hourPicked - mHour2;
           //  minutesRemaining = Math.abs(minutePicked - mMinute2);
            // hoursRemainingString = Integer.toString(hoursRemaining);
            // minutesRemainingString = Integer.toString(minutesRemaining);


             //       if (hoursRemaining < 0) {
             //           hoursRemaining = Math.abs(hoursRemaining) + 2;
             //       }





             //    if (hoursRemaining > 10) {
             //        Toast.makeText(getActivity(),
             //                "Please set the alarm to less than 10 hours", Toast.LENGTH_SHORT).show();
             //    } else {
             //        if (hoursRemaining < 10) {
             //            hoursRemainingString = "0" + hoursRemainingString;
             //        }
             //        if (minutesRemaining < 10) {
             //            minutesRemainingString = "0" + minutesRemainingString;
             //        }

//             Log.v("remaning hours: ", Long.toString(Math.abs(hoursRemaining)));
//             Log.v("remaining minutes: ", Long.toString(Math.abs(minutesRemaining)));

 //                    timer = hoursRemainingString + ":" + minutesRemainingString + ":05";
                     Log.v(timer, " timer");
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

                     mConnectThread = new ConnectThread(mDevice);
                     mConnectThread.start();
                 }
        //    }
        });
        return rootView;
    }

    private class ConnectThread extends Thread {
        private final BluetoothSocket mmSocket;
        private final BluetoothDevice mmDevice;
        private final UUID MY_UUID = UUID.fromString("00001101-0000-1000-8000-00805f9b34fb");

        public ConnectThread(BluetoothDevice device) {
            BluetoothSocket tmp = null;
            mmDevice = device;
            try {
                tmp = device.createRfcommSocketToServiceRecord(MY_UUID);
                Toast.makeText(getActivity(), "almost connected ", Toast.LENGTH_SHORT).show(); // set for 5 hour and 15
            } catch (IOException e) { }
            mmSocket = tmp;
        }

        public void run() {
            //mBluetoothAdapter.cancelDiscovery();
            try {
                mmSocket.connect();
//                mHandler.obtainMessage(3, begin, i, buffer).sendToTarget();

            } catch (IOException connectException) {
                try {
                    mmSocket.close();
                } catch (IOException closeException) { }
                return;
            }
            final Calendar c2 = Calendar.getInstance();
            int mHour2 = c2.get(Calendar.HOUR_OF_DAY);
            int mMinute2 = c2.get(Calendar.MINUTE);
            int mSecond2 = c2.get(Calendar.SECOND);


            //timer = "00:00:05";
            ConnectedThread mConnectedThread = new ConnectedThread(mmSocket);
            mConnectedThread.start();
            //Gaybe modify here to set a custom timer


            mConnectedThread.write((timer).getBytes());
        }
        public void cancel() {
            try {
                mmSocket.close();
            } catch (IOException e) { }
        }
    }
    private class ConnectedThread extends Thread {
        private final BluetoothSocket mmSocket;
        private final InputStream mmInStream;
        private final OutputStream mmOutStream;
        public ConnectedThread(BluetoothSocket socket) {
            mmSocket = socket;
            InputStream tmpIn = null;
            OutputStream tmpOut = null;
            try {
                tmpIn = socket.getInputStream();
                tmpOut = socket.getOutputStream();
            } catch (IOException e) { }
            mmInStream = tmpIn;
            mmOutStream = tmpOut;


        }
        public void run() {
            byte[] buffer = new byte[1024];
            int begin = 0;
            int bytes = 0;
            while (true) {
                try {
                    bytes += mmInStream.read(buffer, bytes, buffer.length - bytes);
                    for(int i = begin; i < bytes; i++) {
                        if(buffer[i] == "#".getBytes()[0]) {

                            mHandler.obtainMessage(1, begin, i, buffer).sendToTarget();
                            begin = i + 1;
                            if(i == bytes - 1) {
                                bytes = 0;
                                begin = 0;
                            }
                        }
                    }
                } catch (IOException e) {
                    break;
                }
            }

        }
        public void write(byte[] bytes) {
            try {
                mmOutStream.write(bytes);
                Message msg = mHandler.obtainMessage(2);
                Bundle bundle = new Bundle();
                bundle.putString("whatever", "name");
                msg.setData(bundle);

                mHandler.sendMessage(msg);
            } catch (IOException e) { }
        }
        public void cancel() {
            try {
                mmSocket.close();
            } catch (IOException e) { }
        }
    }
    public Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            byte[] writeBuf = (byte[]) msg.obj;
            int begin = (int)msg.arg1;
            int end = (int)msg.arg2;

            switch(msg.what) {
                case 1:
                    String writeMessage = new String(writeBuf);
                    writeMessage = writeMessage.substring(begin, end);
                    break;
                case 2:
                    Toast.makeText(getActivity(), "Alarm set", Toast.LENGTH_SHORT).show();

                    break;
                case 3:

                    break;
            }
        }
    };
}
