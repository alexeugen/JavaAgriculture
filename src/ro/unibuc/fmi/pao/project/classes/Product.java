package ro.unibuc.fmi.pao.project.classes;

public class Product {
    private String name;
    private String measure;
    private String quantity;

    public String getName() {
        return name;
    }

    public String getMeasure() {
        return measure;
    }

    public String getQuantity() {
        return quantity;
    }

    public Product(String name, String measure, String quantity) {
        this.name = name;
        this.measure = measure;
        this.quantity = quantity;
    }

//    Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public void print() {
        System.out.println(this.name + " " + this.quantity + " " + this.measure);
    }
}
