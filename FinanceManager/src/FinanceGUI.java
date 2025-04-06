import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class FinanceGUI extends JFrame {
    private TransactionManager manager = new TransactionManager();

    private JComboBox<String> typeBox = new JComboBox<>(new String[]{"Income", "Expense"});
    private JTextField categoryField = new JTextField(10);
    private JTextField amountField = new JTextField(10);
    private JTextArea transactionArea = new JTextArea(10, 30);
    private JLabel balanceLabel = new JLabel("Balance: $0.00");

    public FinanceGUI() {
        setTitle("Personal Finance Manager");
        setLayout(new FlowLayout());
        setSize(400, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JButton addBtn = new JButton("Add Transaction");
        JButton exportBtn = new JButton("Export CSV");

        add(new JLabel("Type:"));
        add(typeBox);
        add(new JLabel("Category:"));
        add(categoryField);
        add(new JLabel("Amount:"));
        add(amountField);
        add(addBtn);
        add(exportBtn);
        add(balanceLabel);
        add(new JScrollPane(transactionArea));

        addBtn.addActionListener(e -> {
            try {
                String type = typeBox.getSelectedItem().toString();
                String category = categoryField.getText();
                double amount = Double.parseDouble(amountField.getText());
                Transaction t = new Transaction(type, category, amount);
                manager.addTransaction(t);
                updateDisplay();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid amount!");
            }
        });

        exportBtn.addActionListener(e -> {
            try {
                manager.exportToCSV("transactions.csv");
                JOptionPane.showMessageDialog(this, "Exported to transactions.csv");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error exporting file!");
            }
        });

        setVisible(true);
    }

    private void updateDisplay() {
        StringBuilder sb = new StringBuilder();
        for (Transaction t : manager.getTransactions()) {
            sb.append(t.toString()).append("\n");
        }
        transactionArea.setText(sb.toString());
        balanceLabel.setText("Balance: $" + String.format("%.2f", manager.getBalance()));
    }
}
