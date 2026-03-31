package com.yuyuto.infinitymaxcore.data;

import com.mojang.serialization.Codec;

import java.util.function.Supplier;

public class DataType<T> {

    private final String id;
    private final Codec<T> codec;
    private final Supplier<T> factory;

    public DataType(String id, Codec<T> codec, Supplier<T> factory) {
        this.id = id;
        this.codec = codec;
        this.factory = factory;
    }

    public String id() { return id; }
    public Codec<T> codec() { return codec; }
    public T create() { return factory.get(); }
}