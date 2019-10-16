package com.pangpang6.hadoop.storm.topology;

import com.pangpang6.hadoop.storm.bolt.HelloBolt01;
import com.pangpang6.hadoop.storm.bolt.HelloBolt02;
import com.pangpang6.hadoop.storm.spout.HelloSpout;
import org.apache.storm.Config;
import org.apache.storm.StormSubmitter;
import org.apache.storm.generated.AlreadyAliveException;
import org.apache.storm.generated.AuthorizationException;
import org.apache.storm.generated.InvalidTopologyException;
import org.apache.storm.topology.TopologyBuilder;

public class HelloTopology {
    public static void main(String[] args) {
        Config config = new Config();
        config.setNumWorkers(2);
        config.setDebug(true);

        TopologyBuilder builder = new TopologyBuilder();
        builder.setSpout("spout", new HelloSpout());
        builder.setBolt("hello01-bolt", new HelloBolt01()).shuffleGrouping("spout");
        builder.setBolt("hello02-bolt", new HelloBolt02()).shuffleGrouping("hello01-bolt");

        try {
            StormSubmitter.submitTopology("hello", config, builder.createTopology());
        } catch (AlreadyAliveException e) {
            e.printStackTrace();
        } catch (InvalidTopologyException e) {
            e.printStackTrace();
        } catch (AuthorizationException e) {
            e.printStackTrace();
        }

    }
}
