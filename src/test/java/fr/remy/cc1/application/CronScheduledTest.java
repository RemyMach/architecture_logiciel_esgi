package fr.remy.cc1.application;

import org.junit.jupiter.api.Test;
import org.springframework.scheduling.support.CronSequenceGenerator;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CronScheduledTest {

    @Test
    public void stringCronSequenceIsTriggerAt6PMALLDAys() {

        assertEquals(new CronSequenceGenerator("0 0 18 * * *").next(new Date(2012, 6, 1, 17, 0, 0)), new Date(2012, 6, 1, 18, 0, 0));
        assertEquals(new CronSequenceGenerator("0 0 18 * * *").next(new Date(2012, 6, 1, 18, 0, 0)), new Date(2012, 6, 2, 18, 0, 0));
        assertEquals(new CronSequenceGenerator("0 0 18 * * *").next(new Date(2012, 6, 1, 18, 0, 1)), new Date(2012, 6, 2, 18, 0, 0));
    }
}
