package com.hbasesoft.framework.message.rocketmq;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.context.ApplicationEvent;

/**
 * <Description> <br>
 * 
 * @author 大刘杰<br>
 * @version 1.0<br>
 * @taskId <br>
 * @CreateDate 2018年6月25日 <br>
 * @since V1.0<br>
 * @see com.hbasesoft.framework.message.rocketmq <br>
 */
public class RocketmqEvent extends ApplicationEvent {
    private static final long serialVersionUID = -4468405250074063206L;

    private DefaultMQPushConsumer consumer;

    private List<MessageExt> msgs;

    public RocketmqEvent(List<MessageExt> msgs, DefaultMQPushConsumer consumer) throws Exception {
        super(msgs);
        this.consumer = consumer;
        this.setMsgs(msgs);
    }

    public String getMsg(int idx) {
        try {
            return new String(getMsgs().get(idx).getBody(), "utf-8");
        }
        catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    public String getMsg(int idx, String code) {
        try {
            return new String(getMsgs().get(idx).getBody(), code);
        }
        catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    public DefaultMQPushConsumer getConsumer() {
        return consumer;
    }

    public void setConsumer(DefaultMQPushConsumer consumer) {
        this.consumer = consumer;
    }

    public MessageExt getMessageExt(int idx) {
        return getMsgs().get(idx);
    }

    public String getTopic(int idx) {
        return getMsgs().get(idx).getTopic();
    }

    public String getTag(int idx) {
        return getMsgs().get(idx).getTags();
    }

    public byte[] getBody(int idx) {
        return getMsgs().get(idx).getBody();
    }

    public String getKeys(int idx) {
        return getMsgs().get(idx).getKeys();
    }

    public List<MessageExt> getMsgs() {
        return msgs;
    }

    public void setMsgs(List<MessageExt> msgs) {
        this.msgs = msgs;
    }
}
