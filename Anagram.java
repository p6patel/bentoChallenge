import java.util.*;

public class Anagram {

    public static void main(String[] args) {
        String[] wordList = { "vase", "bat", "gods", "latte", "name", "apres", "spit", "joke", "ham", "dog",
        "act", "tale", "parse", "pits", "asper", "tab", "table", "mane", "late", "god", "cat", "table",
        "save", "spare"};
        printAnagrams(wordList);
    }

    private static void printAnagrams(String[] arr) {
        Map<Integer, List<String>> map = new HashMap<>();

        for (int i = 0; i < arr.length; i++) {
            // break down each word into char[] and sort
            char[] letters = arr[i].toCharArray();
            Arrays.sort(letters);

            // use this sorted array to generate hash code
            String sortedWord = new String(letters);
            int hashCode = sortedWord.hashCode();

            // if this hashCode already exists in the hash map, we've come across this sequence before
            // and need to add it to the list of anagrams
            if( map.containsKey(hashCode) ) {
                List<String> anagrams = map.get(hashCode);

                // add current word to list of anagrams
                anagrams.add(arr[i]);
                map.put(hashCode, anagrams);
            }
            // if it doesn't already exist, need to create list
            else {
                List<String> anagrams = new ArrayList<>();
                anagrams.add(arr[i]);
                map.put(hashCode, anagrams);
            }
        }

        for(Integer i : map.keySet() ) {
            // if a more than one value map to any key, means we have anagram
            if ( map.get(i).size() > 1 ) {
                System.out.println(map.get(i));
            }
        }

    }

}
