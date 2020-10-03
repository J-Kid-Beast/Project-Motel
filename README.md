# Motel Management System
End of the semester project for Computer Engineering and Computer Science: *Application Development & Design Patterns in Java.*

## Table of Contents
* [Objective](https://github.com/jerrybelmonte/roach-motel-project#objective)
* [Introduction](https://github.com/jerrybelmonte/roach-motel-project#introduction)
* [Features](https://github.com/jerrybelmonte/roach-motel-project#features)
* [UML Class Diagram](https://github.com/jerrybelmonte/roach-motel-project#uml-class-diagram)
* [Technologies](https://github.com/jerrybelmonte/roach-motel-project#technologies)
* [How To Set Up](https://github.com/jerrybelmonte/roach-motel-project#how-to-set-up)
* [Acknowledgements](https://github.com/jerrybelmonte/roach-motel-project#acknowledgements)

## Objective
Get some experience working with several design patterns at once.

## Introduction
Quite often there is a need for more than one design pattern in the same application. For this assignment we built an application that simulates a *simple* establishment called the **Roach Motel**. 
The design patterns we used are as followed:

1. __Singleton Design Pattern__
2. __Factory Design Pattern__
3. __Decorator Design Pattern__
4. __Strategy Design Pattern__

## Features

- There is only one [Roach Motel](Roach%20Motel/src/roachmotel/RoachMotel.java#L21 "RoachMotel.java"). When the motel is created you can specify the `capacity`, which is the number of rooms, and the `name` of the [motel](Roach%20Motel/src/roachmotel/RoachMotel.java#L109 "RoachMotel.getMotel").  
  
- When the motel is [full](Roach%20Motel/src/roachmotel/RoachMotel.java#L315 "RoachMotel.isFull"), the *“No Vacancy”* sign turns on. Otherwise, the *“Vacancy”* sign is shown.  
  
- Every [Motel Room](Roach%20Motel/src/roachmotel/AbstractRoom.java#L8 "AbstractRoom.java") starts out at a _base rate_, but when a customer [checks in](Roach%20Motel/src/roachmotel/RoachMotel.java#L191 "RoachMotel.checkIn"), they can opt for various [amenities](Roach%20Motel/src/roachmotel/Amenity.java#L4 "Amenity.java").    
  1. A [food bar](Roach%20Motel/src/roachmotel/FoodBarDecorator.java#L4 "FoodBarDecorator.java") (extra __$10__ per night)
  2. A [spa](Roach%20Motel/src/roachmotel/SpaDecorator.java#L4 "SpaDecorator.java") (extra __$20__ per night)
  3. Auto [refill](Roach%20Motel/src/roachmotel/RefillBarDecorator.java#L4 "RefillBarDecorator.java") of the food bar (extra __$5__ per night)
  4. Spray resistant [shower](Roach%20Motel/src/roachmotel/ShowerDecorator.java#L4 "ShowerDecorator.java") (extra __$25__ per night)
    * _The spray resistant shower comes in handy when an exterminator comes through_

- Each customer is an entire [Roach Colony](Roach%20Motel/src/roachmotel/RoachColony.java#L7 "RoachColony.java"). The colony has:  
  1. A `name`
  2. An initial `population` *(how many roaches in the colony)*
  3. The `growth rate`

- Roaches are fond of _throwing parties_. Every time they throw a [party](Roach%20Motel/src/roachmotel/RoachColony.java#L143 "RoachColony.party"), the number of occupants in the room **increases** by the _growth rate_.
  - Whenever the roaches _throw a party_, management responds by [spraying](Roach%20Motel/src/roachmotel/RoachMotel.java#L376 "RoachMotel.sprayRoom") the room.
    1. If the room has a _spray resistant shower_, the roach population only **loses 25%** of its member.    
    2. If the room does **not** have the _spray resistant shower_, the roach population **loses fully 50%** of its members.
      - The party method in the `RoachColony` class calls the `RoachMotel.spray` function. (Sounds oddly suicidal, but remember, these are just roaches.)

- When a customer [checks out](Roach%20Motel/src/roachmotel/RoachMotel.java#L253 "RoachMotel.checkOut"), they get charged the [room rate](Roach%20Motel/src/roachmotel/AbstractRoom.java#L83 "AbstractRoom.cost") _(including all of the amenities)_ times the _number of days_ that they stayed. Then the room becomes _"available"_.  
  
- When a colony **checks out** of the motel, they either use [Roach Pay](Roach%20Motel/src/roachmotel/RoachPay.java#L4 "RoachPay.java") or their [Master Roach](Roach%20Motel/src/roachmotel/MasterRoach.java#L4 "MasterRoach.java") credit card to [pay the bill](Roach%20Motel/src/roachmotel/RoachColony.java#L133 "RoachColony.payTheBill"). Roach Pay requires the `name` and `email` of the colony paying the bill. The Master Roach credit card requires a `name`, a `security code`, a `card number` and an `expiration date`.  
  
- All [payments](Roach%20Motel/src/roachmotel/RoachMotel.java#L291 "RoachMotel.pay") made are written to a [transaction log](Roach%20Motel/src/roachmotel/RoachMotel.java#L139 "RoachMotel.transactionLog"). The [log](log.txt "log.txt") contains the _name of the colony_, the _type of payment_ made and the _amount_.  
  
- Displays the [contents](Roach%20Motel/src/roachmotel/RoachMotel.java#L164 "RoachMotel.displayTransactionLog") of the _transaction log_ to the [console](console.txt "console.txt") window before **exiting** the program.  

## UML Class Diagram
![alt text](https://github.com/jerrybelmonte/roach-motel-project/blob/master/RoachMotelUML.jpg?raw=true)

## Technologies
Project was created with:
* JavaSE 12 (JDK v12.0.2)
* JUnit 5 Library (Jupiter API v5.4.0)
* Eclipse IDE 2019 (v4.12.0)

## How To Set Up
To clone and run this project, you will need **Git** and **Eclipse IDE** installed on your computer:

1. Clone this project into your local git repository
   - You can also use the EGit plugin to set up a local git repository: [Eclipse Git Tutorial](https://www.vogella.com/tutorials/EclipseGit/article.html#github)  
2. Create a new **Java Project** on Eclipse and select _JavaSE-12_ for the **JRE**  
   - __File__ > __New__ > __Java Project__ > __Project Name:__ `Roach Motel` , __JRE:__ `JavaSE-12` > __Finish__  
3. Copy the `roachmotel` package and `.java` files into your Java Project `src/` folder  
4. Copy the `unittest` package and `.java` files into your Java Project `src/` folder
   - Follow Eclipse's suggestions to import the `JUnit 5` library into your Java Project folder  
5. Right-click on the `RoachMotelTester.java` file, and select `Run As > JUnit Test`
   - After this step, the results of the JUnit5 test case will be displayed on the `Console` window  

## Acknowledgements
CSULB - Computer Science Faculty Professor: David Brown  
