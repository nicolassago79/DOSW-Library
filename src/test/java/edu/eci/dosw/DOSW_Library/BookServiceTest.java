package edu.eci.dosw.DOSW_Library;

import edu.eci.dosw.DOSW_Library.Modelo.Book;
import edu.eci.dosw.DOSW_Library.Persistence.relational.Entidades.BookEntity;
import edu.eci.dosw.DOSW_Library.Persistence.relational.Mapper.BookMapper;
import edu.eci.dosw.DOSW_Library.Persistence.relational.Repositorios.BookRepository;
import edu.eci.dosw.DOSW_Library.Service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @Mock
    private BookMapper bookMapper;

    @InjectMocks
    private BookService bookService;

    private Book bookModel;
    private BookEntity bookEntity;

    @BeforeEach
    void setUp() {
        // Cambiado: Nicolas es el autor principal, libro de temática futbolística
        bookModel = new Book("b-nicolas-9", "Nicolas", "El 10 de la Cancha");

        bookEntity = new BookEntity();
        bookEntity.setId("b-nicolas-9");
        bookEntity.setTitle("El 10 de la Cancha");
        bookEntity.setAuthor("Nicolas");
        bookEntity.setTotalStock(10);
        bookEntity.setAvailableStock(10);
    }

    @Test
    void shouldSaveBookCorrectly() {
        when(bookMapper.toEntity(any(Book.class))).thenReturn(bookEntity);
        when(bookRepository.save(any(BookEntity.class))).thenReturn(bookEntity);
        when(bookMapper.toModel(any(BookEntity.class))).thenReturn(bookModel);

        Book saved = bookService.save(bookModel);

        assertNotNull(saved);
        verify(bookRepository).save(argThat(entity ->
                entity.getAvailableStock() == 10 && entity.getId().equals("b-nicolas-9")
        ));
    }

    @Test
    void shouldFindByIdUsingEntity() {
        when(bookRepository.findById("b-nicolas-9")).thenReturn(Optional.of(bookEntity));
        when(bookMapper.toModel(bookEntity)).thenReturn(bookModel);

        Book found = bookService.findById("b-nicolas-9");

        assertNotNull(found);
        // Título actualizado
        assertEquals("El 10 de la Cancha", found.getTitle());
    }

    @Test
    void shouldReturnNullWhenNotFoundInDB() {
        // Cambiado a un nombre de futbolista que no está en la base (ej. Pele)
        when(bookRepository.findById("pele-not-found")).thenReturn(Optional.empty());

        Book found = bookService.findById("pele-not-found");

        assertNull(found);
    }

    @Test
    void shouldFindAvailableBooksByStock() {
        // Cambiado a Ronaldinho como ejemplo de stock disponible
        bookEntity.setAuthor("Ronaldinho Gaúcho");
        when(bookRepository.findByAvailableStockGreaterThan(5))
                .thenReturn(Collections.singletonList(bookEntity));
        when(bookMapper.toModel(bookEntity)).thenReturn(bookModel);

        List<Book> result = bookService.findAvailable(5);

        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        verify(bookRepository).findByAvailableStockGreaterThan(5);
    }

    @Test
    void shouldFindEntityDirectly() {
        when(bookRepository.findById("b-nicolas-9")).thenReturn(Optional.of(bookEntity));

        BookEntity entityFound = bookService.findEntityById("b-nicolas-9");

        assertNotNull(entityFound);
        assertEquals("b-nicolas-9", entityFound.getId());
        assertEquals(10, entityFound.getAvailableStock());
    }
}