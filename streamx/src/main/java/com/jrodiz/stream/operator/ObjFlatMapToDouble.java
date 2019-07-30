package com.jrodiz.stream.operator;

import com.jrodiz.stream.DoubleStream;
import com.jrodiz.stream.function.Function;
import com.jrodiz.stream.iterator.PrimitiveExtIterator;
import com.jrodiz.stream.iterator.PrimitiveIterator;
import java.util.Iterator;
import org.jetbrains.annotations.NotNull;

public class ObjFlatMapToDouble<T> extends PrimitiveExtIterator.OfDouble {

    private final Iterator<? extends T> iterator;
    private final Function<? super T, ? extends DoubleStream> mapper;
    private PrimitiveIterator.OfDouble inner;

    public ObjFlatMapToDouble(
            @NotNull Iterator<? extends T> iterator,
            @NotNull Function<? super T, ? extends DoubleStream> mapper) {
        this.iterator = iterator;
        this.mapper = mapper;
    }

    @Override
    protected void nextIteration() {
        if ((inner != null) && inner.hasNext()) {
            next = inner.next();
            hasNext = true;
            return;
        }
        while (iterator.hasNext()) {
            if (inner == null || !inner.hasNext()) {
                final T arg = iterator.next();
                final DoubleStream result = mapper.apply(arg);
                if (result != null) {
                    inner = result.iterator();
                }
            }
            if ((inner != null) && inner.hasNext()) {
                next = inner.next();
                hasNext = true;
                return;
            }
        }
        hasNext = false;
    }
}
