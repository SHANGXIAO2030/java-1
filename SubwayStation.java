package text1;
import java.util.List;

// 地铁站点类
class SubwayStation {
    String name; // 站点名称
    List<String> lines; // 所属线路

    public SubwayStation(String name, List<String> lines) {
        this.name = name;
        this.lines = lines;
    }

    // 获取站点名称
    public String getName() {
        return name;
    }

    // 获取所属线路
    public List<String> getLines() {
        return lines;
    }
}
