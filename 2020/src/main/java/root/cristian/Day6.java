package root.cristian;

import root.cristian.utilities.SplitterCollector;

import java.util.List;

/**
 * --- Day 6: Custom Customs ---
 */
public class Day6 {

    /**
     * As your flight approaches the regional airport where you'll switch to a much larger plane, customs declaration
     * forms are distributed to the passengers.
     * <p/>
     * The form asks a series of 26 yes-or-no questions marked a through z. All you need to do is identify the questions
     * for which anyone in your group answers "yes". Since your group is just you, this doesn't take very long.
     * <p/>
     * However, the person sitting next to you seems to be experiencing a language barrier and asks if you can help. For
     * each of the people in their group, you write down the questions for which they answer "yes", one per line.
     * <p/>
     * For example:
     * <pre>
     * abcx
     * abcy
     * abcz
     * </pre>
     * In this group, there are 6 questions to which anyone answered "yes": a, b, c, x, y, and z. (Duplicate answers to
     * the same question don't count extra; each question counts at most once.)
     * <p/>
     * Another group asks for your help, then another, and eventually you've collected answers from every group on the
     * plane (your puzzle input). Each group's answers are separated by a blank line, and within each group, each
     * person's answers are on a single line.
     * <p/>
     * For example:
     * <pre>
     * abc
     *
     * a
     * b
     * c
     *
     * ab
     * ac
     *
     * a
     * a
     * a
     * a
     *
     * b
     * </pre>
     * This list represents answers from five groups:
     * <ul>
     *     <li>The first group contains one person who answered "yes" to 3 questions: a, b, and c.</li>
     *     <li>The second group contains three people; combined, they answered "yes" to 3 questions: a, b, and c.</li>
     *     <li>The third group contains two people; combined, they answered "yes" to 3 questions: a, b, and c.</li>
     *     <li>The fourth group contains four people; combined, they answered "yes" to only 1 question, a.</li>
     *     <li>The last group contains one person who answered "yes" to only 1 question, b.</li>
     * </ul>
     * In this example, the sum of these counts is 3 + 3 + 3 + 1 + 1 = 11.
     * <p/>
     * For each group, count the number of questions to which anyone answered "yes". What is the sum of those counts?
     */
    final long first(final List<String> lines) {
        return lines.stream()
                    .collect(SplitterCollector.splitBy(String::isBlank))
                    .stream()
                    .mapToLong(groupAnswers -> groupAnswers.stream().flatMapToInt(String::chars).distinct().count())
                    .sum();
    }

    final long second(final List<String> lines) {
        throw new IllegalStateException("Not implemented");
    }

}
