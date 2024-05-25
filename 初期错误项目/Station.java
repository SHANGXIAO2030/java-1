package text2;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Station {
    private String name; // 站点名称
    private Map<Station, Double> neighbors; // 相邻站点及其距离
    private Set<Line> lines; // 经过该站点的所有线路

    public Station(String name) {
        this.name = name;
        this.neighbors = new HashMap<>();
        this.lines = new HashSet<>();
    }

    public String getName() {
        return name;
    }

    public Map<Station, Double> getNeighbors() {
        return neighbors;
    }

    // 添加一个邻居站点，距离为distance
    public void addNeighbor(Station neighbor, double distance) {
        neighbors.put(neighbor, distance);
    }

    // 判断该站点是否在和另一个站点处于同一条线路上
    public boolean isOnSameLine(Station other) {
        for (Line line : lines) {
            if (other.getLines().contains(line)) {
                return true;
            }
        }
        return false;
    }

    // 获取和另一个站点在同一条线路上的线路对象
    public Line getCommonLine(Station other) {
        for (Line line : lines) {
            if (other.getLines().contains(line)) {
                return line;
            }
        }
        return null;
    }

    public Set<Line> getLines() {
        return lines;
    }

    // 添加经过该站点的线路
    public void addLine(Line line) {
        lines.add(line);
    }

    // 获取该站点和邻居站点之间的距离
    public double getDistance(Station neighbor) {
        return neighbors.get(neighbor);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Station other = (Station) obj;
        return this.name.equals(other.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {
        return name;
    }
}