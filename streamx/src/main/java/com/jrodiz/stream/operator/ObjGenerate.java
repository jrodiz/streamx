package com.jrodiz.stream.operator;

import com.jrodiz.stream.function.Supplier;
import com.jrodiz.stream.iterator.LsaIterator;
import org.jetbrains.annotations.NotNull;

public class ObjGenerate<T> extends LsaIterator<T> {

    private final Supplier<T> supplier;

    public ObjGenerate(@NotNull Supplier<T> supplier) {
        this.supplier = supplier;
    }

    @Override
    public boolean hasNext() {
        return true;
    }

    @Override
    public T nextIteration() {
        return supplier.get();
    }
}
