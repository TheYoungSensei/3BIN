import java.util.HashMap;
import java.util.Map;

class TourMap {

    private static java.util.Scanner scanner = new java.util.Scanner(System.in);

    public static void main(String args[]) {

        int nbTravels = scanner.nextInt();
        for(int i = 0; i < nbTravels; i++) {
            int nbFly = scanner.nextInt();
            Map<String, String> departureArrival = new HashMap<>();
            Map<String, String> arrivalDeparture = new HashMap<>();
            Map<Fly, String> prices = new HashMap<>();
            String any = null;
            for(int j = 0; j < nbFly - 1; j++) {
                String departureCity = scanner.next();
                String arrivalCity = scanner.next();
                String price = scanner.next();
                Fly fly = new TourMap().new Fly(departureCity, arrivalCity);
                departureArrival.put(departureCity, arrivalCity);
                arrivalDeparture.put(arrivalCity, departureCity);
                prices.put(fly, price);
                any = departureCity;
            }
            String firstDepart = any;
            while(true) {
                if(!arrivalDeparture.containsKey(firstDepart))
                    break;
                firstDepart = arrivalDeparture.get(firstDepart);
            }
            Fly fly = new TourMap().new Fly(firstDepart, departureArrival.get(firstDepart));
            int total = 0;
            while(true) {
                String price = prices.get(new TourMap().new Fly(fly.departureCity, fly.arrivalCity));
                System.out.println(fly.departureCity + " " + fly.arrivalCity + " "+ price);
                total += Integer.parseInt(price.substring(0, price.length() - 1));
                if(!departureArrival.containsKey(fly.arrivalCity))
                    break;
                fly = new TourMap().new Fly(fly.arrivalCity, departureArrival.get(fly.arrivalCity));
            }

            System.out.println(total + "$");
        }


    }

    private class Fly {
        private String departureCity;
        private String arrivalCity;

        private Fly(String departureCity, String arrivalCity) {
            this.departureCity = departureCity;
            this.arrivalCity = arrivalCity;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Fly fly = (Fly) o;
            return departureCity.equals(fly.departureCity) && arrivalCity.equals(fly.arrivalCity);
        }

        @Override
        public int hashCode() {
            int result = departureCity.hashCode();
            result = 31 * result + arrivalCity.hashCode();
            return result;
        }
    }
}
