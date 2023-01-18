import java.util.*;

public class Solution {
    public static void main(String[] args) {
        Iterator value = anagram("ANAGRAM").iterator();
        int words = 0;
        while (value.hasNext()) {
            System.out.print(value.next() + " ");
            words++;
            if(words == 10){
                words = 0;
                System.out.println();
            }
        }
    }

    public static Set<String> anagram(String s) {
        if (s.length() < 1) {
            return null;
        }


        Set<String> words = new HashSet<>();
        ArrayList<Integer> randomNumbers = new ArrayList();
        char[] word = new char[s.length()];
        Random r = new Random();

        // Case 1111, aaaaa, bbbbb
        String copy = s;
        if(copy.replace(Character.toString(s.charAt(0)),"").length() == 0){
            words.add(s);
            return words;
        }

        int repeatedChars = repeatedChars(s);
        long combinations;
        if(repeatedChars == s.length() - 1){
            combinations = s.length();
        }else if(repeatedChars == s.length()){
            combinations = factorial(s.length()) / repeatedChars;
        }else {
            combinations = factorial(s.length()) / factorial(repeatedChars);
        }

        while (words.size() <  combinations){
            int randomIndex, index = 0;
            for (int i = 0; i < s.length(); i++) {
                //randomIndex = (int) (Math.random() * ((s.length() - 1) - 0) + 0);
                randomIndex = r.nextInt(s.length());
                if (randomNumbers.contains(randomIndex)) {
                    i--;
                } else {
                    word[randomIndex] = s.charAt(index++);
                    randomNumbers.add(randomIndex);
                }
            }
            randomNumbers.clear();
            words.add(new String(word));

        }

        return words;
    }
    private static long factorial(long number) {
        long factorial = 1;
        for (long i = 1; i <= number; ++i) {
            factorial *= i;
        }
        return factorial;
    }

    private static int repeatedChars(String s) {
        int repeated = 0;
        HashMap<Character, Integer> charCountMap = new HashMap<>();

        char[] strArray = s.toCharArray();

        for (char c : strArray) {
            if (charCountMap.containsKey(c)) {
                charCountMap.put(c, charCountMap.get(c) + 1);
            }
            else {
                charCountMap.put(c, 1);
            }
        }

        for (Map.Entry entry : charCountMap.entrySet()) {
            String value = entry.getValue().toString();
            int valueInt = Integer.parseInt(value);
            if(valueInt > 1){
                repeated += valueInt;
            }
        }

        return repeated;
    }

}
