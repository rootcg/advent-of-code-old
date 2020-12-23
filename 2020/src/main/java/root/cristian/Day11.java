package root.cristian;

import java.util.Arrays;
import java.util.List;
import java.util.OptionalInt;
import java.util.function.Predicate;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * --- Day 11: Seating System ---
 */
public class Day11 {

    private static final char FREE = 'L';
    private static final char OCCUPIED = '#';
    private static final char FLOOR = '.';

    private static record WaitingRoom(char[][] seats) {

        public IntStream seatsStream() {
            return Arrays.stream(seats).flatMapToInt(rows -> IntStream.range(0, rows.length).map(i -> rows[i]));
        }

        public IntStream seatsCloseTo(int x, int y) {
            return IntStream.rangeClosed(y - 1, y + 1).flatMap(row ->
                    IntStream.rangeClosed(x - 1, x + 1)
                             .mapToObj(col -> row == y && col == x ? OptionalInt.empty() : seatAt(col, row))
                             .filter(OptionalInt::isPresent)
                             .mapToInt(OptionalInt::getAsInt));
        }

        public IntStream seatsVisibleFrom(int x, int y) {
            record Point(int x, int y) {}
            final Point startingPoint = new Point(x, y);
            final Predicate<Point> hasNext = p -> p.y >= 0 && p.x >= 0 && p.y < seats.length && p.x < seats[p.y].length;
            final Stream<Stream<Point>> allPoints = Stream.of(
                    Stream.iterate(startingPoint, hasNext, p -> new Point(p.x + 1, p.y)),
                    Stream.iterate(startingPoint, hasNext, p -> new Point(p.x + 1, p.y + 1)),
                    Stream.iterate(startingPoint, hasNext, p -> new Point(p.x, p.y + 1)),
                    Stream.iterate(startingPoint, hasNext, p -> new Point(p.x - 1, p.y + 1)),
                    Stream.iterate(startingPoint, hasNext, p -> new Point(p.x - 1, p.y)),
                    Stream.iterate(startingPoint, hasNext, p -> new Point(p.x - 1, p.y - 1)),
                    Stream.iterate(startingPoint, hasNext, p -> new Point(p.x, p.y - 1)),
                    Stream.iterate(startingPoint, hasNext, p -> new Point(p.x + 1, p.y - 1)));

            return allPoints.flatMapToInt(points -> points.skip(1)
                                                          .map(p -> seatAt(p.x, p.y))
                                                          .filter(OptionalInt::isPresent)
                                                          .mapToInt(OptionalInt::getAsInt)
                                                          .filter(seat -> seat != FLOOR)
                                                          .findFirst().stream());
        }

        public OptionalInt seatAt(int x, int y) {
            if (y < 0 || y >= seats.length || x < 0 || x >= seats[y].length) return OptionalInt.empty();
            return OptionalInt.of(seats[y][x]);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            WaitingRoom that = (WaitingRoom) o;
            return IntStream.range(0, seats.length)
                            .mapToObj(i -> Arrays.equals(seats[i], that.seats[i]))
                            .reduce(Boolean::logicalAnd)
                            .orElse(false);
        }

        @Override
        public int hashCode() {
            return Arrays.hashCode(seats);
        }

    }

    /**
     * Your plane lands with plenty of time to spare. The final leg of your journey is a ferry that goes directly to the
     * tropical island where you can finally start your vacation. As you reach the waiting area to board the ferry, you
     * realize you're so early, nobody else has even arrived yet!
     * <p/>
     * By modeling the process people use to choose (or abandon) their seat in the waiting area, you're pretty sure you
     * can predict the best place to sit. You make a quick map of the seat layout (your puzzle input).
     * <p/>
     * The seat layout fits neatly on a grid. Each position is either floor (.), an empty seat (L), or an occupied seat
     * (#). For example, the initial seat layout might look like this:
     * <pre>
     * L.LL.LL.LL
     * LLLLLLL.LL
     * L.L.L..L..
     * LLLL.LL.LL
     * L.LL.LL.LL
     * L.LLLLL.LL
     * ..L.L.....
     * LLLLLLLLLL
     * L.LLLLLL.L
     * L.LLLLL.LL
     * </pre>
     * Now, you just need to model the people who will be arriving shortly. Fortunately, people are entirely predictable
     * and always follow a simple set of rules. All decisions are based on the number of occupied seats adjacent to a
     * given seat (one of the eight positions immediately up, down, left, right, or diagonal from the seat). The
     * following rules are applied to every seat simultaneously:
     * <p/>
     * If a seat is empty (L) and there are no occupied seats adjacent to it, the seat becomes occupied.
     * If a seat is occupied (#) and four or more seats adjacent to it are also occupied, the seat becomes empty.
     * Otherwise, the seat's state does not change.
     * Floor (.) never changes; seats don't move, and nobody sits on the floor.
     * <p/>
     * After one round of these rules, every seat in the example layout becomes occupied:
     * <pre>
     * #.##.##.##
     * #######.##
     * #.#.#..#..
     * ####.##.##
     * #.##.##.##
     * #.#####.##
     * ..#.#.....
     * ##########
     * #.######.#
     * #.#####.##
     * </pre>
     * After a second round, the seats with four or more occupied adjacent seats become empty again:
     * <pre>
     * #.LL.L#.##
     * #LLLLLL.L#
     * L.L.L..L..
     * #LLL.LL.L#
     * #.LL.LL.LL
     * #.LLLL#.##
     * ..L.L.....
     * #LLLLLLLL#
     * #.LLLLLL.L
     * #.#LLLL.##
     * </pre>
     * This process continues for three more rounds:
     * <pre>
     * #.##.L#.##
     * #L###LL.L#
     * L.#.#..#..
     * #L##.##.L#
     * #.##.LL.LL
     * #.###L#.##
     * ..#.#.....
     * #L######L#
     * #.LL###L.L
     * #.#L###.##
     * </pre>
     * <pre>
     * #.#L.L#.##
     * #LLL#LL.L#
     * L.L.L..#..
     * #LLL.##.L#
     * #.LL.LL.LL
     * #.LL#L#.##
     * ..L.L.....
     * #L#LLLL#L#
     * #.LLLLLL.L
     * #.#L#L#.##
     * </pre>
     * <pre>
     * #.#L.L#.##
     * #LLL#LL.L#
     * L.#.L..#..
     * #L##.##.L#
     * #.#L.LL.LL
     * #.#L#L#.##
     * ..L.L.....
     * #L#L##L#L#
     * #.LLLLLL.L
     * #.#L#L#.##
     * </pre>
     * At this point, something interesting happens: the chaos stabilizes and further applications of these rules cause
     * no seats to change state! Once people stop moving around, you count 37 occupied seats.
     * <p/>
     * Simulate your seating area by applying the seating rules repeatedly until no seats change state. How many seats
     * end up occupied?
     *
     * @param lines seats
     * @return occupied seats
     */
    final long first(final List<String> lines) {
        final char[][] seats = lines.stream().map(String::toCharArray).toArray(char[][]::new);

        WaitingRoom waitingRoom_A = new WaitingRoom(seats);
        WaitingRoom waitingRoom_B = movePeople(waitingRoom_A);
        while (!waitingRoom_A.equals(waitingRoom_B)) {
            waitingRoom_A = waitingRoom_B;
            waitingRoom_B = movePeople(waitingRoom_B);
        }

        return waitingRoom_B.seatsStream().filter(seat -> seat == OCCUPIED).count();
    }

    final long second(final List<String> lines) {
        final char[][] seats = lines.stream().map(String::toCharArray).toArray(char[][]::new);

        WaitingRoom waitingRoom_A = new WaitingRoom(seats);
        WaitingRoom waitingRoom_B = moveTolerantPeople(waitingRoom_A);
        while (!waitingRoom_A.equals(waitingRoom_B)) {
            waitingRoom_A = waitingRoom_B;
            waitingRoom_B = moveTolerantPeople(waitingRoom_B);
        }

        return waitingRoom_B.seatsStream().filter(seat -> seat == OCCUPIED).count();
    }

    private WaitingRoom movePeople(final WaitingRoom waitingRoom) {
        final char[][] seats = new char[waitingRoom.seats().length][];

        for (int i = 0; i < waitingRoom.seats().length; i++) {
            seats[i] = new char[waitingRoom.seats()[i].length];
            for (int j = 0; j < waitingRoom.seats()[i].length; j++) {
                if (waitingRoom.seatAt(j, i).isEmpty()) continue;

                final char currentSeat = (char) waitingRoom.seatAt(j, i).getAsInt();
                if (currentSeat == FREE
                        && waitingRoom.seatsCloseTo(j, i).noneMatch(seat -> seat == OCCUPIED)) {
                    seats[i][j] = OCCUPIED;
                } else if (currentSeat == OCCUPIED
                        && waitingRoom.seatsCloseTo(j, i).filter(seat -> seat == OCCUPIED).count() >= 4) {
                    seats[i][j] = FREE;
                } else {
                    seats[i][j] = currentSeat;
                }
            }
        }

        return new WaitingRoom(seats);
    }

    private WaitingRoom moveTolerantPeople(final WaitingRoom waitingRoom) {
        final char[][] seats = new char[waitingRoom.seats().length][];

        for (int i = 0; i < waitingRoom.seats().length; i++) {
            seats[i] = new char[waitingRoom.seats()[i].length];
            for (int j = 0; j < waitingRoom.seats()[i].length; j++) {
                if (waitingRoom.seatAt(j, i).isEmpty()) continue;

                final char currentSeat = (char) waitingRoom.seatAt(j, i).getAsInt();
                if (currentSeat == FREE
                        && waitingRoom.seatsVisibleFrom(j, i).noneMatch(seat -> seat == OCCUPIED)) {
                    seats[i][j] = OCCUPIED;
                } else if (currentSeat == OCCUPIED
                        && waitingRoom.seatsVisibleFrom(j, i).filter(seat -> seat == OCCUPIED).count() >= 5) {
                    seats[i][j] = FREE;
                } else {
                    seats[i][j] = currentSeat;
                }
            }
        }

        return new WaitingRoom(seats);
    }

}
