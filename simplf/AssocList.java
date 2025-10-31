package simplf;

public class AssocList {
    final String name;
    Object value;
    final AssocList next;

    AssocList(String nameIn, Object valueIn, AssocList nextIn) {
        name = nameIn;
        value = valueIn;
        next = nextIn;
    }

    // Inserts or updates a (name, value) pair
    public AssocList insert(String key, Object val) {
        if (this.name == null) {
            return new AssocList(key, val, null);
        }
        if (this.name.equals(key)) {
            return new AssocList(key, val, this.next);
        }
        return new AssocList(this.name, this.value,
                (this.next == null) ? new AssocList(key, val, null)
                        : this.next.insert(key, val));
    }

    // Looks up a value by key
    public Object lookup(String key) {
        if (this.name == null)
            return null;
        if (this.name.equals(key))
            return this.value;
        if (this.next == null)
            return null;
        return this.next.lookup(key);
    }

    // Checks if key exists
    public boolean contains(String key) {
        if (this.name == null)
            return false;
        if (this.name.equals(key))
            return true;
        if (this.next == null)
            return false;
        return this.next.contains(key);
    }
}
