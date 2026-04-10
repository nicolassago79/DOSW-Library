package edu.eci.dosw.DOSW_Library.Util;

import edu.eci.dosw.DOSW_Library.Exception.BookNotAvailableException;
import org.springframework.stereotype.Component;

@Component
public class ValidationUtil {
    public void checkAvailability(int stock) {
        if (stock <= 0) {
            throw new BookNotAvailableException("No hay modelos disponibles para hacer el  préstamo.");
        }
    }
}