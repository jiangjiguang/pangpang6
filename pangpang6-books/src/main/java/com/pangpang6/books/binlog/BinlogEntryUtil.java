package com.pangpang6.books.binlog;

import com.alibaba.otter.canal.protocol.CanalEntry;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.*;

public class BinlogEntryUtil {
    public static CanalEntry.Entry deserializeFromProtoBuf(byte[] input) {
        CanalEntry.Entry entry = null;

        try {
            ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(input));
            entry = (CanalEntry.Entry) ois.readObject();
        } catch (ClassNotFoundException var3) {

        } catch (IOException var4) {

        }

        return entry;
    }

    public static BinlogEntry serializeToBean(byte[] input) {
        BinlogEntry binlogEntry = null;
        CanalEntry.Entry entry = deserializeFromProtoBuf(input);
        if (entry != null) {
            binlogEntry = serializeToBean(entry);
        }

        return binlogEntry;
    }

    public static BinlogEntry serializeToBean(CanalEntry.Entry entry) {
        CanalEntry.RowChange rowChange = null;

        try {
            rowChange = CanalEntry.RowChange.parseFrom(entry.getStoreValue());
        } catch (Exception var8) {
            throw new RuntimeException("parse event has an error , data:" + entry.toString(), var8);
        }

        BinlogEntry binlogEntry = new BinlogEntry();
        String[] logFileNames = entry.getHeader().getLogfileName().split("\\.");
        String logFileNo = "000000";
        if (logFileNames.length > 1) {
            logFileNo = logFileNames[1];
        }

        binlogEntry.setBinlogFileName(logFileNo);
        binlogEntry.setBinlogOffset(entry.getHeader().getLogfileOffset());
        binlogEntry.setExecuteTime(entry.getHeader().getExecuteTime());
        binlogEntry.setTableName(entry.getHeader().getTableName());
        binlogEntry.setEventType(entry.getHeader().getEventType().toString());
        Iterator i$ = rowChange.getRowDatasList().iterator();

        while (i$.hasNext()) {
            CanalEntry.RowData rowData = (CanalEntry.RowData) i$.next();
            BinlogRow row = new BinlogRow(binlogEntry.getEventType());
            row.setBeforeColumns(getColumnInfo(rowData.getBeforeColumnsList()));
            row.setAfterColumns(getColumnInfo(rowData.getAfterColumnsList()));
            //binlogEntry.addRowData(row);
        }

        if (binlogEntry.getRowDatas().size() >= 1) {
            BinlogRow row = (BinlogRow) binlogEntry.getRowDatas().get(0);
            binlogEntry.setPrimaryKeys(getPrimaryKeys(row));
        } else {
            List<String> primaryKeysList = new ArrayList();
            binlogEntry.setPrimaryKeys(primaryKeysList);
        }

        return binlogEntry;
    }

    private static List<String> getPrimaryKeys(BinlogRow row) {
        Map<String, BinlogColumn> columns = row.getCurColumns();
        List<String> primaryKeys = new ArrayList();
        Iterator i$ = columns.values().iterator();

        while (i$.hasNext()) {
            BinlogColumn column = (BinlogColumn) i$.next();
//            if (column.getIsKey()) {
//                primaryKeys.add(column.getName());
//            }
        }

        return primaryKeys;
    }

    private static HashMap<String, BinlogColumn> getColumnInfo(List<CanalEntry.Column> columns) {
        HashMap<String, BinlogColumn> columnInfo = new HashMap();

        CanalEntry.Column column;
        BinlogColumn c;
        for (Iterator i$ = columns.iterator(); i$.hasNext(); columnInfo.put(column.getName(), c)) {
            column = (CanalEntry.Column) i$.next();
            c = new BinlogColumn();
            c.setName(column.getName());
            c.setIndex(column.getIndex());
            //c.setIsKey(column.getIsKey());
            c.setUpdated(column.getUpdated());
            c.setNull(column.getIsNull());
            c.setMysqlType(column.getMysqlType());
            c.setSqlType(column.getSqlType());
            if (column.getMysqlType().equals("blob")) {
                try {
                    byte[] blobdata = column.getValue().getBytes("ISO-8859-1");
                    c.setValue(new String(blobdata, "UTF-8"));
                } catch (IOException var6) {
                    System.out.println("Blob object coding error");
                }
            } else {
                c.setValue(column.getValue());
            }
        }

        return columnInfo;
    }
}
