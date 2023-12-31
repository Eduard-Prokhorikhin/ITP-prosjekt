package personal_finance.core;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class User {

    private String username;
    private String password;
    private List<Transaction> transactions = new ArrayList<>();
    public User() {}

    public User(String username, String password) {
        setUsername(username);
        setPassword(password);; // For later: Hash passord for more security
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public List<Transaction> getTransactions() {
        return this.transactions;
    }

    public List<Category> getCategories() {
        List<Category> categories = new ArrayList<>();
        
        for (int i = 0; i < getTransactions().size(); i++) {
            Category category = getTransactions().get(i).getCategory();
            if (!categories.contains(category)) {
                categories.add(category);
            }
        }
        
        return categories;
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public void setUsername(String username) {
        if (username.length() < 4 || username.length() > 20) {
            throw new IllegalArgumentException("Username must be between 4 and 20 characters!");
        }

        if (!username.matches("[a-zA-Z0-9]+")) {
            throw new IllegalArgumentException("Username must only contain letters and numbers!");
        }
        
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double calcTotalMonth() {
        double sum = 0;
        LocalDate startDate= LocalDate.now().withDayOfMonth(1);
        LocalDate endDate= startDate.plusMonths(1);
        for (Transaction transaction : transactions) {
            if (transaction.getDate().isAfter(startDate) && transaction.getDate().isBefore(endDate)) {
                sum+=transaction.getValue();
            }
        }
        return sum;
    }

    public double calcTotalLife() {
        double sum = 0;
        for (Transaction transaction : transactions) {
            sum+=transaction.getValue();
        }
        return sum;
    }
}
