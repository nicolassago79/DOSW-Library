package edu.eci.dosw.DOSW_Library;

import edu.eci.dosw.DOSW_Library.Persistence.relational.Entidades.BookEntity;
import edu.eci.dosw.DOSW_Library.Persistence.relational.Entidades.LoanEntity;
import edu.eci.dosw.DOSW_Library.Persistence.relational.Entidades.UserEntity;
import edu.eci.dosw.DOSW_Library.Persistence.relational.Mapper.LoanMapper;
import edu.eci.dosw.DOSW_Library.Persistence.relational.Repositorios.LoanRepository;
import edu.eci.dosw.DOSW_Library.Service.BookService;
import edu.eci.dosw.DOSW_Library.Service.LoanService;
import edu.eci.dosw.DOSW_Library.Service.UserService;
import edu.eci.dosw.DOSW_Library.Util.ValidationUtil;
import edu.eci.dosw.DOSW_Library.Validator.LoanValidator;
import edu.eci.dosw.DOSW_Library.Exception.BookNotAvailableException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LoanServiceTest {

    @Mock private BookService bookService;
    @Mock private UserService userService;
    @Mock private LoanRepository loanRepository;
    @Mock private LoanMapper loanMapper;
    @Mock private ValidationUtil validationUtil;
    @Mock private LoanValidator loanValidator;

    @InjectMocks
    private LoanService loanService;

    @Test
    void shouldCreateLoanSuccessfullyWhenStockAvailable() {
        UserEntity userE = new UserEntity();
        userE.setUserId("u-nicolas-10");

        BookEntity bookE = new BookEntity();
        bookE.setId("b-vinicius-jr");
        bookE.setAvailableStock(1);

        when(userService.findEntityById("u-nicolas-10")).thenReturn(userE);
        when(bookService.findEntityById("b-vinicius-jr")).thenReturn(bookE);

        LoanEntity savedLoan = new LoanEntity();
        savedLoan.setStatus("active");
        when(loanRepository.save(any(LoanEntity.class))).thenReturn(savedLoan);

        LoanEntity result = loanService.createLoan("u-nicolas-10", "b-vinicius-jr");

        assertNotNull(result);
        assertEquals("active", result.getStatus());
        verify(bookService).saveEntity(any(BookEntity.class));
    }

    @Test
    void shouldFailLoanWhenNoStockIsAvailable() {
        UserEntity userE = new UserEntity();
        BookEntity bookE = new BookEntity();
        bookE.setAvailableStock(0);

        when(userService.findEntityById("u-nicolas-10")).thenReturn(userE);
        when(bookService.findEntityById("b-vinicius-jr")).thenReturn(bookE);

        doThrow(new BookNotAvailableException("No hay ejemplares disponibles para el libro solicitado"))
                .when(validationUtil).checkAvailability(0);

        assertThrows(BookNotAvailableException.class, () -> {
            loanService.createLoan("u-nicolas-10", "b-vinicius-jr");
        });

        verify(loanRepository, never()).save(any());
    }

    @Test
    void shouldThrowExceptionWhenUserNotFound() {
        // Usando un ID de futbolista que no existe en el sistema
        when(userService.findEntityById("jugador-inexistente")).thenReturn(null);

        assertThrows(RuntimeException.class, () -> {
            loanService.createLoan("jugador-inexistente", "b-nicolas-10");
        });
    }

    @Test
    void shouldReturnAllLoans() {
        when(loanRepository.findAll()).thenReturn(Collections.emptyList());

        List<LoanEntity> result = loanService.getAllLoans();

        assertNotNull(result);
        verify(loanRepository).findAll();
    }
}