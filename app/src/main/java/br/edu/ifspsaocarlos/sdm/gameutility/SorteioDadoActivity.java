package br.edu.ifspsaocarlos.sdm.gameutility;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class SorteioDadoActivity extends Activity {

    private static final int MIN = 1;

    private static final int MAX = 6;

    private ImageView firstImage;

    private ImageView secondImage;

    private TextView playerGanhador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sorteio_dado);
        firstImage = (ImageView)findViewById(R.id.dado01);
        secondImage = (ImageView)findViewById(R.id.dado02);
        playerGanhador = (TextView)findViewById(R.id.player_ganhador);
    }

    public void sortear(View v) {
        Random r = new Random();
        int firstDado = r.nextInt(MAX - MIN + 1) + MIN;
        int secondDado = r.nextInt(MAX - MIN + 1) + MIN;

        setImage(firstDado, firstImage);
        setImage(secondDado, secondImage);

        if(firstDado > secondDado) {
            playerGanhador.setText("Player A ganhou!!");
        }

        if(firstDado < secondDado) {
            playerGanhador.setText("Player B ganhou!!");
        }

        if(firstDado == secondDado) {
            playerGanhador.setText("Empatou!!");
        }
    }

    private void setImage(final int numDado, final ImageView iv) {
        switch (numDado) {
            case 1:
                iv.setImageResource(R.drawable.dado_face_1);
                break;
            case 2:
                iv.setImageResource(R.drawable.dado_face_2);
                break;
            case 3:
                iv.setImageResource(R.drawable.dado_face_3);
                break;
            case 4:
                iv.setImageResource(R.drawable.dado_face_4);
                break;
            case 5:
                iv.setImageResource(R.drawable.dado_face_5);
                break;
            case 6:
                iv.setImageResource(R.drawable.dado_face_6);
                break;
        }
    }
}
