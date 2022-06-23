package com.example.sync;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.example.helper.SingletonBinder;

public class BinderService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return SingletonBinder.testService.asBinder();
    }
}
