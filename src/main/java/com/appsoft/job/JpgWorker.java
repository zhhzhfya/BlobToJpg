package com.appsoft.job;

import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Blob;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.appsoft.data_trans.JobMgr;
import com.appsoft.enums.MessageEnum;
import com.appsoft.event.MsgEvent;
import com.google.common.eventbus.EventBus;

public class JpgWorker implements Runnable {
	private static Logger log = LoggerFactory.getLogger(JsonWorker.class);
	String dir;
	String idCard;
	BufferedImage bufferedImag;
	EventBus eventBus;

	public JpgWorker(String dir, String idCard, BufferedImage bufferedImag, EventBus eventBus) {
		this.dir = dir;
		this.idCard = idCard;
		this.bufferedImag = bufferedImag;
		this.eventBus = eventBus;
	}

	@Override
	public void run() {
		try {
			saveJpg(dir, idCard, bufferedImag);
		} catch (Exception e) {
			log.error("执行命令出现异常:{}", e.getLocalizedMessage());
			e.printStackTrace();
		}
	}

	public boolean isFileExists(String file) {
		File f = new File(file);
		return f.exists();
	}

	/**
	 * blog 写图片
	 */
	public void saveJpg(String path, String idcard, BufferedImage bufferedImage) {
		long b = System.currentTimeMillis();
		FileImageOutputStream outputStream = null;
		try {
			String city = idCard.substring(0, 4);// 城市
			String zone = idCard.substring(4, 6);// 地区
			String year = idCard.substring(6, 10);// 年
			File dir = new File(path + city + File.separator + zone + File.separator + year + File.separator);
			if (!dir.exists()) {
				dir.mkdirs();
			}
			outputStream = new FileImageOutputStream(new File(dir.getPath() + File.separator + idCard + ".jpg"));
			ImageIO.write((RenderedImage) bufferedImage, "JPG", outputStream);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (outputStream != null) {
					outputStream.flush();
					outputStream.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		long use = System.currentTimeMillis() - b;
		if (use > 50) {// 如果写入大于50毫秒
			log.info("写入耗时：" + (use));
		}
		if (System.currentTimeMillis()/1000 % 5 ==0) {
			//eventBus.post(new OrderEvent(MessageEnum.WRITE_TIME.getId() + use));
		}
	}
}