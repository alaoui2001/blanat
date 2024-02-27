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
        
        if(cheapest5product.size()==0) {
        	cheapest5product.add(this);
        	
        }
        else {
        	boolean b=false;
            int count=cheapest5product.size();
            for (int i =count -1; i >=0 ; i--) {
            	Product p=cheapest5product.get(i);
            	
                    if( p.price > this.price) {
                    	cheapest5product.remove(this);
                    	cheapest5product.add(i,this);
                    	b=true;
                    	
                    }
                   
                    else if(p.price == this.price){
                    	if(p.name.compareTo(this.name)>0) {
                    		cheapest5product.remove(this);
                    		cheapest5product.add(i,this);
                    		b=true;
                    	}
                    	else {
                    		try {
                    			cheapest5product.remove(this);
                        		cheapest5product.add(i+1,this);
                    		}
                    		catch(IndexOutOfBoundsException e) {
                    			cheapest5product.add(i,this);
                    			
              
                    		}
                    		b=true;
                    		break;
                    	}
                    	
                    }
                 
                    else
                    	break;
                  
               
            }

         

                if ( cheapest5product.size() > 5) {
                    cheapest5product.remove(5);
                }
                else if(!b && count<5) {
             	   cheapest5product.remove(this);
             	   cheapest5product.add(this);
             	   
                 }
        }
}
        
    public void setprice(double price) {
    	
    	this.price=price;
    	 int count=cheapest5product.size();
    	 boolean b=false;
         for (int i =count -1; i >=0 ; i--) {
         	Product p=cheapest5product.get(i);
       
                 if( p.price > this.price) {
                	cheapest5product.remove(this);
                 	cheapest5product.add(i,this);
                 	b=true;
                 	
                 }
                 else if(p.price == this.price){
                 	if(p.name.compareTo(this.name)>0) {
                 		cheapest5product.remove(this);
                 		cheapest5product.add(i,this);
                 		b=true;
                 	}
                	else {
                		try {
                			cheapest5product.remove(this);
                    		cheapest5product.add(i+1,this);
                    		
                		}
                		catch(IndexOutOfBoundsException e) {
                			cheapest5product.add(i,this);
                			
          
                		}
                		b=true;
                		break;
                	}
                 	
                 }
              
                 else
                 	break;

     }
         if ( cheapest5product.size() > 5) {
             cheapest5product.remove(5);
         }
         else if(!b && count<5) {
       	   cheapest5product.remove(this);
       	   cheapest5product.add(this);
       	   
           }
        
    }
}

class City {
    String name;
    double totaleprice;
    static City cheapestcity;
    public City(String name, double totaleprice) {
        this.name = name;
        this.totaleprice = totaleprice;

    }
    public void setTotalprice(double price) {
    	this.totaleprice+=price;
    	
    }
}

public class handleprob {
    public static void main(String[] args) {
        handleProb("C:\\Users\\ServerDB\\eclipse-workspace\\projprob\\input.txt");
    }

    public static void handleProb(String filename) {
        Map<String, City> cities = new HashMap<>();
        Map<String, Product> products = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            int i=1;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
             
                if(!products.containsKey(parts[1])) 
                	products.put(parts[1],new Product(parts[1], Double.parseDouble(parts[2])) );
                else 
                	    products.get(parts[1]).setprice(Double.parseDouble(parts[2]));
                
                
               
                if(!cities.containsKey(parts[0])) 
                	cities.put(parts[0], new City(parts[0], Double.parseDouble(parts[2]) ));
                else
                cities.get(parts[0]).setTotalprice(Double.parseDouble(parts[2]));
                i++;
                if(i%10000000==0) {
                	System.out.println(i);
             
                }
             
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

