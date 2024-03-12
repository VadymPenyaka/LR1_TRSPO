package util;

import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.CategoryChartBuilder;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.style.Styler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class HistogramGenerator {
    private Map<Integer, Long> numberOfThreadsAndExecutionTimeMap;

    public HistogramGenerator(Map<Integer, Long> numberOfThreadsAndExecutionTimeMap) {
        this.numberOfThreadsAndExecutionTimeMap = numberOfThreadsAndExecutionTimeMap;
        CategoryChart chart = new CategoryChartBuilder().width(800).height(600)
                .title("")
                .xAxisTitle("Number of threads")
                .yAxisTitle("Execution time")
                .build();

        chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideNW);
        chart.getStyler().setAvailableSpaceFill(0.95);
        chart.getStyler().setOverlapped(true);

        List yData = new ArrayList();
        yData.addAll(numberOfThreadsAndExecutionTimeMap.values());
        List xData = Arrays.asList(numberOfThreadsAndExecutionTimeMap.keySet().toArray());
        chart.addSeries("Execution time", xData, yData);

        new SwingWrapper<>(chart).displayChart();
    }

}
