package root.cristian;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * --- Day 3: Toboggan Trajectory ---
 */
public class Day3 {

    private final record Point(int x, int y) {}

    private static final class Mountain {

        private final char[][] map;

        public Mountain(final char[][] map) {this.map = map;}

        public final char getTerrain(final Point point) {
            if (point.y() >= map.length) {
                throw new IllegalStateException("End of the road");
            }

            final Point relativePoint = calculateRelativePoint(point);
            return map[relativePoint.y()][relativePoint.x()];
        }

        private Point calculateRelativePoint(final Point point) {
            if (point.x() >= map[point.y()].length)
                return calculateRelativePoint(new Point(point.x() - map[point.y()].length, point.y()));
            else
                return point;
        }

        final int height() {
            return map.length;
        }

    }

    private static final char TREE = '#';

    /**
     * Due to the local geology, trees in this area only grow on exact integer coordinates in a grid. You make a map
     * (your puzzle input) of the open squares (.) and trees (#) you can see. For example:
     * <pre>
     * ..##.......
     * #...#...#..
     * .#....#..#.
     * ..#.#...#.#
     * .#...##..#.
     * ..#.##.....
     * .#.#.#....#
     * .#........#
     * #.##...#...
     * #...##....#
     * .#..#...#.#
     * </pre>
     * These aren't the only trees, though; due to something you read about once involving arboreal genetics and biome
     * stability, the same pattern repeats to the right many times:
     * <pre>
     * ..##.........##.........##.........##.........##.........##.......  --->
     * #...#...#..#...#...#..#...#...#..#...#...#..#...#...#..#...#...#..
     * .#....#..#..#....#..#..#....#..#..#....#..#..#....#..#..#....#..#.
     * ..#.#...#.#..#.#...#.#..#.#...#.#..#.#...#.#..#.#...#.#..#.#...#.#
     * .#...##..#..#...##..#..#...##..#..#...##..#..#...##..#..#...##..#.
     * ..#.##.......#.##.......#.##.......#.##.......#.##.......#.##.....  --->
     * .#.#.#....#.#.#.#....#.#.#.#....#.#.#.#....#.#.#.#....#.#.#.#....#
     * .#........#.#........#.#........#.#........#.#........#.#........#
     * #.##...#...#.##...#...#.##...#...#.##...#...#.##...#...#.##...#...
     * #...##....##...##....##...##....##...##....##...##....##...##....#
     * .#..#...#.#.#..#...#.#.#..#...#.#.#..#...#.#.#..#...#.#.#..#...#.#  --->
     * </pre>
     * You start on the open square (.) in the top-left corner and need to reach the bottom (below the bottom-most row
     * on your map).The toboggan can only follow a few specific slopes (you opted for a cheaper model that prefers
     * rational numbers); start by counting all the trees you would encounter for the slope right 3, down 1.
     * <p/>
     * Starting at the top-left corner of your map and following a slope of right 3 and down 1, how many trees would you
     * encounter?
     */
    final long first(final List<String> lines) {
        final Mountain mountain = generateMountain(lines);
        final Stream<Point> moves = Stream.iterate(new Point(0, 0), point -> new Point(point.x() + 3, point.y() + 1));
        return traverseMountain(mountain, moves);
    }

    /**
     * Time to check the rest of the slopes - you need to minimize the probability of a sudden arboreal stop, after all.
     * <p/>
     * Determine the number of trees you would encounter if, for each of the following slopes, you start at the top-left
     * corner and traverse the map all the way to the bottom:
     * <ul>
     *     <li>Right 1, down 1.</li>
     *     <li>Right 3, down 1. (This is the slope you already checked.)</li>
     *     <li>Right 5, down 1.</li>
     *     <li>Right 7, down 1.</li>
     *     <li>Right 1, down 2.</li>
     * </ul>
     * In the above example, these slopes would find 2, 7, 3, 4, and 2 tree(s) respectively; multiplied together, these
     * produce the answer 336.
     * <p/>
     * What do you get if you multiply together the number of trees encountered on each of the listed slopes?
     *
     * @param lines map input
     * @return the product of multiplying the number of trees encountered
     */
    final long second(final List<String> lines) {
        final Mountain mountain = generateMountain(lines);
        final Stream<Function<Point, Point>> slopes = Stream.of(
                point -> new Point(point.x() + 1, point.y() + 1),
                point -> new Point(point.x() + 3, point.y() + 1),
                point -> new Point(point.x() + 5, point.y() + 1),
                point -> new Point(point.x() + 7, point.y() + 1),
                point -> new Point(point.x() + 1, point.y() + 2));

        return slopes.map(slope -> Stream.iterate(new Point(0, 0), slope::apply))
                     .mapToLong(moves -> traverseMountain(mountain, moves))
                     .reduce((a, b) -> a * b)
                     .orElseThrow(IllegalStateException::new);
    }

    private Mountain generateMountain(final List<String> lines) {
        char[][] map = lines.stream().map(String::toCharArray).collect(Collectors.toList()).toArray(char[][]::new);
        return new Mountain(map);
    }

    private long traverseMountain(final Mountain mountain, final Stream<Point> moves) {
        return moves.takeWhile(point -> point.y() < mountain.height())
                    .map(mountain::getTerrain)
                    .filter(terrain -> terrain == TREE)
                    .count();
    }

}
