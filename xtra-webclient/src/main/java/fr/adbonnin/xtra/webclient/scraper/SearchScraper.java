package fr.adbonnin.xtra.webclient.scraper;

import java.util.Iterator;

import static java.util.Objects.requireNonNull;

public abstract class SearchScraper<E, T> extends BaseSearchScraper<E, T> {

    private final Handler<E, T> handler;

    public SearchScraper() {
        this(new DefaultHandler<E, T>());
    }

    public SearchScraper(Handler<E, T> handler) {
        this.handler = requireNonNull(handler);
    }

    public abstract Iterator<E> tryRetrieveNextPage() throws Exception;

    public abstract T tryParseItem(E element, int index) throws Exception;

    @Override
    protected final void onEndOfPage(int pageNumber, boolean lastPage, int pageSize) {
        handler.onEndOfPage(pageNumber, lastPage, pageSize);
    }

    @Override
    protected final Iterator<E> retrieveNextPage() {
        try {
            final Iterator<E> nextPage = tryRetrieveNextPage();
            return handler.postRetrieveNextPage(nextPage);
        }
        catch (Throwable e) {
            return handler.retrieveNextPageFailed(e);
        }
    }

    @Override
    protected final T parseItem(E element, int index) {
        try {
            final T item = tryParseItem(element, index);
            return handler.postParseItem(element, item);
        }
        catch (Throwable e) {
            return handler.parseItemFailed(element, e);
        }
    }

    public interface Handler<E, T> {

        Iterator<E> postRetrieveNextPage(Iterator<E> elements);

        Iterator<E> retrieveNextPageFailed(Throwable e);

        T postParseItem(E element, T item);

        T parseItemFailed(E element, Throwable e);

        void onEndOfPage(int index, boolean lastPage, int pageSize);
    }

    public abstract static class HandlerAdapter<E, T> implements Handler<E, T> {

        private final Handler<E, T> handler;

        public HandlerAdapter(Handler<E, T> handler) {
            this.handler = requireNonNull(handler);
        }

        public Handler<E, T> getHandler() {
            return handler;
        }

        @Override
        public Iterator<E> postRetrieveNextPage(Iterator<E> elements) {
            return handler.postRetrieveNextPage(elements);
        }

        @Override
        public Iterator<E> retrieveNextPageFailed(Throwable e) {
            return handler.retrieveNextPageFailed(e);
        }

        @Override
        public T postParseItem(E element, T item) {
            return handler.postParseItem(element, item);
        }

        @Override
        public T parseItemFailed(E element, Throwable e) {
            return handler.parseItemFailed(element, e);
        }

        @Override
        public void onEndOfPage(int index, boolean lastPage, int pageSize) {
            handler.onEndOfPage(index, lastPage, pageSize);
        }
    }

    public static class DefaultHandler<E, T> implements Handler<E, T> {

        @Override
        public Iterator<E> postRetrieveNextPage(Iterator<E> elements) {
            return elements;
        }

        @Override
        public Iterator<E> retrieveNextPageFailed(Throwable e) {
            throw new RuntimeException(e);
        }

        @Override
        public T postParseItem(E element, T item) {
            return item;
        }

        @Override
        public T parseItemFailed(E element, Throwable e) {
            throw new RuntimeException(e);
        }

        @Override
        public void onEndOfPage(int index, boolean lastPage, int pageSize) {

        }
    }
}
