package com.ssafy.algo.segment_tree;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon2042 {

  static long[] tree;

  public static void main(String[] args) throws  Exception{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();
    StringTokenizer st = new StringTokenizer(br.readLine());
    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());
    int K = Integer.parseInt(st.nextToken());
    int[] arr = new int[N + 1];
    tree = new long[N * 4];

    for (int i = 1; i <= N; i++) {
      arr[i] = Integer.parseInt(br.readLine());
    }
    build(arr, 1, 1, N);
    for(int i = 0; i < M + K; i++) {
      st = new StringTokenizer(br.readLine());
      switch (Integer.parseInt(st.nextToken())) {
        case 1:
          update(1, 1, N, Integer.parseInt(st.nextToken()), Long.parseLong(st.nextToken()));
          break;
        case 2:
          sb.append(sum(1, 1, N, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()))).append("\n");
          break;
      }
    }
    System.out.println(sb);
  }

  public static void build(int[] arr, int node, int start, int end) {
    if(start == end) {
      tree[node] = arr[start];
      return;
    }
    int mid = (start + end) / 2;

    build(arr, node * 2, start, mid);
    build(arr, node * 2 + 1, mid + 1, end);
    tree[node] = tree[node * 2] + tree[node * 2 + 1];
  }

  public static long sum(int node, int start, int end, int left, int right) {
    // 현재 범위의 끝이 찾고자하는 시작점보다 작으면
    // 현재 범위의 시작이 찾고자하는 끝점보다 크다면
    // right < start
    // left > end
    if(right < start || left > end) {
      return 0;
    }

    if(left <= start && right >= end) {
      return tree[node];
    }

    int mid = (start + end) / 2;

    return sum(node * 2, start, mid, left, right) +
        sum(node * 2 + 1, mid + 1, end, left, right);
  }

  public static void update(int node, int start, int end, int idx, long newValue) {
    if(end == start) {
      tree[node] = newValue;
      return;
    }

    int mid = (start + end) / 2;
    if(idx <= mid) {
      update(node * 2, start, mid, idx, newValue);
    }else {
      update(node * 2 + 1, mid + 1, end, idx, newValue);
    }
    tree[node] = tree[node * 2] + tree[node * 2 + 1];
  }

}
