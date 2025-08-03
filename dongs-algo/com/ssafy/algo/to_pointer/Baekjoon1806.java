package com.ssafy.algo.to_pointer;

import java.io.*;
import java.util.*;


/**
 * 저번에 풀어봤지만 다시 풀었을 때 애먹었던 문제
 * 문제 자체의 접근은 쉬웠는데 테케 1개를 테스트 안해봐서 계속 틀렸다.
 * 10 10
 * 1 1 1 1 1 1 1 1 1 1
 * 문제 풀 때 항상 극단적인 테케 먼저 만들고 시작하기
 */
public class Baekjoon1806 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] arr = new int[N+1];
        st = new StringTokenizer(br.readLine());
        int max = -1;
        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            max = Math.max(arr[i], max);
        }
        //Arrays.sort(arr);
        int l = 0;
        int r = 2;
        if(max >= M) {
            System.out.println(1);
            return;
        }
        long sum = arr[0] + arr[1];
        int min = Integer.MAX_VALUE;
        while(r <= N) {
            // 당겨질땐 -l
            // 밀어낼땐 +r
            if(sum >= M) {
                min = Math.min(min, r - l);
            }
            if(sum > M) {
                sum -= arr[l++];
            } else {
                sum += arr[r++];
            }
        }
        min = min == Integer.MAX_VALUE ? 0 : min;
        System.out.println(min);
    }
}