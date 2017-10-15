package com.s11113153.chapter1;

import java.util.Enumeration;
import java.util.Vector;

public class Customer {
  private String name;
  private Vector<Rental> rentals = new Vector<>();

  public Customer(String name) {
    this.name = name;
  }

  public void addRental(Rental e) {
    rentals.add(e);
  }

  public String getName() {
    return name;
  }

  public String statement() {
    double totalAmount = 0; // 總消費交額
    int frequentRenterPoints = 0; // 常客積點
    Enumeration<Rental> rentalEnumeration = rentals.elements();
    String result = "Rental record for " + getName() + "\n";

    while (rentalEnumeration.hasMoreElements()) {
      double thisAmount = 0;
      Rental each = rentalEnumeration.nextElement();

      // 根據 movie.price 來區別片子
      switch (each.getMovie().getPrice()) {
        case Movie.REGULAR:
          thisAmount += 2;
          if (each.getDaysRented() > 2)
            thisAmount += (each.getDaysRented() - 2) * 1.5;
          break;
        case Movie.NEW_RELEASE:
          thisAmount += each.getDaysRented() * 3;
          break;
        case Movie.CHILDRENS:
          thisAmount += 1.5;
          if (each.getDaysRented() > 3)
            thisAmount += (each.getDaysRented() - 3) * 1.5;
          break;
      }

      frequentRenterPoints++;
      if ((each.getMovie().getPrice() == Movie.NEW_RELEASE) && each.getDaysRented() > 1) {
        frequentRenterPoints++;
      }

      result += "\t" + each.getMovie().getTitle() + "\t" + String.valueOf(thisAmount) + "\n";
      totalAmount += thisAmount;
    }

    result += "Amount owed is " + String.valueOf(totalAmount) + "\n";
    result +="You earned " + String.valueOf(frequentRenterPoints) + " frequent renter points";

    return result;
  }
}

