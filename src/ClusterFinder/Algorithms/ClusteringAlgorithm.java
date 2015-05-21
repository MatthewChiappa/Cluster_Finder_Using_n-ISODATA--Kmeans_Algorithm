package ClusterFinder.Algorithms;

import ClusterFinder.Algorithms.PlotObjects.Cluster;
import java.io.File;
import java.util.ArrayList;

public interface ClusteringAlgorithm {
    
    public abstract ArrayList<Cluster> getClusters();
    public abstract int getDim();
    public abstract File getFile();
    
}
