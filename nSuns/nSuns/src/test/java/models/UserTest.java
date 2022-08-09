package models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    @Test
    void creation() {
        User user = new User("남성", 28, 182, 82,"오진성");
    }

    @Test
    void equals() {
        User user = new User("남성", 28, 182, 82,"오진성");

        assertEquals(new User("남성", 28, 182, 82,"오진성"), user);
    }

    @Test
    void userGender() {
        User user = new User("남성", 1, 1, 1,"오진성");
        user.updateGender("여성");

        assertEquals("여성", user.userGender());
    }
}
