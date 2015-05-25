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
import android.widget.ImageView;
import android.widget.Toast;
import com.example.narcis.twopages.R;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by narcis on 14/04/15.
 */
public class Settings extends Fragment {
    View rootView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        rootView = inflater.inflate(R.layout.settings_layout, container, false);
        final ImageView settingsImage =(ImageView)rootView.findViewById(R.id.imageView);

        return rootView;
    }
}
