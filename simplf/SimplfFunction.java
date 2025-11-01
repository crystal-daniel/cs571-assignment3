package simplf;

import java.util.List;

class SimplfFunction implements SimplfCallable {

    private final Stmt.Function declaration; // The function’s AST node
    private Environment closure; // The environment where it was defined

    SimplfFunction(Stmt.Function declaration, Environment closure) {
        this.declaration = declaration;
        this.closure = closure;
    }

    // Optional method (some starter templates provide this)
    public void setClosure(Environment environment) {
        this.closure = environment;
    }

    @Override
    public Object call(Interpreter interpreter, List<Object> args) {
        // Create a new environment for the function’s execution,
        // nested inside the closure where the function was declared
        Environment environment = new Environment(closure);

        // Bind each parameter name to its corresponding argument value
        for (int i = 0; i < declaration.params.size(); i++) {
            environment.define(declaration.params.get(i).lexeme, args.get(i));
        }

        try {
            return interpreter.executeBlockWithReturn(declaration.body, environment);
        } catch (Return returnValue) {
            // Capture and return the returned value (if `return` is used inside function)
            return returnValue.value;
        }

    }

    @Override
    public int arity() {
        return declaration.params.size();
    }

    @Override
    public String toString() {
        // Show function name if it exists
        return "<fn " + declaration.name.lexeme + ">";
    }
}
