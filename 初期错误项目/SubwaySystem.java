package text2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class SubwaySystem {
    private Graph graph; // 地铁站点图

    // 构造函数，读取subway.txt文件中的站点间距离信息，并初始化站点图
    public SubwaySystem(String filePath) {
        Map<String, Station> stations = new HashMap<>();
        List<Line> lines = new ArrayList<>();

        // 读取文件
        List<String> linesText = InputReader.readLines(filePath);

        // 解析文件，构造站点和线路对象
        for (String lineText : linesText) {
            String[] parts = lineText.split("\t");

            String stationName1 = parts[0];
            String stationName2 = parts[1];
            double distance = Double.parseDouble(parts[2]);

            Station station1 = stations.computeIfAbsent(stationName1, name -> new Station(name));
            Station station2 = stations.computeIfAbsent(stationName2, name -> new Station(name));

            Line line = new Line(parts[3]);
            if (!lines.contains(line)) {
                lines.add(line);
            }

            station1.addLine(line);
            station2.addLine(line);

            station1.addNeighbor(station2, distance);
            station2.addNeighbor(station1, distance);
        }

        // 构造站点图
        Station[] stationsArray = new Station[stations.size()];
        int i = 0;
        for (Station station : stations.values()) {
            stationsArray[i++] = station;
        }
        this.graph = new Graph(stationsArray, lines.toArray(new Line[0]));
    }

    // 查询所有地铁中转站
    public Set<Station> findTransferStations() {
        Set<Station> transferStations = new HashSet<>();
        for (Station station : graph.getAllStations()) {
            if (station.getTransferLines().size() > 1) {
                transferStations.add(station);
            }
        }
        return transferStations;
    }

    // 查询距离指定站点小于等于n的所有站点
    public Set<Station> findNearbyStations(String stationName, int n) throws IllegalArgumentException {
        Station startStation = graph.findStation(stationName);
        Set<Station> result = new HashSet<>();
        Queue<Station> queue = new LinkedList<>();
        Set<Station> visited = new HashSet<>();

        queue.offer(startStation);
        visited.add(startStation);

        // 广度优先搜索
        while (!queue.isEmpty() && n >= 0) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Station station = queue.poll();
                result.add(station);
                for (Station neighbor : station.getNeighbors().keySet()) {
                    if (!visited.contains(neighbor)) {
                        queue.offer(neighbor);
                        visited.add(neighbor);
                    }
                }
            }
            n--;
        }

        return result;
    }

    // 查询两个站点之间的所有路径
    public List<List<Station>> findAllPaths(String startStationName, String endStationName) throws IllegalArgumentException {
        Station startStation = graph.findStation(startStationName);
        Station endStation = graph.findStation(endStationName);

        List<List<Station>> result = new ArrayList<>();
        List<Station> path = new ArrayList<>();
        Set<Station> visited = new HashSet<>();

        dfsFindAllPaths(startStation, endStation, path, visited, result);

        return result;
    }

    // 深度优先搜索所有路径
    private void dfsFindAllPaths(Station currStation, Station endStation, List<Station> path,
                                 Set<Station> visited, List<List<Station>> result) {
        path.add(currStation);
        visited.add(currStation);

        if (currStation.equals(endStation)) {
            result.add(new ArrayList<>(path));
        } else {
            for (Station neighbor : currStation.getNeighbors().keySet()) {
                if (!visited.contains(neighbor)) {
                    dfsFindAllPaths(neighbor, endStation, path, visited, result);
                }
            }
        }

        path.remove(path.size() - 1);
        visited.remove(currStation);
    }

    // 查找起点到终点的最短路径
    public List<Station> findShortestPath(String startStationName, String endStationName) throws IllegalArgumentException {
        Station startStation = graph.findStation(startStationName);
        Station endStation = graph.findStation(endStationName);

        Dijkstra dijkstra = new Dijkstra(graph, startStation);
        List<Station> shortestPath = dijkstra.getPath(endStation);

        return shortestPath;
    }

    // 打印路径
    public static void printPath(List<Station> path) {
        System.out.print("乘坐地铁从 " + path.get(0).getName());
        for (int i = 1; i < path.size(); i++) {
            Station currStation = path.get(i);
            Station prevStation = path.get(i - 1);

            if (!currStation.isOnSameLine(prevStation)) {
                System.out.print("，在" + prevStation.getName() + "换乘" + prevStation.getCommonLine(currStation).getName() + "号线");
            }

            System.out.print("，到达" + currStation.getName());
        }
        System.out.println("。");
    }

    // 计算普通单程票票价
    public double calculateNormalTicketPrice(List<Station> path) {
        double distance = calculateDistance(path);
        return TicketCalculator.calculateNormalTicketPrice(distance);
    }

    // 计算武汉通刷卡票价
    public double calculateWuhanCardTicketPrice(List<Station> path) {
        double distance = calculateDistance(path);
        return TicketCalculator.calculateWuhanCardTicketPrice(distance);
    }

    // 计算日票票价
    public double calculateDailyTicketPrice(List<Station> path) {
        double distance = calculateDistance(path);
        return TicketCalculator.calculateDailyTicketPrice(distance);
    }

    // 计算路径距离
    private double calculateDistance(List<Station> path) {
        double distance = 0;
        for (int i = 1; i < path.size(); i++) {
            Station currStation = path.get(i);
            Station prevStation = path.get(i - 1);

            distance += currStation.getDistance(prevStation);
        }
        return distance;
    }
}