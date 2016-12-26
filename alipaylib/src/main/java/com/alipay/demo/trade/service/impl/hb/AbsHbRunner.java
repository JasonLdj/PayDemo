package com.alipay.demo.trade.service.impl.hb;

import com.alipay.api.response.MonitorHeartbeatSynResponse;
import com.alipay.demo.trade.config.Configs;
import com.alipay.demo.trade.model.builder.AlipayHeartbeatSynRequestBuilder;
import com.alipay.demo.trade.service.AlipayMonitorService;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by liuyangkly on 15/10/23.
 */
public abstract class AbsHbRunner implements Runnable {
    protected Log log = LogFactory.getLog(getClass());


    public abstract AlipayHeartbeatSynRequestBuilder getBuilder();

    public abstract String getAppAuthToken();

    private ScheduledExecutorService scheduledService = Executors.newSingleThreadScheduledExecutor();
    private AlipayMonitorService monitorService;

    private long delay = 0;
    private long duration = 0;

    public AbsHbRunner(AlipayMonitorService monitorService) {
        this.monitorService = monitorService;
    }

    @Override
    public void run() {
        AlipayHeartbeatSynRequestBuilder builder = getBuilder();
        builder.setAppAuthToken(getAppAuthToken());

        MonitorHeartbeatSynResponse response = monitorService.heartbeatSyn(builder);

        StringBuilder sb = new StringBuilder(response.getCode())
                                .append(":")
                                .append(response.getMsg());
        if (StringUtils.isNotEmpty(response.getSubCode())) {
            sb.append(", ")
            .append(response.getSubCode())
            .append(":")
            .append(response.getSubMsg());
        }
        log.info(sb.toString());
    }

    public void schedule() {
        if (delay == 0) {
            delay = Configs.getHeartbeatDelay();
        }
        if (duration == 0) {
            duration = Configs.getCancelDuration();
        }
        scheduledService.scheduleAtFixedRate(this, delay, duration, TimeUnit.SECONDS);
    }

    public void shutdown() {
        scheduledService.shutdown();
    }

    public long getDelay() {
        return delay;
    }

    public void setDelay(long delay) {
        this.delay = delay;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }
}
