public class Singleton {
    private static Singleton instance;

    private Singleton() {
        // a private constructor
    }

    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();  // (lazy initialization)
        }
        return instance;
    }

    public static void main(String[] args) {
        Singleton obj1 = Singleton.getInstance();
        Singleton obj2 = Singleton.getInstance();
        System.out.println(obj1 == obj2); // true
    }
}
