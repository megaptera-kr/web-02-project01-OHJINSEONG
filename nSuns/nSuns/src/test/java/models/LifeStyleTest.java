package models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LifeStyleTest {
    @Test
    void creation(){
        LifeStyle lifeStyle = new LifeStyle("빈둥빈둥",1.4);
    }

    @Test
    void equals(){
        LifeStyle lifeStyle = new LifeStyle("빈둥빈둥",1.4);

    assertEquals( new LifeStyle("빈둥빈둥",1.4),lifeStyle);
    }

}