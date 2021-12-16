package fr.remy.cc1.domain.user;

import fr.remy.cc1.domain.payment.Currency;

public class UserCategoryCreator {

        private String exceptionMessage;

        public UserCategoryCreator() {
            this.exceptionMessage = "You can choose uniquely";
            for (UserCategory userCategory: UserCategory.values()) {
                this.exceptionMessage += ", " + userCategory.name();
            }

            this.exceptionMessage += " to pay";
        }

        public UserCategory getValueOf(String aspirant) {

            if(!UserCategoryValidator.getInstance().test(aspirant)) {
                throw new IllegalArgumentException(exceptionMessage);
            }

            return UserCategory.valueOf(aspirant);
        }



        public String getExceptionMessage() {
            return exceptionMessage;
        }
}
