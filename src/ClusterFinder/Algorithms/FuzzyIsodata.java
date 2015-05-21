package ClusterFinder.Algorithms;

import java.io.File;

public class FuzzyIsodata extends Isodata {
    
    final double fuzzyness = 0.9;
    
    public FuzzyIsodata(double on, double oc, double os, int k, int l, 
                                        int i, double no, double min, File file, int dim) {
        
        super(on, oc, os, k, l, i, no, min, file, dim);
        findFinalMean();
        
    }
    
    public FuzzyIsodata(FuzzyIsodata algorithm, File f){
        super(algorithm, f);
        findFinalMean();
    }
    
    @Override
    public void findMean2() {
        clusters.stream().forEach((clust) -> { 
            clust.setNewFuzzyCentroid(fuzzyness);
        });
    }
    
    private void findFinalMean() {
        clusters.stream().forEach((clust) -> { 
            clust.setNewCentroid();
        });
    }
    
}
