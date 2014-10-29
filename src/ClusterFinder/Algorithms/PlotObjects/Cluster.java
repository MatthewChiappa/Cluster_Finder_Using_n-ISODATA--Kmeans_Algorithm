package ClusterFinder.Algorithms.PlotObjects;

import java.util.ArrayList;

public class Cluster {
    
    // center and data in the cluster
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
    
    public void addDataPoint(DataPoint point) {
        data.add(point);
    }
    
    public void removeDataPoint(DataPoint point) {
        data.remove(point);
    }
    
    public DataPoint getCentroid(){
        return point;
    }
    
    public void setCentroid(DataPoint point){
        this.point = point;
    }
    
    public DataPoint getFurthest() {
        return furPt;
    }
    
    public void setFurthest(DataPoint furPt) {
        this.furPt = furPt;
    }
    
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
    
    public void clearData() {
        data = new ArrayList<>();
    }
    
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
    
}
