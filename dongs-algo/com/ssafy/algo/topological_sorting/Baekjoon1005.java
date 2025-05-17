package com.ssafy.algo.topological_sorting;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;
import org.w3c.dom.Node;

public class Baekjoon1005 {

  static int[] D;
  static int[] DP;
  static int[] times;
  static int[] parents;
  static List<List<Node>> graph;
  static class Node {
    int v, p;
    public Node(int v, int p) {
      this.v = v;
      this.p = p;
    }
  }
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();
    StringTokenizer st;
    int T = Integer.parseInt(br.readLine());
    for(int t = 0; t < T; t++) {
      st = new StringTokenizer(br.readLine());
      int N = Integer.parseInt(st.nextToken());
      int M = Integer.parseInt(st.nextToken());
      graph = new ArrayList<>();
      parents = new int[N+1];
      DP = new int[N+1];
      D = new int[N+1];
      times = new int[N+1];
      for(int i = 0 ; i <= N; i++) {
        graph.add(new ArrayList<>());
      }

      st = new StringTokenizer(br.readLine());
      for(int i = 1; i <= N; i++) {
        times[i] = Integer.parseInt(st.nextToken());
      }

      for(int i = 0; i < M; i++) {
        st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        graph.get(a).add(new Node(b, 0));
        D[b]++;
      }

      Queue<Node> q = new ArrayDeque<>();
      for(int i = 0; i < D.length; i++) {
        parents[i] = D[i] + 1;
        if(D[i] == 0) {
          q.offer(new Node(i, 0));
          DP[i] = Math.max(times[i], DP[0]);
        }
      }
      while(!q.isEmpty()) {
        Node n = q.poll();
        int curr = n.v;
        // 계산
        // parents이전DP + 현재타임 < parents지금DP
        for(Node next : graph.get(curr)) {
          if(DP[curr] + times[next.v] > DP[next.v]) {
            DP[next.v] = DP[curr] + times[next.v];
          }
          D[next.v]--;
          if(D[next.v] == 0) {
            q.offer(new Node(next.v, 0));
          }
        }
      }
      int result = Integer.parseInt(br.readLine());
      sb.append(DP[result]).append("\n");
    }
    System.out.println(sb);
  }
}
