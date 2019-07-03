package com.beautiful.api.block;

import com.beautiful.api.writable.WritableValue;
import com.google.common.collect.Lists;

import java.io.Serializable;
import java.util.List;

/**
 * @Description:抽象 可以 扩展 这是行列形式
 * @Author: zhuyuping
 * @CreateDate: 2018/4/10 13:46
 **/
public class DataBlock<T extends WritableValue> implements Serializable {

    private List<List<T>> blocks = Lists.newArrayList();


    public List<List<T>> getBlocks() {
        return blocks;
    }

    public void setBlocks(List<List<T>> blocks) {
        this.blocks = blocks;
    }

    public T get(final int row, final int col) {
        return blocks.get(row).get(col);
    }

    public void set(final T value, final int row, final int col) {
        blocks.get(row).set(col, value);
    }

    //public void addHorizontal()

    public void addVertical(List<WritableValue> values) {
        for (List<T> block : blocks) {

        }
    }

    /**
     * 行
     *
     * @return
     */
    public int rows() {
        return blocks.size();
    }

    /**
     * 列
     *
     * @return
     */
    public int cols() {
        return blocks.isEmpty() ? 0 : blocks.get(0).size();
    }

}
