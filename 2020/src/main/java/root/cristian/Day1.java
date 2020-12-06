package root.cristian;

/**
 * --- Day 1: Report Repair ---
 */
public final class Day1 {

    /**
     * After saving Christmas five years in a row, you've decided to take a vacation at a nice resort on a tropical island.
     * Surely, Christmas will go on without you.
     * <p/>
     * The tropical island has its own currency and is entirely cash-only. The gold coins used there have a little picture
     * of a starfish; the locals just call them stars. None of the currency exchanges seem to have heard of them, but
     * somehow, you'll need to find fifty of these coins by the time you arrive so you can pay the deposit on your room.
     * <p/>
     * To save your vacation, you need to get all fifty stars by December 25th. Collect stars by solving puzzles. Two
     * puzzles will be made available on each day in the Advent calendar; the second puzzle is unlocked when you complete
     * the first. Each puzzle grants one star. Good luck!
     * <p/>
     * Before you leave, the Elves in accounting just need you to fix your expense report (your puzzle input); apparently,
     * something isn't quite adding up.Specifically, they need you to find the two entries that sum to 2020 and then
     * multiply those two numbers together.
     * <p/>
     * For example, suppose your expense report contained the following:
     * <ul>
     *      <li>1721</li>
     *      <li>979</li>
     *      <li>366</li>
     *      <li>299</li>
     *      <li>675</li>
     *      <li>1456</li>
     * </ul>
     * In this list, the two entries that sum to 2020 are 1721 and 299. Multiplying them together produces
     * 1721 * 299 = 514579, so the correct answer is 514579.
     *
     * @param numbers expense report
     * @return product of two entries which sum is 2020
     */
    final int first(final int[] numbers) {
        for (final int n : numbers) {
            for (final int m : numbers) {
                if (n + m == 2020) {
                    return n * m;
                }
            }
        }

        throw new IllegalStateException("Number not found");
    }

    /**
     * The Elves in accounting are thankful for your help; one of them even offers you a starfish coin they had left
     * over from a past vacation. They offer you a second one if you can find three numbers in your expense report that
     * meet the same criteria.
     * <p/>
     * Using the above example again, the three entries that sum to 2020 are 979, 366, and 675. Multiplying them
     * together produces the answer, 241861950.
     * <p/>
     * In your expense report, what is the product of the three entries that sum to 2020?
     *
     * @param numbers expense report
     * @return product of three entries which sum is 2020
     */
    final int second(final int[] numbers) {
        for (final int n : numbers) {
            for (final int m : numbers) {
                for (final int l : numbers) {
                    if (n + m + l == 2020) {
                        return n * m * l;
                    }
                }
            }
        }

        throw new IllegalStateException("Number not found");
    }

}
