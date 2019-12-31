package com.pangpang6.hadoop.flink.apps;

import org.apache.avro.Schema;
import org.apache.avro.data.Json;
import org.apache.flink.api.common.restartstrategy.RestartStrategies;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.streaming.api.CheckpointingMode;
import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.environment.CheckpointConfig;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.table.api.Table;
import org.apache.flink.table.api.TableEnvironment;
import org.apache.flink.table.api.TableSchema;
import org.apache.flink.table.api.Types;
import org.apache.flink.table.api.java.StreamTableEnvironment;
import org.apache.flink.table.descriptors.Rowtime;
import org.apache.flink.table.expressions.StreamRecordTimestamp;
import org.apache.flink.table.sources.tsextractors.ExistingField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RtAdCvsnOrderCommonSQLMain {
//    private static final Logger logger = LoggerFactory.getLogger(RtAdCvsnOrderCommonSQLMain.class);
//
//    private static final String TOPIC_CONSUMER = "xxx";
//    private static final String TOPIC_PRODUCER = "xxx";
//    private static final String NAMESPACE = "xxx";
//
//    public static void main(String[] args) throws Exception {
//        /** env **/
//        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
//        StreamTableEnvironment tableEnv = TableEnvironment.getTableEnvironment(env);
//
//        env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime);
//
//        /** params **/
//        ParameterTool params = ParameterTool.fromArgs(args);
//        boolean isDisableChain = params.getBoolean("isDisableChain", false);
//        if (isDisableChain) env.disableOperatorChaining();
//        long checkpoint = params.getLong("checkpoint", MyConstants.FLINK_CHECKPOINT);
//        if (checkpoint > 0) {
//            env.enableCheckpointing(checkpoint, CheckpointingMode.EXACTLY_ONCE);
//            //设置两次checkpoint最小间隔
//            env.getCheckpointConfig().setMinPauseBetweenCheckpoints(10000);
//            //设置checkpoint超时时间
//            env.getCheckpointConfig().setCheckpointTimeout(MyConstants.FLINK_TIMEOUT);
//            //flink作业呗cacel后依然保留检查点
//            env.getCheckpointConfig().enableExternalizedCheckpoints(CheckpointConfig.ExternalizedCheckpointCleanup.RETAIN_ON_CANCELLATION);
//        }
//        // 固定延迟重启策略 默认尝试重启作业3次，重启间隔为30秒
//        int restartAttempt = params.getInt("restart_attempts", 3);
//        int restartInterval = params.getInt("restart_interval", 30);
//        if (restartAttempt > 0 && restartInterval > 0) {
//            env.setRestartStrategy(RestartStrategies.fixedDelayRestart(restartAttempt, restartInterval * 1000));
//        }
//
//        /** source **/
//        String fromOffset = params.get("from_offset", "groupOffsets");
//        MTKafka mtKafkaHandler = new MTKafka(args);
//
//        Kafka sourceTopic0 = mtKafkaHandler.getReadKafkaByTopic(TOPIC_CONSUMER, NAMESPACE);
//        if (fromOffset.equals("earliest")) {
//            sourceTopic0.startFromEarliest();
//        } else if (fromOffset.equals("latest")) {
//            sourceTopic0.startFromLatest();
//        } else if (fromOffset.equals("groupOffsets")) {
//            sourceTopic0.startFromGroupOffsets();
//        }
//
//        //JSON Format 的三种格式
//        //1.通过flink的数据类型定义
//        String[] fieldNames1 = {"event_id", "event_type", "user_id", "order_id",
//                "request_time", "add_time", "poi_id", "shop_id"};
//        TypeInformation[] typeInformations1 = {Types.STRING(), Types.STRING(), Types.LONG(), Types.LONG(),
//                Types.STRING(), Types.STRING(), Types.LONG(), Types.LONG()};
//        //2.通过JSON schema定义
//        String jsonSchema = "{" +
//                "   type: 'object'," +
//                "   properties: {" +
//                "       event_id: {" +
//                "           type: 'string'" +
//                "       }," +
//                "       event_type: {" +
//                "           type: 'string'" +
//                "       }," +
//                "       user_id: {" +
//                "           type: 'string'," +
//                "           format: 'date-time'" +
//                "       }," +
//                "       order_id: {" +
//                "           type: 'number'" +
//                "       }," +
//                "       request_time: {" +
//                "           type: 'string'" +
//                "       }," +
//                "       add_time: {" +
//                "           type: 'string'" +
//                "       }," +
//                "       poi_id: {" +
//                "           type: 'number'" +
//                "       }," +
//                "       shop_id: {" +
//                "           type: 'number'" +
//                "       }" +
//                "   }" +
//                "}";
//
//        TableSchema sourceTableSchema0 = TableSchema.builder()
//                .field("event_id", Types.STRING())
//                .build();
//        StreamRecordTimestamp streamRecordTimestamp = new StreamRecordTimestamp(); //从流数据中抽取
//        ExistingField existingField = new ExistingField("add_time");
//        tableEnv.connect(sourceTopic0)
//                .withFormat(new Json().schema(Types.ROW(fieldNames1, typeInformations1)))
//                //.withFormat(new Json().jsonSchema(jsonSchema))
//                //.withFormat(new Json().deriveSchema())
//                .withFormat(new Json().schema(sourceTableSchema0.toRowType()))
//                .withSchema(new Schema()
//                        .field("event_type", Types.STRING()).from("event_type")
//                        .field("proctime", Types.SQL_TIMESTAMP()).proctime()
//                        .field("rowtime", Types.SQL_TIMESTAMP()).rowtime(
//                                new Rowtime()
//                                        //.timestampsFromField("user_id")
//                                        .timestampsFromExtractor(existingField)
//                                        .watermarksFromStrategy(new MySQLCustomWatermark())
//                        )
//                )
//                .inAppendMode()
//                .registerTableSource("tableSource0");
//        //数据写入table的方式，
//        //1.先执行sqlQuery 得到table，并且根据得到的table的tableSchema注册新的table，然后执行insertInto操作
//        Table tableOut = tableEnv.sqlQuery("select TUMBLE_START(rowtime, INTERVAL '1' MINUTE), " +
//                "TUMBLE_END(rowtime, INTERVAL '1' MINUTE), " +
//                "add_time, count(*) as tt from tableSource0 " +
//                "GROUP BY TUMBLE(rowtime, INTERVAL '1' MINUTE), add_time order by add_time desc");
//
//        Kafka sinkTopic0 = mtKafkaHandler.getWriteKafkaByTopic(TOPIC_PRODUCER);
//        TableSchema tableSchema = tableOut.getSchema();
//        TypeInformation<Row> rowType = tableSchema.toRowType();
//        tableEnv.connect(sinkTopic0)
//                .withFormat(new Json().schema(rowType))
//                .withSchema(new Schema().schema(tableSchema))
//                .inAppendMode()
//                .registerTableSink("sinkTopic");
//
//        tableOut.insertInto("sinkTopic");
//
//        //2.也可以先注册table，然后调用tableEnv.sqlUpdate();方法
//
//        env.execute((new JobConf(args)).getJobName());
//    }
}
