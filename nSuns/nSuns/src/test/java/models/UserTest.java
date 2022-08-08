package models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    @Test
    void creation(){
        User user = new User("남성",28,182,82);
    }

    @Test
    void equals(){
        User user = new User("남성",28,182,82);

        assertEquals(new User("남성",28,182,82),user);
    }
}