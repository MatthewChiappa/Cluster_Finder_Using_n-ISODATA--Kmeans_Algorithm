package ClusterFinder.Algorithms.PlotObjects;

public class DataPoint {
    
    // variables
    private final double x;
    private final double y;
    private final double z;
    public double[] extraParam;
    
    public DataPoint(){
        this.x = 0;
        this.y = 0;
        this.z = 0;
        this.extraParam = null;
    }
    
    // init
    public DataPoint(double x, double y, double z, double[] extraParam) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.extraParam = extraParam;
    }
    
    // returns x
    public double getX() {
        return x;
    }
    
    // returns y
    public double getY() {
        return y;
    }
    
    // returns z
    public double getZ() {
        return z;
    }
    
    // returns the extra parameters in however many dimensions
    public double[] getExtraParams() {
        return extraParam;
    }
    
    // compares this data point to another
    public boolean compareTo(DataPoint newPoint) {
        return x == newPoint.getX() && y == newPoint.getY()
                && z == newPoint.getZ();
    }
    
    // print method for debugging (only first 3 params)
    public String printPoint() {
        return (x + ", " + y + ", " + z);
    }
    
}
