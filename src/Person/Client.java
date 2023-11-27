package Person;
public class Client {
    private int priority;
    private String name;
    private int
            password;

    public Client(int priority, String name, int password) {
        this.priority = priority;
        this.name = name;
        this.password = password;
    }

    public int getPriority() {
        return priority;
    }

    public String getName() {
        return name;
    }

    public int getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "Client{" +
                "priority=" + priority +
                ", name='" + name + '\'' +
                ", password=" + password +
                '}';
    }
}
