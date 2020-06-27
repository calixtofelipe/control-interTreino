package br.com.savecontroladoria.intervalofone;

import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.session.MediaSession;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.InputType;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import br.com.savecontroladoria.intervalofone.eventbus.EscutaEB;
import de.greenrobot.event.EventBus;

public class MainActivity extends AppCompatActivity {
    private EditText timeIntervalo;
    private Button btnAtivar;
    private Button btnEditar;
     static String intervalo;
    //Access to audio service
    BTNPowerReceiver mReceiver = new BTNPowerReceiver(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_ON);
        filter.addAction(Intent.ACTION_SCREEN_OFF);

        registerReceiver(mReceiver, filter);

        TextView tvManual = (TextView) findViewById(R.id.tvManual);
        timeIntervalo = (EditText) findViewById(R.id.timeIntervalo);
        timeIntervalo.setInputType(InputType.TYPE_CLASS_NUMBER);
        btnAtivar = (Button) findViewById(R.id.btnAtivar);
        btnEditar = (Button) findViewById(R.id.btnEditar);

        tvManual.setTextSize(12);
        if (Build.VERSION.SDK_INT >= 24) {
            tvManual.setText(Html.fromHtml("<font size=\"2\"><b>Manual<o:p></o:p></b></p><p class=MsoNormal>Esse aplicativo medir o intervalo do treino." +
                    "<BR>\n" +
                    "Ao dar duplo click no botão de bloqueio/POWER (quando a tela tiver desligada).Tem que ser com a tela desligada e duplo clique, pois assim manterá a tela do celular desligada." +
                    "Inicia a contagem conforme tempo informado, com um bip e ao final outro som é emitido junto com uma vibração.\n" +
                    "PONTO FINAL. É só isso que precisava e criei. Ahhh...mas tem como selecionar os treinos?....medir calorias?!?!...passos...gps..." +
                    "Para isso, tem outros aplicativos excelentes no google play. Esse criei somente para fazer essa funcionalidade que não encontrei. " +
                    "Eu queria medir meus 40 segundos de intervalo, mas eu que queria acionar a contagem. " +
                    "Não sou um robô e dependendo do dia/exercício queria ter um tempo maior ou menor para acionar os 40 segundos. " +
                    "Só isso."
                    , 0));
        } else {
            tvManual.setText(Html.fromHtml("<font size=\"2\"><b>Manual<o:p></o:p></b></p><p class=MsoNormal>Esse aplicativo medir o intervalo do treino." +
                            "<BR>\n" +
                            "Ao dar duplo click no botão de bloqueio/POWER (quando a tela tiver desligada).Tem que ser com a tela desligada e duplo clique, pois assim manterá a tela do celular desligada." +
                            "Inicia a contagem conforme tempo informano, com um bip e ao final outro som é emitido junto com uma vibração.\n" +
                            "PONTO FINAL. É só isso que precisava e criei. Ahhh...mas tem como selecionar os treinos?....medir calorias?!?!...passos...gps..." +
                            "Para isso, tem outros aplicativos execelentes no google play. Esse criei somente para fazer essa funcionalidade que não encontrei. " +
                            "Eu queria medir meus 40 segundos de intervalo, mas eu que queria acionar a contagem. " +
                            "Não sou um robô e dependendo do dia/exercicio queria ter um tempo maior ou menor para acionar os 40 segundos. " +
                    "Só isso."
                    ));
        }

        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnAtivar.setEnabled(true);
                timeIntervalo.setEnabled(true);
            }
        });

        btnAtivar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("MainActivity", "clickbotao");

                setIntervalo(timeIntervalo.getText().toString());
                btnAtivar.setEnabled(false);
                timeIntervalo.setEnabled(false);



            /*
                EventBus eventBus = EventBus.getDefault();
                EscutaEB msg = new EscutaEB();
                msg.setClasseresponder(BTNPowerReceiver.class + "");
                msg.setVlrcampo(timeIntervalo.getText().toString());
                eventBus.post(msg);*/

            }
        });

    }

    public static String getIntervalo() {
        return intervalo;
    }

    public static void setIntervalo(String intervalo) {
        MainActivity.intervalo = intervalo;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Log.d("MainActivity", "entrou n oonkeyDown"+keyCode);
        switch (keyCode) {
            case KeyEvent.KEYCODE_POWER:
            {
                Toast.makeText(getBaseContext(), "Power button pressed", Toast.LENGTH_LONG).show();
                Log.d("MainActivity", "power pressionado 1");
                return true;
            }
            case KeyEvent.KEYCODE_MENU:
                Toast.makeText(getBaseContext(), "Menu Button Pressed", Toast.LENGTH_LONG).show();
                Log.d("MainActivity", "power pressionado 2");
                return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("MainActivity", "entrou no OnPause");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("MainActivity", "entrou no Ondestroy");
        unregisterReceiver(mReceiver);
    }
}
