# Libraries
import random, shelve

# Predefined variables
numberProducts = 25 # Great Seller number of products available
priceLowerLimit = 1
priceUpperLimit = 200
greatSellerStock = []

# Binary shelf file creation
shelfFile = shelve.open('stocks')

# Creating the stock
for eachProduct in range(numberProducts):
    name = 'product' + str(eachProduct + 1)
    price = random.randint(priceLowerLimit, priceUpperLimit)
    product = {'name': name, 'price': price}
    greatSellerStock = greatSellerStock + [product]

# Writing the stock in the binary shelf file
shelfFile['greatSellerStock'] = greatSellerStock

# Closing the binary shelf file
shelfFile.close()

# Printing the generated stock in Latex table format
string = ''

for eachProduct in greatSellerStock:
    string = string + eachProduct['name'] + ' & ' + str(eachProduct['price']) + ' \\\ ' + '\n'
    string = string + '\hline' + '\n'

print(string)