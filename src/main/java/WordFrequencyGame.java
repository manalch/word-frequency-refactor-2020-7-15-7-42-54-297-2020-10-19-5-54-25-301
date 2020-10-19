import java.util.*;
import java.util.stream.Collectors;

public class WordFrequencyGame {

    private static final String WHITE_SPACES = "\\s+";
    private static final String ONE_WORD_COUNT = " 1";

    public String getResult(String sentence) {
        try {
            if (isWord(sentence)) {
                return sentence.concat(ONE_WORD_COUNT);
            }
            List<WordInfo> wordInfoList = getCalculateFrequencyWordInfo(sentence);

            return wordInfoList.stream()
                    .map(wordInfo -> String.format("%s %d", wordInfo.getWord(), wordInfo.getWordCount()))
                    .collect(Collectors.joining("\n"));
        } catch (Exception e) {
            return "Calculate Error";
        }
    }

    private boolean isWord(String sentence) {
        return sentence.split(WHITE_SPACES).length == 1;
    }

    private List<WordInfo> getCalculateFrequencyWordInfo(String sentence) {
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
            if (!wordInfoMap.containsKey(wordInfo.getWord())) {
                List<WordInfo> wordInfos = new ArrayList<>();
                wordInfos.add(wordInfo);
                wordInfoMap.put(wordInfo.getWord(), wordInfos);
            } else {
                wordInfoMap.get(wordInfo.getWord()).add(wordInfo);
            }
        }
        return wordInfoMap;
    }
}
