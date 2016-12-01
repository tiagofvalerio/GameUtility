package br.edu.ifspsaocarlos.sdm.gameutility;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    private final int CRONOMETRAR = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void chamaActivityCronometro(View v) {
        Intent i = new Intent(this, CronometroActivity.class);
        startActivityForResult(i, CRONOMETRAR);
    }
}
