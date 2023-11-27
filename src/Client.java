package CPS2232.FinalProject;

public class Client {
    private int priority;
    private String name;
    private int password;
    private int phoneNumber;

    public Client(int priority, String name, int password，int phoneNumber) {
        this.priority = priority;
        this.name = name;
        this.password = password;
        this.phoneNumber = phoneNumber;
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
