package it.polimi.ingsw.cg23.utility;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * The class of ColorManager. It helps to manage the game's colors.
 * 
 * @author Andrea
 *
 */
public class ColorManager {

	private Map<String, Color> colorList;

	/**
	 * The constructor of ColorManager. Initializes the list of the color.
	 */
	public ColorManager() {
		this.colorList = new HashMap<>();
		initColorList();
	}

	/**
	 * Initializes the color list.
	 */
	private void initColorList() {
		colorList.put("AliceBlue", new Color(0xF0, 0xF8, 0xFF));
		colorList.put("Aquamarine", new Color(0x7F, 0xFF, 0xD4));
		colorList.put("Azure", new Color(0xF0, 0xFF, 0xFF));
		colorList.put("Beige", new Color(0xF5, 0xF5, 0xDC));
		colorList.put("Black", new Color(0x00, 0x00, 0x00));
		colorList.put("Blue", new Color(0x00, 0x00, 0xFF));
		colorList.put("Brown", new Color(0xA5, 0x2A, 0x2A));
		colorList.put("Fuchsia", new Color(0xFF, 0x00, 0xFF));
		colorList.put("Gold", new Color(0xFF, 0xD7, 0x00));
		colorList.put("Gray", new Color(0x80, 0x80, 0x80));
		colorList.put("Green", new Color(0x00, 0x80, 0x00));
		colorList.put("Lavender", new Color(0xE6, 0xE6, 0xFA));
		colorList.put("Magenta", new Color(0xFF, 0x00, 0xFF));
		colorList.put("Orange", new Color(0xFF, 0x66, 0x00));
		colorList.put("Pink", new Color(0xFF, 0xAF, 0xAF));
		colorList.put("Purple", new Color(0x80, 0x00, 0x80));
		colorList.put("Red", new Color(0xFF, 0x00, 0x00));
		colorList.put("Silver", new Color(0xC0, 0xC0, 0xC0));
		colorList.put("Tan", new Color(0xD2, 0xB4, 0x8C));
		colorList.put("Teal", new Color(0x00, 0x80, 0x80));
		colorList.put("Thistle", new Color(0xD8, 0xBF, 0xD8));
		colorList.put("White", new Color(0xFF, 0xFF, 0xFF));
		colorList.put("Yellow", new Color(0xFF, 0xFF, 0x00));
		colorList.put("Violet", new Color(0x8b, 0x00, 0xff));
		colorList.put("Bronze", new Color(0xd2, 0x69, 0x1e));// #708090
		colorList.put("Iron", new Color(0x70, 0x80, 0x90));
	}

	/**
	 * Returns the color name from list.
	 * 
	 * @param color
	 *            The color to be searched.
	 * @return the name of the color. If the color isn't found returns null.
	 */
	public String getColorName(Color color) {
		if (colorList.containsValue(color)) {
			Set<String> colorNames = colorList.keySet();
			for (String name : colorNames) {
				if (colorList.get(name).equals(color))
					return name;
			}
		}
		return null;
	}

	/**
	 * Returns the color of the name.
	 * 
	 * @param name
	 *            the name of the color.
	 * @return the color or null if the name isn't found.
	 */
	public Color getColor(String name) {
		return colorList.get(name);
	}
}