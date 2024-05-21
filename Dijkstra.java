package text2;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

public class Dijkstra {
    private Graph graph; // 地铁站点图
    private Station startStation; // 起点
    private double[] distances; // 起点到各点的距离
    private int[] parents; // 最短路径上各点的前驱节点

    public Dijkstra(Graph graph, Station startStation) {
        this.graph = graph;
        this.startStation = startStation;

        int n = graph.getAllStations().length;
        this.distances = new double[n];
        this.parents = new int[n];
        for (int i = 0; i < n; i++) {
            distances[i] = Double.POSITIVE_INFINITY;
            parents[i] = -1;
        }

        int startIndex = graph.getStationIndex(startStation);
        distances[startIndex] = 0;

        dijkstra();
    }

    // Dijkstra算法求解最短路径
    private void dijkstra() {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.comparingDouble(i -> distances[i]));
        Set<Integer> visited = new HashSet<>();

        pq.offer(graph.getStationIndex(startStation));

        while (!pq.isEmpty()) {
            int currIndex = pq.poll();
            visited.add(currIndex);

            Station currStation = graph.getAllStations()[currIndex];
            for (Station neighbor : currStation.getNeighbors().keySet()) {
                int neighborIndex = graph.getStationIndex(neighbor);
                if (!visited.contains(neighborIndex)) {
                    double distance = graph.getDistance(currStation, neighbor);
                    if (distances[currIndex] + distance < distances[neighborIndex]) {
                        distances[neighborIndex] = distances[currIndex] + distance;
                        parents[neighborIndex] = currIndex;
                        pq.offer(neighborIndex);
                    }
                }
            }
        }
    }

    // 获取起点到指定站点的最短路径
    public List<Station> getPath(Station endStation) {
        List<Station> path = new ArrayList<>();

        int endIndex = graph.getStationIndex(endStation);
        if (parents[endIndex] == -1) {
            return path;
        }

        // 从终点往回走，将路径上的站点加入列表中
        int currIndex = endIndex;
        while (currIndex != -1) {
            Station currStation = graph.getAllStations()[currIndex];
            path.add(0, currStation);
            currIndex = parents[currIndex];
        }

        return path;
    }
}