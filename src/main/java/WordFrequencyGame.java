import java.util.*;

public class WordFrequencyGame {

    private static final String WHITE_SPACES = "\\s+";

    public String getResult(String sentence) {

        if (sentence.split(WHITE_SPACES).length == 1) {
            return sentence + " 1";
        } else {
            try {
                String[] words = sentence.split(WHITE_SPACES);

                List<WordInfo> wordInfoList = new ArrayList<>();
                for (String word : words) {
                    WordInfo wordInfo = new WordInfo(word, 1);
                    wordInfoList.add(wordInfo);
                }

                Map<String, List<WordInfo>> wordInfoMap = getWordInfoMap(wordInfoList);

                List<WordInfo> wordInfos = new ArrayList<>();
                for (Map.Entry<String, List<WordInfo>> entry : wordInfoMap.entrySet()) {
                    WordInfo input = new WordInfo(entry.getKey(), entry.getValue().size());
                    wordInfos.add(input);
                }
                wordInfoList = wordInfos;

                wordInfoList.sort((firstWord, secondWord) -> secondWord.getWordCount() - firstWord.getWordCount());

                StringJoiner joiner = new StringJoiner("\n");
                for (WordInfo wordInfo : wordInfoList) {
                    String line = wordInfo.getValue() + " " + wordInfo.getWordCount();
                    joiner.add(line);
                }
                return joiner.toString();
            } catch (Exception e) {
                return "Calculate Error";
            }
        }
    }


    private Map<String, List<WordInfo>> getWordInfoMap(List<WordInfo> inputList) {
        Map<String, List<WordInfo>> map = new HashMap<>();
        for (WordInfo wordInfo : inputList) {
            if (!map.containsKey(wordInfo.getValue())) {
                ArrayList arr = new ArrayList<>();
                arr.add(wordInfo);
                map.put(wordInfo.getValue(), arr);
            } else {
                map.get(wordInfo.getValue()).add(wordInfo);
            }
        }


        return map;
    }


}
