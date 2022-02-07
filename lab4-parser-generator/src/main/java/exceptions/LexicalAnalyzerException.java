 package exceptions;

 import java.text.ParseException;
 public class LexicalAnalyzerException extends ParseException {
     public LexicalAnalyzerException(String s, int errorOffset) {
         super(s, errorOffset);
     }
 }
