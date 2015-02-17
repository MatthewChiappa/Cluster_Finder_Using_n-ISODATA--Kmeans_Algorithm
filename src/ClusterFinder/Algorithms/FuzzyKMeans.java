package ClusterFinder.Algorithms;

import ClusterFinder.Algorithms.PlotObjects.DataPoint;
import java.io.File;
import java.util.ArrayList;

public class FuzzyKMeans extends KMeans {
    
    // class extends k means algorithm then clusters are minipulated
    public FuzzyKMeans(int k, int maxIt, File file, int dim) {
        
        super(k, maxIt, file, dim);
        findFurthest();
        addToClusters();
        
    }
    
    // the furthest point in each cluster is found and 
    // set as the furthest
    public final void findFurthest() {
        clusters.stream().forEach((clust) -> {
            ArrayList<DataPoint> points = clust.getData();
            double max = Double.MIN_VALUE;
            
            points.stream().filter((point) -> 
                    (max < clust.distanceFromCenter(point))).forEach((point) -> {
                clust.setFurthest(point);
            });
        });
    }

    // the extra points within the threshold are added to each
    // cluster
    private void addToClusters() {

        clusters.stream().forEach((clust) -> {
            
            orig.stream().filter((origPoint) -> (clust.getFurthest() != null && 
                    clust.distanceFromCenter(origPoint) <
                            clust.distanceFromCenter(clust.getFurthest()))).forEach((origPoint) -> {
                                clust.addDataPoint(origPoint);
            });
            
        });
    
    }
    
}
