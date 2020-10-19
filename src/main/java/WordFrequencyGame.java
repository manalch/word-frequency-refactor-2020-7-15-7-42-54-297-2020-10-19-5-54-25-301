import java.util.*;
import java.util.stream.Collectors;

public class WordFrequencyGame {

    private static final String WHITE_SPACES = "\\s+";
    private static final String ONE_WORD_COUNT = " 1";

    public String getResult(String sentence) {
        try {
            if (sentence.split(WHITE_SPACES).length == 1) {
                return sentence.concat(ONE_WORD_COUNT);
            }
            List<WordInfo> wordInfoList = calculateFrequency(sentence);

            return wordInfoList.stream()
                    .map(wordInfo -> String.format("%s %d", wordInfo.getValue(), wordInfo.getWordCount()))
                    .collect(Collectors.joining("\n"));
        } catch (Exception e) {
            return "Calculate Error";
        }
    }

    private List<WordInfo> calculateFrequency(String sentence) {
        String[] words = sentence.split(WHITE_SPACES);
        List<WordInfo> wordInfoList = Arrays.stream(words)
                .map(word -> new WordInfo(word, 1))
                .collect(Collectors.toList());

        Map<String, List<WordInfo>> wordInfoMap = getWordInfoMap(wordInfoList);

        return wordInfoMap.entrySet().stream()
                .map(wordInfoEntrySet -> new WordInfo(wordInfoEntrySet.getKey(), wordInfoEntrySet.getValue().size()))
                .sorted((initialWordInfo, nextWordInfo) -> Integer.compare(nextWordInfo.getWordCount(), initialWordInfo.getWordCount()))
                .collect(Collectors.toList());
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
