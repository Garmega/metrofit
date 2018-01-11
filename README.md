# Metrofit
A bundle of tools made to help developers handle a Retrofit API call throughout all layers of an Android application.

---

### Introduction

Retrofit gives developers a modern set of tools to interface with APIs. Metrofit gives developers the ability to process Retrofit calls in every layer of the application no matter how many layers there may be.

A simple Android application utilizing Retrofit may be structured as follows;

The UI communicates with some object that either is the defined Retrofit interface or a holder object that invokes the Retrofit interface. As more complex applications are built, more layers may be put in-between the UI and the Retrofit interface. For instance;


Retrofit does not provide a convenient way to communicate to all layers of an Android application, nor is it it's job to do so. Metrofit is meant to be an communication channel for the Android application for each layer to respond appropriately and in a timely manner.

### Usage

Metrofit consists of the following objects:

* `ResponseCallback` 
* `ResponseReceiver`
* `APIResult`

**Developer's Note**: This library takes advantage of Kotlin. Java developers please take note of the extra tags inserted.

#### ResponseCallback

###### Signature:

`ResponseCallback(receiver: ResponseReceiver, debugMode: Boolean, debugTag: String)`

This object is a customized sub-class of the `Retrofit.Callback<T>`. 

* `ResponseReceiver` is the object that will be notified when `ResponseCallback` has finished processing the API call result.

* `debugMode` can be used to to print extra information in the console. 
	* This parameter has a default value of `false` and can be omitted in Kotlin implementations.
* `debugTag` can be defined to provide a sorting tag for information printed in console.
	* This parameter has a default value of `NO_TAG` and can be omitted in Kotlin implementations.


###### Kotlin

~~~~
val callback = object: ResponseCallback<WeatherDataResponse>(receiver, false, "GET_WEATHER") {

    override fun performIntake(body: WeatherDataResponse?, outboundFreight: Map<String, Any>) {
        super.performIntake(body, outboundFreight)
    }
}
~~~~

###### Java

~~~~
ResponseCallback callback = new ResponseCallback(receiver, false, "GET_WEATHER") {
    @Override
    public void performIntake(@Nullable Object body, @NotNull Map outboundFreight) {
        super.performIntake(body, outboundFreight);
    }
};
~~~~

