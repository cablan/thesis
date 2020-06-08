import random, math

import matplotlib.pyplot as plt

def sin(upperBoundary, lowerBoundary, freq, initialDelta):
    # Variables
    midValue = lowerBoundary + (upperBoundary - lowerBoundary) / 2
    angularSpeed = 2 * math.pi * freq
    y = []

    for time in range(int(1/freq)):
        temp = midValue + ((upperBoundary - lowerBoundary) / 2) * math.sin(angularSpeed * time + initialDelta)

        y += [temp]

    return y

roomId = 'room2'
timeLength = 120

# Creating Plotting Lists
x = []
y = []

# Temperature Limits and Average
lowerLimit = 20
upperLimit = 24
avgTemp = lowerLimit + (upperLimit - lowerLimit) / 2


# Initial Time
time = 0

# Generating the function
while True:

    for staticValues in range(30):
        y += [avgTemp]

    randValue = random.randint(0,1)
    
    if randValue == 0:
        ysin = sin(avgTemp, lowerLimit, 1/16, math.pi / 2)
    else:
        ysin = sin(upperLimit, avgTemp, 1/28, math.pi * (-1) / 2)

    y += ysin
    time += 1
    staticValues = 0

    if len(y) > timeLength:
        break

for i in range(len(y)):
    x += [i]


fileObject = open('/home/cablan/Desktop/thesisFiles/inputs/room2Sin.txt', 'w')
for eachTemp in y:
    fileObject.write(roomId + ',' + str(eachTemp) + '\n')

# Creating the plot  
# plotting the points  
plt.plot(x, y) 
  
# naming the x axis 
plt.xlabel('Time (s)') 
# naming the y axis 
plt.ylabel('Temperature (Celsius)') 
  
# giving a title to my graph 
plt.title('Chamber 2') 
  
# function to show the plot 
plt.show()