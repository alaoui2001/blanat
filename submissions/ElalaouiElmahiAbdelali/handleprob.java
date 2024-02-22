package projprob;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Product {
    static List<Product> cheapest5product = new ArrayList<>();
    String name;
    double price;
    boolean bool;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
        this.bool = true;

        for (int i = 0; i < cheapest5product.size(); i++) {
            if (cheapest5product.get(i).name.equals(this.name)) {
            	if( cheapest5product.get(i).price > this.price) {
            		 cheapest5product.get(i).price = this.price;
            		  break;
            	}
               
                this.bool = false;
              
            }
        }

        if (this.bool) {
            cheapest5product.add(this);
            Collections.sort(cheapest5product, (p1, p2) -> {
                if (p1.price == p2.price) {
                    return p1.name.compareTo(p2.name);
                }
                return Double.compare(p1.price, p2.price);
            });

            if (this.bool && cheapest5product.size() > 5) {
                cheapest5product.remove(5);
            }
        }
    }
}

class City {
    String name;
    double totaleprice;

    public City(String name, double totaleprice) {
        this.name = name;
        this.totaleprice = totaleprice;
    }
}

public class handleprob {
    public static void main(String[] args) {
        handleProb("C:\\Users\\ServerDB\\Desktop\\project\\input.txt");
    }

    public static void handleProb(String filename) {
        Map<String, City> cities = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            int i=1;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                Product product = new Product(parts[1], Double.parseDouble(parts[2]));

                cities.computeIfAbsent(parts[0], k -> new City(parts[0], Double.parseDouble(parts[2])));
                cities.get(parts[0]).totaleprice += Double.parseDouble(parts[2]);
                i++;
             
            }

            City cheapestCity = Collections.min(cities.values(), (c1, c2) -> Double.compare(c1.totaleprice, c2.totaleprice));

            try (FileWriter writer = new FileWriter("output.txt")) {
                writer.write(cheapestCity.name + " " + String.format("%.2f", cheapestCity.totaleprice) + "\n");

                for (Product product : Product.cheapest5product) {
                    writer.write(product.name + " " + String.format("%.2f", product.price) + "\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

