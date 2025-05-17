package com.ssafy.algo.union_find;

import java.util.*;
import java.io.*;

public class Baekjoon10775{

  static int[] parent;

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int G = Integer.parseInt(br.readLine());
    int P = Integer.parseInt(br.readLine());
    parent = new int[G + 1];
    for(int i = 1; i <= G; i++) parent[i] = i;

    int count = 0;
    for(int i = 0; i < P; i++) {
      int g = Integer.parseInt(br.readLine());
      int gate = find(g);
      if(gate == 0) break;
      count++;
      union(gate - 1, gate);
    }
    System.out.println(count);
  }
  public static void union(int x, int y) {
    x = find(x);
    y = find(y);
    if(x != y) parent[y] = x;
  }
  public static int find(int x) {
    if(parent[x] == x) {
      return x;
    }
    return parent[x] = find(parent[x]);
  }
}