import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class ScanLine {

    static class Interval {
        public int start;
        public int end;
        public Interval(int _start, int _end) {
            start = _start;
            end = _end;
        }
    }

    // 扫描线！！！！ 这就是hulu面试被坑的那道题！！！！！！
    public static List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
        List<int[]> event = new ArrayList<>();
        for (List<Interval> li : schedule) {
            for (Interval interval : li) {
                event.add(new int[]{interval.start, 1});
                event.add(new int[]{interval.end, 0});
            }
        }
        Collections.sort(event, (a,b) -> a[0] != b[0] ? a[0] - b[0] : a[1] - b[1]);
        List<Interval> ans = new ArrayList<>();
        int line = 0;
        int pre = -1;
        for (int[] e : event) {
            if (line==0 && pre > 0 && pre != e[0]) {
                ans.add(new Interval(pre, e[0]));
            }
            if (e[1] == 1) {
                line--;
            }
            if (e[1] == 0) {
                line++;
            }
            pre = e[0];
        }
        return ans;
    }

    public static void main(String[] args) {
//        System.out.printf(employeeFreeTime(new ArrayList<>(){}));
    }
}
