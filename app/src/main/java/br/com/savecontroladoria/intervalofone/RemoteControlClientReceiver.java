package br.com.savecontroladoria.intervalofone;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.util.Log;
import android.view.KeyEvent;

/**
 * Created by ADMIN on 10/11/2016.
 */
public class RemoteControlClientReceiver extends BroadcastReceiver {


    private static final String TAG_MEDIA = "RemoteControle";

    @Override
    public void onReceive(Context context, Intent intent) {

    String intentAction = intent.getAction();
        long time = SystemClock.uptimeMillis();
        Log.i (TAG_MEDIA, intentAction.toString() + " deu certo");
        if (!Intent.ACTION_MEDIA_BUTTON.equals(intentAction)) {
            Log.i (TAG_MEDIA, "no media button information");
            return;
        }
        KeyEvent event = (KeyEvent)intent.getParcelableExtra(Intent.EXTRA_KEY_EVENT);
        if (event == null) {
            Log.i(TAG_MEDIA, "no keypress");
            return;
        }
    }
}
