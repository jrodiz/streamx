package com.jrodiz.stream.operator;

import com.jrodiz.stream.internal.Operators;
import com.jrodiz.stream.iterator.PrimitiveExtIterator;
import com.jrodiz.stream.iterator.PrimitiveIterator;
import java.util.Arrays;
import org.jetbrains.annotations.NotNull;

public class LongSorted extends PrimitiveExtIterator.OfLong {

    private final PrimitiveIterator.OfLong iterator;
    private int index;
    private long[] array;

    public LongSorted(@NotNull PrimitiveIterator.OfLong iterator) {
        this.iterator = iterator;
        index = 0;
    }

    @Override
    protected void nextIteration() {
        if (!isInit) {
            array = Operators.toLongArray(iterator);
            Arrays.sort(array);
        }
        hasNext = index < array.length;
        if (hasNext) {
            next = array[index++];
        }
    }
}
