package com.example.helper;

import android.os.RemoteException;
import android.util.Log;

import com.example.get_sync_binder.ITestService;

public class SingletonBinder {
    public static ITestService testService = new ITestService.Stub() {
        @Override
        public void test() throws RemoteException {
            Log.e("andy", "test");
        }

        @Override
        public void handle(String message) throws RemoteException {
            Log.e("andy", "handle " + message);
        }
    };
}
