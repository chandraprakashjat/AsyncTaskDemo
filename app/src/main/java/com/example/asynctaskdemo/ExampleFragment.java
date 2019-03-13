package com.example.asynctaskdemo;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class ExampleFragment extends Fragment implements TaskListener, View.OnClickListener {

    private ProgressDialog progressDialog;
    private boolean isTaskRunning = false;
    private AsyncTaskExample asyncTask;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        Log.e("onAttach Fragment",isTaskRunning + " ");
    }

    @SuppressLint("LongLogTag")
    @Override
    public void onAttachFragment(Fragment childFragment) {
        super.onAttachFragment(childFragment);

        Log.e("onAttachFragment Fragment",isTaskRunning + " ");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.e("onCreate Fragment",isTaskRunning + " ");
      //setRetainInstance(true);
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        Log.e("onCreateView Fragment",isTaskRunning + " ");
        View view = inflater.inflate(R.layout.fragment_layout, container, false);
        Button button = (Button) view.findViewById(R.id.button5);
        button.setOnClickListener(this);
        return view;
    }


    @SuppressLint("LongLogTag")
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // If we are returning here from a screen orientation
        // and the AsyncTask is still working, re-create and display the

        Log.e("onActivityCreated Fragment",isTaskRunning + " ");
        // progress dialog.
        if (isTaskRunning) {
            progressDialog = ProgressDialog.show(getActivity(), "Loading", "Please wait a moment!");
            progressDialog.setCancelable(true);
        }
    }


    @Override
    public void onStart() {
        super.onStart();

        Log.e("onStart Fragment",isTaskRunning + " ");
    }

    @Override
    public void onResume() {
        super.onResume();

        Log.e("onResume Fragment",isTaskRunning + " ");
    }

    @Override
    public void onPause() {
        super.onPause();

        Log.e("onPause Fragment",isTaskRunning + " ");
    }

    @Override
    public void onStop() {
        super.onStop();

        Log.e("onStop Fragment",isTaskRunning + " ");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        Log.e("onDestroyView Fragment",isTaskRunning + " ");
    }


    @Override
    public void onDestroy() {
        super.onDestroy();

        Log.e("onDestroy Fragment",isTaskRunning + " ");
    }

    @Override
    public void onDetach() {
        // All dialogs should be closed before leaving the activity in order to avoid
        // the: Activity has leaked window com.android.internal.policy... exception
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }

        Log.e("onDetach Fragment",isTaskRunning + " ");
        super.onDetach();
    }

    @Override
    public void onClick(View v) {
        if (!isTaskRunning) {
            asyncTask = new AsyncTaskExample(this);
            asyncTask.execute();
        }
    }

    @Override
    public void onTaskStarted() {
        isTaskRunning = true;
        progressDialog = ProgressDialog.show(getActivity(), "Loading", "Please wait a moment!");
    }

    @Override
    public void onTaskFinished(String result) {
        if (progressDialog != null) {
           progressDialog.dismiss();
        }
        isTaskRunning = false;
    }


}