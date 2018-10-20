package productivitysystem.util.iterator.iterators;

import productivitysystem.util.iterator.colections.Collection;

public interface Iterator {
    boolean hasNext();
    Object next();
    int index();
}
