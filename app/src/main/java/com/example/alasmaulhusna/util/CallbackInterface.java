package com.example.alasmaulhusna.util;

import java.util.ArrayList;

public interface CallbackInterface {
    ArrayList<CallbackInterface> callbacks = new ArrayList<CallbackInterface>();

    void call();
}
