package assignment255;

import java.util.ArrayList;

public class Sphere {

	private Vector centreOfSphere;
	private double radius;
	private Vector sphereColour;
	private int x;
	private int y;
	private int z;

	Sphere(Vector sphereColour, double radius, int x, int y, int z) {
		this.sphereColour = sphereColour;
		this.x = x;
		this.y = y;
		this.z = z;
		this.radius = radius;
		this.centreOfSphere = setCenterofSphere(x, y, z);

	}

	public Vector setCenterofSphere(double x, double y, double z) {
		Vector centreOfSphere = new Vector(x, y, z);
		this.centreOfSphere = centreOfSphere;
		return centreOfSphere;
	}



	/**
	 * @return the centreOfSphere
	 */
	public Vector getCentreOfSphere() {
		return centreOfSphere;
	}

	/**
	 * @return the radius
	 */
	public double getRadius() {
		return radius;
	}

	/**
	 * @return the sphereColour
	 */
	public Vector getSphereColour() {
		return sphereColour;
	}

	/**
	 * @param sphereColour the sphereColour to set
	 */
	public void setSphereColour(Vector sphereColour) {
		this.sphereColour = sphereColour;
	}

	/**
	 * @param x the x to set
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * @param y the y to set
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * @param z the z to set
	 */
	public void setZ(int z) {
		this.z = z;
	}

	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}

	/**
	 * @return the z
	 */
	public int getZ() {
		return z;
	}

	/**
	 * @param centreOfSphere the centreOfSphere to set
	 */
	public void setCentreOfSphere(Vector centreOfSphere) {
		this.centreOfSphere = centreOfSphere;
	}

}
