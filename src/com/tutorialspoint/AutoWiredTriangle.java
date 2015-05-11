package com.tutorialspoint;

public class AutoWiredTriangle {

	
	/* Autowiring works by setting the tag: autowire="byName"
	 * This will come here and look at the name of each class variable
	 * and look in the XML file for id's that match these names.
	 * 
	 * Auto wiring is not recommended for large amounts of data! */
	
	private Point pointA;
	private Point pointB;
	private Point pointC;
	
	public void draw() {
		System.out.println("Point A: (" + getPointA().getX() + ", " + getPointA().getY() + ")");
		System.out.println("Point B: (" + getPointB().getX() + ", " + getPointB().getY() + ")");
		System.out.println("Point C: (" + getPointC().getX() + ", " + getPointC().getY() + ")");
	}

	
	
	public Point getPointA() {
		return pointA;
	}

	public void setPointA(Point pointA) {
		this.pointA = pointA;
	}

	public Point getPointB() {
		return pointB;
	}

	public void setPointB(Point pointB) {
		this.pointB = pointB;
	}

	public Point getPointC() {
		return pointC;
	}

	public void setPointC(Point pointC) {
		this.pointC = pointC;
	}
	
}
