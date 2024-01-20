import java.util.*;

public class ArrayListFunc {
    // new ArrayList<>() / .add / .get

    public static List<Integer> matchingStrings(List<String> stringList, List<String> queries) {
        // Write your code here
        List<Integer> ans = new ArrayList<>();
        Map<String, Integer> strmap = new HashMap<>();
        for (String s : stringList) {
            strmap.put(s, strmap.getOrDefault(s, 0) + 1);
        }
        for (String s : queries) {
            ans.add(strmap.getOrDefault(s, 0));
        }
        return ans;
    }

    public static void main(String[] args) {

    }
}
