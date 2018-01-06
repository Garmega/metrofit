package com.garmega.metrofit_sample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.garmega.metrofit.APIResult;
import com.garmega.metrofit.ResponseReceiver;

import org.jetbrains.annotations.NotNull;

public class JavaActivity extends AppCompatActivity {
    private final String TAG = "MAIN_ACTIVITY";

    private Button btnAPITest;
    private ProgressBar progressBar;
    private TextView messageBar;
    private EditText edtTxtCity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAPITest = findViewById(R.id.btn_api_test);
        progressBar = findViewById(R.id.indeterminateBar);
        messageBar = findViewById(R.id.messageView);
        edtTxtCity = findViewById(R.id.edt_txt_city);

        btnAPITest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edtTxtCity.getText().toString().trim().length() == 0) {
                    messageBar.setText(R.string.get_weather_no_city);
                } else {
                    apiButtonPress();
                }
            }
        });
    }

    private void apiButtonPress() {
        Log.d(TAG, "btnAPITEST pressed!");

        btnAPITest.setEnabled(false);
        progressBar.setVisibility(View.VISIBLE);
        messageBar.setText(R.string.get_weather_fetching);

        ResponseReceiver receiver = new ResponseReceiver() {
            @Override
            public void onSuccessful(@NotNull APIResult result) {
                super.onSuccessful(result);

                Log.i(TAG, "UINotifier - onSuccessful");
                messageBar.setText(R.string.get_weather_success);
            }

            @Override
            public void onUnsuccessful(@NotNull APIResult result) {
                super.onUnsuccessful(result);

                Log.i(TAG, "UINotifier - onUnsuccessful");
                messageBar.setText(R.string.get_weather_unsuccessful);
            }

            @Override
            public void onDestroy() {
                super.onDestroy();

                Log.i(TAG, "UINotifier - onPowerDown");

                btnAPITest.setEnabled(true);
                progressBar.setVisibility(View.INVISIBLE);
            }
        };


        APICaller.INSTANCE.getWEATHER_SERVICE_MANAGER().getWeather(edtTxtCity.getText().toString().trim(), receiver);
    }
}
