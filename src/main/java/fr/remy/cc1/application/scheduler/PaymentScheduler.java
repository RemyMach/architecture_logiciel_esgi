package fr.remy.cc1.application.scheduler;

import fr.remy.cc1.domain.user.User;
import fr.remy.cc1.domain.user.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;
import java.util.stream.Collectors;

public class PaymentScheduler {

    private final Users users;

    @Autowired
    public PaymentScheduler(Users users) {
        this.users = users;
    }

    @Scheduled(cron = "*/10 * * * * *")
    public void PayUserSubscriptionOffer() {
        List<User> userList = users.findAllByPaidSinceMoreThanCertainMonthAgo(0);

        System.out.println(userList);

        //TODO récupérer le dernier paiement
        // si il date de moins d'un mois alors il n'a pas à payer
        // essayer le paiement 10 fois, si fonctionne pas envoi de mail suivant l'étape pour dire erreur de paiement
        // vérifier vos informations de carte de crédit
    }
}
