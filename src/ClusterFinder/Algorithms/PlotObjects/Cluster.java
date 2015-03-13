package ClusterFinder.Algorithms.PlotObjects;

import java.util.ArrayList;

public class Cluster {
    
    // center and data in the cluster, and number of dimensions
    public DataPoint point;
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
        
        double  x = point2.getX() - point.getX();
        double  xSqr = Math.pow(x, 2);
        
        double y = point2.getY() - point.getY();
	double ySqr = Math.pow(y, 2);
        
        double z = point2.getZ() - point.getZ();
	double zSqr = Math.pow(z, 2);
        
        double[] extra = new double[SIZE];
        double sum = 0;
        
        if(dim > 3) {
            for(int i = 0; i < SIZE; i++) {
                extra[i] = point2.getExtraParams()[i] - point.getExtraParams()[i];
                extra[i] = Math.pow(extra[i], 2);
                sum += extra[i];
            }
        }
        
        return Math.sqrt(xSqr + ySqr + zSqr + sum); 
    }

    public double mDistanceFromCenter(DataPoint point2) {
        int SIZE = point2.getExtraParams().length;
        double[] newPt = new double[SIZE+3];
        
        newPt[0] = point2.getX() - point.getX();
        newPt[1] = point2.getY() - point.getY();
        newPt[2] = point2.getZ() - point.getZ();
        
        double[] extra = new double[SIZE];
        double sum = 0;
        
        if(dim > 3) {
            for(int i = 0; i < SIZE; i++) 
                extra[i] = point2.getExtraParams()[i] - point.getExtraParams()[i];
            
            System.arraycopy(extra, 0, newPt, 3, newPt.length);
        }
      
        double[] inverse = new double[newPt.length];
        
        
        
        return 0;
    }

    public double gaussDistanceFromCenter(DataPoint point) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
