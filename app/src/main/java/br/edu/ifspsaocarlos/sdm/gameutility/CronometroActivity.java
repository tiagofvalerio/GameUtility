package br.edu.ifspsaocarlos.sdm.gameutility;

import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Chronometer;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.concurrent.TimeUnit;

public class CronometroActivity extends Activity {

    CountDownTimer temporizador;

    TextView tvTemporizador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cronometro);
        tvTemporizador = (TextView)findViewById(R.id.tv_temporizador);
    }

    public void start(View v) {
        temporizador = new CountDownTimerClass(100000, 1000);
        temporizador.start();
    }

    public class CountDownTimerClass extends CountDownTimer {

        public CountDownTimerClass(long millisInFuture, long countDownInterval) {

            super(millisInFuture, countDownInterval);

        }

        @Override
        public void onTick(long millisUntilFinished) {
            long millis= millisUntilFinished;
            String hms= String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(millis),
                    TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),
                    TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis))
            );

            tvTemporizador.setText(hms);

        }

        @Override
        public void onFinish() {
            tvTemporizador.setText("O tempo acabou!");
        }
    }
}
