package com.cm.stream.realtime.v2.app.ods;

import com.cm.stream.realtime.v1.utils.FlinkSinkUtil;
import com.cm.stream.realtime.v1.utils.FlinkSourceUtil;
import com.ververica.cdc.connectors.mysql.source.MySqlSource;
import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.connector.kafka.sink.KafkaSink;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

/**
 * @Package com.cm.stream.realtime.v1.app.ods.MysqlToKafka
 * @Author chen.ming
 * @Date 2025/4/17 9:00
 * @description: MysqlToKafka
 */

public class MysqlToKafka {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(2);

        MySqlSource<String> realtimeV1 = FlinkSourceUtil.getMySqlSource("realtime_v1", "*");

        DataStreamSource<String> mySQLSource = env.fromSource(realtimeV1, WatermarkStrategy.noWatermarks(), "MySQL Source");

//        mySQLSource.print();

        KafkaSink<String> topic_db = FlinkSinkUtil.getKafkaSink("chenming_db");

        mySQLSource.sinkTo(topic_db);

        env.execute("Print MySQL Snapshot + Binlog");

    }
}



