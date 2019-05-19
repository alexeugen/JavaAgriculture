package ro.unibuc.fmi.pao.project.abs;

public abstract class User {
    static int counter = 0;
    private int id;

    private String name;

    private String phone;

    public User(String name) {
        this.name = name;
    }

    public User(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public String toString() {
        return name;
    }
}
