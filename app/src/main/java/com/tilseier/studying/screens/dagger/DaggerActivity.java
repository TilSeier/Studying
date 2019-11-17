package com.tilseier.studying.screens.dagger;

import android.os.Bundle;

import com.tilseier.studying.R;
import com.tilseier.studying.screens.dagger.di.AppComponent;
import com.tilseier.studying.screens.dagger.di.BraavosModule;
import com.tilseier.studying.screens.dagger.di.Cash;
import com.tilseier.studying.screens.dagger.di.DaggerAppComponent;
import com.tilseier.studying.screens.dagger.di.Soldiers;
import com.tilseier.studying.screens.dagger.di.War;

import androidx.appcompat.app.AppCompatActivity;

public class DaggerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dagger);

        Cash cash = new Cash();
        Soldiers soldiers = new Soldiers();

        AppComponent appComponent = DaggerAppComponent
                .builder()
                .braavosModule(new BraavosModule(cash, soldiers))
                .build();
        War war = appComponent.getWar();
        war.prepare();
        war.report();
    }
}
