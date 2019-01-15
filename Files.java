import java.io.File;
import java.util.*;

public class Files {

    // to display all contents in sorted order, need to keep
    // track of every file we come across and then iterate thru
    // use ArrayList to keep track of all filePaths that have the same size
    private static Map<Long, ArrayList<String>> map = new HashMap<>();

    public static void main(String[] args) {
        // grab parameter
        String path = args[0];
        traverseDirectories(path);
        printPathsAndSizes();

    }

    public static void traverseDirectories(String path) {
        File start = new File(path);
        File[] list = start.listFiles();

        for (File file : list) {
            // if we come across a directory, recursively scan subdirectories
            if (file.isDirectory()) {
                traverseDirectories(file.getAbsolutePath());
            }
            else {
                long size = file.length();
                String filePath = file.getAbsolutePath();
                // if we already have a value, means we already have a file with the same size as the current
                if ( map.get(size) != null ) {
                    ArrayList<String> current = map.get(size);
                    current.add(filePath);
                    map.put(size, current);
                }
                else {
                    ArrayList<String> arr = new ArrayList<>();
                    arr.add(filePath);
                    map.put(size, arr);
                }

            }
        }
    }

    public static void printPathsAndSizes() {
        List<Long> keys = new ArrayList<>(map.keySet());

        // sort keys by descending order
        Collections.sort(keys, Collections.reverseOrder());

        for(long size : keys) {
            // now that keys are sorted, can iterate thru the list to get all file paths in order of size
            ArrayList<String> filePaths = map.get(size);
            for(String p : filePaths) {
                // print path and file size
                System.out.println("PATH: " + p + ", SIZE: " + size);
            }
        }

    }
}
