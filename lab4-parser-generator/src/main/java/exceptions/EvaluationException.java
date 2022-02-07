package exceptions;

import java.util.InputMismatchException;

public class EvaluationException extends InputMismatchException {
    public EvaluationException(String message) {
        super(message);
    }
}
