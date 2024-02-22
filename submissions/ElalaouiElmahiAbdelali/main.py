
class Product:
    cheapest5product=[]
    def __init__(self,name,price) :
        self.name=name
        self.price=price
        self.bool=True
        for i in range(len(Product.cheapest5product)):
            if str(Product.cheapest5product[i]) == self.name and Product.cheapest5product[i].price>self.price:
               Product.cheapest5product.price=self.price
               self.bool=False
               break  
        if  self.bool:       
            Product.cheapest5product.append(self)
            Product.cheapest5product = sorted(Product.cheapest5product, key=lambda x: (x.price, x.name))
            if len(Product.cheapest5product) >5: 
               Product.cheapest5product.pop()
class City:
    def __init__(self,name,totaleprice) :
        self.name=name
        self.totaleprice=totaleprice
def handleProb(filename):
    cities={}
    with open(filename, 'r') as file:
        lines = file.readlines()
    for line in lines:
        line=line.split(",")
        product=Product(line[1],float(line[2]))
        if  cities.get(line[0]) is None:
            cities[line[0]]=City(line[0],float(line[2]) )
        else :   
             cities[line[0]].totaleprice+=float(line[2]) 
    cheapestCity= min(cities.values(), key=lambda x: x.totaleprice)                               
    with open("output.txt", 'w') as file:
       file.write( cheapestCity.name+" "+str(cheapestCity.totaleprice) + "\n")   
       for product in Product.cheapest5product:
           file.write(product.name+" "+str(product.price)+ "\n")  
#handleProb("input.txt")
       
        
        