package edu.eci.dosw.DOSW_Library;

import edu.eci.dosw.DOSW_Library.Exception.ErrorResponse;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ErrorResponseTest {

    @Test
    void testErrorResponseMethods() {
        long marcaTiempo = 1681123456789L;
        ErrorResponse error1 = new ErrorResponse("Fallo en el sistema de Nicolas", 400, marcaTiempo);

        assertEquals("Fallo en el sistema de Nicolas", error1.getMessage());
        assertEquals(400, error1.getStatus());
        assertEquals(marcaTiempo, error1.getTimestamp());

        error1.setMessage("Error critico de servidor");
        error1.setStatus(503);
        error1.setTimestamp(987654321L);

        assertEquals("Error critico de servidor", error1.getMessage());
        assertEquals(503, error1.getStatus());
        assertEquals(987654321L, error1.getTimestamp());
    }

    @Test
    void testEqualsAndHashCode() {
        long momentoActual = System.currentTimeMillis();
        ErrorResponse error1 = new ErrorResponse("Error de Nicolas", 403, momentoActual);
        ErrorResponse error2 = new ErrorResponse("Error de Nicolas", 403, momentoActual);
        ErrorResponse error3 = new ErrorResponse("Error de Vinicius", 404, momentoActual);

        assertEquals(error1, error2);
        assertNotEquals(error1, error3);
        assertEquals(error1.hashCode(), error2.hashCode());
        assertNotEquals(error1.hashCode(), error3.hashCode());

        assertNotEquals(null, error1);
        assertNotEquals("no soy un error", error1);
    }

    @Test
    void testToString() {
        ErrorResponse error = new ErrorResponse("Prueba de Log", 418, 9999L);
        String toString = error.toString();

        assertTrue(toString.contains("Prueba de Log"));
        assertTrue(toString.contains("418"));
        assertTrue(toString.contains("9999"));
    }
}