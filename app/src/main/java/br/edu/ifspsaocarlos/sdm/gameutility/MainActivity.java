package br.edu.ifspsaocarlos.sdm.gameutility;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    private final int CRONOMETRAR = 0;

    private final int SORTEAR = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void chamaActivityCountDown(View v) {
        Intent i = new Intent(this, CountDownActivity.class);
        startActivityForResult(i, CRONOMETRAR);
    }

    public void chamaActivitySorteio(View v) {
        Intent i = new Intent(this, SorteioDadoActivity.class);
        startActivityForResult(i, SORTEAR);
    }
}
