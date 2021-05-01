public class Stock {
    private String name;
    private int price;
    private int sale_count;
    private int buy_count;
    private int quantity;
    Stock(String name) {
        this.name = name;
    }
    void incrementSalesCount() {
        sale_count++;
    }

    void incrementBuysCount() {
        buy_count++;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getSale_count() {
        return sale_count;
    }

    public void setSale_count(int sale_count) {
        this.sale_count = sale_count;
    }

    public int getBuy_count() {
        return buy_count;
    }

    public void setBuy_count(int buy_count) {
        this.buy_count = buy_count;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
