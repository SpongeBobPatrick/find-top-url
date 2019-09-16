package com.top.findtop;
import java.util.*;
/**
 * @author zlm
 * @date 2019-09-16 10:13
 * @description Get top k url with minHeap
 */

public class TopK {
    private PriorityQueue<UrlCount> items;
    private int topk = 0;

    /**
     * initialize to size+1， save the element from index 1
     */

    public TopK(int size) {
        this.items = new PriorityQueue<>(size + 1);
        this.topk = size;
    }

    /**
     * insert the urlCount into minHeap
     */
    public void insert(UrlCount urlCount) {
        this.items.add(urlCount);
        if(this.items.size() > this.topk){
            this.items.poll();
        }
    }

    /**
     * return top k urls
     * @return ArrayList
     */
    public ArrayList<UrlCount> getTopUrls(){
        ArrayList<UrlCount> list = new ArrayList<>(this.topk);
        this.items.stream().forEach(list::add);
        return list;
    }

}
