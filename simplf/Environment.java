package simplf;

class Environment {
    private AssocList values; // head of the linked list
    private final Environment enclosing;

    // Global environment (no enclosing)
    Environment() {
        this.enclosing = null;
        this.values = null;
    }

    // Nested environment (used for blocks)
    Environment(Environment enclosing) {
        this.enclosing = enclosing;
        this.values = null;
    }

    void define(String name, Object value) {
        if (values == null) {
            values = new AssocList(name, value, null);
        } else {
            values = values.insert(name, value);
        }
    }

    // Define a new variable in the current environment
    void define(Token varToken, String name, Object value) {
        values = new AssocList(name, value, values); // prepend to the list
    }

    // Assign updates an existing variable in current or enclosing environment
    void assign(Token name, Object value) {
        for (AssocList current = values; current != null; current = current.next) {
            if (current.name.equals(name.lexeme)) {
                current.value = value;
                return;
            }
        }

        if (enclosing != null) {
            enclosing.assign(name, value);
            return;
        }

        throw new RuntimeError(name, "Undefined variable '" + name.lexeme + "'.");
    }

    // Retrieve a variable’s value
    Object get(Token name) {
        for (AssocList current = values; current != null; current = current.next) {
            if (current.name.equals(name.lexeme)) {
                return current.value;
            }
        }

        if (enclosing != null) {
            return enclosing.get(name);
        }

        throw new RuntimeError(name, "Undefined variable '" + name.lexeme + "'.");
    }
}
