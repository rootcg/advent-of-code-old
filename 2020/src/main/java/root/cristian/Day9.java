package root.cristian;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * --- Day 9: Encoding Error ---
 */
public class Day9 {

    public static class LimitedQueue<E> extends LinkedList<E> {
        private final int limit;

        public LimitedQueue(int limit) {
            this.limit = limit;
        }

        @Override
        public boolean add(E o) {
            boolean added = super.add(o);
            while (added && size() > limit) { super.remove(); }
            return added;
        }
    }

    /**
     * With your neighbor happily enjoying their video game, you turn your attention to an open data port on the little
     * screen in the seat in front of you.
     * <p/>
     * Though the port is non-standard, you manage to connect it to your computer through the clever use of several
     * paperclips. Upon connection, the port outputs a series of numbers (your puzzle input).
     * <p/>
     * The data appears to be encrypted with the eXchange-Masking Addition System (XMAS) which, conveniently for you, is
     * an old cypher with an important weakness.
     * <p/>
     * XMAS starts by transmitting a preamble of 25 numbers. After that, each number you receive should be the sum of
     * any two of the 25 immediately previous numbers. The two numbers will have different values, and there might be
     * more than one such pair.
     * <p/>
     * For example, suppose your preamble consists of the numbers 1 through 25 in a random order. To be valid, the next
     * number must be the sum of two of those numbers:
     * <pre>
     * 26 would be a valid next number, as it could be 1 plus 25 (or many other pairs, like 2 and 24).
     * 49 would be a valid next number, as it is the sum of 24 and 25.
     * 100 would not be valid; no two of the previous 25 numbers sum to 100.
     * 50 would also not be valid; although 25 appears in the previous 25 numbers, the two numbers in the pair must be
     * different.
     * </pre>
     * Suppose the 26th number is 45, and the first number (no longer an option, as it is more than 25 numbers ago) was
     * 20. Now, for the next number to be valid, there needs to be some pair of numbers among 1-19, 21-25, or 45 that
     * add up to it:
     * <pre>
     * 26 would still be a valid next number, as 1 and 25 are still within the previous 25 numbers.
     * 65 would not be valid, as no two of the available numbers sum to it.
     * 64 and 66 would both be valid, as they are the result of 19+45 and 21+45 respectively.
     * </pre>
     * Here is a larger example which only considers the previous 5 numbers (and has a preamble of length 5):
     * <pre>
     * 35
     * 20
     * 15
     * 25
     * 47
     * 40
     * 62
     * 55
     * 65
     * 95
     * 102
     * 117
     * 150
     * 182
     * 127
     * 219
     * 299
     * 277
     * 309
     * 576
     * </pre>
     * In this example, after the 5-number preamble, almost every number is the sum of two of the previous 5 numbers;
     * the only number that does not follow this rule is 127.
     * <p/>
     * The first step of attacking the weakness in the XMAS data is to find the first number in the list (after the
     * preamble) which is not the sum of two of the 25 numbers before it. What is the first number that does not have
     * this property?
     *
     * @param lines    encrypted data
     * @param preamble XMAS preamble
     * @return first number that doesn't hold to the XMAS specification
     */
    final long first(final List<String> lines, final int preamble) {
        final List<Long> previousNumbers = new LimitedQueue<>(preamble);
        final List<Long> numbers = lines.stream().map(Long::parseLong).collect(Collectors.toList());

        final Predicate<Long> isValid = (number) -> {
            for (int i = 0; i < previousNumbers.size() - 1; i++) {
                for (int j = 0; j < previousNumbers.size(); j++) {
                    if (j == i) continue;
                    if (previousNumbers.get(i) + previousNumbers.get(j) == number) return true;
                }
            }

            return false;
        };

        numbers.stream().limit(preamble).forEach(previousNumbers::add);
        for (Long number : numbers.stream().skip(preamble).collect(Collectors.toList())) {
            if (!isValid.test(number)) return number;
            else previousNumbers.add(number);
        }

        throw new IllegalStateException("All numbers are correct");
    }

    /**
     * The final step in breaking the XMAS encryption relies on the invalid number you just found: you must find a
     * contiguous set of at least two numbers in your list which sum to the invalid number from step 1.
     * <p/>
     * Again consider the above example:
     * <pre>
     * 35
     * 20
     * 15
     * 25
     * 47
     * 40
     * 62
     * 55
     * 65
     * 95
     * 102
     * 117
     * 150
     * 182
     * 127
     * 219
     * 299
     * 277
     * 309
     * 576
     * </pre>
     * In this list, adding up all of the numbers from 15 through 40 produces the invalid number from step 1, 127. (Of
     * course, the contiguous set of numbers in your actual list might be much longer.)
     * <p/>
     * To find the encryption weakness, add together the smallest and largest number in this contiguous range; in this
     * example, these are 15 and 47, producing 62.
     * <p/>
     * What is the encryption weakness in your XMAS-encrypted list of numbers?
     *
     * @param lines         encrypted data
     * @param invalidNumber XMAS weakness
     * @return sum of the smallest and largest numbers of the numbers that added together sum up to invalidNumber
     */
    final long second(final List<String> lines, final long invalidNumber) {
        final List<Long> numbers = lines.stream().map(Long::parseLong).collect(Collectors.toList());

        for (int i = 0; i < numbers.size() - 1; i++) {
            final List<Long> sumNumbers = new ArrayList<>();
            sumNumbers.add(numbers.get(i));

            for (int j = i + 1; j < numbers.size(); j++) {
                sumNumbers.add(numbers.get(j));
                final long sum = sumNumbers.stream().mapToLong(Long::longValue).sum();

                if (sum == invalidNumber) {
                    return Stream.concat(sumNumbers.stream().min(Long::compare).stream(),
                                         sumNumbers.stream().max(Long::compare).stream())
                                 .mapToLong(Long::longValue)
                                 .sum();
                } else if (sum > invalidNumber) {
                    break;
                }
            }
        }

        throw new IllegalStateException("Numbers not found");
    }

}
