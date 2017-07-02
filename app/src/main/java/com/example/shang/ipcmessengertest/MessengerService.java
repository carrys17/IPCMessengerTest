package com.example.shang.ipcmessengertest;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by shang on 2017/7/2.
 */

public class MessengerService extends Service {


    final Messenger messenger = new Messenger(new MyServiceHandler());


    private static class MyServiceHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 666:
                    // 接受客户端的信息
                    String s = msg.getData().getString("hhh");
                    Log.i("xyz","receive msg from client:"+s);

                    Messenger messenger1 = msg.replyTo;
                    Message message = new Message();
                    message.what = 777;
                    Bundle bundle = new Bundle();
                    bundle.putString("msggg","I am Service");
                    message.setData(bundle);
                    try {
                        messenger1.send(message);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    super.handleMessage(msg);
            }
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return messenger.getBinder();
    }

}
