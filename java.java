import java.util.*;

public class VertexCoverApproximation {

    public static Set<Integer> greedyVertexCover(List<int[]> edges, int nVertices) {
        Set<Integer> cover = new HashSet<>();
        Set<List<Integer>> remainingEdges = new HashSet<>();

        for (int[] edge : edges) {
            int u = Math.min(edge[0], edge[1]);
            int v = Math.max(edge[0], edge[1]);
            remainingEdges.add(Arrays.asList(u, v));
        }

        while (!remainingEdges.isEmpty()) {
            List<Integer> edge = remainingEdges.iterator().next();
            int u = edge.get(0);
            int v = edge.get(1);
            remainingEdges.remove(edge); 

            cover.add(u);
            cover.add(v);

            remainingEdges.removeIf(e -> e.get(0) == u || e.get(0) == v || e.get(1) == u || e.get(1) == v);
        }
        return cover;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Введите количество вершин (n) и количество ребер (m): ");
        int nVertices, nEdges;
        try {
            nVertices = sc.nextInt();
            nEdges = sc.nextInt();
        } catch (InputMismatchException e) {
            System.err.println("Некорректный ввод для количества вершин и ребер.");
            sc.close();
            return;
        }

        if (nVertices <= 0 || nEdges < 0) {
            System.err.println("Некорректные значения количества вершин или ребер.");
            sc.close();
            return;
        }

        System.out.println("Введите " + nEdges + " ребер в формате 'u v' (номера вершин от 0 до " + (nVertices - 1) + "):");
        List<int[]> edges = new ArrayList<>();
        for (int i = 0; i < nEdges; i++) {
            try {
                int u = sc.nextInt();
                int v = sc.nextInt();
                if (u >= 0 && u < nVertices && v >= 0 && v < nVertices) {
                    edges.add(new int[]{u, v});
                } else {
                    System.err.println("Номер вершины в ребре " + (i + 1) + " выходит за пределы [0, " + (nVertices - 1) + "]. Пропущено.");
                }
            } catch (InputMismatchException e) {
                System.err.println("Некорректный ввод для ребра " + (i + 1) + ". Пропущено.");
                sc.nextLine(); 
            }
        }

        Set<Integer> cover = greedyVertexCover(edges, nVertices);

        System.out.println("Размер приближенного вершинного покрытия: " + cover.size());
        System.out.print("Вершины покрытия: ");
        List<Integer> sortedCover = new ArrayList<>(cover);
        Collections.sort(sortedCover);
        for (int v : sortedCover) {
            System.out.print(v + " ");
        }
        System.out.println();

        sc.close();
    }
}
