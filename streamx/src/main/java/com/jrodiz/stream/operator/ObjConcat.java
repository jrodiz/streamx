package com.jrodiz.stream.operator;

import com.jrodiz.stream.iterator.LsaExtIterator;
import java.util.Iterator;
import org.jetbrains.annotations.NotNull;

public class ObjConcat<T> extends LsaExtIterator<T> {

    private final Iterator<? extends T> iterator1;
    private final Iterator<? extends T> iterator2;
    private boolean firstStreamIsCurrent;

    public ObjConcat(
            @NotNull Iterator<? extends T> iterator1,
            @NotNull Iterator<? extends T> iterator2) {
        this.iterator1 = iterator1;
        this.iterator2 = iterator2;
        firstStreamIsCurrent = true;
    }

    @Override
    protected void nextIteration() {
        if (firstStreamIsCurrent) {
            if (iterator1.hasNext()) {
                next = iterator1.next();
                hasNext = true;
                return;
            }
            firstStreamIsCurrent = false;
        }
        if (iterator2.hasNext()) {
            next = iterator2.next();
            hasNext = true;
            return;
        }
        hasNext = false;
    }
}
