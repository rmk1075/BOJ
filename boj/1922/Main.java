import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Edge implements Comparable<Edge> {
    int u, v, w;
    Edge(int u, int v, int w) {
        this.u = u;
        this.v = v;
        this.w = w;
    }

    @Override
    public int compareTo(Edge o) {
        return this.w - o.w;
    }
}

public class Main {
    static int N, M, parents[];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        parents = new int[N+1];
        for(int i = 1; i <= N; i++) parents[i] = i;

        PriorityQueue<Edge> edges = new PriorityQueue<>();
        StringTokenizer st;
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            edges.offer(new Edge(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        int cnt = 0, ans = 0;
        while(cnt < N-1) {
            Edge edge = edges.poll();
            int a = find(edge.u), b = find(edge.v);
            if(a != b) {
                parents[b] = a;
                ans += edge.w;
                cnt++;
            }
        }

        System.out.println(ans);
    }

    public static int find(int node) {
        if(parents[node] == node) return node;
        return parents[node] = find(parents[node]);
    }
}