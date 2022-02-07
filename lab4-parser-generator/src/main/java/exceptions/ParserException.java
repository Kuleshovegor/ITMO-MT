package exceptions;


import java.text.ParseException;
public class ParserException extends ParseException {
    public ParserException(String s, int errorOffset) {
        super(s, errorOffset);
    }
}