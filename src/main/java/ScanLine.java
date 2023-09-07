import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;

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
                event.add(new int[] { interval.start, 1 });
                event.add(new int[] { interval.end, 0 });
            }
        }
        Collections.sort(event, (a, b) -> a[0] != b[0] ? a[0] - b[0] : a[1] - b[1]);
        List<Interval> ans = new ArrayList<>();
        int line = 0;
        int pre = -1;
        for (int[] e : event) {
            if (line == 0 && pre > 0 && pre != e[0]) {
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

    // 双指针：80. 删除有序数组中的重复项 II
    public static int removeDuplicates(int[] nums) {
        int length = nums.length;
        if (length <= 2) {
            return length;
        }
        // two point, low is the answer, and the fast is what we compare to low
        int slow = 2;
        int fast = 2;

        while (fast < length) {
            // 判断当前数是否保留 是看与当前数的前面的第k==2个元素比较
            if (nums[slow - 2] != nums[fast]) {
                nums[slow] = nums[fast];
                ++slow;
            }
            ++fast;
        }
        ;
        return slow;
    }

    //253. 会议室 II 扫描线 题解是：按照开始时间排序 遍历处理中根据时间遍历节点画扫描线
    // [[0, 30],[5, 10],[15, 20]]
    // Output: 2
    public static int minMeetingRooms(List<Interval> internal){
        internal.sort((a, b) -> a.start - b.start);
        HashSet<Integer> s = new HashSet<Integer>();
        HashMap<Integer, Integer> start = new HashMap<Integer, Integer>();
        HashMap<Integer, Integer> end = new HashMap<Integer, Integer>();
        for (Interval item : internal) {
            s.add(item.start);
            s.add(item.end);
            if (start.containsKey(item.start)){
                Integer value = start.get(item.start);
                start.put(item.start, value+1);
            } else {
                start.put(item.start, 1);
            }
            if (end.containsKey(item.end)){
                Integer value = end.get(item.end);
                end.put(item.end, value+1);
            } else {
              end.put(item.end, 1);
            }
        }
        ArrayList<Integer> l = new ArrayList<Integer>(s);
        Collections.sort(l);
        int ans = 0; int max = 0;
        for (Integer i : l) {
            if (start.containsKey(i)){
                ans += start.get(i);
            }
            if (end.containsKey(i)){
                ans -= end.get(i);
            }
            max = max > ans ? max : ans;
        }
        return max;
    }

    public static void main(String[] args) {
//        System.out.printf(employeeFreeTime(new ArrayList<>(){}));
        List<ScanLine.Interval> test = new ArrayList<ScanLine.Interval>();
        test.add(new Interval(0, 30));
        test.add(new Interval(5, 10));
        test.add(new Interval(15, 20));
        System.out.println(minMeetingRooms(test));
    }
}
