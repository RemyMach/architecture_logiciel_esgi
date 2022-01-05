package fr.remy.cc1.exposition.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

public class PaymentScheduler {

    @Autowired
    public PaymentScheduler() {
    }

    @Scheduled(cron = "*/5 * * * * *")
    public void PayUserSubscriptionOffer() {
        System.out.println("on paye la subscription");
    }
}
