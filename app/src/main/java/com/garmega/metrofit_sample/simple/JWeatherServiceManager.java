package com.garmega.metrofit_sample.simple;

import com.garmega.metrofit.ResponseCallback;
import com.garmega.metrofit.ResponseReceiver;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by Nick on 1/10/18.
 */

public class JWeatherServiceManager {

    public static void getWeather(String cityName, ResponseReceiver receiver) {

        ResponseCallback callback = new ResponseCallback(receiver, false, "GET_WEATHER") {
            @Override
            public void performIntake(@Nullable Object body, @NotNull Map outboundFreight) {

            }

            @Override
            public void onCallFailure(@NotNull Call call, @NotNull Throwable t) {

            }

            @Override
            public void onUnsuccessfulResponse(@NotNull Call call, @NotNull Response response) {

            }
        };

    }
}
