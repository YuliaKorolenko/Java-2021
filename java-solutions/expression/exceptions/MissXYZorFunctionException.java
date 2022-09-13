package expression.exceptions;

import expression.parser.ParseException;

public class MissXYZorFunctionException extends ParseException {
    public MissXYZorFunctionException(String message) {
        super(message);
    }
}
