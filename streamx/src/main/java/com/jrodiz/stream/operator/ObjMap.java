package com.jrodiz.stream.operator;

import com.jrodiz.stream.function.Function;
import com.jrodiz.stream.iterator.LsaIterator;
import java.util.Iterator;
import org.jetbrains.annotations.NotNull;

public class ObjMap<T, R> extends LsaIterator<R> {

    private final Iterator<? extends T> iterator;
    private final Function<? super T, ? extends R> mapper;

    public ObjMap(
            @NotNull Iterator<? extends T> iterator,
            @NotNull Function<? super T, ? extends R> mapper) {
        this.iterator = iterator;
        this.mapper = mapper;
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }

    @Override
    public R nextIteration() {
        return mapper.apply(iterator.next());
    }
}
