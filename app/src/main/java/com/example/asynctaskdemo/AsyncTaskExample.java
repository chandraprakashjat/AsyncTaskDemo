package com.example.asynctaskdemo;

import android.os.AsyncTask;
import android.util.Log;

public class AsyncTaskExample extends AsyncTask<String, Integer, String> {

    private final TaskListener listener;

    public AsyncTaskExample(TaskListener listener) {
        this.listener = listener;
    }

    @Override
    protected void onPreExecute() {
        listener.onTaskStarted();
    }

    @Override
    protected String doInBackground(String... params) {
        for (int i = 1; i <= 10; i++) {
            Log.d("GREC", "AsyncTask is working: " + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return "All Done!";
    }

    @Override
    protected void onPostExecute(String result) {
        listener.onTaskFinished(result);
    }
}
