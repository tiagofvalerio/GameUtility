package br.edu.ifspsaocarlos.sdm.gameutility;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.NumberPicker;

public class ConfigurarCountDownActivity extends Activity {

    private NumberPicker numberPickerSeconds;

    private NumberPicker numberPickerMinutes;

    private NumberPicker numberPickerHours;

    private int seconds = 0;

    private int minutes = 0;

    private int hours = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configurar_count_down);

        numberPickerSeconds = (NumberPicker)findViewById(R.id.np_seconds_configuracao_countdown);
        numberPickerSeconds.setMinValue(0);
        numberPickerSeconds.setMaxValue(59);
        numberPickerSeconds.setWrapSelectorWheel(true);
        numberPickerSeconds.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal){
                //Display the newly selected number from picker
                seconds = newVal;
            }
        });

        numberPickerMinutes = (NumberPicker)findViewById(R.id.np_minutes_configuracao_countdown);
        numberPickerMinutes.setMinValue(0);
        numberPickerMinutes.setMaxValue(59);
        numberPickerMinutes.setWrapSelectorWheel(true);
        numberPickerMinutes.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal){
                //Display the newly selected number from picker
                minutes = newVal;
            }
        });

        numberPickerHours = (NumberPicker)findViewById(R.id.np_hours_configuracao_countdown);
        numberPickerHours.setMinValue(0);
        numberPickerHours.setMaxValue(120);
        numberPickerHours.setWrapSelectorWheel(true);
        numberPickerHours.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal){
                //Display the newly selected number from picker
                hours = newVal;
            }
        });
    }

    public void finalizar(View v) {
        Intent intentResultado = new Intent();
        intentResultado.putExtra("seconds", seconds);
        intentResultado.putExtra("minutes", minutes);
        intentResultado.putExtra("hours", hours);
        setResult(RESULT_OK, intentResultado);
        finish();
    }
}
