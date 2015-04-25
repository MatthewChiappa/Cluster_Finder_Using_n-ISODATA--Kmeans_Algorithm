package ClusterFinder.Algorithms.ClusterValidation;

import ClusterFinder.Algorithms.PlotObjects.Cluster;
import static java.lang.Math.pow;
import java.util.ArrayList;

public class SeparationIndex extends ValidationAlgorithm {

    double sepInd, fuzzyVar = 0;
    int count = 0;
    
    public SeparationIndex(ArrayList<Cluster> clusts) {
        super(clusts);
        findMinMax();
        findIndex();
    }
    
    @Override
    public double getResult() {
        return sepInd;
    }

    private void findIndex() {

        memberships = new double[clusters.size()];
        double[] fuzCards = new double[clusters.size()*clusters.size()];

        clusters.stream().forEach((clust) -> {
            clust.getData().stream().forEach((point) -> {
                for(int i = 0; i < k; i++){
                    memberships[i] = findMembership(point, clusters.get(i));
                    fuzzyVar += pow(memberships[i], 2) * pow(clust.distanceFromCenter(point), 2);
                }
            });
            for(int i = 0; i < k; i++)
                fuzCards[i] = pow(clust.distanceFromCenter(clusters.get(i).getCentroid()), 2);
            
        });
        
        double minNum = Double.MAX_VALUE;
        
        for(int x = 0; x < fuzCards.length; x++)
            if(fuzCards[x] < minNum && fuzCards[x] != 0)
                minNum = fuzCards[x];
        
        sepInd = fuzzyVar/minNum;
        sepInd = changeRange2(sepInd, 10, 200);
    }
    
    private double changeRange2(double oldVal, double oldMin, double oldMax){
        return ((oldVal - oldMin) * 0.2) / (oldMax - oldMin);
    }
    
}
