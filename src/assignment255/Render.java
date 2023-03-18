package assignment255;

import java.util.ArrayList;
import java.util.Collections;

import javafx.scene.control.Slider;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public class Render {

	static ArrayList<Double> closestPointT = new ArrayList<Double>();
	static ArrayList<Double> closestPointIndex = new ArrayList<Double>();

	static double minimum;
	double u;
	double v;
	static Vector originOfRay = new Vector(0, 0, 0);
	static Camera camera = new Camera(originOfRay);
	static Vector ray;


	public static void render(WritableImage image, ArrayList<Sphere> world) {
		// Get image dimensions, and declare loop variables
		int width = (int) image.getWidth();
		int height = (int) image.getHeight();
		PixelWriter image_writer = image.getPixelWriter();

		/**
		 * Origin of the ray
		 */
		
		/**
		 * direction of ray
		 */
		Vector directionOfRay = new Vector(0, 0, 1);

		/**
		 * centre of sphere
		 */
		// Vector centreOfSphere = new Vector(0, 0, 0);
		/**
		 * Radius of sphere
		 */

		for (int j = 0; j < height; j++) {
			/**
			 * column loop
			 */
			for (int i = 0; i < width; i++) {
				/**
				 * row loop
				 */
				double u = i - width / 2;
				double v = ((height - j) - (height /2 ));
				//ray = camera.makeRay(v, u);
				
				originOfRay.x = i - Main.getWidthCenter();
				originOfRay.y = j - Main.getHeightCenter();
				originOfRay.z = -200;

				Color colour = renderedSphereColour(originOfRay, directionOfRay, world);

				image_writer.setColor(i, j, colour);

			}
		}

	}

	public static boolean sphereHit(Vector originOfRay, Vector directionOfRay, Sphere sphere) {
		boolean sphereHitCase;
		double a;
		double b;
		double c;

		/**
		 * v -> simplification of later equation, (origin - center of sphere)
		 */
		Vector v;

		v = originOfRay.sub(sphere.getCentreOfSphere());

		a = directionOfRay.dot(directionOfRay);
		b = 2 * (v.dot(directionOfRay));
		c = v.dot(v) - (sphere.getRadius() * sphere.getRadius());

		// discriminant -->
		double disc = b * b - 4 * a * c;
		/*
		 * There is no intersection, else there is an intersection
		 */
		if (disc < 0) {
			sphereHitCase = false;
			// image_writer.setColor(i, j, Color.color(backgroundColour.x,
			// backgroundColour.y, backgroundColour.z, 1.0));
		} else {

			/**
			 * hit sphere
			 */
			sphereHitCase = true;
		}
		return sphereHitCase;
	}

	public static Color renderedSphereColour(Vector originOfRay, Vector directionOfRay, ArrayList<Sphere> world) {

		int sphereIndex = -1;
		double minimumT = 999999;
		for (int i = 0; i < world.size(); i++) {
			double t;

			/**
			 * DELETE
			 * 
			 */

			double a;
			double b;
			double c;
			/**
			 * v -> simplification of later equation, (origin - center of sphere)
			 */
			Vector v;

			/**
			 * p -> 3D Points (on line or sphere) Point of intersection
			 */

			v = originOfRay.sub(world.get(i).getCentreOfSphere());

			a = directionOfRay.dot(directionOfRay);
			b = 2 * (v.dot(directionOfRay));
			c = v.dot(v) - (world.get(i).getRadius() * world.get(i).getRadius());

			// discriminant -->
			double disc = b * b - 4 * a * c;

			/**
			 * DELETE
			 * 
			 */

			t = (-b - Math.sqrt(disc)) / 2 * a;

			if (t < minimumT) {
				minimumT = t;
				sphereIndex = i;

			}
		}

		double tempT;
		// Vector sphereColour = Main.getSphereColourSelected();

		Vector backgroundColour = new Vector(0.842, 0.424, 0.5);

		ArrayList<Double> tList = new ArrayList<Double>();

		
		Vector pointOfIntersection = new Vector(0, 0, 0);
		//pointOfIntersection = originOfRay.add(ray

		if (minimumT < 0 || sphereIndex == -1 || minimumT >= 99999) {
			//minimumT = (b - Math.sqrt(disc)) / 2 * a;
			//if (minimumT < 0) { // sphere may be behind us
				return Color.color(backgroundColour.x, backgroundColour.y, backgroundColour.z, 1.0);
			//}

		} else {
			Vector sphereColour = world.get(sphereIndex).getSphereColour();
			/**
			 * diffuse shading of sphere
			 */
			pointOfIntersection = originOfRay.add(directionOfRay.mult(minimumT));
			Vector normal = pointOfIntersection.sub(world.get(sphereIndex).getCentreOfSphere());
			normal.normalise();

			/**
			 * Vector of the light
			 */
			Vector light = new Vector(250, 130, -200);
			Vector lv = light.sub(pointOfIntersection);
			lv.normalise();
			double dp = lv.dot(normal);
			if (dp < 0) {
				dp = 0;
			} else if (dp > 1) {
				dp = 1;
			}

			double diffuseShading = 0.62;
			double ambientLevel = (1 - diffuseShading);
			Vector ambientColour = sphereColour.mult(dp * diffuseShading).add(sphereColour.mult(ambientLevel));

			return Color.color(ambientColour.x, ambientColour.y, ambientColour.z);

//			return Color.color(backgroundColour.x, backgroundColour.y, backgroundColour.z, 1.0);

				
	}

			// System.out.println(closestPointTemp.size() + " " + i);
			// findClosestHit(closestPointTemp, world.get(i));
			// System.out.println("Sphere: " + i);



		// closestPointTemp.clear();

	}
	/**
	 * 
	 * public static double findClosestHit(ArrayList<Double> closestPointTemp,
	 * Sphere sphere) { if (closestPointTemp.size() > 1) {
	 * 
	 * Collections.sort(closestPointTemp); if (minimum != closestPointTemp.get(0)) {
	 * minimum = closestPointTemp.get(0); // System.out.println("Min:" + minimum + "
	 * Sphere " + sphere.getRadius()); // System.out.println(i); } }
	 * 
	 * return minimum;
	 * 
	 * }
	 */

}
