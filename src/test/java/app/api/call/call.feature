Feature: using a response json array to loop

  Background:
    * def urlBase = 'https://restful-booker.herokuapp.com/booking'

  Scenario: get a list of booking ids
    * url urlBase
    * params { checkin: '2021-01-01', checkout: '2021-01-31' }
    * method get
    * status 200
    * match each response == { bookingid: '#number' }
    # loop over all ids returned and get booking
    * call read('@name=child') response

  @ignore @name=child
  Scenario:
    * url urlBase
    * path bookingid
    * header Accept = 'application/json'
    * method get
    * status 200
    * match response contains { firstname: '#string', lastname: '#string' }
