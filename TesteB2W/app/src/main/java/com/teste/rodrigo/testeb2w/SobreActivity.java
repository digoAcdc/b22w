package com.teste.rodrigo.testeb2w;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.teste.rodrigo.testeb2w.Constanstes.Constants;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;



public class SobreActivity extends AppCompatActivity {

    TextView tvLoginha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sobre);

        Typeface typeface = Typeface.createFromAsset(getAssets(), Constants.FONTE_PACIFICO_REGULAR);
        tvLoginha = (TextView)findViewById(R.id.tv_lojinha);
        tvLoginha.setTypeface(typeface);

    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

}
