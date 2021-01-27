package com.pangpang6.hadoop.parquet;

import org.apache.hadoop.fs.Path;
import org.apache.parquet.example.data.Group;
import org.apache.parquet.hadoop.ParquetReader;
import org.apache.parquet.hadoop.example.GroupReadSupport;

public class ReadParquet {
    public static final String TRIP_EXAMPLE_SCHEMA = "{\"type\": \"record\",\"name\": \"triprec\",\"fields\": [ "
            + "{\"name\": \"ts\",\"type\": \"long\"},{\"name\": \"uuid\", \"type\": \"string\"},"
            + "{\"name\": \"rider\", \"type\": \"string\"},{\"name\": \"driver\", \"type\": \"string\"},"
            + "{\"name\": \"begin_lat\", \"type\": \"double\"},{\"name\": \"begin_lon\", \"type\": \"double\"},"
            + "{\"name\": \"end_lat\", \"type\": \"double\"},{\"name\": \"end_lon\", \"type\": \"double\"},"
            + "{\"name\":\"fare\",\"type\": \"double\"}]}";

    public static void main(String[] args) throws Exception {
        System.out.println(TRIP_EXAMPLE_SCHEMA);
        parquetReaderV2("/Users/jiangjiguang/Documents/239a9487-b54c-4465-b458-f137501eb17f-0_0-22-28_20210126145045.parquet");
    }


    public static void parquetReaderV2(String inPath) throws Exception {
        GroupReadSupport readSupport = new GroupReadSupport();
        ParquetReader.Builder<Group> reader = ParquetReader.builder(readSupport, new Path(inPath));
        ParquetReader<Group> build = reader.build();
        Group line;
        int count = 0;
        while ((line = build.read()) != null) {
            System.out.println("原始数据: " + line.toString());
            System.out.println("字段数据");
            System.out.println(line.getDouble("ts", 0)
                    + "\t" + line.getString("uuid", 0)
                    + "\t" + line.getString("rider", 0)
                    + "\t" + line.getString("driver", 0)
                    + "\t" + line.getDouble("begin_lat", 0)
                    + "\t" + line.getDouble("begin_lon", 0)
                    + "\t" + line.getDouble("end_lat", 0)
                    + "\t" + line.getDouble("end_lon", 0)
                    + "\t" + line.getDouble("fare", 0)
                    + "\t");
            //System.out.println(line.toString());
            count++;
        }

        System.out.println("读取结束: " + count);

    }
}
