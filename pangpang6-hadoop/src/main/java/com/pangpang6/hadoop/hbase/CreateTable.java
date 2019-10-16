package com.pangpang6.hadoop.hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;

/**
 * Created by jiangjiguang on 2018/4/19.
 */
public class CreateTable {
    public static void main(String[] args) throws IOException {
        Configuration config = HBaseConfiguration.create();
        config.set("hbase.zookeeper.quorum", "node1,node2,node3");
        Connection connection = ConnectionFactory.createConnection(config);
        Admin admin = connection.getAdmin();

        TableName tableName = TableName.valueOf("test1");
        HTableDescriptor descriptor = new HTableDescriptor(tableName);
        HColumnDescriptor columnDescriptor = new HColumnDescriptor(Bytes.toBytes("cf1"));
        descriptor.addFamily(columnDescriptor);

        if (admin.tableExists(tableName)) {
            admin.disableTable(tableName);
            admin.deleteTable(tableName);
        }

        admin.createTable(descriptor);

        boolean flag = admin.tableExists(TableName.valueOf("test1"));
        System.out.println(flag);

    }
}
