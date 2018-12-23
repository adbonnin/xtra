package fr.adbonnin.xtra.base;

import java.nio.CharBuffer;

import static fr.adbonnin.xtra.base.XtraObjects.require;

public class CircularCharArray implements CharSequence {

    private final int maxSize;

    private final char[] buf;

    private boolean full;

    private int nextIndex = 0;

    public CircularCharArray(int maxSize) {
        require(maxSize > 0, "'maxSize' must be positive");
        this.buf = new char[maxSize];
        this.maxSize = maxSize;
    }

    public int addChar(char ch) {
        full |= nextIndex >= maxSize;
        nextIndex = nextIndex % maxSize;

        final int oldChar = full ? buf[nextIndex] : -1;
        buf[nextIndex++] = ch;
        return oldChar;
    }

    @Override
    public int length() {
        return full ? maxSize : nextIndex;
    }

    @Override
    public char charAt(int index) {
        checkBounds(index, false);

        if (full) {
            return buf[(index + nextIndex) % maxSize];
        }
        else {
            return buf[index % maxSize];
        }
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        checkBounds(end, true);

        final int len = end - start;
        if (len == 0) {
            return XtraStrings.EMPTY;
        }
        else if (len < 0) {
            throw new IllegalArgumentException("length must be positive");
        }

        if (full) {
            final int startIndex = (nextIndex + start) % maxSize;
            final int endIndex = (startIndex + len) % maxSize;
            if (startIndex > endIndex) {
                final int rem = maxSize - startIndex;
                final char[] result = new char[len];
                System.arraycopy(buf, startIndex, result, 0, rem);
                System.arraycopy(buf, 0, result, rem, endIndex);
                return CharBuffer.wrap(result, 0, len);
            }
            else {
                return CharBuffer.wrap(buf, startIndex, len);
            }
        }
        else {
            return CharBuffer.wrap(buf, start, len);
        }
    }

    @Override
    public String toString() {
        return subSequence(0, length()).toString();
    }

    public void clear() {
        nextIndex = 0;
        full = false;
    }

    private void checkBounds(int index, boolean endIndex) {
        final int size = length();
        if (endIndex) {
            if (index > size) {
                throw new StringIndexOutOfBoundsException();
            }
        }
        else {
            if (index >= size) {
                throw new StringIndexOutOfBoundsException();
            }
        }
    }
}
