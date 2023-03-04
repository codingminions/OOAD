Project -- Friendly Neighborhood Car Dealership Simulation

Command to run FNCD file: javac Main.java followed by java Main

-- TEAM MEMBERS

Prateek Kumar
Rohan Eswara

-- JAVA VERSION
openjdk 17.0.6 2023-01-17 OpenJDK Runtime Environment Temurin-17.0.6+10 (build 17.0.6+10) OpenJDK 64-Bit Server VM Temurin-17.0.6+10 (build 17.0.6+10, mixed mode.

— Reason to update UML : 
In the previous UML diagram, the VehicleDecorator class was using the Vehicle class instance, however this prevented us from adding multiple add-on features to the same vehicle, which was corrected by creating an interface called “AddonInterface”. The Vehicle class as well as the VehicleDecorator class now implements the AddonInterface interface, and the VehicleDecorator also uses the AddonInterface interface. This ensures that a recursive wrapping of the Vehicles and its respective decorators can now take place. We have also now added the attributes raceswon (boolean) and racescount to keep track if a vehicle has won one or more races and the number of races it has won. The boolean raceswon is now used to increment the selling price of the vehicle by 10%. We also updated the Publisher Pattern classes splitting them into a list of different subscribers mainly Logger and Tracker to implement a Push strategy of the Observer Patterns instead of the Poll strategy. We also made sure that the event information passed to the subscribers is wrapped in a subscriberEventInfo class instance for better scalability in the medium of conveying information to our subscribers.

— OO elements used : 

Decorator pattern : the decorator pattern was used to add multiple Add-ons to the vehicles which were being sold

Strategy pattern : the strategy pattern was used to implement three different washing techniques, where one type was assigned to one intern in a random manner

Observer Pattern : this pattern was used to publish four major events and the subscribers / observers — logger and trackers  were notified of these events and took appropriate actions when these events occurred. The logger logged the occurrence of these major events, while the tracker was used to keep track of metrics such as Total money earned by all Staff, and the Total money earned by the FNCD.
