package com.ssafy.algo.binarysearch;

import java.io.*;
import java.util.*;

public class Baekjoon12757 {
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    TreeMap<Integer, Integer> map = new TreeMap<>();
    StringTokenizer st = new StringTokenizer(br.readLine());
    StringBuilder sb = new StringBuilder();
    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());
    int K = Integer.parseInt(st.nextToken());

    for(int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      map.put(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
    }

    for(int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());

      int command = Integer.parseInt(st.nextToken());
      if(command == 1) {
        map.put(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
      }else if(command == 2) {
        // 조건을 만족하는 키가 있을 때
        int k = Integer.parseInt(st.nextToken());
        int v = Integer.parseInt(st.nextToken());
        if(map.get(k) != null) {
          map.replace(k, v);
        } else {
          Integer l = map.floorKey(k);
          Integer r = map.ceilingKey(k);
          if(l == null && r == null) continue;
          Integer left = Integer.MAX_VALUE;
          Integer right = Integer.MAX_VALUE;
          if(l != null) {
            left = k - l;
          }
          if(r != null) {
            right = r - k;
          }
          if(left == right && left <= K) {
            continue;
          }

          if(left <= K && left < right) {
            map.put(l, v);
          } else if(right <= K && right < left) {
            map.put(r, v);
          }

        }
      }else {
        int k = Integer.parseInt(st.nextToken());
        if(map.get(k) != null) {
          sb.append(map.get(k));
        } else {
          Integer l = map.floorKey(k);
          Integer r = map.ceilingKey(k);
          if(l == null && r == null) {
            sb.append(-1).append("\n");
            continue;
          }
          Integer left = Integer.MAX_VALUE;
          Integer right = Integer.MAX_VALUE;
          if(l != null) {
            left = k - l;
          }
          if(r != null) {
            right = r - k;
          }

          if(left == right && left <= K) {
            sb.append("?").append("\n");
            continue;
          }

          if(left <= K && left < right) {
            sb.append(map.get(l));
          }else if(right <= K && right < left) {
            sb.append(map.get(r));
          }else {
            sb.append(-1);
          }
        }
        sb.append("\n");
      }
    }
    System.out.println(sb);
  }
}