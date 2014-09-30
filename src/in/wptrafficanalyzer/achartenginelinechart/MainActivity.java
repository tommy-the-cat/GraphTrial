package in.wptrafficanalyzer.achartenginelinechart;
 
import org.achartengine.ChartFactory;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;
 
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
 
public class MainActivity extends Activity {
 
    private String[] mMonth = new String[] {
        "Jan", "Feb" , "Mar", "Apr", "May", "Jun",
        "Jul", "Aug" , "Sep", "Oct", "Nov", "Dec"
    };
 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
 
        // Getting reference to the button btn_chart
        Button btnChart = (Button) findViewById(R.id.btn_chart);
 
        // Defining click event listener for the button btn_chart
        OnClickListener clickListener = new OnClickListener() {
 
            @Override
            public void onClick(View v) {
                // Draw Energy Use Chart
                openChart();
            }
        };
 
        // Setting event click listener for the button btn_chart of the MainActivity layout
        btnChart.setOnClickListener(clickListener);
    }
 
    private void openChart(){
        int[] x = { 1,2,3,4,5,6,7,8 };
        int[] energyUse = { 2000,2500,2700,3000,2800,3500,3700,3800};
 
        // Creating an  XYSeries for Energy
        XYSeries energySeries = new XYSeries("Energy Use");
        // Adding data to Energy Series
        for(int i=0;i<x.length;i++){
        	energySeries.add(x[i], energyUse[i]);
        }
 
        // Creating a dataset to hold series
        XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
        // Adding Energy Series to the dataset
        dataset.addSeries(energySeries);
        
        // Creating XYSeriesRenderer to customize energySeries
        XYSeriesRenderer energyRenderer = new XYSeriesRenderer();
        energyRenderer.setColor(Color.WHITE);
        energyRenderer.setPointStyle(PointStyle.CIRCLE);
        energyRenderer.setFillPoints(true);
        energyRenderer.setLineWidth(2);
        energyRenderer.setDisplayChartValues(true);
        
        // Creating a XYMultipleSeriesRenderer to customize the whole chart
        XYMultipleSeriesRenderer multiRenderer = new XYMultipleSeriesRenderer();
        multiRenderer.setXLabels(0);
        multiRenderer.setChartTitle("Energy Usage");
        multiRenderer.setXTitle("Year 2013");
        multiRenderer.setYTitle("Energy Used (kwh)");
        multiRenderer.setZoomButtonsVisible(true);
        for(int i=0;i<x.length;i++){
            multiRenderer.addXTextLabel(i+1, mMonth[i]);
        }
 
        // Adding energyRenderer to multipleRenderer
        // Note: The order of adding dataseries to dataset and renderers to multipleRenderer
        // should be same
        multiRenderer.addSeriesRenderer(energyRenderer);
 
        // Creating an intent to plot line chart using dataset and multipleRenderer
        Intent intent = ChartFactory.getLineChartIntent(getBaseContext(), dataset, multiRenderer);
 
        // Start Activity
        startActivity(intent);
    }
}
