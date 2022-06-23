package com.example.get_sync_binder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.sync.BinderContentProvider;
import com.example.sync.BinderService;

public class MainActivity extends AppCompatActivity {

    public Button get_binder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        get_binder = findViewById(R.id.get_binder);
        get_binder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**
                 * bindService方式获取binder服务
                 * */
                Intent intent = new Intent(MainActivity.this, BinderService.class);
                long begin = System.currentTimeMillis();
                long finalBegin = begin;
                MainActivity.this.bindService(intent, new ServiceConnection() {
                    @Override
                    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                        long end = System.currentTimeMillis();
                        ITestService iTestService = ITestService.Stub.asInterface(iBinder);
                        Log.e("andy", " bindService 回调耗时 " + (end - finalBegin));
                        try {
                            iTestService.test();
                            iTestService.handle("bindService called!");
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onServiceDisconnected(ComponentName componentName) {

                    }
                }, Context.BIND_AUTO_CREATE);

                /**
                 * ContentPrivider方式获取binder服务
                 * */
                ContentResolver contentResolver = MainActivity.this.getContentResolver();
                Uri uri = Uri.parse("content://com.example.get_sync_binder.provider");

                begin = System.currentTimeMillis();
                Bundle bundle = contentResolver.call(uri, BinderContentProvider.sGetBinderMethod, null, null);
                IBinder binder = bundle.getBinder(BinderContentProvider.BinderKey);
                ITestService iTestService = ITestService.Stub.asInterface(binder);
                long end = System.currentTimeMillis();
                Log.e("andy", " ContentProvider call 回调耗时 " + (end - begin));

                try {
                    iTestService.test();
                    iTestService.handle("ContentProvider call called!");
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}