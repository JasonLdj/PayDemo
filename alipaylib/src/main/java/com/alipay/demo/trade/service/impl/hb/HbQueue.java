package com.alipay.demo.trade.service.impl.hb;

import com.alipay.demo.trade.model.hb.SysTradeInfo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by liuyangkly on 15/10/23.
 private List<BlockingQueue<E>> concurrentQueueList = Collections.synchronizedList(queueList);
 */
public class HbQueue {
    private static Log log = LogFactory.getLog(HbQueue.class);

    public static final int QUEUE_SIZE = 300;   //
    private static final BlockingQueue<SysTradeInfo> queue = new ArrayBlockingQueue<SysTradeInfo>(QUEUE_SIZE);

    public synchronized static void offer(SysTradeInfo info) {
        //
        if (info != null) {
            try {

                queue.put(info);
            } catch (InterruptedException e) {
                log.warn("interrupted for tradeInfo:" + info);
                e.printStackTrace();
            }
        }
    }

    public synchronized static List<SysTradeInfo> poll() {
        if (queue.isEmpty()) {

            return null;
        }

        int size = 30;
        List<SysTradeInfo> tradeInfoList = new ArrayList<SysTradeInfo>(size);
        for (int i = 0; i < size; i++) {

            SysTradeInfo info = queue.poll();
            if (info == null) {
                break;
            }
            tradeInfoList.add(info);
        }
        return tradeInfoList;
    }
}
