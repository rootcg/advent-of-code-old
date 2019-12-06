package third;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Third {

	enum Direction {
		U, R, D, L
	}

	static class Move {
		final Direction direction;
		final int length;

		private Move(Direction direction, int length) {
			this.direction = direction;
			this.length = length;
		}

		static Move of(String rawMove) {
			return new Move(Direction.valueOf(rawMove.substring(0, 1)), Integer.parseInt(rawMove.substring(1)));
		}
	}

	static class Point {
		final int x;
		final int y;

		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

		List<Point> move(Move move) {
			List<Point> locationHistory = new ArrayList<>();

			switch (move.direction) {
				case D:
					for (int i = y - 1; i >= y - move.length; i--)
						locationHistory.add(new Point(x, i));
					break;
				case U:
					for (int i = y + 1; i <= y + move.length; i++)
						locationHistory.add(new Point(x, i));
					break;
				case R:
					for (int i = x + 1; i <= x + move.length; i++)
						locationHistory.add(new Point(i, y));
					break;
				case L:
					for (int i = x - 1; i >= x - move.length; i--)
						locationHistory.add(new Point(i, y));
					break;
			}

			return locationHistory;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || getClass() != o.getClass()) return false;
			Point point = (Point) o;
			return x == point.x && y == point.y;
		}

		@Override
		public int hashCode() {
			return Objects.hash(x, y);
		}

		@Override
		public String toString() {
			return "Point{" +
			       "x=" + x +
			       ", y=" + y +
			       '}';
		}
	}

	public static void main(String[] args) throws IOException, URISyntaxException {
		List<List<Move>> wireMoves = readInput();
		Set<Point> locationsWireA = trace(wireMoves.get(0));
		Set<Point> locationsWireB = trace(wireMoves.get(1));

		locationsWireA.stream()
		              .filter(locationsWireB::contains)
		              .map(point -> Math.abs(point.x) + Math.abs(point.y))
		              .sorted()
		              .forEach(System.out::println);
	}

	private static Set<Point> trace(List<Move> wireMoves) {
		Set<Point> locationHistory = new LinkedHashSet<>();
		Point location = new Point(0, 0);

		for (Move move : wireMoves) {
			List<Point> trace = location.move(move);
			locationHistory.addAll(trace);
			location = trace.get(trace.size() - 1);
		}

		return locationHistory;
	}

	private static List<List<Move>> readInput() throws IOException, URISyntaxException {
		Path inputPath = Paths.get(Thread.currentThread().getContextClassLoader().getResource("third/input.txt").toURI());
		try (Stream<String> rawInput = Files.lines(inputPath)) {
			return rawInput.map(line -> Arrays.asList(line.split(",")))
			               .map(moveList -> moveList.stream().map(Move::of).collect(Collectors.toList()))
			               .collect(Collectors.toList());
		}
	}

}
