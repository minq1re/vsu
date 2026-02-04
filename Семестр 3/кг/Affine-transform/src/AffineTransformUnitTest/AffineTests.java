package AffineTransformUnitTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import AffineTransforms.*;
import ru.vsu.cs.Math.*;
class AffineTests extends AffineMatrices{
	
	@Test
	void scale1() {
		Vector4f v4f = new Vector4f(1, 1, 1,1);
		Matrix4f scale1 = AffineMatrices.initScale(3, 3, 3);
		Vector4f scaledv4d = scale1.multiply(v4f);
		Assertions.assertEquals(3, scaledv4d.getX());
		Assertions.assertEquals(3, scaledv4d.getY());
		Assertions.assertEquals(3, scaledv4d.getZ());
	}
	
	@Test
	void scale2() {
		Vector4f v4f = new Vector4f(6, 7, 9,1);
		Matrix4f scale2 = AffineMatrices.initScale(2, 3, 9);
		Vector4f scaledv4d = scale2.multiply(v4f);
		Assertions.assertEquals(12, scaledv4d.getX());
		Assertions.assertEquals(21, scaledv4d.getY());
		Assertions.assertEquals(81, scaledv4d.getZ());
	}
	
	@Test
	void scale3() {
		Vector4f v4f = new Vector4f(5, 8, 9,1);
		Matrix4f scale3 = AffineMatrices.initScale((float)Math.pow(2, -1), (float)Math.pow(4, -1), (float)Math.pow(3, -1));
		Vector4f scaledv4d = scale3.multiply(v4f);
		Assertions.assertEquals(2.5, scaledv4d.getX());
		Assertions.assertEquals(2, scaledv4d.getY());
		Assertions.assertEquals(3, scaledv4d.getZ());
	}
	
	@Test
	void translate1() {
		Vector4f v4f = new Vector4f(1, 1, 1,1);
		Matrix4f translate1 = AffineMatrices.initTranslation(15, 8, -3);
		Vector4f translatedv4f = translate1.multiply(v4f);
		Assertions.assertEquals(16, translatedv4f.getX());
		Assertions.assertEquals(9, translatedv4f.getY());
		Assertions.assertEquals(-2, translatedv4f.getZ());
	}
	
	@Test
	void translate2() {
		Vector4f v4f = new Vector4f(1, 1, 1,1);
		Matrix4f translate2 = AffineMatrices.initTranslation(0, 10, -1);
		Vector4f translatedv4f = translate2.multiply(v4f);
		Assertions.assertEquals(1, translatedv4f.getX());
		Assertions.assertEquals(11, translatedv4f.getY());
		Assertions.assertEquals(0, translatedv4f.getZ());
	}
	
	@Test
	void rotateZ() {
		double eps = 0.00001;
		Vector4f v4f = new Vector4f(1, 1, 1,1);
		Matrix4f rotate1 = AffineMatrices.initRotation(0, 0, 45);
		Vector4f rotatev4f = rotate1.multiply(v4f);
		Assertions.assertTrue(Math.sqrt(2) - rotatev4f.getX() < eps);
		Assertions.assertEquals(0, rotatev4f.getY());
		Assertions.assertEquals(1, rotatev4f.getZ());
	}
	
	@Test
	void rotateY() {
		double eps = 0.00001;
		Vector4f v4f = new Vector4f(1, 1, 1,1);
		Matrix4f rotate2 = AffineMatrices.initRotation(0, 45, 0);
		Vector4f rotatev4f = rotate2.multiply(v4f);
		Assertions.assertTrue(Math.sqrt(2) - rotatev4f.getX() < eps);
		Assertions.assertEquals(1, rotatev4f.getY());
		Assertions.assertEquals(0, rotatev4f.getZ());
	}
	
	@Test
	void rotateX() {
		double eps = 0.00001;
		Vector4f v4f = new Vector4f(1, 1, 1,1);
		Matrix4f rotate2 = AffineMatrices.initRotation(45, 0, 0);
		Vector4f rotatev4f = rotate2.multiply(v4f);
		Assertions.assertEquals(1, rotatev4f.getX());
		Assertions.assertTrue(Math.sqrt(2) - rotatev4f.getY() < eps);
		Assertions.assertEquals(0, rotatev4f.getZ());
	}
}
