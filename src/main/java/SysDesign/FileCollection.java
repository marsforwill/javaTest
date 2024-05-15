package SysDesign;

import java.util.*;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

// You will be given a list of file names, the collection the file belongs to and the size of each file. 
// Write a program to find the top N collections by size and the total size of all the files in the system.
//  Follow up - collections can be nested, find top N collections by size.
public class FileCollection {

    public FileCollection(){
        collectionToFileMap = new HashMap<>();
        collectionSize = new HashMap<>();
        parentToSon = new HashMap<>();
    }

    public void addFileToCollection(String colle, FileItem file){
        collectionToFileMap.putIfAbsent(colle, new ArrayList<>());
        collectionToFileMap.get(colle).add(file);
    }

    public Map<String, List<FileItem>> collectionToFileMap;
    public Map<String, Integer> collectionSize;
    public Map<String, String> parentToSon;

    // top N collections
    public List<Pair<String, Integer>> getTopCollection(int topN){
        PriorityQueue<Pair<String, Integer>> pq = new PriorityQueue<>((a, b) -> Integer.compare(a.getValue(), b.getValue()));
        for (String key : collectionToFileMap.keySet()) {
            int colSize = 0;
            // count col size
            List<FileItem> files = collectionToFileMap.get(key);
            for (FileItem file : files) {
                colSize += file.size;
            }

            collectionSize.put(key, colSize);
        }
        //  add self and son collection size
        for (String key : collectionSize.keySet()) {
            int self = collectionSize.get(key);
            String col = key;
            while(parentToSon.containsKey(col)){
                String son = parentToSon.get(col);
                self += collectionSize.get(son);
                col = son;
            }

            pq.offer(new ImmutablePair<String,Integer>(key, self));
            while (pq.size() > topN) {
                pq.poll();
            }
        }

        List<Pair<String, Integer>> ans = new ArrayList<>();  
        while (pq.size() > 0) {
            ans.add(pq.poll());
        }      
        Collections.reverse(ans);
        return ans;
    }

    public int getTotalSize(){
        int totalSize = 0;
        for (String key : collectionToFileMap.keySet()) {
            List<FileItem> files = collectionToFileMap.get(key);
            for (FileItem file : files) {
                totalSize += file.size;
            }
        }
        return totalSize;
    }

    public void nest(String parent, String son){
        parentToSon.put(parent, son);
    }

    public static void main(String[] args) {
        FileItem file1 = new FileItem("file1.txt", 100);
        FileItem file2 = new FileItem("file2.jpg", 200);
        FileItem file3 = new FileItem("file3.mp4", 300);
        FileItem file4 = new FileItem("file4.pdf", 150);
        FileItem file5 = new FileItem("file5.doc", 400);

        FileCollection collectionSystem = new FileCollection();
        collectionSystem.addFileToCollection("documents", file1);
        collectionSystem.addFileToCollection("documents", file4);
        collectionSystem.addFileToCollection("images", file2);
        collectionSystem.addFileToCollection("videos", file3);
        collectionSystem.addFileToCollection("office", file5);

        System.out.println(collectionSystem.getTotalSize());
        System.out.println(collectionSystem.getTopCollection(3));;
        collectionSystem.nest("documents", "office");
        collectionSystem.nest("images", "documents");
        System.out.println(collectionSystem.getTopCollection(3));;
    }

}

