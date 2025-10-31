package simplf;

import java.util.List;

interface SimplfCallable {
    Object call(Interpreter interpreter, List<Object> args);

    default int arity() {
        return 0;
    }
}
