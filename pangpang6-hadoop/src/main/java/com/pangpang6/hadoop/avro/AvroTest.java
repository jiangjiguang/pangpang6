package com.pangpang6.hadoop.avro;

import com.pangpang6.hadoop.entity.avro.User;
import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;

import java.io.File;
import java.io.IOException;

public class AvroTest {
    public static void main(String[] args) throws Exception {
        write();
        read();
    }

    public static void write() throws Exception {
        User user3 = User.newBuilder()
                .setName("Charlie")
                .setFavoriteColor("blue")
                .setFavoriteNumber(null)
                .build();
        String path = "/tmp/user.avro";
        DatumWriter<User> userDatumWriter = new SpecificDatumWriter<>(User.class);
        try (DataFileWriter<User> dataFileWriter = new DataFileWriter<>(userDatumWriter)) {
            dataFileWriter.create(user3.getSchema(), new File(path));
            dataFileWriter.append(user3);
        }
    }

    public static void read() throws IOException {
        DatumReader<User> reader = new SpecificDatumReader<>(User.class);
        DataFileReader<User> dataFileReader = new DataFileReader<>(new File("/tmp/user.avro"), reader);
        User user;
        while (dataFileReader.hasNext()) {
            user = dataFileReader.next();
            System.out.println(user);
        }
    }
}
