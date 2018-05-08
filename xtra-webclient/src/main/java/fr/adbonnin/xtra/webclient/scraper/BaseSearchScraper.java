package fr.adbonnin.xtra.webclient.scraper;

import fr.adbonnin.xtra.collect.AbstractIterator;

import java.util.Iterator;

import static java.util.Collections.emptyIterator;

public abstract class BaseSearchScraper<E, T> implements Iterator<T> {

    private final ItemIterator itemIterator = new ItemIterator();

    private final PageIterator pageIterator = new PageIterator();

    abstract Iterator<E> retrieveNextPage();

    abstract T parseItem(E element, int index);

    protected Iterator<E> noMorePage() {
        return pageIterator.noMorePage();
    }

    /**
     * Called at the end of a page.
     * Note: Can be overridden
     */
    protected void onEndOfPage(int pageNumber, boolean lastPage, int pageSize) {
    }

    @Override
    public boolean hasNext() {
        return itemIterator.hasNext();
    }

    @Override
    public T next() {
        return itemIterator.next();
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }

    private class ItemIterator extends AbstractIterator<T> {

        private Iterator<E> elementIterator = emptyIterator();

        private int pageSize = 0;

        private int pageNumber = 0;

        @Override
        protected T computeNext() {
            for (; ; ) {
                if (!elementIterator.hasNext()) {

                    final boolean hasMorePage = pageIterator.hasNext();
                    if (hasMorePage) {
                        elementIterator = pageIterator.next();
                    }

                    if (pageNumber > 0) {
                        onEndOfPage(pageNumber, !hasMorePage, pageSize);
                    }

                    ++pageNumber;
                    pageSize = 0;

                    if (!hasMorePage) {
                        return endOfData();
                    }
                }

                if (elementIterator.hasNext()) {
                    final E element = elementIterator.next();
                    final T item = parseItem(element, pageSize);

                    ++pageSize;
                    return item;
                }
            }
        }
    }

    private class PageIterator extends AbstractIterator<Iterator<E>> {

        @Override
        protected Iterator<E> computeNext() {
            return retrieveNextPage();
        }

        private Iterator<E> noMorePage() {
            return endOfData();
        }
    }
}
