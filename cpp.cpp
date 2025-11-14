#include<iostream>
#include<set>
#include<vector>
#include<algorithm>
#include<utility> // for std::pair
#include<limits> // for std::numeric_limits

using namespace std;

set<int> greedyVertexCover(vector<pair<int, int>>& edges, int n_vertices){
    set<int> cover;
    set<pair<int, int>> remainingEdges;

    // Инициализировать множество ребер, убедиться, что u <= v для упрощения
    for(auto& edge: edges){
        int u = min(edge.first, edge.second);
        int v = max(edge.first, edge.second);
        remainingEdges.insert({u, v});
    }

    while(!remainingEdges.empty()){
        // Выбрать первое ребро из множества
        auto it = remainingEdges.begin();
        int u = it->first;
        int v = it->second;
        remainingEdges.erase(it); // Удаляем выбранное ребро

        // Добавить обе вершины в покрытие
        cover.insert(u);
        cover.insert(v);

        // Удалить инцидентные ребра
        // Создаем вектор ребер для удаления, чтобы не модифицировать set во время итерации
        vector<pair<int, int>> to_remove;
        for(const auto& e : remainingEdges){
            if(e.first == u || e.first == v || e.second == u || e.second == v){
                to_remove.push_back(e);
            }
        }
        for(const auto& e : to_remove){
            remainingEdges.erase(e);
        }
    }
    return cover;
}

int main(){
    int n_vertices, n_edges;
    cout << "Введите количество вершин (n) и количество ребер (m): ";
    if(!(cin >> n_vertices >> n_edges)){
        cerr << "Некорректный ввод для количества вершин и ребер." << endl;
        return 1;
    }

    if(n_vertices <= 0 || n_edges < 0){
        cerr << "Некорректные значения количества вершин или ребер." << endl;
        return 1;
    }

    cout << "Введите " << n_edges << " ребер в формате 'u v' (номера вершин от 0 до " << n_vertices - 1 << "):" << endl;
    vector<pair<int, int>> edges;
    for(int i = 0; i < n_edges; i++){
        int u, v;
        if(!(cin >> u >> v)){
             cerr << "Некорректный ввод для ребра " << i+1 << "." << endl;
             // Пропустим оставшийся ввод или очистим буфер
             cin.clear();
             cin.ignore(numeric_limits<streamsize>::max(), '\n');
             continue;
        }
        if(u >= 0 && u < n_vertices && v >= 0 && v < n_vertices){
            edges.push_back({u, v});
        } else {
            cerr << "Номер вершины в ребре " << i+1 << " выходит за пределы [0, " << n_vertices - 1 << "]. Пропущено." << endl;
        }
    }

    set<int> cover = greedyVertexCover(edges, n_vertices);

    cout << "Размер приближенного вершинного покрытия: " << cover.size() << endl;
    cout << "Вершины покрытия: ";
    for(int v : cover){
        cout << v << " ";
    }
    cout << endl;

    return 0;
}
