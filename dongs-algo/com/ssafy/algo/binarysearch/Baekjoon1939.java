package com.ssafy.algo.binarysearch;

import java.io.*;
import java.util.*;


public class Baekjoon1939 {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringBuilder sb = new StringBuilder();
  static StringTokenizer st;

  static int n, m, s, e;
  static int[] parent;
  static List<Edge> list;

  static class Edge {
    int from, to, cost;

    Edge(int from, int to, int cost) {
      this.from = from; this.to = to; this.cost = cost;
    }
  }

  static void input() throws Exception {
    st = new StringTokenizer(br.readLine());
    n = Integer.parseInt(st.nextToken());
    m = Integer.parseInt(st.nextToken());

    list = new ArrayList<>();

    while (m-- > 0) {
      st = new StringTokenizer(br.readLine());
      int x = Integer.parseInt(st.nextToken());
      int y = Integer.parseInt(st.nextToken());
      int c = Integer.parseInt(st.nextToken());
      list.add(new Edge(x, y, c));
    }

    st = new StringTokenizer(br.readLine());
    s = Integer.parseInt(st.nextToken());
    e = Integer.parseInt(st.nextToken());
  }

  public static boolean union(int a, int b) {
    int parentA = find(a);
    int parentB = find(b);

    if (parentA == parentB) return false;

    parent[parentB] = parentA;
    return true;
  }

  static int find(int a) {
    if (parent[a] == a) return a;
    return parent[a] = find(parent[a]);
  }

  public static void pro() {
    parent = new int[n + 1];
    for (int i = 1; i <= n; i++) {
      parent[i] = i;
    }

    Collections.sort(list, (o1, o2) -> o2.cost - o1.cost);

    for (Edge edge: list) {
      union(edge.from, edge.to);

      if (find(s) == find(e)) {
        System.out.println(edge.cost);
        break;
      }
    }
  }

  public static void main(String[] args) throws Exception{
    input();
    pro();
  }
}


