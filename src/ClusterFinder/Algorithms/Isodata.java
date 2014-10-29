package ClusterFinder.Algorithms;

import ClusterFinder.Algorithms.PlotObjects.Cluster;
import java.io.File;
import java.util.ArrayList;

public class Isodata {
    
    private final double threshNum;
    private final double threshDist;
    private final double deviationThresh;
    private final int k;
    private final int maxClustMixed;
    private final int maxIter; 
    private final double extraParam;
    private final double minDist;
    private final File file;
    private final int dim;
    
    public ArrayList<Cluster> clusters;
    
    @SuppressWarnings("Convert2Diamond")
    public Isodata(double on, double oc, double os, int k, int l, 
                                        int i, double no, double min, File file, int dim) {
        
        this.threshNum = on;
        this.threshDist = oc;
        this.deviationThresh = os;
        this.k = k;
        this.maxClustMixed = l;
        this.maxIter = i;
        this.extraParam = no;
        this.minDist = min;
        this.file = file;
        this.dim = dim;
        
        clusters = new ArrayList<Cluster>();
        
    }
    
    public ArrayList<Cluster> getClusters() {
        return clusters;
    }
    
}
