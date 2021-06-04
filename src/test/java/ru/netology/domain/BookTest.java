package ru.netology.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookTest {

    @Test
    public void shouldGetAndSet() {
        Book book = new Book();
        String expected = "Святослав Куликов";
        assertNull(book.getAuthor());
        book.setAuthor(expected);
        assertEquals(expected, book.getAuthor());
    }
}