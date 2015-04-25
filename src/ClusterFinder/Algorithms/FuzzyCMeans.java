package ClusterFinder.Algorithms;

import ClusterFinder.Algorithms.PlotObjects.Cluster;
import java.io.File;

public class FuzzyCMeans extends KMeans {
    
    final double fuzzyness = 0.9;
    final double epsilon = 0.001;
    
    // class extends k means algorithm then clusters are minipulated
    public FuzzyCMeans(int k, int maxIt, File file, int dim) {
        
        super(k, maxIt, file, dim);
        
    }
    
    @Override
    public void runAlg() {
        double chg = Double.MAX_VALUE;
        
        // fuzzy c means algorithm is implemented until the max iterations
        // is exceeded or the change in the cluster center is less than epsilon
        while(chg > epsilon && iterations <= maxIt) {
            for(int x = 0; x < k; x++)
                    clusters.get(x).clearData();
            iterations++;
            initClusters();
            findMean();
            chg = findDiff();
        }    
        findFinalMean();
    }
    
    @Override
    public void findMean() {
        for(int i = 0; i < k; i++) 
            clusters.get(i).setNewFuzzyCentroid(fuzzyness);
    }
    
    public void findFinalMean() {
        for(int i = 0; i < k; i++) 
            clusters.get(i).setNewCentroid();
    }


    private double findDiff() {
        double maxChg = Double.MIN_VALUE;
        
        for (Cluster clust : clusters)
            if(clust.chgInCenter() > maxChg)
                maxChg = clust.chgInCenter();
        
        return maxChg;
    }
    
}
