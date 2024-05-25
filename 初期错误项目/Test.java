package text2;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Test {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SubwaySystem subwaySystem = new SubwaySystem("D:\subway.txt");

        while (true) {
            System.out.println("请输入数字选择要测试的功能（输入-1退出）：");
            System.out.println("1. 查询所有中转站");
            System.out.println("2. 查询距离指定站点小于等于n的所有站点");
            System.out.println("3. 查询两个站点之间的所有路径");
            System.out.println("4. 查询两个站点之间的最短路径");
            System.out.println("5. 打印路径");
            System.out.println("6. 计算普通单程票票价");
            System.out.println("7. 计算武汉通刷卡票价");
            System.out.println("8. 计算日票票价");

            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == -1) {
                break;
            }

            switch (choice) {
                case 1:
                    Set<Station> transferStations = subwaySystem.findTransferStations();
                    for (Station station : transferStations) {
                        System.out.println(station.getName() + ": " + station.getLines());
                    }
                    break;

                case 2:
                    System.out.print("请输入站点名称：");
                    String stationName = scanner.nextLine();
                    System.out.print("请输入距离n：");
                    int n = scanner.nextInt();
                    scanner.nextLine();

                    try {
                        Set<Station> nearbyStations = subwaySystem.findNearbyStations(stationName, n);
                        for (Station station : nearbyStations) {
                            System.out.println(station.getName() + ": " + station.getLines() + ", " +
                                    "距离" + stationName + "站" + (n - 1) + "站");
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 3:
                    System.out.print("请输入起点站名称：");
                    String startStationName = scanner.nextLine();
                    System.out.print("请输入终点站名称：");
                    String endStationName = scanner.nextLine();

                    try {
                        List<List<Station>> allPaths = subwaySystem.findAllPaths(startStationName, endStationName);
                        for (List<Station> path : allPaths) {
                            System.out.println(path);
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 4:
                    System.out.print("请输入起点站名称：");
                    String startStationName2 = scanner.nextLine();
                    System.out.print("请输入终点站名称：");
                    String endStationName2 = scanner.nextLine();

                    try {
                        List<Station> shortestPath = subwaySystem.findShortestPath(startStationName2, endStationName2);
                        System.out.println(shortestPath);
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 5:
                    System.out.print("请输入起点站名称：");
                    String startStationName3 = scanner.nextLine();
                    System.out.print("请输入终点站名称：");
                    String endStationName3 = scanner.nextLine();

                    try {
                        List<Station> shortestPath2 = subwaySystem.findShortestPath(startStationName3, endStationName3);
                        SubwaySystem.printPath(shortestPath2);
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 6:
                    System.out.print("请输入起点站名称：");
                    String startStationName4 = scanner.nextLine();
                    System.out.print("请输入终点站名称：");
                    String endStationName4 = scanner.nextLine();

                    try {
                        List<Station> path = subwaySystem.findShortestPath(startStationName4, endStationName4);
                        double price = subwaySystem.calculateNormalTicketPrice(path);
                        System.out.println("普通单程票价格：" + price);
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 7:
                    System.out.print("请输入起点站名称：");
                    String startStationName5 = scanner.nextLine();
                    System.out.print("请输入终点站名称：");
                    String endStationName5 = scanner.nextLine();

                    try {
                        List<Station> path = subwaySystem.findShortestPath(startStationName5, endStationName5);
                        double price = subwaySystem.calculateWuhanCardTicketPrice(path);
                        System.out.println("武汉通刷卡价格：" + price);
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 8:
                    System.out.print("请输入起点站名称：");
                    String startStationName6 = scanner.nextLine();
                    System.out.print("请输入终点站名称：");
                    String endStationName6 = scanner.nextLine();

                    try {
                        List<Station> path = subwaySystem.findShortestPath(startStationName6, endStationName6);
                        double price = subwaySystem.calculateDailyTicketPrice(path);
                        System.out.println("日票价格：" + price);
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                default:
                    System.out.println("无效的数字，请输入1~8之间的数字或-1退出。");
                    break;
            }
        }

        scanner.close();
    }
}