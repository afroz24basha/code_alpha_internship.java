// Stock.java  
public class Stock {  
   private String symbol;  
   private double price;  
   private double quantity;  
  
   public Stock(String symbol, double price, double quantity) {  
      this.symbol = symbol;  
      this.price = price;  
      this.quantity = quantity;  
   }  
  
   public String getSymbol() {  
      return symbol;  
   }  
  
   public double getPrice() {  
      return price;  
   }  
  
   public double getQuantity() {  
      return quantity;  
   }  
  
   public void setPrice(double price) {  
      this.price = price;  
   }  
  
   public void setQuantity(double quantity) {  
      this.quantity = quantity;  
   }  
}  
  
// Portfolio.java  
import java.util.ArrayList;  
import java.util.List;  
  
public class Portfolio {  
   private List<Stock> stocks;  
   private double cash;  
  
   public Portfolio() {  
      stocks = new ArrayList<>();  
      cash = 10000.0; // initial cash balance  
   }  
  
   public void addStock(Stock stock) {  
      stocks.add(stock);  
   }  
  
   public void removeStock(Stock stock) {  
      stocks.remove(stock);  
   }  
  
   public double getPortfolioValue() {  
      double totalValue = cash;  
      for (Stock stock : stocks) {  
        totalValue += stock.getPrice() * stock.getQuantity();  
      }  
      return totalValue;  
   }  
  
   public void buyStock(Stock stock, double quantity) {  
      if (cash >= stock.getPrice() * quantity) {  
        cash -= stock.getPrice() * quantity;  
        stock.setQuantity(stock.getQuantity() + quantity);  
      } else {  
        System.out.println("Insufficient cash balance");  
      }  
   }  
  
   public void sellStock(Stock stock, double quantity) {  
      if (stock.getQuantity() >= quantity) {  
        cash += stock.getPrice() * quantity;  
        stock.setQuantity(stock.getQuantity() - quantity);  
      } else {  
        System.out.println("Insufficient stock quantity");  
      }  
   }  
}  
  
// MarketData.java  
import java.util.ArrayList;  
import java.util.List;  
  
public class MarketData {  
   private List<Stock> stocks;  
  
   public MarketData() {  
      stocks = new ArrayList<>();  
      // load historical market data from a file or database  
      // for demonstration purposes, let's hardcode some sample data  
      stocks.add(new Stock("AAPL", 150.0, 100));  
      stocks.add(new Stock("GOOG", 2000.0, 50));  
      stocks.add(new Stock("MSFT", 100.0, 200));  
   }  
  
   public List<Stock> getStocks() {  
      return stocks;  
   }  
  
   public void updateMarketData() {  
      // update stock prices based on some algorithm or external data feed  
      for (Stock stock : stocks) {  
        stock.setPrice(stock.getPrice() + (Math.random() - 0.5) * 10.0);  
      }  
   }  
}  
  
// TradingPlatform.java  
import java.util.Scanner;  
  
public class TradingPlatform {  
   private Portfolio portfolio;  
   private MarketData marketData;  
  
   public TradingPlatform() {  
      portfolio = new Portfolio();  
      marketData = new MarketData();  
   }  
  
   public void run() {  
      Scanner scanner = new Scanner(System.in);  
      while (true) {  
        System.out.println("1. Buy stock");  
        System.out.println("2. Sell stock");  
        System.out.println("3. View portfolio");  
        System.out.println("4. Exit");  
        int choice = scanner.nextInt();  
        switch (choice) {  
           case 1:  
              System.out.print("Enter stock symbol: ");  
              String symbol = scanner.next();  
              System.out.print("Enter quantity: ");  
              double quantity = scanner.nextDouble();  
              Stock stock = getStockBySymbol(symbol);  
              if (stock!= null) {  
                portfolio.buyStock(stock, quantity);  
              } else {  
                System.out.println("Stock not found");  
              }  
              break;  
           case 2:  
              System.out.print("Enter stock symbol: ");  
              symbol = scanner.next();  
              System.out.print("Enter quantity: ");  
              quantity = scanner.nextDouble();  
              stock = getStockBySymbol(symbol);  
              if (stock!= null) {  
                portfolio.sellStock(stock, quantity);  
              } else {  
                System.out.println("Stock not found");  
              }  
              break;  
           case 3:  
              System.out.println("Portfolio value: " + portfolio.getPortfolioValue());  
              break;  
           case 4:  
              System.exit(0);  
        }  
        marketData.updateMarketData();  
      }  
   }  
  
   private Stock getStockBySymbol(String symbol) {  
      for (Stock stock : marketData.getStocks()) {  
        if (stock.getSymbol().equals(symbol)) {  
           return stock;  
        }  
      }  
      return null;  
   }  
  
   public static void main(String[] args) {  
      TradingPlatform platform = new TradingPlatform();  
      platform.run();  
   }  
}
