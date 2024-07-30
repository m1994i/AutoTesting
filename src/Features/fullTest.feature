@tag
  Feature: User Login
    Scenario: Successfully login
      Given The user is on the login page
      When User insert valid credentials (username: "ivanovicmilan401", password: "zELOGIa03xP3")
      And User click on sign in button
      Then User will get account page
      And User click on button back to shop
      Then User navigate to catalogue page
      Then User sort catalogue by dropdown menu
      And User click on page number three then click on product and click on plus button one time
      Then User click to button cart adn click to logo button to get primary page
      And User click on button cart and click button cart review and click on checkout button
      Then User entering checkout info and click to checkbox and click to my account button
      And User click on log out button




