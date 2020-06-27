package br.com.savecontroladoria.intervalofone;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.SystemClock;
import android.os.Vibrator;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.Toast;

import br.com.savecontroladoria.intervalofone.eventbus.EscutaEB;
import de.greenrobot.event.EventBus;

/**
 * Created by ADMIN on 10/11/2016.
 */
public class BTNPowerReceiver extends BroadcastReceiver {

    private Activity activity=null;
    private static final String TAG_MEDIA = "RemoteControle";
    private static int countPowerOff = 0;
    long time = 0*1000;
    long time2 = 0*1000;
    Integer tempo=0;
    Integer tempo2=0;
    public BTNPowerReceiver(){}
    public BTNPowerReceiver (Activity activity)
    {
        this.activity=activity;
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
            time =SystemClock.uptimeMillis();
            tempo=(int) time;
            Integer result = tempo2-tempo;
            Log.e("In on receive", time+"In Method:  ACTION_SCREEN_OFF"+MainActivity.getIntervalo());
            Log.e("In on receive", "resultado:"+result);
            if(result<0 & result> -1200){
                long regressividade = 1 * 1000;
                ContagemRegressiva contagem = new ContagemRegressiva(context, Long.parseLong(MainActivity.getIntervalo()) * 1000, regressividade);

                MediaPlayer mp = MediaPlayer.create(context, R.raw.sound_inicio);
                mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

                    @Override
                    public void onCompletion(MediaPlayer mp) {

                        mp.release();
                    }

                });
                mp.start();
                contagem.start();
            } else {
           countPowerOff=0;
            }

        } else if (intent.getAction().equals(Intent.ACTION_SCREEN_ON)) {
            time2 =SystemClock.uptimeMillis();
            tempo2=(int) time2;
            Integer result = tempo2-tempo;
            Log.e("In on receive", time2+"In Method:  ACTION_SCREEN_ON"+countPowerOff);
            Log.e("In on receive", "resultado:"+result);
            if(countPowerOff==0){
                countPowerOff++;
            } else {
                countPowerOff=0;
            }
        }



    }


 /*EventBus.getDefault().unregister(BTNPowerReceiver.this);
    public void onEvent(EscutaEB escutaEB) {
        if (!escutaEB.getClasseresponder().equalsIgnoreCase(BTNPowerReceiver.class + "")) {
            return;
        } else {
            Log.i("BTNPower", "" + escutaEB.getVlrcampo());
            if (escutaEB.getVlrcampo() != null) {
                intervalo = escutaEB.getVlrcampo();
            }
        }

    }*/
}
