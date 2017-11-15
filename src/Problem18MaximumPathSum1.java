import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Problem18MaximumPathSum1 {

    private static final int VALUE_UPPER_BOUND = 100;

    private static final int[][] TRIANGLE = new int[][] {
            {75},
            {95, 64},
            {17, 47, 82},
            {18, 35, 87, 10},
            {20,  4, 82, 47, 65},
            {19,  1, 23, 75,  3, 34},
            {88,  2, 77, 73,  7, 63, 67},
            {99, 65,  4, 28,  6, 16, 70, 92},
            {41, 41, 26, 56, 83, 40, 80, 70, 33},
            {41, 48, 72, 33, 47, 32, 37, 16, 94, 29},
            {53, 71, 44, 65, 25, 43, 91, 52, 97, 51, 14},
            {70, 11, 33, 28, 77, 73, 17, 78, 39, 68, 17, 57},
            {91, 71, 52, 38, 17, 14, 91, 43, 58, 50, 27, 29, 48},
            {63, 66,  4, 68, 89, 53, 67, 30, 73, 16, 69, 87, 40, 31},
            { 4, 62, 98, 27, 23,  9, 70, 98, 73, 93, 38, 53, 60,  4, 23}
    };
    // convert triangle into node objects
    private static final List<List<PathNode>> NODE_ROWS = Arrays.stream(TRIANGLE)
            .map(row -> Arrays.stream(row).mapToObj(PathNode::new).collect(Collectors.toList()))
            .collect(Collectors.toList());

    public static void main(final String[] args) {
        // update the node objects with pointers to their neighbors
        for (int row = 0; row < NODE_ROWS.size() - 1; row++) {
            final List<PathNode> nodeCols = NODE_ROWS.get(row);
            for (int col = 0; col < nodeCols.size(); col++) {
                final PathNode node = nodeCols.get(col);
                final List<PathNode> subRow = NODE_ROWS.get(row + 1);
                node.setNeighbors(subRow.get(col), subRow.get(col + 1));
            }
        }

        /*
         * Run Dijkstra's algorithm finding the shortest path to all nodes
         */
        final PriorityQueue<PathNode> queue = new PriorityQueue<>();
        final PathNode root = NODE_ROWS.get(0).get(0);
        root.distance = 0;
        NODE_ROWS.forEach(queue::addAll);
        while (!queue.isEmpty()) {
            final PathNode current = queue.poll();
            if (current.neighbors == null) {
                continue;
            }
            for (final PathNode neighbor : current.neighbors) {
                final int alternateDistance = current.distance + neighbor.reverseValue;
                if (alternateDistance < neighbor.distance) {
                    neighbor.distance = alternateDistance;
                    neighbor.previous = current;

                    // neighbor's distance has changed, remove and re-add to the priority queue to update it's priority
                    queue.remove(neighbor);
                    queue.add(neighbor);
                }
            }
        }

        // find the minimum path to the last row
        final PathNode lastNode = NODE_ROWS.get(NODE_ROWS.size() - 1)
                .stream()
                .min(Comparator.comparing(PathNode::getDistance))
                .orElseThrow(RuntimeException::new);
        final List<PathNode> shortestPath = Stream.iterate(lastNode, PathNode::getPrevious)
                .takeWhile(Objects::nonNull)
                .collect(Collectors.toList());
        final int totalPath = shortestPath.stream()
                .mapToInt(PathNode::getValue)
                .sum();
        System.out.println(totalPath);
    }

    private static class PathNode implements Comparable {

        private final int value;
        private final int reverseValue;

        private int distance;
        private PathNode previous;
        private List<PathNode> neighbors;

        PathNode(final int value) {
            this.value = value;
            this.reverseValue = VALUE_UPPER_BOUND - value;
            this.distance = Integer.MAX_VALUE;
        }

        int getValue() {
            return value;
        }

        int getDistance() {
            return distance;
        }

        PathNode getPrevious() {
            return previous;
        }

        void setNeighbors(final PathNode left, final PathNode right) {
            this.neighbors = List.of(left, right);
        }

        @Override
        public int compareTo(Object o) {
            return Integer.compare(distance, ((PathNode)o).distance);
        }
    }

}
