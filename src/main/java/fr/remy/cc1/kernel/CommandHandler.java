package fr.remy.cc1.kernel;

import fr.remy.cc1.kernel.error.BasicException;

public interface CommandHandler<C extends Command, R> {
    R handle(C command) throws BasicException;
}
