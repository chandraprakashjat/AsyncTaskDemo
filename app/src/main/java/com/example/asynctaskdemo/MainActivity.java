package com.example.asynctaskdemo;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{

    Button buttonStart,buttonStop,buttonCheckStatus;

    ThreadPoolExecutor threadPoolExecutor ;
    private TextView textView;
    private Test test;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Log.e("OnCreate","oncreate");
        setContentView(R.layout.activity_main);
        buttonStart = findViewById(R.id.button);
        buttonStop = findViewById(R.id.button2);
        buttonCheckStatus = findViewById(R.id.button3);
        textView = (TextView)findViewById(R.id.textView1);


        buttonStart.setOnClickListener(this);
        buttonStop.setOnClickListener(this);
        buttonCheckStatus.setOnClickListener(this);




    }

    @Override
    public void onClick(View v)
    {

        switch (v.getId())
        {

            case R.id.button:

                startAsyncTask();
              break;

            case R.id.button2:


                stopAsyncTask();
                break;

            case R.id.button3:


                checkStatus();
                break;

        }
    }

    private void checkStatus()
    {

        Log.e("checkStatus",test.getStatus() + "");

        try {
            Log.e("checkGet",test.get() + "");
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    //Mark:- stop async task
    @SuppressLint("LongLogTag")
    private void stopAsyncTask()
    {

        //Log.e("stopAsyncTask status",test.getStatus() + "status");
        boolean CANCEL = test.cancel(true);

        Log.e("stopA CANCEL",CANCEL + " CANCEL");
        Log.e("stopA status after caNCEL",test.getStatus() + "status");
    }



    //start AsyncTask
    @SuppressLint("LongLogTag")
    private void startAsyncTask()
    {

       test = new Test();
       Log.e(" startAsyncTask status", test.getStatus()+"");
       // test.execute();


        test =  new Test();

        test.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,1);

        test =  new Test();


        test.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,2);

        test =  new Test();

        test.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,3);

        test =  new Test();

        test.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,4);

        test =  new Test();

        test.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,5);

        test =  new Test();

        test.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,6);

        test =  new Test();

        test.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,7);

        for (int i = 8 ; i<350;i++)
        {
            test =  new Test();

            test.executeOnExecutor(AsyncTask.SERIAL_EXECUTOR,i);
        }





       Log.e(" startAsyncTask isCancelled", test.isCancelled()+"");

    }


    class Test extends AsyncTask<Integer ,Integer,Integer>
    {

        @Override
        protected void onPreExecute()
        {

            Log.e("STATUS 1",test.getStatus() + "IS STATUS1");
            super.onPreExecute();

            Log.e("onPreExecute","onPreExecute");
        }

        @Override
        protected Integer doInBackground(Integer... voids)
        {

           // Log.e("doInBackground","doInBackground " + voids + "");

            int i = 0;
            for (  i = 0;i<2;i++)
            {

                Log.e("test Value",i + "is For the "+ voids[0] );
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


                final int finalI = i;


               // publishProgress(i);

            }

           // Log.e("doInBackground isCancel", test.isCancelled()+"1");

            return voids[0];
        }

        @Override
        protected void onProgressUpdate(Integer... integers)
        {
            super.onProgressUpdate(integers);

           //Log.e("ValueNull",textView+"");
            textView.setText(String.valueOf(integers[0].intValue()));

            Log.e("onProgressUpdate",integers[0].intValue()+"");
        }

        @Override
        protected void onPostExecute(Integer integer)
        {
            super.onPostExecute(integer);

            Log.e("onPostExecute","onPostExecute " + integer.intValue() + "");

            textView.setText(String.valueOf(integer.intValue()));

            Log.e("onPostExecute isCancel", test.isCancelled() + "");
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();

            Log.e("onCancelled","onCancelled");

            Log.e("onCancelled",test.getStatus() + " status");
        }

        @Override
        protected void onCancelled(Integer integer)
        {
            super.onCancelled(integer);

            Log.e("onCancelled with result",test.getStatus() + "");
        }
    }



}
