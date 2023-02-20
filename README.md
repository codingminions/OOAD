# OOAD
**Project** -- Friendly Neighborhood Car Dealership Simulation

Command to run FNCD file: **javac Main.java** followed by **java Main**

**-- TEAM MEMBERS**
1. Prateek Kumar
2. Rohan Eswara

**-- JAVA VERSION**
openjdk 17.0.6 2023-01-17
OpenJDK Runtime Environment Temurin-17.0.6+10 (build 17.0.6+10)
OpenJDK 64-Bit Server VM Temurin-17.0.6+10 (build 17.0.6+10, mixed mode.

**-- Reason to Update UML:**
The inital UML diagram had repeated attributes across Classes of Staff and SalesPeople, Mechanics and Interns respectively.
This was fixed by creating a SuperClass ManageStaff which was extended to the three types of Staff. This helped us to 
track the objects of the different StaffType in one place and use the common functionalities of the staffType. The same procedure
was repeated for Vehicle Class named: VehicleInventory. Finally, we also updated Buyer Class to include the 
vehicleDesired and salesPerson from their respective class instances. Finally, we added classes of different activities of the FNCD
 simulation:Opening, Washing, Repairing, Selling and EndOfDay operations which were an extension of the FNCD class. FNCD class is used to track 
the buyers, entire staff fleet and vehicle fleet and uses function like getDateDay() to track if the FNCD will be open on the day of the simulation. 

**-- OO ELEMENTS:**

