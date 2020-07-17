package uf;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class UF {
    private int[] eleAndGroup;

    private int count;

    public UF(int N){
        this.count=N;
        this.eleAndGroup=new int[N];

        for (int i = 0; i < eleAndGroup.length; i++) {
            eleAndGroup[i]=i;
        }
    }

    private int count(){
        return count;
    }

    private int find(int p){
        return eleAndGroup[p];
    }

    private boolean connected(int p,int q){
        return find(p)==find(q);
    }

    private void union(int p,int q){
        if (connected(p,q)){
            return;
        }

        int pGroup=find(p);
        int qGroup=find(q);

        for (int i = 0; i < eleAndGroup.length; i++) {
            if(eleAndGroup[i]==pGroup){
                eleAndGroup[i]=qGroup;
            }
        }
        this.count--;
    }

    public static void main(String[] args) throws IOException {
        UF uf=new UF(5);
        System.out.println(uf.count);
        BufferedReader input=new BufferedReader(new InputStreamReader(System.in));
        while (true){
            System.out.println("input first");
            int p=Integer.parseInt(input.readLine());
            System.out.println("input second");
            int q=Integer.parseInt(input.readLine());
            if(uf.connected(p,q)){
                System.out.println("the same group yet");
                continue;
            }
            uf.union(p,q);
            System.out.println(uf.count());
        }
    }
}
