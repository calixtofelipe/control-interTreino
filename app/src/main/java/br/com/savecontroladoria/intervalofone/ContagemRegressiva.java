package br.com.savecontroladoria.intervalofone;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.util.Log;

/**
 * Created by ADMIN on 10/11/2016.
 */
public class ContagemRegressiva extends CountDownTimer {
    private Context context;
    /**
     * @param millisInFuture    The number of millis in the future from the call
     *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
     *                          is called.
     * @param countDownInterval The interval along the way to receive
     *                          {@link #onTick(long)} callbacks.
     */
    public ContagemRegressiva(Context context,long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);
        this.context = context;
    }

    @Override
    public void onTick(long millisUntilFinished) {
        Log.i("ContaRegre", "" + millisUntilFinished / 1000);
    }

    @Override
    public void onFinish() {
        Log.i("ContaRegre","finalizou Contagem");
        MediaPlayer mp = MediaPlayer.create(context, R.raw.sound_final);
        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

            @Override
            public void onCompletion(MediaPlayer mp) {

                mp.release();
            }

        });
        mp.start();

        Vibrator rr = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        long[] milliseconds = new long[]{150,300,150,600};//'30' é o tempo em milissegundos, é basicamente o tempo de duração da vibração. portanto, quanto maior este numero, mais tempo de vibração você irá ter
        rr.vibrate(milliseconds,-1);

    }
}
