package Graphs.BFSorDFS;

import java.util.ArrayList;
import java.util.List;

public class Pair {
    private int first;
    private int second;
    private int third;
    private int fourth;
    private String str;
    private List<String> list;

    public Pair(int u, int v, int w) {
        this.first = u;
        this.second = v;
        this.third = w;
    }

    public Pair(int u, int v) {
        this.first = u;
        this.second = v;
    }

    public Pair(int u, int v, int w, int x) {
        this.first = u;
        this.second = v;
        this.third = w;
        this.fourth = x;
    }

    public Pair(int i, String str) {
        this.first = i;
        this.str = str;
    }

    public Pair(int i,String s , ArrayList<String> list) {
        this.first = i;
        this.str = s;
        this.list = list;
    }

    public int getFirst() {
        return first;
    }

    public int getSecond() {
        return second;
    }

    public int getThird() {
        return third;
    }

    public int getFourth() {
        return fourth;
    }

    public String getStr() {
        return str;
    }

    public List<String> getList() {
        return list;
    }

}
