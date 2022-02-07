package objects.grammar;

import java.util.Objects;

public class Code implements State{
    private final static String name = "$code";
    private final String body;

    public Code(String body) {
        this.body = body;
    }

    @Override
    public String getName() {
        return name;
    }

    public String getBody() {
        return body;
    }

    @Override
    public String toString() {
        return "Сode{" +
                "name='" + name + '\'' +
                ", body='" + body + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Code code = (Code) o;
        return Objects.equals(body, code.body);
    }

    @Override
    public int hashCode() {
        return Objects.hash(body);
    }
}
