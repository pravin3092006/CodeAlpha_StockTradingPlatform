import java.util.*;

/* Stock class */
class Stock {
    String symbol;
    double price;

    Stock(String symbol, double price) {
        this.symbol = symbol;
        this.price = price;
    }
}

/* Portfolio class */
class Portfolio {
    Map<String, Integer> holdings = new HashMap<>();
    double balance = 10000.0; // initial balance

    void buyStock(Stock stock, int quantity) {
        double cost = stock.price * quantity;
        if (cost <= balance) {
            holdings.put(stock.symbol, holdings.getOrDefault(stock.symbol, 0) + quantity);
            balance -= cost;
            System.out.println("✅ Bought " + quantity + " shares of " + stock.symbol);
        } else {
            System.out.println("❌ Insufficient balance!");
        }
    }

    void sellStock(Stock stock, int quantity) {
        int owned = holdings.getOrDefault(stock.symbol, 0);
        if (owned >= quantity) {
            holdings.put(stock.symbol, owned - quantity);
            balance += stock.price * quantity;
            System.out.println("✅ Sold " + quantity + " shares of " + stock.symbol);
        } else {
            System.out.println("❌ Not enough shares to sell!");
        }
    }

    void displayPortfolio() {
        System.out.println("\n📊 PORTFOLIO SUMMARY");
        System.out.println("Balance: ₹" + balance);
        System.out.println("Holdings:");
        if (holdings.isEmpty()) {
            System.out.println("No stocks owned.");
        } else {
            for (String key : holdings.keySet()) {
                System.out.println(key + " : " + holdings.get(key) + " shares");
            }
        }
    }
}

/* Main class */
public class StockTradingPlatform {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Stock apple = new Stock("AAPL", 150);
        Stock google = new Stock("GOOG", 2800);
        Stock tesla = new Stock("TSLA", 700);

        Portfolio portfolio = new Portfolio();

        while (true) {
            System.out.println("\n📈 STOCK TRADING PLATFORM");
            System.out.println("1. View Market Data");
            System.out.println("2. Buy Stock");
            System.out.println("3. Sell Stock");
            System.out.println("4. View Portfolio");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("\n📉 MARKET DATA");
                    System.out.println("AAPL - ₹150");
                    System.out.println("GOOG - ₹2800");
                    System.out.println("TSLA - ₹700");
                    break;

                case 2:
                    System.out.print("Enter stock symbol: ");
                    String buySymbol = sc.next().toUpperCase();
                    System.out.print("Enter quantity: ");
                    int buyQty = sc.nextInt();

                    if (buySymbol.equals("AAPL"))
                        portfolio.buyStock(apple, buyQty);
                    else if (buySymbol.equals("GOOG"))
                        portfolio.buyStock(google, buyQty);
                    else if (buySymbol.equals("TSLA"))
                        portfolio.buyStock(tesla, buyQty);
                    else
                        System.out.println("❌ Invalid stock!");
                    break;

                case 3:
                    System.out.print("Enter stock symbol: ");
                    String sellSymbol = sc.next().toUpperCase();
                    System.out.print("Enter quantity: ");
                    int sellQty = sc.nextInt();

                    if (sellSymbol.equals("AAPL"))
                        portfolio.sellStock(apple, sellQty);
                    else if (sellSymbol.equals("GOOG"))
                        portfolio.sellStock(google, sellQty);
                    else if (sellSymbol.equals("TSLA"))
                        portfolio.sellStock(tesla, sellQty);
                    else
                        System.out.println("❌ Invalid stock!");
                    break;

                case 4:
                    portfolio.displayPortfolio();
                    break;

                case 5:
                    System.out.println("👋 Exiting Stock Trading Platform...");
                    sc.close();
                    return;

                default:
                    System.out.println("❌ Invalid choice!");
            }
        }
    }
}