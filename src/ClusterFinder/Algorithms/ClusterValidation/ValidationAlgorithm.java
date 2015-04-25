package ClusterFinder.Algorithms.ClusterValidation;

import ClusterFinder.Algorithms.PlotObjects.Cluster;
import ClusterFinder.Algorithms.PlotObjects.DataPoint;
import static java.lang.Math.exp;
import static java.lang.Math.pow;
import java.util.ArrayList;

public abstract class ValidationAlgorithm {
    
    ArrayList<Cluster> clusters = null;
    int k = 0;
    double[] memberships;
    double mainMin = 0, mainMax = 0, partCoeff = 0;
    
    public ValidationAlgorithm(ArrayList<Cluster> clusts){
        this.clusters = clusts;
        this.k = clusts.size();
    }
    
    public abstract double getResult();
    
    public void findMinMax(){
        mainMin = Double.MAX_VALUE;
        mainMax = Double.MIN_VALUE;    
        
        clusters.stream().forEach((temp) -> {
            temp.getData().stream().forEach((point2) -> {
                clusters.stream().forEach((temp2) -> {
                    temp2.getData().stream().forEach((point3) -> {
                        if(point2.distanceToPoint(point3) > mainMax)
                            mainMax = point2.distanceToPoint(point3);
                        else if (point2.distanceToPoint(point3) < mainMin)
                            mainMin = point2.distanceToPoint(point3);
                    });
                });
            });
        });
    }
    
    public double findMembership(DataPoint point, Cluster clust) {
        double x1 = changeRange(clust.distanceFromCenter(point), mainMin, mainMax);
        return (double)Math.round((1/exp(pow(x1, 2))) * 1000000) / 1000000;
    }
    
    public double changeRange(double oldVal, double oldMin, double oldMax){
        return (((oldVal - oldMin) * 3) / (oldMax - oldMin));
    }
    
}
