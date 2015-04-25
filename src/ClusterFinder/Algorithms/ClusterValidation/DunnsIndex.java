package ClusterFinder.Algorithms.ClusterValidation;

import ClusterFinder.Algorithms.PlotObjects.Cluster;
import java.util.ArrayList;

public class DunnsIndex extends ValidationAlgorithm {

    double index;
    double minDist = Double.MAX_VALUE;
    double maxDist = Double.MIN_VALUE;

    public DunnsIndex(ArrayList<Cluster> clusts) {
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
                    if(temp2.distanceFromCenter(point2) < minDist)
                        minDist = temp2.distanceFromCenter(point2);
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
