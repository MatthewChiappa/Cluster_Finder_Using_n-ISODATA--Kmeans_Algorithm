package ClusterFinder.Algorithms.ClusterValidation;

import ClusterFinder.Algorithms.PlotObjects.Cluster;
import ClusterFinder.Algorithms.PlotObjects.DataPoint;
import static java.lang.Math.abs;
import java.util.ArrayList;

public class AltDunnIndex extends ValidationAlgorithm {

    double index;
    double minDist = Double.MAX_VALUE;
    double maxDist = Double.MIN_VALUE;
    
    public AltDunnIndex(ArrayList<Cluster> clusts) {
        super(clusts);
        findIndex();
    }

    @Override
    public double getResult() {
        return index;
    }
    
    private void findIndex() {
        
        clusters.stream().forEach((temp) -> {
            temp.getData().stream().forEach((point2) -> {
                clusters.stream().forEach((temp2) -> {
                    DataPoint pt1 = new DataPoint(point2.getX(), 0, 0, new double[1]);
                    DataPoint pt2 = new DataPoint(0, point2.getY(), 0, new double[1]);
                    
                    double distBtwnPts = abs(temp2.distanceFromCenter(pt2) - temp2.distanceFromCenter(pt1));
                    
                    if(distBtwnPts < minDist)
                        minDist = distBtwnPts;
                });
            });
        });
        
        clusters.stream().forEach((clust2) -> {
            clusters.stream().filter((tempClust) -> 
                    (clust2.distanceFromCenter(tempClust.getCentroid()) >
                            maxDist)).forEach((tempClust) -> {
                maxDist = clust2.distanceFromCenter(tempClust.getCentroid());
            });
        });
        
        index = minDist/maxDist;
    }
    
}
