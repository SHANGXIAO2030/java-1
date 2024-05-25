package text2;

import java.util.HashMap;
import java.util.Map;

public class Graph {
    private Station[] stations; // 所有站点
    private Map<Station, Integer> stationIndexMap; // 站点下标映射
    private Line[] lines; // 所有线路
    private double[][] adjacencyMatrix; // 邻接矩阵

    public Graph(Station[] stations, Line[] lines) {
        this.stations = stations;
        this.lines = lines;

        // 初始化邻接矩阵
        int n = stations.length;
        this.adjacencyMatrix = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                adjacencyMatrix[i][j] = Double.POSITIVE_INFINITY;
            }
        }

        // 初始化站点下标映射
        this.stationIndexMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            stationIndexMap.put(stations[i], i);
        }

        // 根据站点和线路信息构造邻接矩阵
        for (Station station : stations) {
            int i = stationIndexMap.get(station);
            for (Station neighbor : station.getNeighbors().keySet()) {
                int j = stationIndexMap.get(neighbor);
                adjacencyMatrix[i][j] = station.getDistance(neighbor);
            }
        }
    }

    public Station[] getAllStations() {
        return stations;
    }

    public Line[] getAllLines() {
        return lines;
    }

    // 查找指定名称的站点
    public Station findStation(String stationName) throws IllegalArgumentException {
        for (Station station : stations) {
            if (station.getName().equals(stationName)) {
                return station;
            }
        }
        throw new IllegalArgumentException("站点不存在：" + stationName);
    }

    // 获取指定站点在邻接矩阵中的下标
    public int getStationIndex(Station station) {
        return stationIndexMap.get(station);
    }

    // 获取从起点到终点的距离
    public double getDistance(Station startStation, Station endStation) {
        int i = getStationIndex(startStation);
        int j = getStationIndex(endStation);
        return adjacencyMatrix[i][j];
    }
}