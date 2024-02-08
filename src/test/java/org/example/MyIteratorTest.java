package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MyIteratorTest {

    @Test
    @DisplayName("hasNext - false в пустом массиве")
    void hasNextVoid() {
        MyIterator<Integer> m = new MyIterator<>(new Integer[0]);
        Assertions.assertFalse(m.hasNext());
    }

    @Test
    @DisplayName("hasNext - true в начале непустого массива")
    void hasNextNotVoidAtStart() {
        MyIterator<Integer> m = new MyIterator<>(new Integer[]{10, 15});
        Assertions.assertTrue(m.hasNext());
    }

    @Test
    @DisplayName("hasNext - false в конце непустого массива")
    void hasNextNotVoidAtEnd() {
        MyIterator<Integer> m = new MyIterator<>(new Integer[]{10, 15});
        m.next();
        m.next();
        Assertions.assertFalse(m.hasNext());
    }

    @Test
    @DisplayName("next - ArrayIndexOutOfBoundsException в пустом массиве")
    void nextInVoid() {
        MyIterator<Integer> m = new MyIterator<>(new Integer[0]);
        Assertions.assertThrowsExactly(ArrayIndexOutOfBoundsException.class, m::next);
    }

    @Test
    @DisplayName("next - элементы в правильном порядке в непустом массиве")
    void nextInNotVoid() {
        MyIterator<Integer> m = new MyIterator<>(new Integer[]{10, -15, 31});
        Assertions.assertEquals(10, m.next());
        Assertions.assertEquals(-15, m.next());
        Assertions.assertEquals(31, m.next());
        Assertions.assertFalse(m.hasNext(),"После перебора всех элементов осталось куда идти");
    }

    @Test
    @DisplayName("remove - UnsupportedOperationException при удалении, когда итератор ЕЩЕ НЕ указывает на элемент")
    void removeBeforeMove() {
        MyIterator<Integer> m = new MyIterator<>(new Integer[]{10, -15, 31});
        Assertions.assertThrowsExactly(UnsupportedOperationException.class, m::remove);
    }

    @Test
    @DisplayName("remove - UnsupportedOperationException при удалении, когда итератор УКАЗЫВАЕТ на элемент")
    void removeInMove() {
        MyIterator<Integer> m = new MyIterator<>(new Integer[]{10, -15, 31});
        m.next();
        m.next();
        Assertions.assertThrowsExactly(UnsupportedOperationException.class, m::remove);
    }
    @Test
    @DisplayName("remove - UnsupportedOperationException при удалении, когда итератор УЖЕ НЕ указывает на элемент")
    void removeAfterMove() {
        MyIterator<Integer> m = new MyIterator<>(new Integer[]{10, -15, 31});
        m.next();
        m.next();
        m.next();
        Assertions.assertThrowsExactly(UnsupportedOperationException.class, m::remove);
    }

}