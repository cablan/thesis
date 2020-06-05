import socket, time

# Predefined variables
waitingTime = 1
fileLength = 1000

# Open the source file in read mode
fileName = 'tuples' + str(fileLength) +'File.txt'
fileName = '/home/cablan/Desktop/thesisFiles/' + fileName
tuplesFile = open(fileName, 'r')

# Create a socket
s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

# Define the host and the port and listen into them
host = 'localhost'
port = 9999
s.bind((host, port))
s.listen(5)

while True:
    # Accept the requested connection
    clientSocket, addr = s.accept()
    print("Connection accepted from " + repr(addr[1]))
    
    # Send each tuple contained in the file
    for eachTuple in tuplesFile:
        time.sleep(waitingTime)
        print('Sending tuple: ' + '(' + eachTuple[0:-1] + ')')
        clientSocket.send(bytes(eachTuple, 'utf-8'))

    # Close the connection
    clientSocket.close()