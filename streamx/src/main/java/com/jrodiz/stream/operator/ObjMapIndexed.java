package com.jrodiz.stream.operator;

import com.jrodiz.stream.function.IndexedFunction;
import com.jrodiz.stream.iterator.IndexedIterator;
import com.jrodiz.stream.iterator.LsaIterator;
import org.jetbrains.annotations.NotNull;

public class ObjMapIndexed<T, R> extends LsaIterator<R> {

    private final IndexedIterator<? extends T> iterator;
    private final IndexedFunction<? super T, ? extends R> mapper;

    public ObjMapIndexed(
            @NotNull IndexedIterator<? extends T> iterator,
            @NotNull IndexedFunction<? super T, ? extends R> mapper) {
        this.iterator = iterator;
        this.mapper = mapper;
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }

    @Override
    public R nextIteration() {
        return mapper.apply(iterator.getIndex(), iterator.next());
    }
}
