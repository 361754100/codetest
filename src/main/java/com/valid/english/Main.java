package com.valid.english;

import java.util.*;

/**
 * author: Jianzhang Mo
 * date: 2020-03-09
 * desc: code test
 */

public class Main {

    public static void main(String args[]) {

        String dictionary[] = {"i", "like", "sam", "sung", "samsung", "mobile", "icecream", "and", "man go", "mango"};

        String input = "ilikesamsungmobile";
        System.out.println("input = "+ input + ", dictionary = "+ Arrays.asList(dictionary));
        analysisSentence(input, dictionary);
        System.out.println();

        input = "ilikeicecreamandmango";
        System.out.println("input = "+ input + ", dictionary = "+ Arrays.asList(dictionary));
        analysisSentence(input, dictionary);
        System.out.println();

        input = "ilikesamsungmobileicecreamandmango";
        System.out.println("input = "+ input + ", dictionary = "+ Arrays.asList(dictionary));
        analysisSentence(input, dictionary);
        System.out.println();
    }
    
    public static void analysisSentence(String input, String dict[]) {
        List<Integer> indexs = new ArrayList<Integer>();
        Map<Integer, List<String>> wordMap = new HashMap<Integer, List<String>>();

        for(int i = 0; i < dict.length; i++) {
            Integer index = input.indexOf(dict[i].replaceAll(" ", ""));
            List<String> words = wordMap.get(index);
            if(words == null) {
                words = new ArrayList<String>();
                words.add(dict[i]);

                wordMap.put(index, words);
            } else {
                words.add(dict[i]);
            }
            if(index.intValue() != -1 && !indexs.contains(index)) {
                indexs.add(index);
            }
        }

        Integer tmpIndexs[] = new Integer[indexs.size()];
        indexs.toArray(tmpIndexs);
        for(int i=0; i< tmpIndexs.length-1; i++){
            for(int j=0; j< tmpIndexs.length-i-1; j++){
                if(tmpIndexs[j].intValue() > tmpIndexs[j+1].intValue()){
                    int num = tmpIndexs[j+1].intValue();
                    tmpIndexs[j+1] = tmpIndexs[j].intValue();
                    tmpIndexs[j] = num;
                }
            }
        }
        List<Integer> wordIndexs = Arrays.asList(tmpIndexs);

        int numOfSentence = 0;
        for(Map.Entry<Integer, List<String>> entry: wordMap.entrySet()) {
            int tmpSize = entry.getValue().size();
            if(tmpSize > numOfSentence) {
                numOfSentence = tmpSize;
            }
        }

        Set<String> outputSet = new HashSet<String>();
        String regex = "[a-zA-Z]";
        for(int i = 0; i < numOfSentence-1; i++) {
            String tmpInput = new String(input);
            StringBuffer strBuffer = new StringBuffer();
            for(int j = 0; j < wordIndexs.size(); j++) {
                Integer index = wordIndexs.get(j);
                List<String> words = wordMap.get(index);
                if(words == null) {
                    continue;
                }
                String word = "";
                if(words.size() == 1) {
                    word = words.get(0);
                }else if(i < words.size()){
                    word = words.get(i);
                }

                if(!"".equals(word)) {
                    if(index.intValue() > 1 && tmpInput.charAt(index.intValue()-1) != '0') {
                        strBuffer = new StringBuffer();
                        break;
                    }
                    String tmpWord = word.replaceAll(" ","");
                    if(tmpInput.indexOf(tmpWord) == index.intValue()) {
                        String replaceStr = tmpWord.replaceAll(regex, "0");
                        tmpInput = tmpInput.replaceFirst(tmpWord, replaceStr);
                        strBuffer.append(word).append(" ");
                    }
                }
            }
            String sentence = strBuffer.toString();
            if(!"".equals(sentence) && sentence.replaceAll(" ", "").equals(input)) {
                outputSet.add(sentence);
            }
        }

        int num = 1;
        for(String output: outputSet) {
            System.out.println("output sentence["+num+"] = "+ output);
            num++;
        }
    }
}
