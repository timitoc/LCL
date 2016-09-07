package com.sasluca.lcl.permissions;

public interface IPermission
{
    int getCode();
    void requestPermission();
    boolean isRequesting();
    boolean isGranted();
}
