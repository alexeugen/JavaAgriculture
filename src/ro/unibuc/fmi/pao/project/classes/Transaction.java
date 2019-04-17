package ro.unibuc.fmi.pao.project.classes;

public class Transaction {
    private Farmer farmer;
    private Client client;
    private Product product;

    public Transaction(Farmer farmer, Client client, Product product) {
        this.farmer = farmer;
        this.client = client;
        this.product = product;
    }

    public void print() {
        System.out.println(farmer.getName() + " - " + client.getName() + " - " + product.getName());
    }

    public int getQuantity() {
        return Integer.parseInt(product.getQuantity());
    }
}
