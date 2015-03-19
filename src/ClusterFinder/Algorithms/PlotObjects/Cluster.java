package ClusterFinder.Algorithms.PlotObjects;

import java.util.ArrayList;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.stat.correlation.Covariance;

public class Cluster {
    
    // center and data in the cluster, and number of dimensions
    public DataPoint point;
    public DataPoint oldPt;
    public DataPoint furPt;
    private final int dim;
    
    ArrayList<DataPoint> data = new ArrayList<>();
    
    // init method
    public Cluster(DataPoint point, int dim) {
        this.point = point;
        this.dim = dim;
    }
    
    // returns the data in the cluster
    public ArrayList<DataPoint> getData() {
        return data;
    }
    
    // adds a point to the cluster
    public void addDataPoint(DataPoint point) {
        data.add(point);
    }
    
    // removes a point in the cluster
    public void removeDataPoint(DataPoint point) {
        data.remove(point);
    }
    
    // returns the mean of the cluster
    public DataPoint getCentroid(){
        return point;
    }
    
    // sets the mean of the cluster
    public void setCentroid(DataPoint point){
        this.point = point;
    }
    
    // returns the furthest point in the cluster
    public DataPoint getFurthest() {
        return furPt;
    }
    
    // sets the furthest point in the cluster
    public void setFurthest(DataPoint furPt) {
        this.furPt = furPt;
    }
    
    // setd the new mean of the cluster
    public void setNewCentroid() {
        double x=0, y=0, z=0;
        double[] extraParams = new double[dim];
        
        for (DataPoint newPt: data) {
            x += newPt.getX();
            y += newPt.getY();
            z += newPt.getZ();
            
            double[] list = newPt.getExtraParams();
            int i = 0;
            
            for(double p : list){
                extraParams[i] += p;
                i++;
            }
        }
        
        for(int i = 0; i < extraParams.length; i++) {
            extraParams[i] /= data.size();
        }
        
        x /= data.size();
        y /= data.size();
        z /= data.size();
        
        DataPoint newPoint = new DataPoint(x, y, z, extraParams);
        
        point = newPoint;
    }
    
    public void setNewFuzzyCentroid(double fuzzyness) {
        if(point != null)
            oldPt = point;
        
        double x=0, y=0, z=0;
        double[] extraParams = new double[dim];
        
        for (DataPoint newPt: data) {
            x += newPt.getX();
            y += newPt.getY();
            z += newPt.getZ();
            
            double[] list = newPt.getExtraParams();
            int i = 0;
            
            for(double p : list){
                extraParams[i] += p;
                i++;
            }
        }
        
        for(int i = 0; i < extraParams.length; i++)
            extraParams[i] /= data.size();
        
        x /= data.size();
        y /= data.size();
        z /= data.size();
        
        x = Math.pow(x, fuzzyness);
        y = Math.pow(y, fuzzyness);
        z = Math.pow(z, fuzzyness);
        for(int i = 0; i < extraParams.length; i++)
            extraParams[i] = Math.pow(extraParams[i], fuzzyness);
        
        DataPoint newPoint = new DataPoint(x, y, z, extraParams);
        
        point = newPoint;
    }
    
    public double chgInCenter(){
        double[] changes = new double[dim];
        double max = Double.MIN_VALUE;
        
        changes[0] = point.getX() - oldPt.getX();
        changes[1] = point.getY() - oldPt.getY();
        changes[2] = point.getZ() - oldPt.getZ();
        for(int i = 0; i < changes.length; i++)
            changes[i] = point.getExtraParams()[i] - oldPt.getExtraParams()[i];
        
        for(int i = 0; i < changes.length; i++)
            if(changes[i] > max)
                max = changes[i]; 
        
        return max;
    }
    
    // checks if the previous mean is the same
    public boolean equalCentroid() {
        double x=0, y=0, z=0;
        double[] extraParams = new double[dim];
        
        for (DataPoint newPt: data) {
            x += newPt.getX();
            y += newPt.getY();
            z += newPt.getZ();
            
            double[] list = newPt.getExtraParams();
            int i = 0;
            
            for(double p : list){
                extraParams[i] += p;
                i++;
            }
        }
        
        for(int i = 0; i < extraParams.length; i++) {
            extraParams[i] /= data.size();
        }
        
        x /= data.size();
        y /= data.size();
        z /= data.size();
        
        DataPoint newPoint = new DataPoint(x, y, z, extraParams);
        
        return newPoint.equals(point);
    }
    
    // clears the data points in the cluste
    public void clearData() {
        data = new ArrayList<>();
    }
    
    // prints the cluster for debugging
    public void printCluster() {
        data.stream().forEach((newPoint) -> { 
            System.out.println(newPoint.printPoint());
        });
    }
    
    // euclidean distance formula to calculate distance from the center
    // of the cluster
    public double distanceFromCenter(DataPoint point2) {
        int SIZE = point2.getExtraParams().length;
        double[] center = new double[SIZE+3];
        
        center[0] = point.getX();
        center[1] = point.getY();
        center[2] = point.getZ();
        
        double[] newPt = new double[SIZE+3];
        
        newPt[0] = point2.getX();
        newPt[1] = point2.getY();
        newPt[2] = point2.getZ();
        
        double[] extra = new double[SIZE];
        
        if(dim > 3) {
            for(int i = 0; i < SIZE; i++) {
                extra[i] = point2.getExtraParams()[i];
                center[i+3] = point.getExtraParams()[i];
            }
            
            System.arraycopy(extra, 0, newPt, 3, newPt.length);
        }
        
        double sum = 0, sumSqr;
        
        for (int i = 0 ; i < SIZE+3; i++){
            if(Double.isNaN(newPt[i]))
                newPt[i] = 0;
            else if(Double.isNaN(center[i]))
                center[i] = 0;
        
            
            sumSqr = newPt[i] - center[i];
            sumSqr = Math.pow(sumSqr, 2);
            sum += sumSqr;
        }
        
        return Math.sqrt(sum); 
    }

    public double mDistanceFromCenter(DataPoint point2) {
        int SIZE = point2.getExtraParams().length;
        double[] center = new double[SIZE+3];
        
        center[0] = point.getX();
        center[1] = point.getY();
        center[2] = point.getZ();
        
        double[] newPt = new double[SIZE+3];
        
        newPt[0] = point2.getX();
        newPt[1] = point2.getY();
        newPt[2] = point2.getZ();
        
        double[] extra = new double[SIZE];
        
        if(dim > 3) {
            for(int i = 0; i < SIZE; i++) {
                extra[i] = point2.getExtraParams()[i];
                center[i+3] = point.getExtraParams()[i];
            }
            
            System.arraycopy(extra, 0, newPt, 3, newPt.length);
        }
        
        double[][] covM = new double[SIZE+3][SIZE+3];
        covM[0] = center;
        covM[1] = newPt;
        
        Covariance cov = new Covariance(covM);
        RealMatrix covMat = cov.getCovarianceMatrix();

        RealMatrix ptMean = new Array2DRowRealMatrix(center);
        RealMatrix ptNew = new Array2DRowRealMatrix(newPt);

        RealMatrix subPt  = ptNew.subtract(ptMean);

        RealMatrix distSquared = subPt.transpose().multiply(covMat)
                .multiply(subPt);

        return Math.sqrt(distSquared.getEntry(0, 0));
    }

    public double gaussDistanceFromCenter(DataPoint point2) {

        int SIZE = point2.getExtraParams().length;
        double[] center = new double[SIZE+3];
        
        center[0] = point.getX();
        center[1] = point.getY();
        center[2] = point.getZ();
        
        double[] newPt = new double[SIZE+3];
        
        newPt[0] = point2.getX();
        newPt[1] = point2.getY();
        newPt[2] = point2.getZ();
        
        double[] extra = new double[SIZE];
        
        if(dim > 3) {
            for(int i = 0; i < SIZE; i++) {
                extra[i] = point2.getExtraParams()[i];
                center[i+3] = point.getExtraParams()[i];
            }
            
            System.arraycopy(extra, 0, newPt, 3, newPt.length);
        }

        double distance = 0, sigma = 50, centerComp = 255;
        double value;
        
        for (int i = 0 ; i < SIZE+3; i++){
            if(Double.isNaN(newPt[i]))
                newPt[i] = 0;
            else if(Double.isNaN(center[i]))
                center[i] = 0;
            
            distance += Math.abs(newPt[i]-center[i])*Math.abs(newPt[i]-center[i]);
        }
        distance = Math.sqrt(distance);
        value = centerComp*Math.exp((-1*distance*distance)/(1.442695*sigma*sigma));
        
        return value;
    }
    
}
