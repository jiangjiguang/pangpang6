package com.pangpang6.books.binlog;

import com.alibaba.otter.canal.protocol.CanalEntry;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.pangpang6.utils.MyJSONMapper;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class BinlogUtils {
    private static final Logger logger = LoggerFactory.getLogger(BinlogUtils.class);

    public static BinlogEntity parse(BinlogEntry binlogEntry, Map<String, BinlogParamEntity> tableAndFields) {
        if (binlogEntry == null) return null;
        try {
            String tableName = binlogEntry.getTableName();
            BinlogParamEntity readFields = tableAndFields.get(tableName);
            if (readFields == null) return null;

            if (!readFields.getEventTypes().contains(binlogEntry.getEventType())) return null;

            List<String> primaryKeys = binlogEntry.getPrimaryKeys();


            List<BinlogItemEntity> itemEntityList = getBinlogEntity(binlogEntry, readFields.getFields(), readFields.getEventTypes());
            if (CollectionUtils.isEmpty(itemEntityList)) return null;

            BinlogEntity binlogEntity = new BinlogEntity();

            binlogEntity.setTableName(tableName);
            binlogEntity.setEventTypes(readFields.getEventTypes());
            binlogEntity.setPrimaryKeys(primaryKeys);
            binlogEntity.setItemEntityList(itemEntityList);
            return binlogEntity;
        } catch (Exception ex) {
            logger.error("parse error {}, {}",
                    MyJSONMapper.nonEmptyMapper().toJSONString(binlogEntry),
                    ExceptionUtils.getFullStackTrace(ex));
            return null;
        }
    }

    public static BinlogEntity parse(byte[] bytes, Map<String, BinlogParamEntity> tableAndFields) {
        if (bytes == null || tableAndFields == null) return null;

        CanalEntry.Entry entry = BinlogEntryUtil.deserializeFromProtoBuf(bytes);
        if (entry == null) return null;

        BinlogEntry binlogEntry = BinlogEntryUtil.serializeToBean(entry);
        return parse(binlogEntry, tableAndFields);
    }

    private static void setValue(Map<String, BinlogColumn> columnMap, Set<String> readFields, Map<String, String> resultMap) {
        if (MapUtils.isEmpty(columnMap)) return;

        for (Map.Entry<String, BinlogColumn> entry : columnMap.entrySet()) {
            String key = entry.getKey();
            BinlogColumn binlogColumn = entry.getValue();
            if (binlogColumn != null && readFields.contains(key)) {
                resultMap.put(binlogColumn.getName(), binlogColumn.getValue());
            }
        }
        return;
    }

    private static List<BinlogItemEntity> getBinlogEntity(BinlogEntry binlogEntry, Set<String> readFields, Set<String> eventTypes) {
        if (binlogEntry == null || readFields == null || eventTypes == null) return null;
        List<BinlogRow> binlogRows = binlogEntry.getRowDatas();
        if (CollectionUtils.isEmpty(binlogRows)) return null;

        List<BinlogItemEntity> itemEntityList = Lists.newArrayList();

        for (BinlogRow row : binlogRows) {
            String eventType = row.getEventType();
            if (!eventTypes.contains(eventType)) {
                continue;
            }

            Map<String, String> preValueMap = Maps.newHashMap();
            Map<String, String> curValueMap = Maps.newHashMap();
            Map<String, String> afterValueMap = Maps.newHashMap();

            Map<String, BinlogColumn> beforeColumnsMap = row.getBeforeColumns();
            Map<String, BinlogColumn> curColumnsMap = row.getCurColumns();
            Map<String, BinlogColumn> afterColumnsMap = row.getAfterColumns();

            setValue(beforeColumnsMap, readFields, preValueMap);
            setValue(curColumnsMap, readFields, curValueMap);
            setValue(afterColumnsMap, readFields, afterValueMap);

            BinlogItemEntity itemEntity = new BinlogItemEntity();
            itemEntity.setEventType(eventType);
            itemEntity.setPreValueMap(preValueMap);
            itemEntity.setCurValueMap(curValueMap);
            itemEntity.setAfterValueMap(afterValueMap);
            itemEntityList.add(itemEntity);
        }
        return itemEntityList;
    }
}
