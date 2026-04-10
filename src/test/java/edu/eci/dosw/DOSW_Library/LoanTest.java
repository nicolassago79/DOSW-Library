package edu.eci.dosw.DOSW_Library;

import edu.eci.dosw.DOSW_Library.Modelo.Loan;
import org.junit.jupiter.api.Test;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;

class LoanTest {
    @Test
    void testLoanFull() {
        Loan loan1 = new Loan();
        Date fechaActual = new Date();

        // Configuración con datos de Nicolas
        loan1.setLoandate(fechaActual);
        loan1.setStatus("Prestamo Nicolas Activo");

        assertEquals(fechaActual, loan1.getLoandate());
        assertEquals("Prestamo Nicolas Activo", loan1.getStatus());

        // Test para Equals y HashCode con datos de Vinicius Jr
        Loan loan2 = new Loan();
        loan2.setLoandate(fechaActual);
        loan2.setStatus("Prestamo Nicolas Activo");

        assertEquals(loan1, loan2);
        assertEquals(loan1.hashCode(), loan2.hashCode());
        assertNotNull(loan1.toString());

        // Verificación de cambio de estado a uno de fútbol
        loan1.setStatus("Finalizado - TechCup");
        assertNotEquals(loan1, loan2);
    }
}