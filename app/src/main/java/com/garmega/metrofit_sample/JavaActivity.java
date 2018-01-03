package com.garmega.metrofit_sample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.garmega.metrofit.UINotifier;
import com.garmega.metrofit_sample.complex.UserManager;

public class JavaActivity extends AppCompatActivity {
    private final String TAG = "MAIN_ACTIVITY";

    private Button btnAPITest;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAPITest = findViewById(R.id.btn_api_test);

        btnAPITest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                apiButtonPress();
            }
        });
    }

    private void apiButtonPress() {
        Log.d(TAG, "btnAPITEST pressed!");

        UINotifier notifier = new UINotifier() {
            @Override
            public void onIncoming() {
                Log.i(TAG, "UINotifier - onIncoming");
            }

            @Override
            public void onSuccessful() {
                Log.i(TAG, "UINotifier - onSuccessful");
            }

            @Override
            public void onUnsuccessful() {
                Log.i(TAG, "UINotifier - onUnsuccessful");
            }

            @Override
            public void onPowerDown() {
                Log.i(TAG, "UINotifier - onPowerDown");
            }
        };

        APICaller.INSTANCE.getWEATHER_SERVICE_MANAGER().getWeather("SEATTLE", notifier);

    }
}
