package ClusterFinder.Algorithms;

import ClusterFinder.Algorithms.PlotObjects.DataPoint;
import java.io.File;
import java.util.ArrayList;

public class FuzzyKMeans extends KMeans {
    
    public FuzzyKMeans(int k, int maxIt, File file, int dim) {
        
        super(k, maxIt, file, dim);
        findFurthest();
        addToClusters();
        
    }
    
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
