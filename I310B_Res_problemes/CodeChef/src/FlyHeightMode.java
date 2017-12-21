import java.util.Arrays;

class FlyHeightMode {
    private static java.util.Scanner scanner = new java.util.Scanner(System.in);

    private static int HEIGHT = 0;
    private static int COLOR = 1;

    public static void main(String[] args) {
        int n = scanner.nextInt();
        int[][] points = new int[2 * n - 2][2];
        int precHeight = scanner.nextInt();
        int RED = 1;
        initialisation(points, precHeight, RED);
        sorting(points);
        System.out.println(finishHim(points, RED));
    }

    private static int finishHim(int[][] points, int RED) {
        int cpt = 0;
        int max = 1;
        for (int point[] : points) {
            if (point[COLOR] == RED) {
                cpt++;
            } else {
                cpt--;
            }
            if (cpt > max)
                max = cpt;
        }
        return max;
    }

    private static void sorting(int[][] points) {
        Arrays.sort(points, (point1, point2) -> {
            if (point1[HEIGHT] == point2[HEIGHT]) {
                return point1[COLOR] - point2[COLOR];
            } else {
                return point1[HEIGHT] - point2[HEIGHT];
            }
        });
    }

    private static void initialisation(int[][] points, int precHeight, int RED) {
        for (int i = 0; i < points.length; i = i + 2) {
            int currentheight = scanner.nextInt();
            int GREEN = 0;
            if (precHeight < currentheight) {
                points[i][HEIGHT] = precHeight;
                points[i][COLOR] = RED;
                points[i + 1][HEIGHT] = currentheight;
                points[i + 1][COLOR] = GREEN;
            } else {
                points[i][HEIGHT] = precHeight;
                points[i][COLOR] = GREEN;
                points[i + 1][HEIGHT] = currentheight;
                points[i + 1][COLOR] = RED;
            }

            precHeight = currentheight;
        }
    }
}