package com.beautiful.data.vec.basic.vec.dataframe

import com.beautiful.api.ops._
import org.apache.spark.sql.DataFrame

/**
  *
  * @Description:
  * @Author: zhuyuping
  * @CreateDate: 2018/4/12 21:38
  *
  **/
class DataFrames {


}

object DataFrames {

  implicit class PreDataFrame(dataframe: DataFrame) {
    def preLoader(loader: Loader): this.type = {

      this
    }

    def preFilter(filter: Filter): this.type = {

      this
    }

    def preTransformer(transformer: Transform): this.type = {

      this
    }

    def preRanker(ranker: Ranker): this.type = {

      this
    }

    def preGrouper(group: Grouper): this.type = {

      this
    }

    def preAggregater(aggregater: Aggregater): this.type = {

      this
    }


  }

}
