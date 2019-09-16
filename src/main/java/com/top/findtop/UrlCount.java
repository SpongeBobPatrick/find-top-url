package com.top.findtop;

/**
 * @author zlm
 * @date 2019-09-16 16:10
 * @description Each url and the number of occurrences
 */

public class UrlCount implements  Comparable {
    private String url;

    private Long count;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    @Override
    public int compareTo(Object o) {
        //return ((UrlCount)o).getCount().compareTo(count);
        return count.compareTo(((UrlCount)o).getCount());
    }
}
