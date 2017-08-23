package com.pangpang6.books.algorithm.hash;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.DigestUtils;

import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 一致性哈希环
 * Created by jiangjg on 2017/7/11.
 */
public class ConsistencyHash {
    //所有的服务对应的Hash环
    private TreeMap<Integer,String> nodesTree = new TreeMap<>();

    public HashFunc getHashFunc() {
        return hashFunc;
    }

    // Hash计算对象，用于自定义hash算法
    HashFunc hashFunc;

    //默认虚拟节点数目  1000
    static int DEF_VIRTUAL_NUM = 1000;

    /**
     * 默认虚拟节点数目  1000
     * @return
     */
    public int getDefVirtualNum(){
        return DEF_VIRTUAL_NUM;
    }


    public ConsistencyHash(){
        hashFunc = new HashFunc() {
            @Override
            public Integer hash(String key) {
                try {
                    if (StringUtils.isBlank(key))
                        return 0;
                    return DigestUtils.md5DigestAsHex(key.getBytes()).hashCode();
                }catch (Exception e){

                }
                return 0;
            }
        };
    }

    /**
     * 初始化一致环,numberOfReplicas数目  默认1000
     * @param shards
     */
    public ConsistencyHash(List<String> shards){
        this();
        addNotes(shards);
    }

    public ConsistencyHash(Map<String,Integer> shards){
        this();
        addNotes(shards);
    }


    public String getNode(String key) {
        int keyHashCode = hashFunc.hash(key);

        SortedMap<Integer, String> tail = nodesTree.tailMap(keyHashCode);
        if (tail.size() == 0) {
            return nodesTree.get(nodesTree.firstKey());
        }
        return tail.get(tail.firstKey()); // 返回该虚拟节点对应的真实机器节点的信息
    }

    /**
     * 添加节点,每个节点的虚拟节点数目  默认1000
     */
    public void addNotes(List<String> nodes) {
        addNotes(listConvertMap(nodes));
    }

    private Map listConvertMap(List<String> nodes){
        Map<String,Integer> newNodes = new ConcurrentHashMap<>();
        for(String node:nodes){
            newNodes.put(node,getDefVirtualNum());
        }
        return newNodes;
    }

    /**
     * 添加节点,指定每个节点的 默认 numberOfReplicas
     * @param notes
     */
    public void addNotes(Map<String,Integer> notes) {
        for(String note:notes.keySet()){
            addNote(nodesTree, note, notes.get(note));
        }
    }
    private void addNote(TreeMap<Integer,String> treeMap,String nodeName,int numberOfReplicas){
        for (int j = 0; j < numberOfReplicas; j++) {
            treeMap.put(hashFunc.hash(nodeName + "_" + j), nodeName);
        }
    }
    /*
    public TreeMap getConsistencyHashTreeMap(List<String> nodes){
        Map<String,Integer> newNodes = new ConcurrentHashMap<>();
        for(String node:nodes){
            newNodes.put(node,VIRTUAL_NUM);
        }
        return getConsistencyHashTreeMap(newNodes);
    }*/

    /**
     *
     * @param nodes Map类型，key为node名字，value为权重
     * @return
     */
    public TreeMap getConsistencyHashTreeMap(Map<String,Integer> nodes){
        TreeMap<Integer,String> treeMap = new TreeMap<>();
        for (String shardName : nodes.keySet()) {
            addNote(treeMap,shardName,nodes.get(shardName));
        }
        return treeMap;
    }



    public interface HashFunc {
        public Integer hash(String key);
    }
}
