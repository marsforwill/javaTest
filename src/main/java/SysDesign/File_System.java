package SysDesign;
import java.nio.file.FileSystem;
import java.util.*;

import jdk.internal.joptsimple.internal.Strings;;

// 1166. Design File System
public class File_System {

    private Map<String, Integer> pathValue;

    public File_System(){
        pathValue = new HashMap<>();
        pathValue.put(Strings.EMPTY, 0);
    }

    public boolean createPath(String path, int value){
        // judge if path valid
        int idx = path.lastIndexOf("/");
        if (idx >= 0 && pathValue.containsKey(path.substring(0, idx))){
            pathValue.put(path, value);
            return true;
        }

        // if valid save value
        return false;
    }

    public int get(String path){
        return pathValue.getOrDefault(path, -1);
    }

    public static void main(String[] args) {
        File_System fileSystem = new File_System();

        System.out.println(fileSystem.createPath("/a", 1));  // returns true
        System.out.println(fileSystem.get("/a"));  // returns 1
        System.out.println(fileSystem.createPath("/a/b", 2));  // returns true
        System.out.println(fileSystem.get("/a/b"));  // returns 2
        System.out.println(fileSystem.createPath("/c/d", 3));  // returns false because the parent path "/c" does not exist.
        System.out.println(fileSystem.get("/c"));  // returns -1 because this path does not exist.
    }

}
