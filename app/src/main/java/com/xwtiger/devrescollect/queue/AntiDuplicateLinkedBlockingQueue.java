package com.xwtiger.devrescollect.queue;

import java.util.AbstractMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;

public class AntiDuplicateLinkedBlockingQueue<Key,Value> {

    private final LinkedList<Map.Entry<Key,Value>> list = new LinkedList<Map.Entry<Key,Value>>();

    private final HashSet<Key> keySet = new HashSet<Key>();

    public synchronized  int size(){
        return list.size();
    }

    /**
     * 添加数据
     * @param key
     * @param value
     * @return
     */
   public synchronized  boolean offer(Key key,Value value){
        if(keySet.contains(key)){
            return false;
        }
        list.add(new AbstractMap.SimpleEntry<Key, Value>(key,value));
        keySet.add(key);
        notifyAll();
        return true;
   }

    /**
     * 取数据
     * @return
     * @throws
     */
   public synchronized Value take() throws InterruptedException {
        while(list.size() ==0){
            wait();
        }
       Map.Entry<Key, Value> keyValueEntry = list.removeFirst();
       keySet.remove(keyValueEntry);
       return keyValueEntry.getValue();
   }

   public synchronized  void clear(){
        list.clear();
        keySet.clear();
   }



}
