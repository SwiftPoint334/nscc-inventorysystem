import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ArrayList<String> itemNames = new ArrayList<>();
        ArrayList<Double> itemPrices = new ArrayList<>();
        ArrayList<Integer> itemQuantities = new ArrayList<>();

        System.out.println("Welcome to the inventory management system.");

        while (true) {
            System.out.println("Select an option:");
            System.out.println("1 - Add a new item");
            System.out.println("2 - Show all items");
            System.out.println("3 - Search an item by name");
            System.out.println("4 - Update the price of an item");
            System.out.println("5 - Update the quantity of an item");
            System.out.println("6 - Exit the program");

            int choice = 0;

            try {
                choice = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                scanner.nextLine();
                System.out.println("Enter an option from above");
            }

            if (choice == 1) {
                newItem(scanner, itemNames, itemPrices, itemQuantities);
            } else if (choice == 2) {
                displayItems(scanner, itemNames, itemPrices, itemQuantities);
            } else if (choice == 3) {
                searchItems(scanner, itemNames, itemPrices, itemQuantities);
            } else if (choice == 4) {
                updatePrice(scanner, itemNames, itemPrices, itemQuantities);
            } else if (choice == 5) {
                updateQuantity(scanner, itemNames, itemPrices, itemQuantities);
            } else if (choice == 6) {
                break;
            } else {
                System.out.println("Enter a valid option");
            }
        }
    }
    public static double getPositiveDouble(Scanner scanner) {
        while (true) {
            try {
                double value = scanner.nextDouble();
                scanner.nextLine();
                if (value > 0) {
                    return value;
                } else {
                    System.out.println("Value must be positive");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input, must be a number.");
                scanner.nextLine();
            }
        }
    }

    public static Integer getPositiveInteger(Scanner scanner) {
        while (true) {
            try {
                Integer value = scanner.nextInt();
                scanner.nextLine();
                if (value > 0) {
                    return value;
                } else {
                    System.out.println("Value must be positive.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input, must be a number.");
            }
        }
    }

    public static void newItem(Scanner scanner, ArrayList<String> itemNames, ArrayList<Double> itemPrices, ArrayList<Integer> itemQuantities) {
        System.out.println("Enter name of new item:");
        String name = scanner.nextLine();

        if (itemNames.contains(name)) {
            System.out.println("Item already exists");
            return;
        }

        System.out.println("Enter item price:");
        double price = getPositiveDouble(scanner);

        System.out.println("Enter quantity of " + name);
        Integer quantity = getPositiveInteger(scanner);

        itemNames.add(name);
        itemPrices.add(price);
        itemQuantities.add(quantity);

        System.out.println("Item added successfully.");

        return;
    }

    public static void displayItems(Scanner scanner, ArrayList<String> itemNames, ArrayList<Double> itemPrices, ArrayList<Integer> itemQuantities) {
        if (itemNames.isEmpty()) {
            System.out.println("No items in inventory");
            return;
        }

        System.out.println("All items:");
        for (int i = 0; i < itemNames.size(); i++) {
            System.out.println("Name: " + itemNames.get(i) + " Price: " + itemPrices.get(i) + " Quantity: " + itemQuantities.get(i));
        }

        return;
    }

    public static void searchItems(Scanner scanner, ArrayList<String> itemNames, ArrayList<Double> itemPrices, ArrayList<Integer> itemQuantities) {
        System.out.println("Enter name to search:");
        String query = scanner.nextLine();

        if (itemNames.contains(query)) {
            int index = itemNames.indexOf(query);
            System.out.println("Name: " + itemNames.get(index) + " Price: " + itemPrices.get(index) + " Quantity: " + itemQuantities.get(index));
        } else {
            System.out.println("No item found");
        }

        return;
    }

    public static void updatePrice(Scanner scanner, ArrayList<String> itemNames, ArrayList<Double> itemPrices, ArrayList<Integer> itemQuantities) {
        System.out.println("Enter name of item to update:");
        String query = scanner.nextLine();

        if (itemNames.contains(query)) {
            int index = itemNames.indexOf(query);
            System.out.println("Enter new price: ");
            double newPrice = getPositiveDouble(scanner);
            itemPrices.set(index, newPrice);
            System.out.println("Price updated.");
        } else {
            System.out.println("No item found");
        }
    }

    public static void updateQuantity(Scanner scanner, ArrayList<String> itemNames, ArrayList<Double> itemPrices, ArrayList<Integer> itemQuantities) {
        System.out.println("Enter name of item to update:");
        String query = scanner.nextLine();

        if (itemNames.contains(query)) {
            int index = itemNames.indexOf(query);
            System.out.println("Enter new quantity:");
            Integer newQuantity = getPositiveInteger(scanner);
            if (newQuantity == 0) {
                itemNames.remove(index);
                itemPrices.remove(index);
                itemQuantities.remove(index);
                System.out.println("Item removed");
            } else {
                itemQuantities.set(index, newQuantity);
            }
        } else {
            System.out.println("No item found");
        }
    }
}