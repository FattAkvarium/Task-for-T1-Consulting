package ru.FattAkvarium.taskForT1Consulting;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.FattAkvarium.taskForT1Consulting.model.IncomingString;
import ru.FattAkvarium.taskForT1Consulting.service.IncomingStringService;

import java.util.LinkedHashMap;
import java.util.Map;


@SpringBootTest
class TaskApplicationTests {

    @Autowired
    IncomingStringService incomingStringService;
    @Test
    public void stringShouldBeCreatedClassAndReturnCorrectMap() {
        IncomingString incomingString = new IncomingString();
        incomingString.setIncomingString("aabbccccwww");

        incomingStringService.save(incomingString);

        final Map<String, Long> myResult = incomingStringService.returnSortedMap();
        final Map<String, Long> expectation = new LinkedHashMap<>(){
            {
                put("a", 2L);
                put("b", 2L);
                put("c", 4L);
                put("w", 3L);
            }
        };
        Assertions.assertEquals(expectation, myResult);
    }
}
