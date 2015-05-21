package ClusterFinder.Algorithms;

import ClusterFinder.Algorithms.IsodataHelper.ClusterMerger;
import ClusterFinder.Algorithms.IsodataHelper.ClusterSplitter;
import ClusterFinder.Algorithms.PlotObjects.Cluster;
import ClusterFinder.Algorithms.PlotObjects.DataPoint;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

public class Isodata extends KMeans{
    
    private final double threshNum;
    private final double threshDist;
    private final double deviationThresh;
    private final int maxClustMixed; 
    private final double extraParam;
    private final double minDist;
    
    int clustMixed = 0;

    // class extends k means algorithm then clusters are minipulated
    public Isodata(double on, double oc, double os, int k, int l, 
                                        int i, double no, double min, File file, int dim) {
        
        // step 1 + 2
        super(k, i, file, dim);
        
        this.threshNum = on;
        this.threshDist = oc;
        this.deviationThresh = os;
        this.maxClustMixed = l;
        this.extraParam = no;
        this.minDist = min;
        
        runIso();
        
    }

    public Isodata(Isodata algorithm, File f) {
        super(algorithm,f);
        this.threshNum = algorithm.threshNum;
        this.threshDist = algorithm.threshDist;
        this.deviationThresh = algorithm.deviationThresh;
        this.maxClustMixed = algorithm.maxClustMixed;
        this.extraParam = algorithm.extraParam;
        this.minDist = algorithm.minDist;
        
        runIso();
    }
    
    public final void runIso(){
        runIsoAlg();
    }
    
    public void runIsoAlg() {
        boolean clustRemoved = false;
               
        iterations = 1;
        while (iterations <= maxIt){
            
            Iterator<Cluster> iter = clusters.iterator();
            // step 3a
            while (iter.hasNext()) {
                Cluster clust = iter.next();
                if(threshNum > clust.getData().size()){
                    iter.remove();
                    // make one less cluster
                    clustRemoved = true;
                }
            }
            
            if (clustRemoved){
                // step 3b - assign points to clusters again w/ k - 1
                KMeans newKMeans = new KMeans(clusters.size(), maxIt, file, dim);
                clusters = newKMeans.getClusters();
            }
            
            // step 4
            @SuppressWarnings("UnusedAssignment")
                double[] coVar = new double[clusters.size()];
            findMean2();
            coVar = getCovariences(clusters.size());
            
            // step 5
            if(clusters.size() <= k/2 && canBeSplit()) 
                // step 6
                splitClusters(clusters.size(), coVar);
            else if(clusters.size() > 2*k && maxClustMixed > clustMixed) 
                //step 7
                mergeClusters(clusters.size());

            clustRemoved = false;
            iterations++;
        }
    }

    final double[] getCovariences(int k1){
        
        double[] cov = new double[k1];
        int count = 0;
        
        for(Cluster clust : clusters) {
            DataPoint mean = clust.getCentroid();
            
            for (DataPoint pt : clust.getData()){
                cov[count] = (pt.getX() - mean.getX()) * (pt.getY() - mean.getY()) *
                        (pt.getZ() - mean.getZ());
                for(int i = 0; i < pt.getExtraParams().length; i++)
                    cov[count] *= (pt.getExtraParams()[i] - mean.getExtraParams()[i]); 
            }
            cov[count] /= clust.getData().size();
            
            count++;
        }
        
        
        
        return cov;
    
    }

    private void splitClusters(int k1, double[] cov) {

        double maxCov = Double.MIN_VALUE;
        int count = 0;
        
        for (int i = 0; i < cov.length; i++){
            maxCov = Math.max(maxCov, cov[i]);
            count++;
        }
            
        Cluster clust = clusters.get(count-1);
        
        if(maxCov/dim > deviationThresh && clust.getData().size()+10 > 2*threshNum){
            // split
            ClusterSplitter newClusts = new ClusterSplitter(clusters.get(count-1), dim);
            clusters.remove(count-1);
            clusters.addAll(newClusts.getClusters());
            
        }
        
    }

    private void mergeClusters(int k) {
        
        Iterator<Cluster> iter = clusters.iterator();
        Iterator<Cluster> iter2 = clusters.iterator();
        
        while (iter.hasNext()) {
                Cluster clust = iter.next();
            while (iter.hasNext()) {
                Cluster clust2 = iter.next();
                if(clust.distanceFromCenter(clust2.getCentroid()) < threshDist){
                    ArrayList<Cluster> merge = new ArrayList<>();
                    merge.add(clust);
                    merge.add(clust2);
                    iter.remove();
                    iter.remove();
                    iter2.remove();
                    iter2.remove();
                    
                    ClusterMerger mergeClust = new ClusterMerger(merge);
                    clusters.add(mergeClust.getClust());
                    
                    clustMixed++;
                    k--;
                    return;
                }
            }
        }
        
    }
    
    public void findMean2() {
        clusters.stream().forEach((cluster) -> {
            cluster.setNewCentroid();
        });
    }
    
    public final boolean canBeSplit(){
        int count = 0;
        
        count = clusters.stream().filter((clust) -> (clust.getData().size() > threshDist))
                .map((_item) -> 1).reduce(count, Integer::sum);
        
        return count >= 2;
    }
    
}
