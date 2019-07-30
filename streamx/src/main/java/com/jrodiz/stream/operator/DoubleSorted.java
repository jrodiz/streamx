package com.jrodiz.stream.operator;

import com.jrodiz.stream.internal.Operators;
import com.jrodiz.stream.iterator.PrimitiveExtIterator;
import com.jrodiz.stream.iterator.PrimitiveIterator;
import java.util.Arrays;
import org.jetbrains.annotations.NotNull;

public class DoubleSorted extends PrimitiveExtIterator.OfDouble {

    private final PrimitiveIterator.OfDouble iterator;
    private int index;
    private double[] array;

    public DoubleSorted(@NotNull PrimitiveIterator.OfDouble iterator) {
        this.iterator = iterator;
        index = 0;
    }

    @Override
    protected void nextIteration() {
        if (!isInit) {
            array = Operators.toDoubleArray(iterator);
            Arrays.sort(array);
        }
        hasNext = index < array.length;
        if (hasNext) {
            next = array[index++];
        }
    }
}
