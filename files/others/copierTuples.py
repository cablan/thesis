# Libraries
import sys, time, pyperclip

# Predefined variables
waitingTime = 2

if len(sys.argv) < 2:
    print('Usage: python3 copierTuples.py [fileLength]')
    sys.exit()

# Running defined variables
#fileLength = int(sys.argv[1])

# Open the destination file in read mode
# fileName = 'tuples' + str(fileLength) +'File.txt'
fileName = 'tuples' + sys.argv[1] +'File.txt'
tuplesFile = open(fileName, 'r')

for eachTuple in tuplesFile:
    time.sleep(waitingTime)
    pyperclip.copy(eachTuple)
    print('Copied tuple: ' + '(' + eachTuple[0:-2] + ')')