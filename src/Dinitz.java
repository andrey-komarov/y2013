import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;

public class Dinitz {

    static final int INF = Integer.MAX_VALUE / 2;

    static class Graph {
        /* Ребро. remain --- сколько ещё можно по нему пустить
         * rev --- обратное ребро. У каждого ребра есть обратное,
         * пуск потока по которому соответствует отмене потока
         * через прямое
         */
        static class Edge {
            int to;
            int remain;
            Edge rev;

            Edge(int to, int remain) {
                this.to = to;
                this.remain = remain;
            }
        }

        int n;
        ArrayList<Edge>[] edges;

        /* Слой, в котором находится вершина. Используется для поиска блокирующего
            потока
         */
        int[] layer;

        /* Массив "посещена ли вершина обходом в глубину, ищущим дополняющий путь"
         *
         * Выглядит он так странно потому, что хочется его быстро очищать
         * читай used[v] == true, если used[v] == version.
         * Теперь для очистки нужно всего лишь сделать version++. Тогда все
         * автоматически сбросятся в false
         */
        int[] used;
        int version;

        /* Тут магия.
        *
        * start[v] - номер первого ребра, выходящего из вершины v, которое
        * осмысленно рассматривать. При рассмотрении рёбер
        * edges[v][0..start[v]) нельзя найти дополняющий путей.
        * */
        int[] start;

        Graph(int n) {
            this.n = n;
            edges = new ArrayList[n];
            for (int i = 0; i < n; i++) {
                edges[i] = new ArrayList<>();
            }

            used = new int[n];
            version = 1;

            start = new int[n];
        }

        /* Добавить ребро from->to пропускной способности cap
         * При этом, добавляется обратное ребро, по которому можно
         * пустить ещё 0 ("можно отменить 0 потока")
         */
        void addEdge(int from, int to, int cap) {
            Edge e = new Edge(to, cap);
            Edge e2 = new Edge(from, 0);
            e.rev = e2;
            e2.rev = e;
            edges[from].add(e);
            edges[to].add(e2);
        }

        /* "Есть ли вообще путь s ~> t?"
         *
         * А заодно, перенумеровать слои
         */
        boolean bfs(int s, int t) {
            layer = new int[n];
            Arrays.fill(layer, -1);
            Queue<Integer> q = new ArrayDeque<>();
            q.add(s);
            layer[s] = 0;
            while (!q.isEmpty()) {
                int v = q.poll();
                for (int i = 0; i < edges[v].size(); i++) {
                    Edge e = edges[v].get(i);
                    if (e.remain == 0) {
                        continue;
                    }
                    if (layer[e.to] == -1) {
                        layer[e.to] = layer[v] + 1;
                        q.add(e.to);
                    }
                }
            }
            return layer[t] != -1;
        }

        /* "Найти дополняющий путь v ~> t и обновить поток по нему, с учётом того,
         * что до v дотекает всего min потока. Вернуть, сколько потока добавилось"
         */
        int dfs(int v, int t, int min) {
            // Нашли
            if (v == t) {
                return min;
            }
            used[v] = version;
            /* Обещанная магия. Начинаем рассматривать рёбра не с первого, а с
             * первого осмысленного.
             *
             * Если при дальнейшем рассмотрении нашёлся дополняющий путь, то
             * start[v] не двигается (текущее ребро всё ещё осмысленно).
             * Если же не нашёлся, то и до следующего пересчёта слоёв не найдётся,
             * и его можно выкинуть.
             */
            for (; start[v] < edges[v].size(); start[v]++) {
                Edge e = edges[v].get(start[v]);
                if (e.remain == 0 || layer[e.to] != layer[v] + 1) {
                    continue;
                }
                int add;
                if ((add = dfs(e.to, t, Math.min(min, e.remain))) != 0) {
                    // Пускаем поток. add - сколько добавилось.
                    e.remain -= add;
                    // Столько же можно отменить.
                    e.rev.remain += add;
                    return add;
                }
            }
            return 0;
        }

        long maxFlow(int s, int t) {
            long flow = 0;
            // Пока вообще есть путь...
            while (bfs(s, t)) {
                long add;
                version++;
                Arrays.fill(start, 0);
                // Пока не нашли блокирующий поток...
                while ((add = dfs(s, t, INF)) != 0) {
                    flow += add;
                    version++;
                }
            }
            return flow;
        }
    }
}
