# Assignment Laundry booking

## The assignment
Imagine a house with 20 households that share 2 laundry rooms. The rooms are
bookable between 7-22 every day.
Write code that allows the residents to book a laundry time for any laundry room.

The service should:
* Book (an available) laundry time.
* List booked times.
* Allow cancellations.

Make sure to include:
* Runnable code along with instructions on how to run it locally.
* An SQL database model.
* Automated tests.

You're not required to build a web service or any external API (i.e. REST, gRPC,
etc.). An automated test to interact with your implementation is enough.

Choose technologies and frameworks that you are already comfortable working with.
We want you to showcase your talent and strengths. We care
about your time and encourage you not to spend too much effort on this assignment.
We're not expecting a ready-to-release product.

Commit your solution to a version control system (such as GitHub or BitBucket) and
make sure that we can access it at least 24h prior to your debrief discussion. We
expect you to unpublish it at the latest one week after you had the home assignment
discussion.

Prepare to walk us through your code and your thought process during the debrief
session.

---

## Solution:
How to run:
1. Run "mvn spring-boot:run" in the terminal from the laundry_booking_service module.
2. Go to localhost:8080/swagger-ui.html
   1. In the swagger api you can create bookings via the post method
      1. The laundryRoomId must be 1 or 2 because that is the only rooms that exist at the moment
      2. The householdId must be between 1-20
      3. The date must be today or in the future
      4. The hourSlot must be between 7 - 22. If the date is todays date, the hourSlot must be the current hour or future hour
   2. You can get all bookings
   3. You can delete a booked time
3. If you want to see the SQL database model
   1. Either go to http://localhost:8080/h2-console and enter "password" in the password field to see the database
   2. Or, go to V1__initial_schema.sql to see all tables
4. The tests are in the LaundryBookingControllerIT, currently there is one test for get, one for post and one for delete.
   1. To run the tests. Run "mvn clean test" from the root of the project.

* I chose to do a simple database model where I added the actual physical laundry rooms in on table, the households in one table and the actual bookings in another.

* Choices I've made regarding dependencies
  * I chose H2 as an inmemory database because I read that it's easy to get it up and running and easy to use
  * Same for flyway, very easy to get up and running and to get the process with creating table automated
  * I also added the springdoc-openapi-ui dependency to get the swagger ui for easier testing/use
  * Since I've followed the clean code principles at Pricerunner I chose to do the same here. Therefor no javadoc and such.

Todo - Stuff I would want to implement but lacked time (can discuss it during the interview)
* I would want to fix, so you don't have to add a laundry room when booking, either add one or get assigned one at random if it's not important which room the user want
* Would like UUID instead of Integer as key
* I would like a poller of some sorts to remove old bookings that have passed, or move them to a history table
* Fix java.sql.Date to java.util.Date in LaundryBookingService class for method checkDateAndHourSlot
* Rethink the whole solution with java Date, use LocalDate instead of Instant.... Small stuff that I needed to do to go around it such as:
  * cast to string when querying the sql
  * work with sql LocalDate when checking the time and date for a valid booking 
* Add NotFoundException if you try to delete a booking that do not exist
* More tests to test the badrequest validation and add a beforeEach between each test running