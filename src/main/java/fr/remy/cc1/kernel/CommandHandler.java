package fr.remy.cc1.kernel;

public interface CommandHandler<C extends Command, R> {
    R handle(C command);
}
