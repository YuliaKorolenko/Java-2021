package expression.exceptions;

import expression.parser.ParseException;

public class MissVarorBrascketsException extends ParseException {
    public MissVarorBrascketsException(String message) {
        super(message);
    }
}
