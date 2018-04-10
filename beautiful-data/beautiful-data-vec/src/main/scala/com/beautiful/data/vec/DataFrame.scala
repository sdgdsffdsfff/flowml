package com.beautiful.data.vec


import com.beautiful.api.block.DataBlock
import com.beautiful.api.column.DataColumn
import com.beautiful.api.index.Index
import com.beautiful.api.ops._
import com.beautiful.api.row.DataRow
import com.beautiful.api.writable.WritableValue
import com.beautiful.api.writable.Writables.WritableValueLike
import shapeless.T


/**
  *
  * @Description:
  * @Author: zhuyuping
  * @CreateDate: 2018/4/9 13:21
  *
  **/
trait DataFrame {

  def toList: Seq[Seq[WritableValue]]

  def add(column: DataColumn, values: Seq[WritableValue])

  def add(rows: Seq[DataRow]): DataFrame

  def drop: DataFrame

  def rename

  def reshape(rows: Int, cols: Int)

  def transpose: DataFrame

  def retain: DataFrame

  def reindex: DataFrame

  def merge: DataFrame

  def join: DataFrame

  def update: DataFrame

  def size: Int = getColumns.length

  def isEmpty: Boolean = length == 0

  def length: Int = toList.length

  def fillna[T: WritableValueLike](v: T)

  def dropna: DataFrame

  def downsample: DataFrame

  def oversample: DataFrame

  def conversion: DataFrame

  def semiSupervised: DataFrame

  def getIndex: Index

  def getColumns: Array[DataColumn]

  def getColumn(name: String): DataColumn

  def getColumn(index: Int): DataColumn

  def groupBy(grouper: Grouper*): DataFrame

  def reducer(reducer: AggregateReducer*): DataFrame

  def sort(sort: Ranker): DataFrame

  def map(transform: Transform): DataFrame

  def filter(filter: Filter): DataFrame

  def from(loader: Loader): DataFrame

  def to(export: Exporter): DataFrame

  def get(row: Int, col: Int): WritableValue

  def slice(rowStart: Int, rowEnd: Int)

  def vizPlot(xColumn: Int, yColumn: Int): Unit

  def vizCount(xColumn: Int, yColumn: Int): Unit

  def print(): Unit


}

abstract class BasicDataFrame(var index: Index, var columns: Seq[DataColumn], var datas: DataBlock[T]) extends DataFrame {


}


