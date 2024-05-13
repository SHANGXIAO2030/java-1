import java.util.List;
import java.util.Set;

// 地铁系统类
public class SubwaySystem {
    List<SubwayLine> lines; // 地铁线路列表

    public SubwaySystem(List<SubwayLine> lines) {
        this.lines = lines;
    }

    // 功能1: 找出所有中转站
    public Set<SubwayStation> findTransferStations() {
        // 实现找出所有中转站的逻辑
    }

    // 功能2: 查找距离给定站点距离小于n的所有站点集合
    public List<SubwayStation> findStationsWithinDistance(SubwayStation station, int n) {
        // 实现查找距离给定站点距离小于n的所有站点集合的逻辑
    }

    // 功能3: 查找连接起点和终点的所有路径
    public List<List<SubwayStation>> findAllPaths(SubwayStation start, SubwayStation end) {
        // 实现查找连接起点和终点的所有路径的逻辑
    }
    public List<List<SubwayStation>> findAllPaths(SubwayStation start, SubwayStation end) {
        // 实现查找连接起点和终点的所有路径的逻辑
    }

    // 功能4: 查找最短路径
    public List<SubwayStation> findShortestPath(SubwayStation start, SubwayStation end) {
        // 实现查找最短路径的逻辑
    }

    // 功能5: 打印路径
    public void printPath(List<SubwayStation> path) {
        // 实现打印路径的逻辑
    }

    // 功能6: 计算乘车费用（普通单程票）
    public double calculateFare(List<SubwayStation> path) {
        // 实现计算乘车费用的逻辑
    }

    // 功能7: 计算使用武汉通和日票的乘客的票价
    public double calculateFareWithCardOrPass(SubwayStation start, SubwayStation end, boolean useWuhanCard, boolean useDayPass) {
        // 实现计算使用武汉通和日票的乘客的票价的逻辑
    }
}
