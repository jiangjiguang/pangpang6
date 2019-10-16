package com.pangpang6.hadoop.flink.partitioners;

import com.pangpang6.hadoop.common.ClickBean;
import com.pangpang6.utils.MyJSONMapper;
import org.apache.flink.streaming.connectors.kafka.partitioner.FlinkKafkaPartitioner;

public class MyFlinkKafkaPartitioner extends FlinkKafkaPartitioner<String> {
    @Override
    public int partition(String record, byte[] key, byte[] value, String targetTopic, int[] partitions) {
        MyJSONMapper myJSONMapper = MyJSONMapper.nonEmptyMapper();
        ClickBean clickBean = myJSONMapper.parseObject(record, ClickBean.class);
        return partitions[clickBean.getType() % partitions.length];
    }
}
