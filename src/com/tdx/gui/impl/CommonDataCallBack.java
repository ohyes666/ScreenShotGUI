package com.tdx.gui.impl;

public interface CommonDataCallBack<T> {
    void onSuccess(T data);

    void onFailed(Exception e);
}
