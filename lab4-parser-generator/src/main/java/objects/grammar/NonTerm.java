package objects.grammar;

import java.util.Objects;

public class NonTerm  implements State {
    protected final String name;
    private final String params;

    public NonTerm(String name) {
        this.name = name;
        this.params = "";
    }

    public NonTerm(String name, String params) {
        this.name = name;
        this.params = params;
    }

    @Override
    public String getName() {
        return this.name;
    }

    public String getParams() {
        return params;
    }

    @Override
    public String toString() {
        return "NonTerm{" +
                "name='" + name + '\'' +
                ", params='" + params + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NonTerm nonTerm = (NonTerm) o;
        return Objects.equals(name, nonTerm.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
