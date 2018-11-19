package com.digicon_valley.bound_service_using_messenger;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.widget.Toast;

public class MyService extends Service {
    Messenger messenger=new Messenger(new incomingHandler());
    static final int JOB_1=1;
    static final int JOB_2=2;

    public IBinder onBind(Intent intent) {

        Toast.makeText(getApplicationContext(),"Service Binder...",Toast.LENGTH_SHORT).show();
        return messenger.getBinder();
    }

    class incomingHandler extends Handler{

        @Override
        public void handleMessage(Message msg) {

            switch ((msg.what)){

                case JOB_1:
                    Toast.makeText(getApplicationContext(),"Hello From Job 1",Toast.LENGTH_SHORT).show();
                    break;
                case JOB_2:
                    Toast.makeText(getApplicationContext(),"Hello From Job 2",Toast.LENGTH_SHORT).show();
                    break;
                default:
            super.handleMessage(msg);
            }
        }
    }
}
