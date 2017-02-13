package com.teste.rodrigo.testeb2w;

import android.app.Application;
import android.graphics.Typeface;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class B2wApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();



        /*CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Pacifico-Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );*/
    }
}
