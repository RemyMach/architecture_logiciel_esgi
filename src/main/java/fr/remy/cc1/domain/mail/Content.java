package fr.remy.cc1.domain.mail;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Content {

    private String text;

    private Content(String text) {};

    public static Content withTemplate(String templatePath) {
        try {
            Path templateFilePath = Path.of(templatePath);
            if (Files.notExists(templateFilePath)) {
                throw new FileNotFoundException();
            }
            return new Content(Files.readString(templateFilePath));
        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }
    }

    public static Content withText(String text) {
        return new Content(text);
    }


}
