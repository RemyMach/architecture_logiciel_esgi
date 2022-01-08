package fr.remy.cc1.kernel;

import fr.remy.cc1.kernel.error.ValidationException;

public interface CommandHandler<C extends Command, R> {
    R handle(C command) throws ValidationException, Exception;
}
