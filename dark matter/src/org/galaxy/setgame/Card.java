package org.galaxy.setgame;

public class Card {
	
	private NumberType number;
	private ColorType color;
	private ShapeType shape;
	private ShadeType shade;

	public Card(NumberType number, ColorType color, ShapeType shape, ShadeType shade) {
		this.number = number;
		this.color = color;
		this.shape = shape;
		this.shade = shade;
	}
	
	public NumberType getNumber() {
		return number;
	}

	public void setNumber(NumberType number) {
		this.number = number;
	}

	public ColorType getColor() {
		return color;
	}

	public void setColor(ColorType color) {
		this.color = color;
	}

	public ShapeType getShape() {
		return shape;
	}

	public void setShape(ShapeType shape) {
		this.shape = shape;
	}

	public ShadeType getShade() {
		return shade;
	}

	public void setShade(ShadeType shade) {
		this.shade = shade;
	}

	@Override
	public String toString() {
		return "Card [number=" + number + ", color=" + color + ", shape="
				+ shape + ", shade=" + shade + "]";
	}

}
