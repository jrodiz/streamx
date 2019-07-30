package com.jrodiz.stream.operator;

import com.jrodiz.stream.internal.Operators;
import com.jrodiz.stream.iterator.PrimitiveExtIterator;
import com.jrodiz.stream.iterator.PrimitiveIterator;
import java.util.Arrays;
import org.jetbrains.annotations.NotNull;

public class IntSorted extends PrimitiveExtIterator.OfInt {

    private final PrimitiveIterator.OfInt iterator;
    private int index;
    private int[] array;

    public IntSorted(@NotNull PrimitiveIterator.OfInt iterator) {
        this.iterator = iterator;
        index = 0;
    }

    @Override
    protected void nextIteration() {
        if (!isInit) {
            array = Operators.toIntArray(iterator);
            Arrays.sort(array);
        }
        hasNext = index < array.length;
        if (hasNext) {
            next = array[index++];
        }
    }
}
