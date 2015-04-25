package ClusterFinder.Algorithms.ClusterValidation;

import ClusterFinder.Algorithms.PlotObjects.Cluster;
import static java.lang.Math.pow;
import java.util.ArrayList;

public class PartitionCoefficent extends ValidationAlgorithm {
    
    public PartitionCoefficent(ArrayList<Cluster> clusts){
        super(clusts);
        findMinMax();
        findCoefficent();
    }
    
    @Override
    public double getResult(){
        return partCoeff;
    }

    private void findCoefficent() {

        memberships = new double[clusters.size()];

        clusters.stream().forEach((clust) -> {
            clust.getData().stream().forEach((point) -> {
                for(int i = 0; i < k; i++)
                    memberships[i] = findMembership(point, clusters.get(i));
            });
            for (int x = 0; x < memberships.length; x++)
                partCoeff += pow(memberships[x], 2);
            
            partCoeff = partCoeff/clusters.size();
        });
        
    }
    
}
