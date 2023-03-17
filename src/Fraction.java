/**
 * Represents a fraction p/q in simplest terms.
 * p and q are ints, q is positive, and p and q have no
 * common divisor greater than 1.
 * <p>
 * Student Directions: Try to read and understand the code first, then
 * complete the sections labeled "TODO" from top to bottom.  As you
 * proceed, compile and run the code: it will run the tests in main().
 * Ideally they should all "pass" and print "true".  You should add
 * more tests too!
 * 
 * @author YOUR NAME HERE
 * @author A. Jacoby (template)
 * @version 0.6 (14 Dec 2022)
 */
public class Fraction implements Comparable<Fraction> {
  // Constants
  public static final Fraction ZERO = new Fraction(0, 1);
  public static final Fraction ONE = new Fraction(1, 1);
  public static final Fraction NEG_ONE = new Fraction(-1, 1);
  
  // Fields
  /** Numerator. */
  private final int num;
  /** Denominator. */
  private final int denom;
  // Note: Feel free to rename these fields, but you'll want to
  // do a search and replace since they're referenced multiple
  // times!
  
  // Static methods
  /**
   * Returns the greatest common divisor of a and b (always positive).
   */
  public static int gcd(int a, int b) {
    // Style-wise, this method should probably go after non-static methods!
    // Ignore the sign of both numbers.
    a = Math.abs(a);
    b = Math.abs(b);
    // TODO
    // Usually everything in a method after the "TODO" comment has to be
    // replaced by you.  It's there as a placeholder to allow the code to
    // compile, even if it doesn't work correctly yet!
    // Remove the "TODO" comments as you finish them.
    // IntelliJ (and other IDEs) have a special pane to show you your remaining
    // TODO items.
    return -1;
  }
  
  // Constructors
  /**
   * Creates new fraction with given numerator and denominator.
   * Fraction will be simplified to lowest terms.
   * Denominator cannot be 0.
   */
  public Fraction(int numerator, int denominator) {
    if (denominator == 0) {
      throw new IllegalArgumentException("denominator cannot be zero");
    }
    // TODO: Finish this!
    // If denominator < 0, switch the signs of both numerator and denominator.
    // Find the gcd of the numerator and denominator (use the method above),
    // then divide numerator and denominator by that value before assigning
    // fields num and denom.
    // Note: Since num and denom are "final", you must make sure that
    // they are only assigned a value (initialized) EXACTLY one time.
    num = -1;
    denom = -1;
  }
  
  // Methods
  /**
   * Returns a String representation of this Fraction, like "-1/2"
   * or "13".
   */
  public String toString() {
    // TODO
    // Return just num if denom is 1, otherwise return num/denom.
    return "";
  }
  
  /**
   * Returns a double approximation of this Fraction (that is,
   * converts the fraction to a decimal).
   */
  public double toDouble() {
    // TODO
    return -1;
  }
    
  /**
   * Returns numerator (top part) of fraction. Will be negative if the
   * fraction is negative and zero if the fraction is zero.
   */
  public int numerator() { return num; }

  /**
   * Returns denominator (bottom part) of fraction. Always positive.
   */
  public int denominator() { return denom; }
  
  /** Does this fraction equal zero? */
  public boolean isZero() { return num == 0; }
  
  /**
   * Returns whether or not the fraction b represents is numerically
   * equivalent to this fraction (like how the == operator works for
   * ints).
   */
  public boolean equals(Fraction f) {
    if (f == null) { return false; }
    return this.num == f.num && this.denom == f.denom;
  }
  
  /**
   * Returns a negative number if this is less than b, zero if this and b are
   * equal, and a positive number if this is greater than b.
   */
  public int compareTo(Fraction b) {
    // a/b < c/d iff ad < bc
    // TODO
    return -1;
  }

  // Operations
  
  /** Returns a new Fraction which is the product of this and b. */
  public Fraction times(Fraction b) {
    // TODO
    return new Fraction(1, 1);
  }
  
  /** Returns a new Fraction equal to this times -1. */
  public Fraction negate() {
    return new Fraction(-num, denom);
  }
  
  /**
   * Returns a new Fraction equal to the reciprocal of this one.
   * 
   * @throws ArithmeticException if this fraction equals zero.
   */
  public Fraction reciprocal() {
    if (num == 0) {
      throw new ArithmeticException("Cannot take reciprocal of zero");
    }
    // TODO
    return new Fraction(1, 1);
  }
  
  /** Returns a new Fraction which is the quotient of this and b. */
  public Fraction dividedBy(Fraction b) {
    // TODO
    // Hint: Try using times and reciprocal!
    // Hint 2: Take a look at minus() below for a similar situation.
    return new Fraction(1, 1);
  }
  
  /** Returns a new Fraction which is the sum of this and b. */
  public Fraction plus(Fraction b) {
    // TODO
    // Tip: a/b + c/d = (ad + cb)/(bd)
    return new Fraction(1, 1);
  }
  
  /** Returns a new Fraction which is the difference between this and b. */
  public Fraction minus(Fraction b) {
    return plus(b.negate());
  }
  
  // Static methods
  /** Converts Strings of the form p or p/q to Fractions. */
  public static Fraction parseFraction(String f) {
    if (f.contains("/")) {
      int slashIdx = f.indexOf("/");
      int num = Integer.parseInt(f.substring(0, slashIdx));
      int denom = Integer.parseInt(f.substring(slashIdx + 1));
      return new Fraction(num, denom);
    } else {
      int num = Integer.parseInt(f);
      switch (num) {
        case 1: return ONE;
        case 0: return ZERO;
        case -1: return NEG_ONE;
        default: return new Fraction(num, 1);
      }
    }
  }

  /** Tests the class. All targetted tests should print "true"! */
  public static void main(String[] args) {
    // Basic test - demonstrates how the class could be used.
    System.out.println("\n** Demo **");
    Fraction a = new Fraction(2, -4);
    Fraction b = new Fraction(2, 3);
    Fraction c = a.times(b);
    System.out.println(a + " * " + b + " = " + c + " ~= " + c.toDouble());
    // Should print "-1/2 * 2/3 = -1/3 ~= -0.33333333333"

    // Targetted tests
    System.out.println("\n** GCD Tests **");
    System.out.println("gcd(10, 15)=5?\t"   + (Fraction.gcd(15, 10) == 5));
    System.out.println("gcd(10, 13)=1?\t"   + (Fraction.gcd(15, 13) == 1));
    System.out.println("gcd(-10, 20)=10?\t" + (Fraction.gcd(-10, 20) == 10));
    System.out.println("gcd(0, 20)=20?\t"   + (Fraction.gcd(0, 20) == 20));

    System.out.println("\n** Constructor Tests **");
    Fraction f;
    f = new Fraction(2, 4);
    System.out.println("Fraction(2,4)==1/2?\t" + (f.num == 1 && f.denom == 2));
    f = new Fraction(-4, 2);
    System.out.println("Fraction(-4,2)==-2?\t" + (f.num == -2 && f.denom == 1));
    f = new Fraction(4, -2);
    System.out.println("Fraction(4,-2)==-2?\t" + (f.num == -2 && f.denom == 1));
    f = new Fraction(-5, 5);
    System.out.println("Fraction(-5,5)==-1?\t" + (f.num == -1 && f.denom == 1));
    f = new Fraction(-5, 3);
    System.out.println("Fraction(-5,3)==-5/3?\t" + (f.num == -5 && f.denom == 3));
    f = new Fraction(0, 2);
    System.out.println("Fraction(0,2)==0?\t"   + f.isZero());
    
    System.out.println("\n** toString Tests **");
    System.out.println("ONE == 1\t\t\t" + (ONE.toString().equals("1")));
    Fraction half = new Fraction(1, 2);
    System.out.println("Fraction(1,2) == 1/2\t" + (half.toString().equals("1/2")));

    System.out.println("\n** times Tests **");
    // 1/2 * 1/2 == 1/4
    testTimes("1/2", "1/2", "1/4");
    // 15/20 * 3/6 = 3/4 * 1/2 = 3/8
    testTimes("15/20", "3/6", "3/8");
    // -12/5 * 10/-24 = -12/5 * -5/12 = 1
    testTimes("-12/5", "10/-24", "1");
    // 1/2 * 0 = 0
    testTimes("1/2", "0", "0");
    
    System.out.println("\n** plus Tests **");
    testPlus("1/2", "1/2", "1");
    // TODO: Add more tests!
    System.out.println("\nTODO: Add more tests!");
  } // end main()
  
  /**
   * Tests multiplication: prints if f1 * f2 == expected.
   */
  private static void testTimes(String f1Str, String f2Str, String expectedStr) {
    Fraction f1 = parseFraction(f1Str);
    Fraction f2 = parseFraction(f2Str);
    Fraction expected = parseFraction(expectedStr);
    Fraction actual = f1.times(f2);
    System.out.println(f1 + " * " + f2 + " == " + expectedStr + "\t" +
                       actual.equals(expected));
  }

  /**
   * Tests addition: prints if f1 + f2 == expected.
   */
  private static void testPlus(String f1Str, String f2Str, String expectedStr) {
    Fraction f1 = parseFraction(f1Str);
    Fraction f2 = parseFraction(f2Str);
    Fraction expected = parseFraction(expectedStr);
    Fraction actual = f1.plus(f2);
    System.out.println(f1 + " + " + f2 + " == " + expectedStr + "\t" +
                       actual.equals(expected));
  }

}