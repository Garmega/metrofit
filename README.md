# Metrofit (Developer Preview)

A cross-layer communication tool for Square's Retrofit.


**This document is draft and may not be completely accurate**

---

### Introduction

Retrofit gives developers a modern set of tools to interface with APIs. Metrofit gives developers the ability to react to Retrofit calls in every layer of the application no matter how many layers there may be.

A simple Android application utilizing Retrofit may be structured as follows;

The UI communicates with some object that either is the defined Retrofit interface or a holder object that invokes the Retrofit interface. As more complex applications are built, more layers may be put in-between the UI and the Retrofit interface. For instance;


Retrofit does not provide a convenient way to communicate to all layers of an Android application, nor is it it's job to do so. Metrofit is meant to be an communication channel for the Android application for each layer to respond appropriately and in a timely manner.

---

### Usage

*Developer's Note: This library takes advantage of Kotlin. Java developers please take note of the extra tags inserted.*


1) Define a `ResponseReceiver`.

###### Kotlin:

~~~~
val receiver = object : ResponseReceiver() {
    override fun onSuccessful(result: APIResult) {
        super.onSuccessful(result)

        // Display success/do more work
    }
}
~~~~
###### Java:
~~~~
ResponseReceiver receiver = new ResponseReceiver() {
    @Override
    public void onSuccessful(@NotNull APIResult result) {
        super.onSuccessful(result);
        
        // Display success/do more work
    }
};
~~~~


2) Define a `ResponseCallback` for each unique Retrofit API call. Pass in your `ResponseReceiver`

###### Kotlin:
~~~~
val callback = object: ResponseCallback<T>(receiver, false, "NEW_TAG") {
	override fun performIntake(body: T?, outboundFreight: Map<String, Any>) {
        super.performIntake(body, outboundFreight)

        // Perform conversions to local types here.
        // Add anything you want to outboundFreight.
        // outboundFreight will be available inside APIResult on the receiver end.
    }
}
~~~~

###### Java:

~~~~
ResponseCallback callback = new ResponseCallback(receiver, false, "NEW_TAG") {
    @Override
    public void performIntake(@Nullable Object body, @NotNull Map outboundFreight) {
        super.performIntake(body, outboundFreight);

        // Perform conversions to local types here.
        // Add anything you want to outboundFreight.
        // outboundFreight will be available inside APIResult on the receiver end.
    }
};
~~~~

    

3) Invoke a Retrofit call and instead pass in your newly defined `ResponseCallback<T>` instead of `Retrofit.Callback<T>`

###### Kotlin & Java
~~~~
call.enqueue(callback)
~~~~ 

---

#### ResponseCallback

###### Overview

This object is a customized sub-class of the `Retrofit.Callback<T>`. Create, customize and pass this into your `Retrofit.Call<T>` objects provided by your Retrofit interface definition. 

Check API Documentation (temporarily at the bottem of this document) for more.

###### Kotlin Initialization Example

~~~~
val callback = object: ResponseCallback<T>(receiver, false, "NEW_TAG") {

    override fun performIntake(body: T?, outboundFreight: Map<String, Any>) {
        super.performIntake(body, outboundFreight)
        
        // Perform conversions to local types here.
    }
}
~~~~

###### Java Initialization Example

~~~~
ResponseCallback callback = new ResponseCallback(receiver, false, "NEW_TAG") {

    @Override
    public void performIntake(@Nullable Object body, @NotNull Map outboundFreight) {
        super.performIntake(body, outboundFreight);
        
        // Perform conversions to local types here.
    }
};
~~~~


---

# API Documentation

Metrofit consists of the following objects:

* `ResponseCallback` 
* `ResponseReceiver`
* `APIResult`

---

### ResponseCallback

###### Signature:

`ResponseCallback(receiver: ResponseReceiver, debugMode: Boolean, debugTag: String)`


* `ResponseReceiver` is the object that will be notified when `ResponseCallback` has finished processing the API call result.

* `debugMode` can be used to to print extra information in the console. 
	* This parameter has a default value of `false` and can be omitted in Kotlin implementations.
* `debugTag` can be defined to provide a sorting tag for information printed in console.
	* This parameter has a default value of `NO_TAG` and can be omitted in Kotlin implementations.

