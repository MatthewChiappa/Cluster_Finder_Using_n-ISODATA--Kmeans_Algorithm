package ClusterFinder.Algorithms.IsodataHelper;

import ClusterFinder.Algorithms.PlotObjects.Cluster;
import ClusterFinder.Algorithms.PlotObjects.DataPoint;
import java.util.ArrayList;

public class ClusterMerger {
    
    Cluster finalClust;
    ArrayList<Cluster> oldClusts;
    ArrayList<DataPoint> data = new ArrayList<>();
    
    public ClusterMerger(ArrayList<Cluster> clustsToBeMerged) {
        
        oldClusts = clustsToBeMerged;
        
        init(); 
        findMean();
        
    }
    
    private void init() {

        oldClusts.stream().forEach((clust) -> {
            clust.getData().stream().forEach((pt) -> {
                data.add(pt);
            });
        });
        
        for(DataPoint pt : data){
            finalClust.addDataPoint(pt);
        }
            
    }
    
    public Cluster getClust(){
        return finalClust;
    }
 
    private void findMean() {
            finalClust.setNewCentroid();    
    }
    
}
