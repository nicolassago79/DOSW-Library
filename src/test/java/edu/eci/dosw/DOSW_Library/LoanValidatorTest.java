package edu.eci.dosw.DOSW_Library;

import edu.eci.dosw.DOSW_Library.Modelo.Book;
import edu.eci.dosw.DOSW_Library.Modelo.Loan;
import edu.eci.dosw.DOSW_Library.Modelo.User;
import edu.eci.dosw.DOSW_Library.Exception.LibraryException;
import edu.eci.dosw.DOSW_Library.Validator.LoanValidator;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class LoanValidatorTest {

    private final LoanValidator validator = new LoanValidator();

    @Test
    void shouldPassWhenLoanIsComplete() {
        Loan loan = new Loan();
        loan.setBook(new Book("b-nicolas-10", "Nicolas", "Sistema de Biblioteca"));
        loan.setUser(new User("u-nicolas-1", "Nicolas Sanchez", "nicolas.sanchez-g"));
        loan.setLoandate(new Date());

        assertDoesNotThrow(() -> validator.validateLoan(loan));
    }

    @Test
    void shouldThrowExceptionWhenBookIsNull() {
        Loan loan = new Loan();
        loan.setUser(new User("u-vinicius", "Vinicius Jr", "vini.jr"));
        loan.setLoandate((new Date()));

        LibraryException exception = assertThrows(LibraryException.class,
                () -> validator.validateLoan(loan));
        assertEquals("El libro no puede ser nulo para un préstamo.", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenUserIsNull() {
        Loan loan = new Loan();
        loan.setBook(new Book("b-messi", "Lionel Messi", "El Arte del Regate"));
        loan.setLoandate(new Date());

        LibraryException exception = assertThrows(LibraryException.class,
                () -> validator.validateLoan(loan));
        assertEquals("El usuario no puede ser nulo para un préstamo.", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenDateIsNull() {
        Loan loan = new Loan();
        loan.setBook(new Book("b-nicolas-20", "Nicolas", "Desarrollo en Spring"));
        loan.setUser(new User("u-cr7", "Cristiano Ronaldo", "cr7.siu"));

        LibraryException exception = assertThrows(LibraryException.class,
                () -> validator.validateLoan(loan));
        assertEquals("La fecha de préstamo es obligatoria.", exception.getMessage());
    }
}