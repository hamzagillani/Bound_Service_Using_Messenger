package com.digicon_valley.bound_service_using_messenger;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Messenger messenger = null;
    boolean inBind = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void bindService(View view) {

        Intent intent = new Intent(this, MyService.class);
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
    }

    public void sayHello(View view) {
        if (inBind) {
            String button_text;
            button_text = (String) ((Button) view).getText();

            if (button_text.equals("Say Hello")) {

                Message message = Message.obtain(null, MyService.JOB_1, 0, 0, 0);
                try {
                    messenger.send(message);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }

            } else if (button_text.equals("Say Hello Again")) ;


            Message message = Message.obtain(null, MyService.JOB_2, 0, 0, 0);
            try {
                messenger.send(message);
            } catch (RemoteException e) {
                e.printStackTrace();
            }

        } else {
            Toast.makeText(getApplicationContext(), "Bind Service First", Toast.LENGTH_LONG).show();
        }

    }

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

            messenger = new Messenger(service);
            inBind = true;


        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

            messenger = null;
            inBind = false;

        }
    };

    @Override
    protected void onStop() {
        unbindService(serviceConnection);
        inBind = false;
        messenger = null;
        super.onStop();
    }

}
