public class Transaction {
    private String type; // Income or Expense
    private String category;
    private double amount;

    public Transaction(String type, String category, double amount) {
        this.type = type;
        this.category = category;
        this.amount = amount;
    }

    public String toCSV() {
        return type + "," + category + "," + amount;
    }

    public String toString() {
        return type + " | " + category + " | $" + amount;
    }

    public double getSignedAmount() {
        return type.equalsIgnoreCase("Expense") ? -amount : amount;
    }
}
