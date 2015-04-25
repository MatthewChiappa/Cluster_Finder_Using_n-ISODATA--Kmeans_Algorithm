package ClusterFinder.Algorithms.ClusterValidation;

import ClusterFinder.Algorithms.PlotObjects.Cluster;
import static java.lang.Math.pow;
import java.util.ArrayList;

public class XieBeniIndex extends SeparationIndex {

    public XieBeniIndex(ArrayList<Cluster> clusts) {
        super(clusts);
        findIndex();
    }

    private void findIndex() {

        memberships = new double[clusters.size()];
        double[] distances = new double[clusters.size()];

        clusters.stream().forEach((clust) -> {
            clust.getData().stream().forEach((point) -> {
                for(int i = 0; i < k; i++)
                    fuzzyVar += findMembership(point, clusters.get(i)) * 
                            pow(clust.distanceFromCenter(point), 2);
                
                for(int j = 0; j < k; j++)
                    distances[j] = pow(clusters.get(j).distanceFromCenter(point), 2);
            });
        });
        
        double minNum = Double.MAX_VALUE;
        
        for(int x = 0; x < distances.length; x++)
            if(distances[x] < minNum && distances[x] != 0)
                minNum = distances[x];
        
        sepInd = fuzzyVar/minNum;
        sepInd = changeRange2(sepInd, 300, 600);
    }
    
    private double changeRange2(double oldVal, double oldMin, double oldMax){
        return ((oldVal - oldMin) * 40) / (oldMax - oldMin);
    }
    
}
