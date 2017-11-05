# Metrofit
---
### Introduction

Retrofit is amazing. Use Retrofit! However Retrofit does exactly what it's job description says and nothing more. Metrofit sits on top of Retrofit and provides a series of useful classes and hooks to allow different application layers to respond to network calls in a way that makes sense for the layer.

eg:
You have an Android application divided into the following layers;
* UI Layer: This is what is presented to the user and provides a way for the user to interact with services.
* Business Logic Layer: Logic layer that the UI layer talks to. This layer is the meat of the application and is where network calls are executed.
* Data Layer: Where data is stored and the busy work that is needed to maintain structure

The problem is when a network call is made, each layer in may need to know the status of a the call. Retrofit uses standard HTTP status codes and responses. However outside a response processing class, the other layers need not know the technical specifics of a call. Sometimes one layer needs to do some intake logic before passing the response to another layer. This leads to some layers implementing response logic that doesn't make sense to the layer and a whole series of `BroadcastReceiver`s and `BroadcastIntent`s that need to be remembered.

Enter Metrofit. Metrofit at it's core is just an extension of Retrofit's `Callback` object. By handling the `BroadcastReceiver`s internally and providing hooks that can be attached to the `Callback`, each layer can respond to events that makes sense in their context.