package edu.eci.dosw.DOSW_Library;

import edu.eci.dosw.DOSW_Library.Modelo.User;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void testUserGettersAndSetters() {
        User user = new User();
        user.setId("u-nicolas-10");
        user.setName("Nicolas");

        assertEquals("u-nicolas-10", user.getId());
        assertEquals("Nicolas", user.getName());
        assertNotNull(user.toString());
    }

    @Test
    void testUserEquality() {
        // Creamos dos usuarios con los mismos datos
        User user1 = new User();
        user1.setId("u-nicolas-10");
        user1.setName("Nicolas");

        User user2 = new User();
        user2.setId("u-nicolas-10");
        user2.setName("Nicolas");

        // Un usuario con datos diferentes basado en un futbolista conocido
        User user3 = new User();
        user3.setId("u-vinicius-7");
        user3.setName("Vinicius Jr");

        assertEquals(user1, user2);
        assertEquals(user1, user1);
        assertNotEquals(user1, user3);
        assertNotEquals(user1, null);
        assertNotEquals(user1, "no es user");
        assertEquals(user1.hashCode(), user2.hashCode());
        assertNotEquals(user1.hashCode(), user3.hashCode());
    }
}