package root.cristian;

import java.util.Arrays;
import java.util.List;

/**
 * --- Day 2: Password Philosophy ---
 */
public final class Day2 {

    private interface Policy {
        boolean validate(final String password);
    }

    private static final record DeprecatedPolicy(int min, int max, char letter) implements Policy {
        @Override
        public boolean validate(final String password) {
            long letterCount = password.chars().filter(letter -> letter == this.letter).count();
            return letterCount >= min && letterCount <= max;
        }
    }

    private static final record UltraSecurePolicy(char letter, int... positions) implements Policy {

        @Override
        public boolean validate(final String password) {
            return Arrays.stream(positions).map(i -> --i).filter(i -> password.charAt(i) == letter).count() == 1;
        }

    }

    /**
     * Your flight departs in a few days from the coastal airport; the easiest way down to the coast from here is via
     * toboggan.
     * <p/>
     * The shopkeeper at the North Pole Toboggan Rental Shop is having a bad day. "Something's wrong with our computers; we
     * can't log in!" You ask if you can take a look.
     * <p/>
     * Their password database seems to be a little corrupted: some of the passwords wouldn't have been allowed by the
     * Official Toboggan Corporate Policy that was in effect when they were chosen.
     * <p/>
     * To try to debug the problem, they have created a list (your puzzle input) of passwords (according to the corrupted
     * database) and the corporate policy when that password was set.
     * <p/>
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
     *
     * @param lines passwords with their policies
     * @return number of valid passwords
     */
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

    /**
     * While it appears you validated the passwords correctly, they don't seem to be what the Official Toboggan
     * Corporate Authentication System is expecting.
     * <p/>
     * The shopkeeper suddenly realizes that he just accidentally explained the password policy rules from his old job
     * at the sled rental place down the street! The Official Toboggan Corporate Policy actually works a little
     * differently.
     * <p/>
     * Each policy actually describes two positions in the password, where 1 means the first character, 2 means the
     * second character, and so on. (Be careful; Toboggan Corporate Policies have no concept of "index zero"!) Exactly
     * one of these positions must contain the given letter. Other occurrences of the letter are irrelevant for the
     * purposes of policy enforcement.
     * <p/>
     * Given the same example list from above:
     * <ul>
     *   <li>1-3 a: abcde is valid: position 1 contains a and position 3 does not.</li>
     *   <li>1-3 b: cdefg is invalid: neither position 1 nor position 3 contains b.</li>
     *   <li>2-9 c: ccccccccc is invalid: both position 2 and position 9 contain c.</li>
     * </ul>
     * How many passwords are valid according to the new interpretation of the policies?
     *
     * @param lines passwords with their policies
     * @return number of valid passwords
     */
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
