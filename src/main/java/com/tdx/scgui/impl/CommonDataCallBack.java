package com.tdx.scgui.impl;

public interface CommonDataCallBack<T> {
    void onSuccess(T data);

    void onFailed(Exception e);
}
