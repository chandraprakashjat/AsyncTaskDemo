package com.example.asynctaskdemo;

public interface TaskListener {

    void onTaskStarted();

    void onTaskFinished(String result);
}
