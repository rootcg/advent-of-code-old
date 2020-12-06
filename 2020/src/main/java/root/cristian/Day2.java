package root.cristian;

import java.util.Arrays;
import java.util.List;

/**
 * For example, suppose you have the following list:
 * <ul>
 *      <li>1-3 a: abcde</li>
 *      <li>1-3 b: cdefg</li>
 *      <li>2-9 c: ccccccccc</li>
 * </ul>
 * Each line gives the password policy and then the password. The password policy indicates the lowest and highest
 * number of times a given letter must appear for the password to be valid. For example, 1-3 a means that the password
 * must contain a at least 1 time and at most 3 times.
 * <p/>
 * In the above example, 2 passwords are valid. The middle password, cdefg, is not; it contains no instances of b, but
 * needs at least 1. The first and third passwords are valid: they contain one a or nine c, both within the limits of
 * their respective policies.
 * <p/>
 * How many passwords are valid according to their policies?
 */
public final class Day2 {

    private interface Policy {

        boolean validate(final String password);

    }

    private static final class DeprecatedPolicy implements Policy {

        private final int min;
        private final int max;
        private final char letter;

        public DeprecatedPolicy(final int min, final int max, final char letter) {
            this.min = min;
            this.max = max;
            this.letter = letter;
        }

        @Override
        public boolean validate(final String password) {
            long letterCount = password.chars().filter(letter -> letter == this.letter).count();
            return letterCount >= min && letterCount <= max;
        }

    }

    private static final class UltraSecurePolicy implements Policy {

        private final int[] positions;
        private final char letter;

        public UltraSecurePolicy(final char letter, final int... positions) {
            this.letter = letter;
            this.positions = positions;
        }

        @Override
        public boolean validate(final String password) {
            return Arrays.stream(positions).map(i -> --i).filter(i -> password.charAt(i) == letter).count() == 1;
        }

    }

    final long first(final List<String> lines) {
        int count = 0;

        for (final String line : lines) {
            final String[] attributes = line.split(":");
            final String password = attributes[1].strip();
            final String[] policyAttributes = attributes[0].split(" ");
            final String[] range = policyAttributes[0].split("-");
            final String letter = policyAttributes[1];

            Policy policy = new DeprecatedPolicy(
                    Integer.parseInt(range[0]),
                    Integer.parseInt(range[1]),
                    letter.charAt(0));

            if (policy.validate(password)) {
                count++;
            }
        }

        return count;
    }

    final long second(final List<String> lines) {
        int count = 0;

        for (final String line : lines) {
            final String[] attributes = line.split(":");
            final String password = attributes[1].strip();
            final String[] policyAttributes = attributes[0].split(" ");
            final String[] range = policyAttributes[0].split("-");
            final String letter = policyAttributes[1];

            final Policy policy = new UltraSecurePolicy(letter.charAt(0),
                    Arrays.stream(range).map(Integer::valueOf).mapToInt(Integer::intValue).toArray());

            if (policy.validate(password)) {
                count++;
            }
        }

        return count;
    }

}
