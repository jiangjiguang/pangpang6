package com.pangpang6.utils.unzip;

import org.apache.commons.codec.binary.Base64;

import java.io.*;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * Created by jiangjg on 2017/2/20.
 */
public class GzipUtils {
    public static void main(String[] args) throws IOException {
        String param = "[{\"name\":\"variable_CID_AMT_STD_ALL\",\"day\":\"2017-02-22\",\"type\":\"table\",\"callerList\":[\"C0\",\"C1\",\"C2\"],\"count\":488},{\"name\":\"hdfhd\",\"day\":\"2017-02-22\",\"type\":\"variable\",\"callerList\":[\"C0\",\"C1\",\"C2\"],\"count\":512}] ";
        System.out.println(compressToBase64Data(param));
        System.out.println(decompressFromBase64Data(compressToBase64Data(param)));
    }
    public static String compressToBase64Data(String data) throws IOException {
        byte[] binaryData = compress(data);
        return Base64.encodeBase64String(binaryData);
    }

    public static String decompressFromBase64Data(String compressedBase64Data) throws IOException {
        byte[] binaryData = Base64.decodeBase64(compressedBase64Data);
        return decompress(binaryData);
    }

    public static byte[] compress(String data) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream(data.length());
        GZIPOutputStream gzip = new GZIPOutputStream(bos);
        gzip.write(data.getBytes("UTF-8"));
        gzip.close();
        byte[] compressed = bos.toByteArray();
        bos.close();
        return compressed;
    }

    public static String decompress(byte[] compressed) throws IOException {
        ByteArrayInputStream bis = new ByteArrayInputStream(compressed);
        GZIPInputStream gis = new GZIPInputStream(bis);
        BufferedReader br = new BufferedReader(new InputStreamReader(gis, "UTF-8"));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        br.close();
        gis.close();
        bis.close();
        return sb.toString();
    }
}
