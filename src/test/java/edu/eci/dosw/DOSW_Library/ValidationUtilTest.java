package edu.eci.dosw.DOSW_Library;

import edu.eci.dosw.DOSW_Library.Exception.BookNotAvailableException;
import edu.eci.dosw.DOSW_Library.Util.ValidationUtil;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ValidationUtilTest {
    private final ValidationUtil validationUtil = new ValidationUtil();

    @Test
    void shouldPassWhenStockIsAvailable() {
        // Validacion para un stock disponible en la biblioteca de Nicolas
        assertDoesNotThrow(() -> validationUtil.checkAvailability(10));
    }

    @Test
    void shouldThrowExceptionWhenStockIsZero() {
        // Simulando que el libro de Vinicius Jr se ha agotado
        assertThrows(BookNotAvailableException.class, () -> validationUtil.checkAvailability(0));
    }
}