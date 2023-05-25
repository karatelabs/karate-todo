Feature: API test using RESTfulBooker application

Background:
  * url 'https://restful-booker.herokuapp.com'

Scenario:
  # obtain token
  * path 'auth'
  * request { username: 'admin', password: 'password123' }
  * method post
  * status 200
  * def authToken = response.token

  * def booking =
  """
  {
    firstname: 'Anthony', lastname: 'ODonnell',
    totalprice: 500, depositpaid: true,
    additionalneeds: 'Clearly Defined Requirements',
    bookingdates: { checkin: '2022-08-30', checkout: '2022-09-10' }
  }
  """

  # create booking
  * path 'booking'
  * header Accept = 'application/json'
  * request booking
  * method post
  * status 200
  * match response contains { bookingid: '#number', booking: '#(booking)' }
  * def bookingid = response.bookingid;

  # find booking by name
  * path 'booking'
  * param firstname = booking.firstname
  * param lastname = booking.lastname
  * method get
  * status 200
  * match response contains { bookingid: '#(bookingid)' }
  * def toDelete = response

  # find booking by date
  * path 'booking'
  * param checkin = '2022-07-01'
  * param checkout = booking.bookingdates.checkout
  * method get
  * status 200
  * match response contains { bookingid: '#(bookingid)' }

  # update booking
  * booking.totalprice = 0
  * path 'booking', bookingid
  * header Accept = 'application/json'
  * header Cookie = 'token=' + authToken
  * request booking
  * method put
  * status 200
  * match response contains booking

  # partially update booking
  * booking.totalprice = 1000
  * path 'booking', bookingid
  * header Accept = 'application/json'
  * header Cookie = 'token=' + authToken
  * request { totalprice: 1000 }
  * method patch
  * status 200
  * match response contains booking

  # delete bookings
  * call read('@delete') toDelete

@ignore @delete
Scenario:
  * path 'booking', bookingid
  * header Cookie = 'token=' + authToken
  * method delete
  * status 201
