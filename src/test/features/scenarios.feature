Feature: Automation Assignment
  Scenario: Booking flight from MakeMyTrip.com website
    When I launch MakeMyTrip Website
    Then I search for flight from "MUM" to "DEL"
    Then I select departure date of tomorrow
    Then I select non stop flight option
    Then I select 6AM to 12PM in depature
    Then I click on view prices for airline having lowest price value
    Then I select book now for lowest price
    Then I select enter passenger details and secure the trip
    Then I confirm the passenger and capture the total value on fare summary and assert values on itenary and review page

  Scenario: Searching for Hotel on MakeMyTrip.com website
    When I launch MakeMyTrip Website
    Then I search for hotels in Mumbai
    Then I provide location as "Mumbai"
    Then I verify if results are getting displayed for Mumbai location or not






