import java.util.*;
import java.io.*;

public class TransactionManager {
    private List<Transaction> transactions = new ArrayList<>();

    public void addTransaction(Transaction t) {
        transactions.add(t);
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public double getBalance() {
        return transactions.stream().mapToDouble(Transaction::getSignedAmount).sum();
    }

    public void exportToCSV(String filename) throws IOException {
        PrintWriter writer = new PrintWriter(new FileWriter(filename));
        writer.println("Type,Category,Amount");
        for (Transaction t : transactions) {
            writer.println(t.toCSV());
        }
        writer.close();
    }
}
