package com.jrodiz.stream.operator;

import com.jrodiz.stream.iterator.LsaExtIterator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import org.jetbrains.annotations.NotNull;

public class ObjDistinct<T> extends LsaExtIterator<T> {

    private final Iterator<? extends T> iterator;
    private final Set<T> set;

    public ObjDistinct(@NotNull Iterator<? extends T> iterator) {
        this.iterator = iterator;
        set = new HashSet<T>();
    }

    @Override
    protected void nextIteration() {
        while (hasNext = iterator.hasNext()) {
            next = iterator.next();
            if (set.add(next)) {
                return;
            }
        }
    }
}
