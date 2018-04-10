package com.beautiful.api.row;

import com.beautiful.api.column.DataColumn;
import com.beautiful.api.writable.WritableValue;

import java.io.Serializable;

/**
 * @Description:
 * @Author: zhuyuping
 * @CreateDate: 2018/4/10 13:28
 **/
public interface DataRow extends Serializable {

    DataColumn[] getColumns();

    WritableValue get(int index);

    WritableValue get(String name);


}
