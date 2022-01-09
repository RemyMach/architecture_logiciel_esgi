package fr.remy.cc1.application;

import fr.remy.cc1.SpringConfiguration.UserConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.scheduling.support.CronSequenceGenerator;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

public class CronScheduledTest {

    @Test
    public void stringCronSequenceIsTriggerAt6PMALLDAys() {

        assertEquals(new CronSequenceGenerator("0 0 18 * * *").next(new Date(2012, 6, 1, 17, 0, 0)), new Date(2012, 6, 1, 18, 0, 0));
        assertEquals(new CronSequenceGenerator("0 0 18 * * *").next(new Date(2012, 6, 1, 18, 0, 0)), new Date(2012, 6, 2, 18, 0, 0));
        assertEquals(new CronSequenceGenerator("0 0 18 * * *").next(new Date(2012, 6, 1, 18, 0, 1)), new Date(2012, 6, 2, 18, 0, 0));
    }
}
