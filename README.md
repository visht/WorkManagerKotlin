# WorkManagerKotlin

This project is a very basic example of work manager.

# To do a work we need to create a custom worker class which inherites Worker class and finall we implement its doWorkMethod.

# Now doWork method runs on background thread 

Now here we do required work SCheduling pedriodic location updates to server, fetching data from server and inserting that data to db etc.

# Finally in our activity or fragment we create object for Workrequest e.g: OneTimeWorkRequest, PeriodicWorkRequest
# Create your Workmanager instance
# And finally enque your work.

# Thanks
