package pl.edu.agh.student.utils.common;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class Utils {

    // x,y;x1,y1
    public static List<MyPoint> pointsFromString(String pointsString) {
        List<MyPoint> result = new LinkedList<MyPoint>();

        String[] singlePointStrings = pointsString.split(";");
        for (String singlePointString : singlePointStrings) {
            String[] coords = singlePointString.trim().split(",");
            result
                .add(new MyPoint(new BigDecimal(coords[0].trim()),
                    new BigDecimal(coords[1].trim())));
        }

        return result;
    }

    public static List<MyPoint> pointsToRightOf(List<MyPoint> points, int index) {
        return points.subList(index + 1, points.size());
    }

    public static List<MyPoint> pointsToLeftOf(List<MyPoint> points, int index) {
        return points.subList(0, index);
    }

    public static int getMedianIndex(List<MyPoint> points, boolean byXValue) {
        int n = points.size();

        if (n == 1) {
            return 0;
        }

        BigDecimal median;
        if (byXValue) {
            median = n % 2 == 0 ? points.get(n / 2).getX().add(points.get(n / 2 - 1).getX()).divide(
                BigDecimal.valueOf(2), BigDecimal.ROUND_DOWN) : points.get((n - 1) / 2).getX();
        } else {
            median = n % 2 == 0 ? points.get(n / 2).getY().add(points.get(n / 2 - 1).getY()).divide(
                BigDecimal.valueOf(2), BigDecimal.ROUND_DOWN) : points.get((n - 1) / 2).getY();
        }

        for (int i = 0; i < n; i++) {

            if ((byXValue && points.get(i).getX().compareTo(median) > 0) || (!byXValue
                && points.get(i).getY().compareTo(median) > 0)) {
                return i - 1;
            }
        }

        return n - 1;
    }

    public static void sortByX(List<MyPoint> points) {
        Collections.sort(points, new XComparator());
    }

    public static void sortByY(List<MyPoint> points) {
        Collections.sort(points, new YComparator());
    }

    public static List<MyPoint> getPointsByXValue(List<MyPoint> points, MyPoint point) {
        List<MyPoint> result = new LinkedList<MyPoint>();
        BigDecimal x = point.getX();

        for (MyPoint p : points) {
            if (p.getX().equals(point.getX())) {
                result.add(p);
            }
        }

        return result;
    }

    static class XComparator implements Comparator<MyPoint> {
        @Override public int compare(MyPoint o1, MyPoint o2) {
            int xResult = o1.getX().compareTo(o2.getX());
            if (xResult == 0) {
                return o1.getY().compareTo(o2.getY());
            }
            return xResult;
        }
        /*
        @Override
        public int compare(MyPoint o1, MyPoint o2) {
            return o1.getX().compareTo(o2.getX());
        }
        */
    }


    static class YComparator implements Comparator<MyPoint> {
        @Override public int compare(MyPoint o1, MyPoint o2) {
            int yResult = o1.getY().compareTo(o2.getY());
            if (yResult == 0) {
                return o1.getY().compareTo(o2.getX());
            }
            return yResult;
        }
        /*
        @Override
        public int compare(MyPoint o1, MyPoint o2) {
            return o1.getY().compareTo(o2.getY());
        }
        */
    }

}
