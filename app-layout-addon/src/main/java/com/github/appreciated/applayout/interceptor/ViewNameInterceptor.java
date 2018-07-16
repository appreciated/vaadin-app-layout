package com.github.appreciated.applayout.interceptor;

import com.github.appreciated.applayout.builder.interfaces.Factory;

/**
 * The ViewNameInterceptor ist meant to be used to change the paths before they are added to the router / Navigator.
 * This can f.e. be important if you are not giving any routes to the views but leave it up to the builder how to the
 * name the paths for you. Usually the captions of the buttons are being used, which in some cases might bear the
 * problem of invalid url encoding.
 *
 * A example implementation can be found #{@see com.github.appreciated.app.layout.interceptor.DefaultViewNameInterceptor}
 */
public interface ViewNameInterceptor extends Factory<String, String> {
}
