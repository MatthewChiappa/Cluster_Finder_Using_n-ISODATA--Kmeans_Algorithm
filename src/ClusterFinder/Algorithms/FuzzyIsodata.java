package ClusterFinder.Algorithms;

import ClusterFinder.Algorithms.PlotObjects.DataPoint;
import java.io.File;
import java.util.ArrayList;

public class FuzzyIsodata extends Isodata {
    
    public FuzzyIsodata(double on, double oc, double os, int k, int l, 
                                        int i, double no, double min, File file, int dim) {
        
        super(on, oc, os, k, l, i, no, min, file, dim);
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
