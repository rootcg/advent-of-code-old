package root.cristian;

/**
 * Specifically, they need you to find the two entries that sum to 2020 and then multiply those two numbers together.
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
 * In this list, the two entries that sum to 2020 are 1721 and 299.
 * Multiplying them together produces 1721 * 299 = 514579, so the correct answer is 514579.
 */
public final class First {

    final int first(final int[] numbers) {
        for (int n : numbers) {
            for (int m : numbers) {
                if (n + m == 2020) {
                    return n * m;
                }
            }
        }

        throw new IllegalStateException("Number not found");
    }

    final int second(final int[] numbers) {
        for (int n : numbers) {
            for (int m : numbers) {
                for (int l : numbers) {
                    if (n + m + l == 2020) {
                        return n * m * l;
                    }
                }
            }
        }

        throw new IllegalStateException("Number not found");
    }

}
