package uf;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UF_tree {
    private int[] eleAndGroup;

    private int count;

    public UF_tree(int N){
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
        while (true){
            if(p==eleAndGroup[p]){
                return p;
            }
            p=eleAndGroup[p];
        }
    }

    private boolean connected(int p,int q){
        return find(p)==find(q);
    }

    private void union(int p,int q){
        int pRoot = find(p);
        int qRoot = find(q);
        if(pRoot==qRoot){
            return;
        }
        eleAndGroup[pRoot]=qRoot;
        this.count--;
    }

    public static void main(String[] args) throws IOException {
        UF_tree uf=new UF_tree(5);
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
