package ClusterFinder.Algorithms;

import ClusterFinder.Algorithms.PlotObjects.Cluster;
import java.io.File;

public class GustafsonKessel extends KMeans {

    public GustafsonKessel(int k, int maxIt, File file, int dim) {
        super(k, maxIt, file, dim);
    }
    
    public GustafsonKessel(GustafsonKessel algorithm, File f) {  
        super(algorithm, f);   
    }
    
    @Override
    public void initClusters(){
        orig.stream().forEach((point) -> {
            double min = Double.MAX_VALUE;
            
            
            for(int i = 0; i < k; i++){
                Cluster clust = clusters.get(i);
                
                if(clust.mDistanceFromCenter(point) < min)
                    min = clust.mDistanceFromCenter(point);
            }
            
            for(int i = 0; i < k; i++){
                Cluster clust = clusters.get(i);
                
                if(clust.mDistanceFromCenter(point) == min)
                    clusters.get(i).addDataPoint(point);
            }
        });
    }
    
}
