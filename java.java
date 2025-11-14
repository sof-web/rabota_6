import java.util.*;

public class VertexCover {
    public static Set<Integer> greedyVertexCover(List<int[]> edges) {
        Set<Integer> cover = new HashSet<>();
        List<int[]> remainingEdges = new ArrayList<>(edges);
        
        while (!remainingEdges.isEmpty()) {
            // Берём первое непокрытое ребро
            int[] edge = remainingEdges.get(0);
            int u = edge[0];
            int v = edge[1];
            
            // Добавляем обе вершины в покрытие
            cover.add(u);
            cover.add(v);
            
            // Удаляем все рёбра, инцидентные u или v
            List<int[]> newEdges = new ArrayList<>();
            for (int[] e : remainingEdges) {
                if (!cover.contains(e[0]) && !cover.contains(e[1])) {
                    newEdges.add(e);
                }
            }
            remainingEdges = newEdges;
        }
        
        return cover;
    }
    
    public static void main(String[] args) {
        List<int[]> edges = Arrays.asList(
            new int[]{0, 1}, new int[]{1, 2}, new int[]{2, 3},
            new int[]{3, 4}, new int[]{4, 5}, new int[]{5, 6},
            new int[]{6, 7}, new int[]{7, 8}, new int[]{8, 9},
            new int[]{9, 0}
        );
        
        Set<Integer> cover = greedyVertexCover(edges);
        
        // Вывод результата
        System.out.println("Размер покрытия: " + cover.size());
        System.out.print("Покрытие: ");
        List<Integer> sortedCover = new ArrayList<>(cover);
        Collections.sort(sortedCover);
        for (int v : sortedCover) {
            System.out.print(v + " ");
        }
        System.out.println();
        
        // Оценка коэффициента аппроксимации
        int optimalSize = 5;
        double approxRatio = (double) cover.size() / optimalSize;
        System.out.println("Коэффициент аппроксимации: " + approxRatio);
    }
}
