package com.ssafy.algo.topological_sorting;

import java.io.*;
import java.util.*;

class Test {
  static int[] D;
  static int[] DP;
  static int[] times;
  static List<List<Integer>> graph;

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;
    StringBuilder sb = new StringBuilder();


    int T = Integer.parseInt(br.readLine());
    for(int t = 0; t < T; t++) {
      st = new StringTokenizer(br.readLine());
      int N = Integer.parseInt(st.nextToken());
      int M = Integer.parseInt(st.nextToken());
      D = new int[N+1];
      DP = new int[N+1];
      times = new int[N+1];
      graph = new ArrayList<>();
      for(int i = 0; i <= N; i++) {
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
        graph.get(a).add(b);
        D[b]++;
      }

      Queue<Integer> q = new ArrayDeque<>();
      for(int i = 1; i <= N; i++) {
        if(D[i] == 0) {
          q.offer(i);
          DP[i] = Math.max(DP[i], times[i]);
        }
      }

      while(!q.isEmpty()) {
        int curr = q.poll();
        for(int next : graph.get(curr)) {
          D[next]--;
          DP[next] = Math.max(DP[curr] + times[next], DP[next]);
          if(D[next] == 0) {
            q.offer(next);
          }
        }
      }
      int result = Integer.parseInt(br.readLine());
      System.out.println(DP[result]);
    }
  }
}