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
    
    // global variables
    public int k;
    final int maxIt;
    private final File file;
    final int dim;
    
    // cluster list, orignal cluster list, and
    // iteration variable
    public ArrayList<Cluster> clusters;
    ArrayList<DataPoint> orig;
    public int iterations = 1;
    
    // init
    public KMeans(int k, int maxIt, File file, int dim) {
        
        this.k = k;
        this.maxIt = maxIt;
        this.file = file;
        this.dim = dim;
        
        clusters = new ArrayList<>();
        orig = new ArrayList<>();
        
        // k means algoritm is initilized
        initKMeans();
        initClusters();
        findMean();
        
        // init allTrue to each if mean stays the same
        boolean allTrue = allEqual();
        
        // k means algorithm is implemented until the max iterations
        // is exceeded or the points in the cluster remain the same
        while(!allTrue && iterations <= maxIt) {
            for(int x = 0; x < k; x++)
                    clusters.get(x).clearData();
            iterations++;
            initClusters();
            findMean();
            allTrue = allEqual();
        }
        
    }
    
    // returns the cluster list
    public ArrayList<Cluster> getClusters() {
        return clusters;
    }
    
    // starts the k means algoritm by putting all data points into one cluster
    // by reading the text file then setting random datapoints to the mean of each
    // cluster
    public final void initKMeans() {
        if(dim == 2) {
            try {
                Scanner scan = new Scanner(file);
                
                while (scan.hasNextLine()) {
                            if (scan.hasNextDouble())
                                break;
                            scan.nextLine();
                        }
                
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
               
               scan.nextLine();
               scan.nextLine();
               
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

    // seperates the clusters into k clusters by checking the distance of each point
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

    // finds the mean of each cluster
    public void findMean() {
        
        for(int i = 0; i < k; i++) 
            clusters.get(i).setNewCentroid();
        
    }

    // checks if the means in the clusters remain the same
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
    
    public int getNumClusters(){
        return k;
    }
    
}
