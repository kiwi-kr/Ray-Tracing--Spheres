package assignment255;

import java.io.FileNotFoundException;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Toggle;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.stage.Stage;
import java.util.ArrayList;
import javafx.geometry.Insets;

public class Main extends Application {
	static int Width = 640;
	static int Height = 640;
	static int widthCenter = Width / 2;
	static int heightCenter = Height / 2;

	static double greenColour = 0.93;
	static double redColour = 0.724;
	static double blueColour = 0.2;

	double xD;
	double yD;
	double zD;

	int x;
	int y;
	int z;

	public static Vector sphereColour1 = new Vector(0.44, 0.9, 0.1);
	public static Vector sphereColour2 = new Vector(0.2, 0.7, 0.63);
	public static Vector sphereColour3 = new Vector(0.8, 0.1, 0.53);
	public static Vector sphereColourSelected;

	Sphere sphere1 = new Sphere(sphereColour1, 20, 5, 12, 2);
	Sphere sphere2 = new Sphere(sphereColour2, 70, 50, 120, 54);
	Sphere sphere3 = new Sphere(sphereColour3, 50, 140, 120, 100);

	Sphere sphereSelected;

	ArrayList<Sphere> world = new ArrayList<Sphere>();
	{
		world.add(sphere1);
		world.add(sphere2);
		world.add(sphere3);
	}

	public static void setSlidersToValues(Slider redSlider, Slider greenSlider, Slider blueSlider, Slider xSlider, Slider ySlider, Slider zSlider, Sphere sphere, Vector sphereColour) { 
		redSlider.setValue((sphereColour.x) * 255);
		greenSlider.setValue((sphereColour.y) * 255);
		blueSlider.setValue((sphereColour.z) * 255);
		
		xSlider.setValue(sphere.getX());
		ySlider.setValue(sphere.getY());
		zSlider.setValue(sphere.getZ());
	}
	
	@Override
	public void start(Stage stage) throws FileNotFoundException {
		stage.setTitle("Ray Tracing");

		WritableImage image = new WritableImage(Width, Height);
		ImageView view = new ImageView(image);

		Slider redSlider = new Slider(0, 255, redColour);
		Slider greenSlider = new Slider(0, 255, greenColour);
		Slider blueSlider = new Slider(0, 255, blueColour);

		Button button = new Button();
		button.setText("Render");

		Label redLabel = new Label("Red");
		Label greenLabel = new Label("Green");
		Label blueLabel = new Label("Blue");

		Slider xSlider = new Slider(-320, 320, xD);
		Slider ySlider = new Slider(-320, 320, yD);
		Slider zSlider = new Slider(0, 320, zD);
		Label xLabel = new Label("X");
		Label yLabel = new Label("Y");
		Label zLabel = new Label("Z");

		RadioButton r1 = new RadioButton("Sphere 1");
		RadioButton r2 = new RadioButton("Sphere 2");
		RadioButton r3 = new RadioButton("Sphere 3");
		ToggleGroup tg = new ToggleGroup();
		r1.setToggleGroup(tg);
		r2.setToggleGroup(tg);
		r3.setToggleGroup(tg);

		tg.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
			public void changed(ObservableValue<? extends Toggle> ob, Toggle o, Toggle n) {

				RadioButton rb = (RadioButton) tg.getSelectedToggle();

				if (rb != null) {
					String s = rb.getText();

					// change the label
					System.out.println(s + " selected");

					switch (s) {
					case "Sphere 1":
						sphereSelected = sphere1;
						sphereColourSelected = sphereColour1;
						setSlidersToValues( redSlider,  greenSlider,  blueSlider,  xSlider,  ySlider,  zSlider, sphere1, sphereColour1);	
						System.out.println(sphereColour1.x);
						break;
					case "Sphere 2":
						sphereSelected = sphere2;
						sphereColourSelected = sphereColour2;
						setSlidersToValues( redSlider,  greenSlider,  blueSlider,  xSlider,  ySlider,  zSlider, sphere2, sphereColour2);						

						break;
					case "Sphere 3":
						sphereSelected = sphere3;
						sphereColourSelected = sphereColour3;
						setSlidersToValues( redSlider,  greenSlider,  blueSlider,  xSlider,  ySlider,  zSlider, sphere3, sphereColour3);						

						break;
					}
				}
			}
		});

		// Add all the event handlers
		greenSlider.valueProperty().addListener(new ChangeListener<Number>() {
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				greenColour = newValue.intValue();
				greenColour = greenColour / 255;
				sphereColourSelected.setY(greenColour);

			}
		});

		redSlider.valueProperty().addListener(new ChangeListener<Number>() {
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				redColour = newValue.intValue();
				// Render(image);
				redColour = redColour / 255;
				sphereColourSelected.setX(redColour);
				System.out.println(redColour);

			}
		});

		blueSlider.valueProperty().addListener(new ChangeListener<Number>() {
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				blueColour = newValue.intValue();
				blueColour = blueColour / 255;
				sphereColourSelected.setZ(blueColour);

			}
		});

		xSlider.valueProperty().addListener(new ChangeListener<Number>() {
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				xD = newValue.intValue();
				x = (int) xD;
				sphereSelected.setX(x);
				System.out.println(x);

			}
		});

		ySlider.valueProperty().addListener(new ChangeListener<Number>() {
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				yD = newValue.intValue();
				y = (int) yD;
				sphereSelected.setY(y);
				System.out.println(y);

			}
		});

		zSlider.valueProperty().addListener(new ChangeListener<Number>() {
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				zD = newValue.intValue();
				z = (int) zD;
				sphereSelected.setZ(z);
				System.out.println("Z:" + z);
				System.out.println("sphere 2 Z:" + sphere2.getZ());

			}
		});

		EventHandler<ActionEvent> buttonPressed = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				sphereSelected.setCenterofSphere(x, y, z);

				Render.render(image, world);

				System.out.println("Red:" + redColour + "/n Green: " + greenColour + "/n Blue" + blueColour);
				System.out.println(x + " " + y + " " + z);
				System.out.println(sphereSelected.getX());
				System.out.println(world.get(0).getX());
				System.out.println(sphereSelected.getCentreOfSphere());
			}
		};

		button.setOnAction(buttonPressed);

		Render.render(image, world);

		GridPane root = new GridPane();
		
		

		GridPane gPane = new GridPane();
		
		
		gPane.setHgap(10);
		gPane.setVgap(10);
		gPane.setPadding(new Insets(0, 10, 0, 10));
		root.setVgap(12);
		root.setHgap(12);

		root.add(gPane, 1, 0);
		root.add(view, 0, 0);
		root.add(redSlider, 0, 1);
		applySliderDeco(redSlider);
		root.add(redLabel, 1, 1);

		root.add(greenSlider, 0, 2);
		applySliderDeco(greenSlider);

		root.add(greenLabel, 1, 2);

		root.add(blueSlider, 0, 3);
		applySliderDeco(blueSlider);

		root.add(blueLabel, 1, 3);
		root.add(button, 0, 0);

		gPane.add(xSlider, 0, 0);
		gPane.add(ySlider, 0, 1);
		gPane.add(zSlider, 0, 2);
		applySliderDeco(xSlider);
		applySliderDeco(ySlider);
		applySliderDeco(zSlider);
		gPane.add(xLabel, 1, 0);
		gPane.add(yLabel, 1, 1);
		gPane.add(zLabel, 1, 2);

		gPane.add(r1, 0, 3);
		gPane.add(r2, 2, 3);
		gPane.add(r3, 3, 3);

		Scene scene = new Scene(root, 1024, 768);
		stage.setScene(scene);
		stage.show();
	}
	
	public void applySliderDeco(Slider slider) { 
		slider.setShowTickMarks(true);
		slider.setShowTickLabels(true);
		slider.setMajorTickUnit(50);
		
		slider.setBlockIncrement(10);
	}

	public static void main(String[] args) {
		launch();
	}

	/**
	 * @return the width
	 */
	public int getWidth() {
		return Width;
	}

	/**
	 * @param width the width to set
	 */
	public void setWidth(int width) {
		Width = width;
	}

	/**
	 * @return the height
	 */
	public int getHeight() {
		return Height;
	}

	/**
	 * @param height the height to set
	 */
	public void setHeight(int height) {
		Height = height;
	}

	/**
	 * @return the widthCenter
	 */
	public static int getWidthCenter() {
		return widthCenter;
	}

	/**
	 * @param widthCenter the widthCenter to set
	 */
	public void setWidthCenter(int widthCenter) {
		this.widthCenter = widthCenter;
	}

	/**
	 * @return the heightCenter
	 */
	public static int getHeightCenter() {
		return heightCenter;
	}

	/**
	 * @param heightCenter the heightCenter to set
	 */
	public void setHeightCenter(int heightCenter) {
		this.heightCenter = heightCenter;
	}

	/**
	 * @return the greenColour
	 */
	public static double getGreenColour() {
		return greenColour;
	}

	/**
	 * @param greenColour the greenColour to set
	 */
	public void setGreenColour(double greenColour) {
		this.greenColour = greenColour / 255;
	}

	/**
	 * @return the redColour
	 */
	public static double getRedColour() {
		return redColour;
	}

	/**
	 * @param redColour the redColour to set
	 */
	public void setRedColour(double redColour) {
		this.redColour = redColour / 255;
	}

	/**
	 * @return the blueColour
	 */
	public static double getBlueColour() {
		return blueColour;
	}

	/**
	 * @param blueColour the blueColour to set
	 */
	public void setBlueColour(double blueColour) {
		this.blueColour = blueColour / 255;
	}

	/**
	 * @return the sphereSelected
	 */
	public Sphere getSphereSelected() {
		return sphereSelected;
	}

	/**
	 * @return the sphereColourSelected
	 */
	public static Vector getSphereColourSelected() {
		return sphereColourSelected;
	}

}