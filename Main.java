import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;
import java.util.Set;

public class Main {

    public static class Address {
        private final String country;
        private final String city;

        public Address(String country, String city) {
            this.country = country;
            this.city = city;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Address address = (Address) o;
            return Objects.equals(country.toLowerCase(), address.country.toLowerCase()) &&
                    Objects.equals(city.toLowerCase(), address.city.toLowerCase());
        }

        @Override
        public int hashCode() {
            return Objects.hash(country.toLowerCase(), city.toLowerCase());
        }
    }

    public static void main(String[] args) {
        Map<Address, Integer> costPerAddress = new HashMap<>();
        costPerAddress.put(new Address("Россия", "Москва"), 150);
        costPerAddress.put(new Address("Россия", "Казань"), 200);
        costPerAddress.put(new Address("Беларусь", "Минск"), 300);
        costPerAddress.put(new Address("Казахстан", "Алматы"), 400);

        Scanner scanner = new Scanner(System.in);
        int totalCost = 0;
        Set<String> uniqueCountries = new HashSet<>();

        while (true) {
            System.out.println("Заполнение нового заказа.");
            System.out.print("Введите страну: ");
            String country = scanner.nextLine().trim();

            if ("end".equalsIgnoreCase(country)) {
                break;
            }

            System.out.print("Введите город: ");
            String city = scanner.nextLine().trim();

            System.out.print("Введите вес (кг): ");
            int weight = Integer.parseInt(scanner.nextLine().trim());

            Address currentAddress = new Address(country, city);

            if (costPerAddress.containsKey(currentAddress)) {
                int pricePerKg = costPerAddress.get(currentAddress);
                int currentDeliveryCost = pricePerKg * weight;

                totalCost += currentDeliveryCost;
                uniqueCountries.add(country.toLowerCase());

                System.out.println("Стоимость доставки составит: " + currentDeliveryCost + " руб.");
                System.out.println("Общая стоимость всех доставок: " + totalCost + " руб.");
                System.out.println("Уникальных стран: " + uniqueCountries.size());
            } else {
                System.out.println("Доставки по этому адресу нет");
            }

            System.out.println();
        }
    }
}