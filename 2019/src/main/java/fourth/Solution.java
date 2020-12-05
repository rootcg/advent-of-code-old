package fourth;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

public class Solution {

	private static final int first = 138307;
	private static final int last = 654504;
	private static final Pattern twoAdjacent = Pattern.compile("(.)\\1");
	private static final Pattern moreThanTwoAdjacent = Pattern.compile("(.)\\1{2}");

	public static void main(String[] args) {
		long time = System.currentTimeMillis();
		long count = IntStream.range(first, last)
							  .filter(Solution::isIncreaseNumber)
							  .filter(number -> twoAdjacent(String.valueOf(number)))
							  .count();

		System.out.println("Candidates: " + count);
		System.out.println("Time: " + (System.currentTimeMillis() - time) + "ms");
	}

	private static boolean twoAdjacent(String text) {
		Matcher moreThanTwoMatcher = moreThanTwoAdjacent.matcher(text);
		Matcher twoMatcher = twoAdjacent.matcher(text);

		List<String> twoAdjacentList = new ArrayList<>();
		while (twoMatcher.find())
			twoAdjacentList.add(twoMatcher.group(1) + twoMatcher.start() + twoMatcher.end(1));

		twoAdjacentList.removeIf(element -> {
			int index = twoAdjacentList.indexOf(element);
			if (index == 0) return false;

			String previousElement = twoAdjacentList.get(index - 1);
			if (previousElement.charAt(0) != element.charAt(0)) return false;

			int previousEnd = Character.getNumericValue(previousElement.charAt(2));
			int start = Character.getNumericValue(element.charAt(1));
			int distance = start - previousEnd;

			return distance < 2;
		});

		if (twoAdjacentList.isEmpty())
			return false;

		while (moreThanTwoMatcher.find())
			twoAdjacentList.remove(moreThanTwoMatcher.group(1) + moreThanTwoMatcher.start(1) + moreThanTwoMatcher.end(1));

		return !twoAdjacentList.isEmpty();
	}

	private static boolean isIncreaseNumber(int number) {
		int remainder = number % 10;
		number = number / 10;

		while (number > 0) {
			int nextRemainder = number % 10;
			if (nextRemainder > remainder)
				return false;

			number = number / 10;
			remainder = nextRemainder;
		}

		return true;
		// 123 / 10 = 12 % 3; 12 / 10 = 1 % 2
	}

}
