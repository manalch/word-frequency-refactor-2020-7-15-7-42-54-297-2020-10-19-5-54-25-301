import java.util.*;
import java.util.stream.Collectors;

public class WordFrequencyGame {

    private static final String WHITE_SPACES = "\\s+";

    public String getResult(String sentence) {

        if (sentence.split(WHITE_SPACES).length == 1) {
            return sentence + " 1";
        } else {
            try {
                List<WordInfo> wordInfoList = calculateFrequency(sentence);

                return wordInfoList.stream()
                        .map(wordInfo -> String.format("%s %d", wordInfo.getValue(), wordInfo.getWordCount()))
                        .collect(Collectors.joining("\n"));
            } catch (Exception e) {
                return "Calculate Error";
            }
        }
    }

    private List<WordInfo> calculateFrequency(String sentence) {
        String[] words = sentence.split(WHITE_SPACES);
        List<WordInfo> wordInfoList = Arrays.stream(words)
                .map(word -> new WordInfo(word, 1))
                .collect(Collectors.toList());

        Map<String, List<WordInfo>> wordInfoMap = getWordInfoMap(wordInfoList);

        List<WordInfo> wordInfos = new ArrayList<>();
        for (Map.Entry<String, List<WordInfo>> entry : wordInfoMap.entrySet()) {
            WordInfo wordInfo = new WordInfo(entry.getKey(), entry.getValue().size());
            wordInfos.add(wordInfo);
        }
        wordInfoList = wordInfos;

        wordInfoList.sort((firstWord, secondWord) -> secondWord.getWordCount() - firstWord.getWordCount());
        return wordInfoList;
    }


    private Map<String, List<WordInfo>> getWordInfoMap(List<WordInfo> wordInfoList) {
        Map<String, List<WordInfo>> wordInfoMap = new HashMap<>();
        for (WordInfo wordInfo : wordInfoList) {
            if (!wordInfoMap.containsKey(wordInfo.getValue())) {
                List<WordInfo> wordInfos = new ArrayList<>();
                wordInfos.add(wordInfo);
                wordInfoMap.put(wordInfo.getValue(), wordInfos);
            } else {
                wordInfoMap.get(wordInfo.getValue()).add(wordInfo);
            }
        }

        return wordInfoMap;
    }
}
