package com.appsoft.job;

import java.awt.image.BufferedImage;
import java.io.File;
import java.sql.Blob;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.appsoft.data_trans.JobMgr;
import com.appsoft.data_trans.QueryJobMgr;
import com.appsoft.db.Bean;
import com.appsoft.db.DbUtil;
import com.appsoft.enums.MessageEnum;
import com.appsoft.event.MsgEvent;
import com.appsoft.utils.SysUtil;
import com.google.common.eventbus.EventBus;

public class QueryWorker implements Runnable {
	private static Logger log = LoggerFactory.getLogger(JsonWorker.class);

	private String app_home;
	private String dir;
	private String sql;
	private int offset;
	private int count;
	private EventBus eventBus;

	public QueryWorker(String app_home, String dir, String sql, int offset, int count, EventBus eventBus) {
		this.app_home = app_home;
		this.dir = dir;
		this.sql = sql;
		this.offset = offset;
		this.count = count;
		this.eventBus = eventBus;
	}

	@Override
	public void run() {
		long b = System.currentTimeMillis();
		List<Bean> list = DbUtil.getInstance().query(sql, offset, count);
		log.info(SysUtil.getMemInfo() + "\t查询耗时：" + (System.currentTimeMillis() - b) + "毫秒 \t工作队列：" + JobMgr.getQueueSize());
		if (list.size() == 0) {
			QueryJobMgr.clearnQueue();
			eventBus.post(new MsgEvent(MessageEnum.OK.getId() + "OK"));
			return;
		}
		// 查询耗时
		eventBus.post(new MsgEvent(MessageEnum.QUERY_TIME.getId() + (System.currentTimeMillis() - b)));
		String idCard = null;
		for (int i = 0; i < list.size(); i++) {
			Bean bean = list.get(i);
			idCard = bean.getStr("ID_CARD");
			if (idCard.length() < 5) {
				continue;
			}
			Blob blob = (Blob) bean.get("PIC");
			try {
				while (JobMgr.getQueueSize() >= JobMgr.queueSize - 100 && list.size() - i > 100) {// 结果集剩余小于100就全放入队列，然后赶紧去查数据库
					Thread.sleep(100);
				}
				BufferedImage bufferedImag = ImageIO.read(blob.getBinaryStream());
				JobMgr.addJob(new JpgWorker(dir, idCard, bufferedImag, eventBus));
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// 总的处理数量
		eventBus.post(new MsgEvent(MessageEnum.ALL_COUNT.getId() + list.size()));
		// 写入速度
		eventBus.post(new MsgEvent(MessageEnum.WRITE_TIME.getId() + (double) ((System.currentTimeMillis() - b) / list.size())));
		eventBus.post(new MsgEvent(MessageEnum.CONSOLE.getId() + SysUtil.getMemInfo() + "\t查询耗时：" + (System.currentTimeMillis() - b) + "毫秒 \t查队列：" + QueryJobMgr.getQueueSize() + " \t写队列：" + JobMgr.getQueueSize() + " \t写平均耗时：" + ((System.currentTimeMillis() - b) / list.size()) + "毫秒"));
	}

}
