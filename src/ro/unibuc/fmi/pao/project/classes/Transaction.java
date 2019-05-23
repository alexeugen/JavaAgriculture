package ro.unibuc.fmi.pao.project.classes;

public class Transaction {
    private Farmer farmer;
    private Client client;
    private Product product;
    private String _farmer;
    private String _client;
    private String _value;

    public Transaction(Farmer farmer, Client client, Product product) {
        this.farmer = farmer;
        this.client = client;
        this.product = product;
    }

    public Transaction(String farmer, String client, String value) {
        this._farmer = farmer;
        this._client = client;
        this._value= value;
    }

    public void print() {
        System.out.println(farmer.getName() + " - " + client.getName() + " - " + product.getName());
    }

    @Override
    public String toString() {
        return  _farmer + ' ' +
                 _client + ' ' +
               _value + ' '
         ;
    }

    public int getQuantity() {
        return Integer.parseInt(product.getQuantity());
    }
}
