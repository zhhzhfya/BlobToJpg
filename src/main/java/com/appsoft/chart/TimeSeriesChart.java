package com.appsoft.chart;

import java.awt.Font;
import java.text.SimpleDateFormat;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.Millisecond;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

public class TimeSeriesChart implements Runnable {
	private ChartPanel frame1;
	private JFreeChart jfreechart;
	private static TimeSeries timeSeries1;
	private static TimeSeries timeSeries2;

	public TimeSeriesChart() {
		TimeSeriesCollection timeseriescollection = new TimeSeriesCollection();
		timeSeries1 = new TimeSeries("查询速度", Millisecond.class);
		timeSeries2 = new TimeSeries("写入速度", Millisecond.class);
		timeseriescollection.addSeries(timeSeries1);
		timeseriescollection.addSeries(timeSeries2);
		jfreechart = ChartFactory.createTimeSeriesChart("平均速度", "时间", "速度", timeseriescollection, true, true, true);
		XYPlot xyplot = jfreechart.getXYPlot();
		DateAxis dateaxis = (DateAxis) xyplot.getDomainAxis();
//		dateaxis.setDateFormatOverride(new SimpleDateFormat("MMM-yyyy"));
		frame1 = new ChartPanel(jfreechart, true);
		dateaxis.setLabelFont(new Font("黑体", Font.BOLD, 14)); // 水平底部标题
		dateaxis.setTickLabelFont(new Font("宋体", Font.BOLD, 12)); // 垂直标题
		ValueAxis rangeAxis = xyplot.getRangeAxis();// 获取柱状
		rangeAxis.setLabelFont(new Font("黑体", Font.BOLD, 14));
		jfreechart.getLegend().setItemFont(new Font("黑体", Font.BOLD, 14));
		jfreechart.getTitle().setFont(new Font("宋体", Font.BOLD, 16));// 设置标题字体
		
//		frame1 = new ChartPanel(jfreechart, true);
//		XYPlot xyplot = jfreechart.getXYPlot();
//		// 纵坐标设定
//		ValueAxis valueaxis = xyplot.getDomainAxis();
//		// 自动设置数据轴数据范围
//		valueaxis.setAutoRange(true);
//		// 数据轴固定数据范围 30s
//		valueaxis.setFixedAutoRange(30000D);
//
//		valueaxis = xyplot.getRangeAxis();
		// valueaxis.setRange(0.0D,200D);
		
	}
	
	public void run() {
		while (true) {
			try {
				timeSeries1.add(new Millisecond(), randomNum());
				Thread.sleep(300);
			} catch (InterruptedException e) {
			}
		}
	}

	private long randomNum() {
		System.out.println((Math.random() * 20 + 80));
		return (long) (Math.random() * 20 + 80);
	}

	public void addData1(double value) {
		timeSeries1.addOrUpdate(new Millisecond(), value);
	}
	public void addData2(double value) {
		timeSeries2.addOrUpdate(new Millisecond(), value);
	}

	public ChartPanel getChartPanel() {
		return frame1;
	}
}