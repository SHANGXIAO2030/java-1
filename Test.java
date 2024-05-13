package text1;
import java.util.Arrays;
import java.util.List;

// 测试类
public class Test {
    public static void main(String[] args) {
        // 从文件读取地铁网站点和距离信息，构建地铁系统
        SubwaySystem subwaySystem = buildSubwaySystemFromFile("subway.txt");

        // 测试功能1: 找出所有中转站
        Set<SubwayStation> transferStations = subwaySystem.findTransferStations();
        for (SubwayStation station : transferStations) {
            System.out.println(station.name + " " + station.lines);
        }

        // 测试功能2: 查找距离给定站点距离小于n的所有站点集合
        SubwayStation station = new SubwayStation("某站", Arrays.asList("1号线"));
        List<SubwayStation> nearbyStations = subwaySystem.findStationsWithinDistance(station, 3);
        for (SubwayStation nearbyStation : nearbyStations) {
            System.out.println(nearbyStation.name + " " + nearbyStation.lines);
        }
     // 测试功能3: 查找连接起点和终点的所有路径
        SubwayStation start = new SubwayStation("起点站", Arrays.asList("1号线"));
        SubwayStation end = new SubwayStation("终点站", Arrays.asList("2号线"));
        List<List<SubwayStation>> allPaths = subwaySystem.findAllPaths(start, end);
        for (List<SubwayStation> path : allPaths) {
            subwaySystem.printPath(path);
        }

        // 测试功能4: 查找最短路径
        List<SubwayStation> shortestPath = subwaySystem.findShortestPath(start, end);
        subwaySystem.printPath(shortestPath);

        // 测试功能5: 打印路径
        subwaySystem.printPath(shortestPath);

        // 测试功能6: 计算乘车费用（普通单程票）
        double fare = subwaySystem.calculateFare(shortestPath);
        System.out.println("Fare: " + fare);

        // 测试功能7: 计算使用武汉通和日票的乘客的票价
        double fareWithCard = subwaySystem.calculateFareWithCardOrPass(start, end, true, false);
        System.out.println("Fare with Wuhan card: " + fareWithCard);
        double fareWithDayPass = subwaySystem.calculateFareWithCardOrPass(start, end, false, true);
        System.out.println("Fare with day pass: " + fareWithDayPass);
    }
}
