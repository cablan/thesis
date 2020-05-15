import socket, time, sys

# Predefined variables
waitingTime = 1

# Choose fileLength
if len(sys.argv) == 2:
    fileLength = sys.argv[1]
else:
    fileLength = 10

# Open the source file in read mode
fileName = 'room1.txt'
fileName = '/home/cablan/Desktop/thesisFiles/inputs/' + fileName
tuplesFile = open(fileName, 'r')

# Create a socket
s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

# Define the host and the port and listen into them
host = 'localhost'
port = 8888
s.bind((host, port))
s.listen(5)

# Accept the requested connection
clientSocket, addr = s.accept()
print("Connection accepted from " + repr(addr[1]))

time.sleep(5)

# Send each tuple contained in the file
for eachTuple in tuplesFile:
    print('Sending tuple: ' + '(' + eachTuple[0:-1] + ')')
    clientSocket.send(bytes(eachTuple, 'utf-8'))
    time.sleep(waitingTime)

# Close the connection
clientSocket.close()

# Close the file
tuplesFile.close()