package assignment255;
import java.lang.Math.*;

public class Vector {
  double x, y, z;
  public Vector() {}
  public Vector(double i, double j, double k) {
    x = i;
    y = j;
    z = k;
  }
  public double magnitude() {
    return Math.sqrt(x * x + y * y + z * z);
  }
  public void normalise() {
    double mag = magnitude();
    if (mag != 0) {
      x /= mag;
      y /= mag;
      z /= mag;
    }
  }
  public double dot(Vector a) {
    return x * a.x + y * a.y + z * a.z;
  }
  public Vector sub(Vector a) {
    return new Vector(x - a.x, y - a.y, z - a.z);
  }
  public Vector add(Vector a) {
    return new Vector(x + a.x, y + a.y, z + a.z);
  }
  public Vector mult(double d) {
    return new Vector(d * x, d * y, d * z);
  }
  
  public Vector cross(Vector a) { 
	  return new Vector (
		  this.y * a.z - this.z * a.y,
		  this.z * a.x - this.x * a.z,
		  this.x * a.y - this.y * a.x
		  );
	    }
  public void print() {
    System.out.println("x=" + x + ", y=" + y + ", z=" + z);
  }
/**
 * @param x the x to set
 */
public void setX(double x) {
	this.x = x;
}
/**
 * @param y the y to set
 */
public void setY(double y) {
	this.y = y;
}
/**
 * @param z the z to set
 */
public void setZ(double z) {
	this.z = z;
}
}