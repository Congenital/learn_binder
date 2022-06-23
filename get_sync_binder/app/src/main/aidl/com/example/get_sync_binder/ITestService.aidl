// ITestService.aidl
package com.example.get_sync_binder;

// Declare any non-default types here with import statements

interface ITestService {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */

    void test();
    void handle(String message);
}