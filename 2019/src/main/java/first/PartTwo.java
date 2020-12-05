package first;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class PartTwo {

	public static void main(String[] args) throws URISyntaxException, IOException {
		Path inputPath = Paths.get(Thread.currentThread().getContextClassLoader().getResource("first/input.txt").toURI());

		try (Stream<String> moduleMassStream = Files.lines(inputPath)) {
			moduleMassStream.map(Long::valueOf)
							.map(mass -> calculateFuel(mass, 0))
							.reduce(Long::sum)
							.ifPresent(System.out::println);
		}
	}

	private static long calculateFuel(long mass, long acc) {
		long fuel = mass / 3 - 2;
		return fuel > 0 ? calculateFuel(fuel, acc + fuel) : acc;
	}

}
