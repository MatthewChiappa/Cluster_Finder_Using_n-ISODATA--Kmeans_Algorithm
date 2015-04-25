package ClusterFinder.Algorithms.ClusterValidation;

import ClusterFinder.Algorithms.PlotObjects.Cluster;
import static java.lang.Math.pow;
import java.util.ArrayList;

public class PartitionIndex extends ValidationAlgorithm {

    double partInd;
    double fuzzyVar = 0, fuzzyCard = 0;
    
    public PartitionIndex(ArrayList<Cluster> clusts) {
        super(clusts);
        findMinMax();
        findIndex();
    }

    @Override
    public double getResult() {
        return partInd;
    }

    private void findIndex() {
        
        memberships = new double[clusters.size()];

        clusters.stream().forEach((clust) -> {
            clust.getData().stream().forEach((point) -> {
                for(int i = 0; i < k; i++){
                    memberships[i] = findMembership(point, clusters.get(i));
                    fuzzyVar += pow(memberships[i], 2) * pow(clust.distanceFromCenter(point), 2);
                }
            });
        });

        for (Cluster clust2 : clusters)
                for (int j = 0 ; j < clusters.size(); j++)
                    fuzzyCard += pow(clust2.distanceFromCenter(clusters.get(j).getCentroid()), 2);
    
        partInd = fuzzyVar/fuzzyCard;
        partInd = changeRange2(partInd, 0, 20);
        
    }

    private double changeRange2(double oldVal, double oldMin, double oldMax){
        return ((oldVal - oldMin) * 2) / (oldMax - oldMin);
    }
    
}
