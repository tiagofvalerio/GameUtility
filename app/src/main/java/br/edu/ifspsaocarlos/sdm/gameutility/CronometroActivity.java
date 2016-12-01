package br.edu.ifspsaocarlos.sdm.gameutility;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Chronometer;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.concurrent.TimeUnit;

public class CronometroActivity extends Activity {

    private static final int CONFIGURA_COUNTDOWN = 0;

    private CountDownTimer temporizadorPlayerA;

    private CountDownTimer temporizadorPlayerB;

    private TextView tvTemporizadorPlayerA;

    private TextView tvTemporizadorPlayerB;

    private Boolean isPlayerA = false;

    private Boolean isPlayerB = false;

    private Boolean isPausedA = false;

    private Boolean isPausedB = false;

    private Long startCountDown = 900000L;

    private Long lastCountDownA = 900000L;

    private Long lastCountDownB = 900000L;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cronometro);
        tvTemporizadorPlayerA = (TextView) findViewById(R.id.temporizadorA);
        tvTemporizadorPlayerB = (TextView) findViewById(R.id.temporizadorB);

        temporizadorPlayerA = new CountDownTimerClass(startCountDown, 1000);
        temporizadorPlayerB = new CountDownTimerClass(startCountDown, 1000);
    }

    public void movedA(View v) {
        if ((isPlayerA.equals(isPlayerB)) || isPlayerA) {
            isPlayerA = false;
            isPlayerB = true;
            temporizadorPlayerB = new CountDownTimerClass(lastCountDownB, 1000);
            temporizadorPlayerB.start();
            temporizadorPlayerA.cancel();
        }
    }

    public void movedB(View v) {
        if ((isPlayerA.equals(isPlayerB)) || isPlayerB) {
            isPlayerB = false;
            isPlayerA = true;
            temporizadorPlayerA = new CountDownTimerClass(lastCountDownA, 1000);
            temporizadorPlayerB.cancel();
            temporizadorPlayerA.start();
        }
    }

    public void resetScore(View v) {
        isPlayerA = false;
        isPlayerB = false;
        temporizadorPlayerA.cancel();
        temporizadorPlayerB.cancel();
        temporizadorPlayerA = new CountDownTimerClass(startCountDown, 1000);
        temporizadorPlayerB = new CountDownTimerClass(startCountDown, 1000);
        String hms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(startCountDown),
                TimeUnit.MILLISECONDS.toMinutes(startCountDown) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(startCountDown)),
                TimeUnit.MILLISECONDS.toSeconds(startCountDown) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(startCountDown)));
        tvTemporizadorPlayerA.setText(hms);
        tvTemporizadorPlayerB.setText(hms);
        lastCountDownA = startCountDown;
        lastCountDownB = startCountDown;
    }

    public void resumePause(View v) {
        if (isPlayerA) {
            if (isPausedA) {
                isPausedA = false;
                temporizadorPlayerA = new CountDownTimerClass(lastCountDownA, 1000);
                temporizadorPlayerA.start();
            } else {
                isPausedA = true;
                temporizadorPlayerA.cancel();
            }
        }

        if (isPlayerB) {
            if (isPausedB) {
                isPausedB = false;
                temporizadorPlayerB = new CountDownTimerClass(lastCountDownB, 1000);
                temporizadorPlayerB.start();
            } else {
                isPausedB = true;
                temporizadorPlayerB.cancel();
            }
        }
    }

    public void configurar(View v) {
        Intent intentConfigurarCountDown = new Intent(this, ConfigurarCountDownActivity.class);
        startActivityForResult(intentConfigurarCountDown, CONFIGURA_COUNTDOWN);
    }

    public class CountDownTimerClass extends CountDownTimer {

        public CountDownTimerClass(long millisInFuture, long countDownInterval) {

            super(millisInFuture, countDownInterval);

        }

        @Override
        public void onTick(long millisUntilFinished) {
            long millis = millisUntilFinished;
            String hms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(millis),
                    TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),
                    TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis))
            );

            if (isPlayerA) {
                tvTemporizadorPlayerA.setText(hms);
                lastCountDownA = millisUntilFinished;
            } else {
                tvTemporizadorPlayerB.setText(hms);
                lastCountDownB = millisUntilFinished;
            }

        }

        @Override
        public void onFinish() {
            if (isPlayerA) {
                tvTemporizadorPlayerA.setText("Time out!");
            } else {
                tvTemporizadorPlayerB.setText("Time out!");
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CONFIGURA_COUNTDOWN) {
            if (resultCode == RESULT_OK) {
                int seconds = data.getIntExtra("seconds", 0);
                int minutes = data.getIntExtra("minutes", 15);
                int hours = data.getIntExtra("hours", 0);

                long milliseconds = 0;
                milliseconds += TimeUnit.SECONDS.toMillis(seconds) + TimeUnit.MINUTES.toMillis(minutes) + TimeUnit.HOURS.toMillis(hours);
                temporizadorPlayerA = new CountDownTimerClass(milliseconds, 1000);
                temporizadorPlayerB = new CountDownTimerClass(milliseconds, 1000);

                String hms = String.format("%02d:%02d:%02d", hours, minutes, seconds);
                tvTemporizadorPlayerA.setText(hms);
                tvTemporizadorPlayerB.setText(hms);
                startCountDown = milliseconds;
            }
        }
    }
}
