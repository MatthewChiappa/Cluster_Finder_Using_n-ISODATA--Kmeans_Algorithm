package ClusterFinder.Algorithms;

import ClusterFinder.Algorithms.PlotObjects.Cluster;
import java.io.File;

public class GathGeva extends KMeans {

    public GathGeva(int k, int maxIt, File file, int dim) {
        super(k, maxIt, file, dim);
    }
    
    @Override
    public void initClusters(){
        orig.stream().forEach((point) -> {
            double min = Double.MAX_VALUE;
            
            
            for(int i = 0; i < k; i++){
                Cluster clust = clusters.get(i);
                
                if(clust.gaussDistanceFromCenter(point) < min)
                    min = clust.gaussDistanceFromCenter(point);
            }
            
            for(int i = 0; i < k; i++){
                Cluster clust = clusters.get(i);
                
                if(clust.gaussDistanceFromCenter(point) == min)
                    clusters.get(i).addDataPoint(point);
            }
        });
    }
    
}
