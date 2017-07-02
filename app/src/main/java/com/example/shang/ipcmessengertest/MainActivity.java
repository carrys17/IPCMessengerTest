package com.example.shang.ipcmessengertest;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private Messenger messenger2 = new Messenger(new MyHanlder());
    private class MyHanlder extends Handler{
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 777:
                    String s = msg.getData().getString("msggg");
                    Log.i("xyz","receive from service: "+s);
                    break;
                default:
                    super.handleMessage(msg);
            }
        }
    }

    private Messenger messenger;

    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            messenger = new Messenger(service);
            Message msg = Message.obtain(null,666);
            Bundle bundle = new Bundle();
            bundle.putString("hhh","I am Client ,Hello!");
            msg.setData(bundle);

            msg.replyTo = messenger2;
            try {
                //发送消息给服务器
                messenger.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(this,MessengerService.class);
        bindService(intent,conn, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onDestroy() {
        unbindService(conn);
        super.onDestroy();
    }
}
