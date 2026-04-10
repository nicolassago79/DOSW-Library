package edu.eci.dosw.DOSW_Library;

import edu.eci.dosw.DOSW_Library.Modelo.Book;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BookTest {

    @Test
    void testBookGettersAndSetters() {
        Book book = new Book();
        // ID actualizado con tu nombre
        book.setId("b-nicolas-10");
        book.setTitle("Magia en el Camp Nou");
        book.setAuthor("Nicolas");

        assertEquals("b-nicolas-10", book.getId());
        assertEquals("Magia en el Camp Nou", book.getTitle());
        assertEquals("Nicolas", book.getAuthor());
        assertNotNull(book.toString());
    }

    @Test
    void testBookEquality() {
        // Usando a Cristiano Ronaldo y su libro de ejemplo
        Book book1 = new Book("7", "Cristiano Ronaldo", "Mentalidad Ganadora");
        Book book2 = new Book("7", "Cristiano Ronaldo", "Mentalidad Ganadora");

        // Usando a Zinedine Zidane para el caso de no igualdad
        Book book3 = new Book("5", "Zinedine Zidane", "El Arte del Control");

        assertEquals(book1, book2);
        assertNotEquals(book1, book3);
        assertNotEquals(null, book1);
        assertNotEquals("no es un libro", book1);

        // hashCode
        assertEquals(book1.hashCode(), book2.hashCode());
        assertNotEquals(book1.hashCode(), book3.hashCode());
    }

    @Test
    void testBookFullConstructor() {
        // Usando a Kylian Mbappé como ejemplo de constructor completo
        Book book = new Book("b-nicolas-7", "Kylian Mbappé", "Velocidad Pura");

        assertEquals("b-nicolas-7", book.getId());
        assertEquals("Velocidad Pura", book.getTitle());
        assertEquals("Kylian Mbappé", book.getAuthor());
    }
}