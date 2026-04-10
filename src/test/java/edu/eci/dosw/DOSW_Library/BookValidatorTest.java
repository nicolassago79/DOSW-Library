package edu.eci.dosw.DOSW_Library;

import edu.eci.dosw.DOSW_Library.Modelo.Book;
import edu.eci.dosw.DOSW_Library.Exception.LibraryException;
import edu.eci.dosw.DOSW_Library.Validator.BookValidator;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BookValidatorTest {

    private final BookValidator validator = new BookValidator();

    @Test
    void shouldPassValidationWhenBookIsCorrect() {
        // Usando a Nicolas como autor válido para la prueba de éxito
        Book book = new Book("b-nicolas-10", "Nicolas", "Táctica y Estrategia");
        assertDoesNotThrow(() -> validator.validate(book));
    }

    @Test
    void shouldThrowExceptionWhenTitleIsBlank() {
        // Usando a Neymar Jr como autor, pero con título vacío para forzar el error
        Book book = new Book("b-nicolas-11", "Neymar Jr", "");

        LibraryException exception = assertThrows(LibraryException.class,
                () -> validator.validate(book));

        assertEquals("El título del libro es obligatorio.", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenAuthorIsNull() {
        // Forzando el error de autor nulo en un libro de "Balón de Oro"
        Book book = new Book("b-nicolas-gold", null, "Balón de Oro");

        LibraryException exception = assertThrows(LibraryException.class,
                () -> validator.validate(book));

        assertEquals("El autor del libro es obligatorio.", exception.getMessage());
    }
}