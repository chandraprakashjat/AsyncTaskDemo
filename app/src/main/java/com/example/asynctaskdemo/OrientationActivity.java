package com.example.asynctaskdemo;


import android.app.ProgressDialog;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

public class OrientationActivity extends AppCompatActivity implements TaskListener, View.OnClickListener {

    private ProgressDialog progressDialog;
    private TextView textView;
    private static final int CONTENT_VIEW_ID = 10101010;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orientation);

        textView = findViewById(R.id.textView2);

        (findViewById(R.id.button4)).setOnClickListener(this);


//        int currentOrientation = getResources().getConfiguration().orientation;
//
//        if( currentOrientation == Configuration.ORIENTATION_LANDSCAPE)
//        {
//          setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
//        }else
//        {
//            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
//        }
//
//        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);



        if (savedInstanceState == null)
        {
            setFragment(new ExampleFragment());
        }

    }

    protected void setFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction =
                fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.frame_container, fragment);
        fragmentTransaction.addToBackStack("test");
        fragmentTransaction.commit();
    }

    @Override
    public void onClick(View v)
    {
        if (v.getId() == R.id.button4)
        {
            new AsyncTaskExample(this).execute();


        }
    }

    @Override
    public void onTaskStarted() {

       progressDialog = ProgressDialog.show(OrientationActivity.this, "Loading", "Please wait a moment!");

        textView.setText("start");

    }

    @Override
    public void onTaskFinished(String result) {
       if (progressDialog != null) {
          progressDialog.dismiss();
     }

        textView.setText(result);
    }
}
