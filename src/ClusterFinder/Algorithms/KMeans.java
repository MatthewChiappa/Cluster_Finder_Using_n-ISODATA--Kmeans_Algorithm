package ClusterFinder.Algorithms;

import ClusterFinder.Algorithms.PlotObjects.DataPoint;
import ClusterFinder.Algorithms.PlotObjects.Cluster;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class KMeans {
    
    private final int k;
    private final int maxIt;
    private final File file;
    private final int dim;
    
    public ArrayList<Cluster> clusters;
    ArrayList<DataPoint> orig;
    public int iterations = 1;
    
    public KMeans(int k, int maxIt, File file, int dim) {
        
        this.k = k;
        this.maxIt = maxIt;
        this.file = file;
        this.dim = dim;
        
        clusters = new ArrayList<>();
        orig = new ArrayList<>();
        
        initKMeans();
        initClusters();
        findMean();
        
        boolean allTrue = allEqual();
        
        while(!allTrue && iterations <= maxIt) {
            for(int x = 0; x < k; x++)
                    clusters.get(x).clearData();
            iterations++;
            initClusters();
            findMean();
            allTrue = allEqual();
        }
        
    }
    
    public ArrayList<Cluster> getClusters() {
        return clusters;
    }
    
    public final void initKMeans() {
        if(dim == 2) {
            try {
                Scanner scan = new Scanner(file);
                        
                while(scan.hasNext()){
                    DataPoint newPoint = new DataPoint(scan.nextDouble(), 
                            scan.nextDouble(), 0, new double[1]);   
                    orig.add(newPoint);
                    
                    scan.nextLine();
                }
            
            } catch (FileNotFoundException ex) {
                Logger.getLogger(KMeans.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if(dim == 3) {
            
            try {
               Scanner scan = new Scanner(file);
               
               while(scan.hasNext()){
                    DataPoint newPoint = new DataPoint(scan.nextDouble(), 
                            scan.nextDouble(), scan.nextDouble(), new double[1]);
                   
                    orig.add(newPoint);
                    
                    scan.nextLine();
                }
            
            } catch (FileNotFoundException ex) {
                Logger.getLogger(KMeans.class.getName()).log(Level.SEVERE, null, ex);
            }
               
        }
        else {
            
            try {
               Scanner scan = new Scanner(file);
               
               while(scan.hasNext()){
                    double x = scan.nextDouble(); 
                    double y = scan.nextDouble();
                    double z = scan.nextDouble();
                    double[] extra = new double[dim-3];
                   
                    if(scan.hasNext()) {
                        for(int i = 0; i < extra.length; i++)
                            extra[i] = scan.nextDouble();
                    }
                   
                    DataPoint newPoint = new DataPoint(x, y, z, extra);
                   
                    orig.add(newPoint);
                    
                    scan.nextLine();
                }
            
            } catch (FileNotFoundException ex) {
                Logger.getLogger(KMeans.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        for(int i = 0; i < k; i++) {
            clusters.add(new Cluster(orig.get(i), dim));
        }
        
    }

    private void initClusters() {
 
        orig.stream().forEach((point) -> {
            double min = Double.MAX_VALUE;
            
            
            for(int i = 0; i < k; i++){
                Cluster clust = clusters.get(i);
                
                if(clust.distanceFromCenter(point) < min)
                    min = clust.distanceFromCenter(point);
            }
            
            for(int i = 0; i < k; i++){
                Cluster clust = clusters.get(i);
                
                if(clust.distanceFromCenter(point) == min)
                    clusters.get(i).addDataPoint(point);
            }
        });
        
    }

    private void findMean() {
        
        for(int i = 0; i < k; i++) 
            clusters.get(i).setNewCentroid();
        
    }

    public final boolean allEqual() {
        boolean[] allEqual = new boolean[k];
        
        for(int i = 0; i < k; i++) {
            if (clusters.get(i).equalCentroid())
                allEqual[i] = true;
        }
        
        for(boolean b : allEqual) 
            if(!b) return false;
        
        return true; 
    }
    
}
