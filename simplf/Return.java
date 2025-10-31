package simplf;

class Return extends RuntimeException {
    final Object value;

    Return(Object value) {
        super(null, null, false, false); // disable stack trace for performance
        this.value = value;
    }
}
