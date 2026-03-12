package com.yuyuto.infinitymaxcore.libs.data;

public interface PIMXMergeHandler<T> {
    T merge(T oldVal, T newVal);
}