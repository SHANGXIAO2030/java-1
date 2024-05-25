package text2;
public class TicketCalculator {
    // 普通单程票价格表
    private static final double[] NORMAL_PRICES = {2, 3, 4, 5, 6, 7, 8, 9, 10};
    private static final double[] NORMAL_PRICE_INTERVALS = {6, 12, 22, 32, 52, 72, 92, 112};

    // 武汉通刷卡价格折扣
    private static final double CARD_DISCOUNT = 0.9;

    // 日票价格
    private static final double DAILY_PRICE_1DAY = 18;
    private static final double DAILY_PRICE_3DAYS = 45;
    private static final double DAILY_PRICE_7DAYS = 90;

    // 根据距离计算普通单程票价格
    public static double calculateNormalTicketPrice(double distance) {
        int intervalIndex = findIntervalIndex(distance, NORMAL_PRICE_INTERVALS);

        if (intervalIndex == -1) {
            return Double.POSITIVE_INFINITY;
        }

        double price = NORMAL_PRICES[intervalIndex];
        if (intervalIndex < NORMAL_PRICE_INTERVALS.length - 1) {
            double delta = distance - NORMAL_PRICE_INTERVALS[intervalIndex];
            price += Math.ceil(delta / 20) * 1;
        }

        return price;
    }

    // 根据距离计算武汉通刷卡价格
    public static double calculateWuhanCardTicketPrice(double distance) {
        return CARD_DISCOUNT * calculateNormalTicketPrice(distance);
    }

    // 根据距离计算日票价格
    public static double calculateDailyTicketPrice(double distance) {
        if (distance <= 0) {
            return 0;
        } else if (distance <= 80) {
            return DAILY_PRICE_1DAY;
        } else if (distance <= 240) {
            return DAILY_PRICE_3DAYS;
        } else {
            return DAILY_PRICE_7DAYS;
        }
    }

    // 查找距离所在区间的下标
    private static int findIntervalIndex(double distance, double[] intervals) {
        for (int i = 0; i < intervals.length; i++) {
            if (distance <= intervals[i]) {
                return i;
            }
        }
        return -1;
    }
}