#include <iostream>
#include <vector>
#include <set>
#include <algorithm>

using namespace std;

set<int> greedyVertexCover(vector<pair<int, int>> edges) {
    set<int> cover;
    vector<pair<int, int>> remainingEdges = edges;
    
    while (!remainingEdges.empty()) {
        // Берём первое непокрытое ребро
        int u = remainingEdges[0].first;
        int v = remainingEdges[0].second;
        
        // Добавляем обе вершины в покрытие
        cover.insert(u);
        cover.insert(v);
        
        // Удаляем все рёбра, инцидентные u или v
        vector<pair<int, int>> newEdges;
        for (const auto& e : remainingEdges) {
            if (cover.find(e.first) == cover.end() &&
                cover.find(e.second) == cover.end()) {
                newEdges.push_back(e);
            }
        }
        remainingEdges = newEdges;
    }
    
    return cover;
}

int main() {
    vector<pair<int, int>> edges = {
        {0, 1}, {1, 2}, {2, 3}, {3, 4}, {4, 5},
        {5, 6}, {6, 7}, {7, 8}, {8, 9}, {9, 0}
    };
    
    set<int> cover = greedyVertexCover(edges);
    
    // Вывод результата
    cout << "Размер покрытия: " << cover.size() << endl;
    cout << "Покрытие: ";
    for (int v : cover) {
        cout << v << " ";
    }
    cout << endl;
    
    // Оценка коэффициента аппроксимации
    int optimalSize = 5;
    double approxRatio = static_cast<double>(cover.size()) / optimalSize;
    cout << "Коэффициент аппроксимации: " << approxRatio << endl;
    
    return 0;
}
