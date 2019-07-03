package com.beautiful.common.model;

import java.io.Serializable;

/**
 * @description: ${description}
 * @author: zhuyuping
 * @create: 2018-05-18 16:48
 **/
public class TuplePair<L, R> implements Serializable {

    private L left;

    private R right;


    public TuplePair() {
    }

    public TuplePair(L left, R right) {
        this.left = left;
        this.right = right;
    }

    public L getLeft() {
        return left;
    }

    public void setLeft(L left) {
        this.left = left;
    }

    public R getRight() {
        return right;
    }

    public void setRight(R right) {
        this.right = right;
    }
}
