import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
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
            return getWordInfoMap(wordInfoList);
        } catch (Exception e) {
            return "Calculate Error";
        }
    }

    private boolean isWord(String sentence) {
        return sentence.split(WHITE_SPACES).length == 1;
    }

    private List<WordInfo> getCalculateFrequencyWordInfo(String sentence) {
        List<String> words = Arrays.asList(sentence.split(WHITE_SPACES));
        HashSet<String> distinctWords = new HashSet<>(words);

        return distinctWords.stream()
                .map(word -> new WordInfo(word, Collections.frequency(words, word)))
                .sorted((firstWord, secondWord) -> secondWord.getWordCount() - firstWord.getWordCount())
                .collect(Collectors.toList());
    }

    private String getWordInfoMap(List<WordInfo> wordInfoList) {
        return wordInfoList.stream()
                .map(wordInfo -> String.format("%s %d", wordInfo.getWord(), wordInfo.getWordCount()))
                .collect(Collectors.joining("\n"));
    }
}
