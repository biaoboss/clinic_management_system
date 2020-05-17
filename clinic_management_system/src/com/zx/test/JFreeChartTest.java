package com.zx.test;

import java.awt.RenderingHints;


import java.awt.Font;
import java.awt.RenderingHints;
import java.io.FileOutputStream;



import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;


public class JFreeChartTest {

    public JFreeChartTest() {

        DefaultCategoryDataset dataSet = new DefaultCategoryDataset();
        dataSet.addValue(10, "测试数量", "一");
        dataSet.addValue(3, "测试数量", "二");
        dataSet.addValue(7, "测试数量", "三");
        JFreeChart chart = ChartFactory.createBarChart("测试表", "列数", "高度", dataSet, PlotOrientation.VERTICAL,
                true, false, false);
        //
        processChart(chart);
        
        ChartFrame chartFrame = new ChartFrame("测试专用图", chart);
        chartFrame.pack();
        chartFrame.setVisible(true);
        
        
        
        
        
        
        
    }
    
    private static void processChart(JFreeChart chart) {
        CategoryPlot plot = chart.getCategoryPlot();
        CategoryAxis domainAxis = plot.getDomainAxis();
        ValueAxis rAxis = plot.getRangeAxis();
        chart.getRenderingHints().put(RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
        TextTitle textTitle = chart.getTitle();
        textTitle.setFont(new Font("宋体", Font.PLAIN, 20));
        domainAxis.setTickLabelFont(new Font("sans-serif", Font.PLAIN, 11));
        domainAxis.setLabelFont(new Font("宋体", Font.PLAIN, 12));
        rAxis.setTickLabelFont(new Font("sans-serif", Font.PLAIN, 12));
        rAxis.setLabelFont(new Font("宋体", Font.PLAIN, 12));
        chart.getLegend().setItemFont(new Font("宋体", Font.PLAIN, 12));
        // renderer.setItemLabelGenerator(new LabelGenerator(0.0));
        // renderer.setItemLabelFont(new Font("宋体", Font.PLAIN, 12));
        // renderer.setItemLabelsVisible(true);
    }

    
    

    
    public static void main(String[] args) {
        new JFreeChartTest();
    }
}
