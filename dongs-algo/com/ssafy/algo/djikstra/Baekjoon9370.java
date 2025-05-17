package com.ssafy.algo.djikstra;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Baekjoon9370 {
  static int[] dist;
  static int[] prev;
  static List<List<Node>> graph;
  static int N, M;
  static class Node implements Comparable<Node> {
    int v, d;
    public Node(int v, int d) {
      this.v = v;
      this.d = d;
    }

    public int compareTo(Node other) {
      return Integer.compare(this.d, other.d);
    }
  }
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();
    StringTokenizer st;
    int T = Integer.parseInt(br.readLine());
    for(int t = 0; t < T; t++) {
      st = new StringTokenizer(br.readLine());
      N = Integer.parseInt(st.nextToken());
      M = Integer.parseInt(st.nextToken());
      int eCount = Integer.parseInt(st.nextToken());
      st = new StringTokenizer(br.readLine());
      int s = Integer.parseInt(st.nextToken());
      int g = Integer.parseInt(st.nextToken());
      int h = Integer.parseInt(st.nextToken());
//      System.out.println(N);
      init();
//      System.out.println(graph);
////      graph.get(N);
//      System.out.println(11231231);
      for(int i = 0; i < M; i++) {
        st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());
        addEdge(a, b, w);
      }
      int[] dist_s = djikstra(s); // 2 -> 1, 2 -> 6, 2 -> 5
      int[] dist_g = djikstra(g); // 1 -> 3
      int[] dist_h = djikstra(h); // 3 -> 6


      int gh = 0;
      for(Node n : graph.get(g)) {
        if(n.v == h) {
          gh = n.d;
          break;
        }
      }
      List<Integer> results = new ArrayList<>();
      for(int i = 0; i < eCount; i++) {
        int e = Integer.parseInt(br.readLine());
        int sum1 = dist_s[g] + gh + dist_h[e]; // s -> g -> h -> e
        int sum2 = dist_s[h] + gh + dist_g[e]; // s -> h -> g -> e
        int min = Math.min(sum1, sum2);
        if(min == dist_s[e]) {
          results.add(e);
        }
      }

      Collections.sort(results);
      for(int i = 0; i < results.size(); i++) {
        sb.append(results.get(i)).append(" ");
      }
      sb.append("\n");
    }
    System.out.println(sb);
  }

  private static boolean isPath(int start, int end, int g, int h) {
    List<Integer> path = new ArrayList<>();

    // 경로가 존재하지 않으면 false 반환
    if(dist[end] == Integer.MAX_VALUE) return false;

    // 무한 루프 방지를 위한 방문 체크
    boolean[] visited = new boolean[N+1];

    // 역추적으로 경로 구성
    for (int at = end; at != -1 && !visited[at]; at = prev[at]) {
      path.add(at);
      visited[at] = true;
    }

    // 경로를 시작 -> 도착 순서로 변경
    Collections.reverse(path);

    // 경로 내에서 g->h 또는 h->g 직접 간선이 있는지 확인
    for (int i = 0; i < path.size() - 1; i++) {
      if ((path.get(i) == g && path.get(i + 1) == h) ||
          (path.get(i) == h && path.get(i + 1) == g)) {
        return true;
      }
    }

    return false;
  }

  public static void addEdge(int a, int b, int w) {
    graph.get(a).add(new Node(b, w));
    graph.get(b).add(new Node(a, w));
  }

  public static int[] djikstra(int s) {
    PriorityQueue<Node> pq = new PriorityQueue<>();
    dist = new int[N + 1];
    prev = new int[N + 1];
    Arrays.fill(dist, 100_000_000);
    Arrays.fill(prev, -1);
    dist[s] = 0;
    pq.offer(new Node(s, 0));

    while(!pq.isEmpty()) {
      Node curr = pq.poll();
      if(curr.d > dist[curr.v]) continue;
      for(Node next : graph.get(curr.v)) {
        int newDistance = next.d + dist[curr.v];
        if(dist[next.v] > newDistance) {
          dist[next.v] = newDistance;
          prev[next.v] = curr.v;
          pq.offer(new Node(next.v, dist[next.v]));
        }
      }
    }
    return dist;
  }

  public static void init() {
    graph = new ArrayList<>();
    for(int i = 0; i <= N; i++) {
      graph.add(new ArrayList<>());
    }
  }
}
