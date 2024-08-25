package project3;
import java.util.Scanner;

//Abstract ShopAcc Class
abstract class ShopAcc {
 private int accNo;
 private String accNm;
 private float charges;

 public ShopAcc(int accNo, String accNm, float charges) {
     this.accNo = accNo;
     this.accNm = accNm;
     this.charges = charges;
 }
 public int getAccNo() {
     return accNo;
 }

 public String getAccNm() {
     return accNm;
 }

 public void setAccNm(String accNm) {
     this.accNm = accNm;
 }

 public float getCharges() {
     return charges;
 }

 public abstract void bookProduct(float charges);

 @Override
 public String toString() {
     return "ShopAcc [accNo=" + accNo + ", accNm=" + accNm + ", charges=" + charges + "]";
 }
}

//Abstract PrimeAcc Class
abstract class PrimeAcc extends ShopAcc {
 private boolean isPrime;

 public PrimeAcc(int accNo, String accNm, float charges, boolean isPrime) {
     super(accNo, accNm, charges);
     this.isPrime = isPrime;
 }

 public boolean isPrime() {
     return isPrime;
 }

 @Override
 public void bookProduct(float charges) {
     System.out.println("Product booked with charges: " + charges + " (No delivery charges for Prime account)");
 }

 @Override
 public String toString() {
     return "PrimeAcc [isPrime=" + isPrime + ", toString()=" + super.toString() + "]";
 }
}

//Abstract NormalAcc Class
abstract class NormalAcc extends ShopAcc {
 private static final float DELIVERY_CHARGE = 50;

 public NormalAcc(int accNo, String accNm, float charges) {
     super(accNo, accNm, charges);
 }

 public float getDeliveryCharge() {
     return DELIVERY_CHARGE;
 }

 @Override
 public void bookProduct(float charges) {
     System.out.println("Product booked with charges: " + (charges + DELIVERY_CHARGE) + " (Including fixed delivery charges of 50)");
 }

 @Override
 public String toString() {
     return "NormalAcc [deliveryCharge=" + DELIVERY_CHARGE + ", toString()=" + super.toString() + "]";
 }
}

//Abstract ShopFactory Class
abstract class ShopFactory {
 public abstract PrimeAcc getNewPrimeAccount(int accNo, String accNm, float charges, boolean isPrime);
 public abstract NormalAcc getNewNormalAccount(int accNo, String accNm, float charges);
}

//Concrete GSPrimeAcc Class
class GSPrimeAcc extends PrimeAcc {
 public GSPrimeAcc(int accNo, String accNm, float charges, boolean isPrime) {
     super(accNo, accNm, charges, isPrime);
 }
}

//Concrete GSNormalAcc Class
class GSNormalAcc extends NormalAcc {
 public GSNormalAcc(int accNo, String accNm, float charges) {
     super(accNo, accNm, charges);
 }
}

//Concrete GSShopFactory Class
class GSShopFactory extends ShopFactory {
 @Override
 public PrimeAcc getNewPrimeAccount(int accNo, String accNm, float charges, boolean isPrime) {
     return new GSPrimeAcc(accNo, accNm, charges, isPrime);
 }

 @Override
 public NormalAcc getNewNormalAccount(int accNo, String accNm, float charges) {
     return new GSNormalAcc(accNo, accNm, charges);
 }
}

//Entry Point for Application
public class GoShoppingApp {
 public static void main(String[] args) {
     Scanner sc = new Scanner(System.in);

     // Ask user whether they want a Prime account or a Normal account
     System.out.println("Do you want to create a Prime Account? (yes/no)");
     String accountTypeChoice = sc.nextLine().trim().toLowerCase();

     ShopFactory shopFactory = new GSShopFactory();
     ShopAcc account;

     if (accountTypeChoice.equals("yes")) {
         // Take input for Prime Account
         System.out.println("Enter details for Prime Account:");
         System.out.print("Account Number: ");
         int accNo = sc.nextInt();
         sc.nextLine();  // Consume newline left-over
         System.out.print("Account Name: ");
         String accNm = sc.nextLine();
         System.out.print("Account Charges: ");
         float charges = sc.nextFloat();
         System.out.print("Is Prime (true/false): ");
         boolean isPrime = sc.nextBoolean();

         account = shopFactory.getNewPrimeAccount(accNo, accNm, charges, isPrime);
     } else {
         // Take input for Normal Account
         System.out.println("Enter details for Normal Account:");
         System.out.print("Account Number: ");
         int accNo = sc.nextInt();
         sc.nextLine();  // Consume newline left-over
         System.out.print("Account Name: ");
         String accNm = sc.nextLine();
         System.out.print("Account Charges: ");
         float charges = sc.nextFloat();

         account = shopFactory.getNewNormalAccount(accNo, accNm, charges);
     }

     // Booking a product
     System.out.println("\nBooking a product for your account:");
     account.bookProduct(account.getCharges());

     // Displaying account details
     System.out.println("\nAccount Details:");
     System.out.println(account);

     sc.close();
 }
}
