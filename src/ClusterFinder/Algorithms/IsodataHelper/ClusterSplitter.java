package ClusterFinder.Algorithms.IsodataHelper;

import ClusterFinder.Algorithms.PlotObjects.Cluster;
import ClusterFinder.Algorithms.PlotObjects.DataPoint;
import java.util.ArrayList;

public class ClusterSplitter {
    
    ArrayList<Cluster> clusts = new ArrayList<>();
    ArrayList<DataPoint> data = new ArrayList<>();
    private final int k = 2;
    private final int maxIt = 10;

    public ClusterSplitter(Cluster clustToBeSplit, int dim) {
        
        clustToBeSplit.getData().stream().forEach((pt) -> {
            data.add(pt);
        });
        
        boolean allTrue = false;
        int iterations = 1;
        
        clusts.add(new Cluster(data.get(0), dim));
        clusts.add(new Cluster(data.get(1), dim));
        
        init();
        while(!allTrue && iterations <= maxIt){
            for(int x = 0; x < k; x++)
                    clusts.get(x).clearData();
            
            init();
            findMean();
            iterations++;
            allTrue = allEqual();
        }
        
    }

    public ArrayList<Cluster> getClusters() {
        return clusts;
    }

    private void init() {
        
        data.stream().forEach((point) -> {
            double min = Double.MAX_VALUE;
            
            
            for (Cluster clust : clusts) {
                if(clust.distanceFromCenter(point) < min)
                    min = clust.distanceFromCenter(point);
            }
            
            for (Cluster clust : clusts) {
                if (clust.distanceFromCenter(point) == min) {
                    clust.addDataPoint(point);
                }
            }
        });
      
    }
    
    private void findMean() {
        
        for (Cluster clust : clusts) {
            clust.setNewCentroid();
        }
        
    }
    
    public final boolean allEqual() {
        boolean[] allEqual = new boolean[clusts.size()];
        
        for(int i = 0; i < clusts.size(); i++) {
            if (clusts.get(i).equalCentroid())
                allEqual[i] = true;
        }
        
        for(boolean b : allEqual) 
            if(!b) return false;
        
        return true; 
    }
    
}
