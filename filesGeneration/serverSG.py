import socket, time

# Predefined variables
waitingTime = 1
fileLength = 1000

# Open the destination file in read mode
fileName = 'tuples' + str(fileLength) +'File.txt'
fileName = '/home/cablan/Desktop/thesisFiles/' + fileName
tuplesFile = open(fileName, 'r')

# Explain socket connections
s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
host = 'localhost'
port = 9999
s.bind((host, port))
s.listen(5)

while True:
    clientSocket, addr = s.accept()
    print("Connection accepted from " + repr(addr[1]))
    
    for eachTuple in tuplesFile:
        time.sleep(waitingTime)
        print('Sending tuple: ' + '(' + eachTuple[0:-1] + ')')
        clientSocket.send(bytes(eachTuple, 'utf-8'))

    clientSocket.close()