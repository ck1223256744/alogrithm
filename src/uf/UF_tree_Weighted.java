package uf;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UF_tree_Weighted {
    private int[] eleAndGroup;

    private int count;

    private int[] sz;

    public UF_tree_Weighted(int N){
        this.count=N;
        this.eleAndGroup=new int[N];

        for (int i = 0; i < eleAndGroup.length; i++) {
            eleAndGroup[i]=i;
        }

        this.sz=new int[N];
        for (int i = 0; i < sz.length; i++) {
            sz[i]=1;
        }
    }

    public int count(){
        return count;
    }

    public int find(int p){
        while (true){
            if(p==eleAndGroup[p]){
                return p;
            }
            p=eleAndGroup[p];
        }
    }

    public boolean connected(int p,int q){
        return find(p)==find(q);
    }

    public void union(int p,int q){
        int pRoot = find(p);
        int qRoot = find(q);
        if(pRoot==qRoot){
            return;
        }
        if(sz[pRoot]<sz[qRoot]){
            eleAndGroup[pRoot]=qRoot;
            sz[qRoot]+=sz[pRoot];
        }else {
            eleAndGroup[qRoot]=pRoot;
            sz[pRoot]+=sz[qRoot];
        }
        this.count--;
    }

    public static void main(String[] args) throws IOException {
        UF_tree_Weighted uf=new UF_tree_Weighted(5);
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
