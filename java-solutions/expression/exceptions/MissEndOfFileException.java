package expression.exceptions;

import expression.parser.ParseException;

public class MissEndOfFileException extends ParseException {

    public MissEndOfFileException(String message) {
        super(message);
    }
}
