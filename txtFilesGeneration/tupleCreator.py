# Libraries
import random, sys

# Predefined variables
dataSubjectList = ['Bob', 'Carlos', 'Elisabetta', 'Michele']
numberProducts = 25 # Great Seller number of products available
priceLowerLimit = 10
priceUpperLimit = 20099
greatSellerStock = []

if len(sys.argv) < 2:
    print('Usage: python3 tupleCreator.py [fileLength]')
    sys.exit()

# Running defined variables
fileLength = int(sys.argv[1])

# Built variables
for eachProduct in range(numberProducts):
    # Great Seller strock definition
    name = 'product' + str(eachProduct + 1)
    price = random.randint(priceLowerLimit, priceUpperLimit) / 100
    product = {'name': name, 'price': price}
    greatSellerStock = greatSellerStock + [product]


# Open the destination file in write mode
fileName = 'tuples' + str(fileLength) +'File.txt'
tuplesFile = open(fileName, 'w')

# In each iteration we create a random tuple
for eachValue in range(fileLength):
    
    # Tuple values creation
    transactionId = 'transaction' + str(eachValue + 1)
    dataSubject = dataSubjectList[random.randint(0, len(dataSubjectList)-1)]
    product = greatSellerStock[random.randint(0, len(greatSellerStock)-1)]
    amount = str(product['price'])
    recipientId = product['name']

    # Tuple List creation
    tupleValues = [transactionId, dataSubject, amount, recipientId]

    # Tuple joined in a string separated by a comma
    builtTuple = ','.join(tupleValues) + '\n'

    # Tuple written in the file
    tuplesFile.write(builtTuple)

# Close the destination file
tuplesFile.close()