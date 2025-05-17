package com.ssafy.algo.binarysearch;

import java.io.*;
import java.util.*;

public class Baekjoon17951 {
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());

    int[] arr = new int[N + 1];
    st = new StringTokenizer(br.readLine());
    for(int i = 1; i <= N; i++) {
      arr[i] = arr[i - 1] + Integer.parseInt(st.nextToken());
    }

    int l = 0;
    int r = N;
    int max = -1;
    while(l < r) {
      int mid = (l + r) / 2;

      // 나누어 진다.
      // 안나누어 진다.
      double division = (double) N / mid;
      if((int)Math.ceil(division) == M) {
        int min = Integer.MAX_VALUE;
        for(int i = mid; i <= N; i += mid) {
          //System.out.println(arr[i] - arr[i - mid]);
          min = Math.min(min, arr[i] - arr[i - mid]);
          if(i < N && i + mid > N) {
            min = Math.min(min, arr[N] - arr[i]);
          }
        }
        max = Math.max(min, max);
      }
      //System.out.println(division + ", " + mid);
      if((int)Math.ceil(division) < M) {
        r = mid;
      }else {
        l = mid + 1;
      }
    }
    System.out.println(max);
  }
}
