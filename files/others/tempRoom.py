import random, time

import matplotlib.pyplot as plt

roomId = 'room1' # 'room1' or 'room2'
timeLength = 100

# Creating Plotting Lists
x = []
y = []

# Temperature Limits and Average
lowLimit = 20 # 20 or 22
highLimit = 24 # 24 or 29
avgTemp = lowLimit + (highLimit - lowLimit) / 2

# Temperature Speed Change
deltaTemp = 0.5

# Initial Temperature and Time
initialTemp = random.randint(lowLimit, highLimit)
print('Initial Temperature: ' + str(initialTemp) + ';')
time = 0

if initialTemp == highLimit or initialTemp == lowLimit:
    initialTemp = avgTemp

while True:
    temp = initialTemp + deltaTemp * time

    print(str(time) + ' - ' + str(temp) + ';')

    if temp == avgTemp:
        waitingTime = 0
        while waitingTime < (timeLength / 10):
            y += [temp]
            if len(y) > timeLength:
                break
            waitingTime += 1
        randValue = random.randint(1,2)
        if randValue == 1:
            deltaTemp = deltaTemp * (-1)
        else:
            deltaTemp = deltaTemp
        initialTemp = temp
        time = 0
        print('Initial Temperature: ' + str(initialTemp) + ';')
    elif temp == lowLimit or temp == highLimit:
        deltaTemp = deltaTemp * (-1)
        initialTemp = temp
        time = 0
        print('Initial Temperature: ' + str(initialTemp) + ';')

    y += [temp]

    time += 1

    if len(y) > timeLength:
        break

# Creating the plot  
# plotting the points  
plt.plot(x, y) 
  
# naming the x axis 
plt.xlabel('Time') 
# naming the y axis 
plt.ylabel('Temperature') 
  
# giving a title to my graph 
plt.title('Room 2 Temperatures') 
  
# function to show the plot 
plt.show()