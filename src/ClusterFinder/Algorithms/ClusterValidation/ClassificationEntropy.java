package ClusterFinder.Algorithms.ClusterValidation;

import ClusterFinder.Algorithms.PlotObjects.Cluster;
import static java.lang.Math.log;
import java.util.ArrayList;

public class ClassificationEntropy extends PartitionCoefficent{

    public ClassificationEntropy(ArrayList<Cluster> clusts) {
        super(clusts);
        findCoefficent();
    }
    
    private void findCoefficent() {
        memberships = new double[clusters.size()];
        
        clusters.stream().forEach((clust) -> {
            clust.getData().stream().forEach((point) -> {
                for(int i = 0; i < k; i++)
                    memberships[i] = findMembership(point, clusters.get(i));
            });
        });
        
        for (int x = 0; x < memberships.length; x++)
            partCoeff += memberships[x] * log(memberships[x]);
        
        partCoeff = -partCoeff/clusters.size();
    }
    
}
