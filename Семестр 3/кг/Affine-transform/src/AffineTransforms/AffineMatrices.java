package AffineTransforms;

import java.util.ArrayList;
import java.util.List;

import ru.vsu.cs.Math.*;
/**
 * Вспомогательный класс для инициализации матриц масштабирования (Scale), поворота (Rotate), перемещения (Translate)
 * 
 * */
public class AffineMatrices {
	
	public AffineMatrices() {
		
	}
	
	/**
	 * Инициализация матрицы увеличения (Scale)
	 * 
	 * @param x Величина масштабирования вдоль оси x
	 * @param y Величина масштабирования вдоль оси y
	 * @param z Величина масштабирования вдоль оси z
	 * */
	public static Matrix4f initScale(float x, float y, float z) {
		Matrix4f mat = new Matrix4f(new float[4][4]);
		
		mat.getMatrix()[0][0] = x;	mat.getMatrix()[0][1] = 0; 	mat.getMatrix()[0][2] = 0; mat.getMatrix()[0][3] = 0;
		mat.getMatrix()[1][0] = 0;	mat.getMatrix()[1][1] = y; 	mat.getMatrix()[1][2] = 0; mat.getMatrix()[1][3] = 0;
		mat.getMatrix()[2][0] = 0;	mat.getMatrix()[2][1] = 0; 	mat.getMatrix()[2][2] = z; mat.getMatrix()[2][3] = 0;
		mat.getMatrix()[3][0] = 0;	mat.getMatrix()[3][1] = 0; 	mat.getMatrix()[3][2] = 0; mat.getMatrix()[3][3] = 1;
		
		return mat;
	}
	
	/**
	 * Инициализация матрицы поворота (Rotate)
	 * Результирующая матрица представляет собой произведение матриц поворота относительно осей x, y, z соответственно
	 * 
	 * @param x Угол поворота вдоль оси x
	 * @param y Угол поворота вдоль оси y
	 * @param z Угол поворота вдоль оси z
	 * */
	public static Matrix4f initRotation(float x, float y, float z) {
		Matrix4f rx = new Matrix4f(new float[4][4]);
		Matrix4f ry = new Matrix4f(new float[4][4]);
		Matrix4f rz = new Matrix4f(new float[4][4]);
		
		x =(float) Math.toRadians(x);
		y =(float) Math.toRadians(y);
		z =(float) Math.toRadians(z);
		
		rz.getMatrix()[0][0] = (float)Math.cos(z);		rz.getMatrix()[0][1] = (float)Math.sin(z);		rz.getMatrix()[0][2] = 0;						rz.getMatrix()[0][3] = 0;
		rz.getMatrix()[1][0] = -(float)Math.sin(z);		rz.getMatrix()[1][1] = (float)Math.cos(z);		rz.getMatrix()[1][2] = 0;						rz.getMatrix()[1][3] = 0;
		rz.getMatrix()[2][0] = 0;						rz.getMatrix()[2][1] = 0;						rz.getMatrix()[2][2] = 1;						rz.getMatrix()[2][3] = 0;
		rz.getMatrix()[3][0] = 0;						rz.getMatrix()[3][1] = 0;						rz.getMatrix()[3][2] = 0;						rz.getMatrix()[3][3] = 1;
		
		rx.getMatrix()[0][0] = 1;						rx.getMatrix()[0][1] = 0;						rx.getMatrix()[0][2] = 0;						rx.getMatrix()[0][3] = 0;
		rx.getMatrix()[1][0] = 0;						rx.getMatrix()[1][1] = (float)Math.cos(x);		rx.getMatrix()[1][2] = (float)Math.sin(x);		rx.getMatrix()[1][3] = 0;
		rx.getMatrix()[2][0] = 0;						rx.getMatrix()[2][1] = -(float)Math.sin(x);		rx.getMatrix()[2][2] = (float)Math.cos(x);		rx.getMatrix()[2][3] = 0;
		rx.getMatrix()[3][0] = 0;						rx.getMatrix()[3][1] = 0;						rx.getMatrix()[3][2] = 0;						rx.getMatrix()[3][3] = 1;
		
		ry.getMatrix()[0][0] = (float)Math.cos(y);		ry.getMatrix()[0][1] = 0;						ry.getMatrix()[0][2] = (float)Math.sin(y);		ry.getMatrix()[0][3] = 0;
		ry.getMatrix()[1][0] = 0;						ry.getMatrix()[1][1] = 1;						ry.getMatrix()[1][2] = 0;						ry.getMatrix()[1][3] = 0;
		ry.getMatrix()[2][0] = -(float)Math.sin(y);		ry.getMatrix()[2][1] = 0;						ry.getMatrix()[2][2] = (float)Math.cos(y);		ry.getMatrix()[2][3] = 0;
		ry.getMatrix()[3][0] = 0;						ry.getMatrix()[3][1] = 0;						ry.getMatrix()[3][2] = 0;						ry.getMatrix()[3][3] = 1;
		
		return rx.multiply(ry).multiply(rz);
		
	}
	
	/**
	 * Инициализация матрицы смещения (Translate)
	 * 
	 * @param x Величина смещения модели вдоль оси x
	 * @param y Величина смещения модели вдоль оси y
	 * @param z Величина смещения модели вдоль оси z
	 * */
	public static Matrix4f initTranslation(float x, float y, float z) {
		Matrix4f mat = new Matrix4f(new float[4][4]);
		
		mat.getMatrix()[0][0] = 1;	mat.getMatrix()[0][1] = 0; 	mat.getMatrix()[0][2] = 0; mat.getMatrix()[0][3] = x;
		mat.getMatrix()[1][0] = 0;	mat.getMatrix()[1][1] = 1; 	mat.getMatrix()[1][2] = 0; mat.getMatrix()[1][3] = y;
		mat.getMatrix()[2][0] = 0;	mat.getMatrix()[2][1] = 0; 	mat.getMatrix()[2][2] = 1; mat.getMatrix()[2][3] = z;
		mat.getMatrix()[3][0] = 0;	mat.getMatrix()[3][1] = 0; 	mat.getMatrix()[3][2] = 0; mat.getMatrix()[3][3] = 1;
		
		return mat;
	}
	
	public static ArrayList<Vector3f> applyTransformToVertices(ArrayList<Vector3f> verticesToTransform, Matrix4f transformMatrix){
		
		ArrayList<Vector3f> transformedVertices = new ArrayList<Vector3f>();
		for(int i = 0; i < verticesToTransform.size(); i++) {
			Vector3f vertex = verticesToTransform.get(i);
			Vector4f augmentedVertex = new Vector4f(vertex.getX(), vertex.getY(),vertex.getZ(), 1);
			Vector4f transformedVertex = transformMatrix.multiply(augmentedVertex);
			transformedVertices.add(new Vector3f(transformedVertex.getX(),transformedVertex.getY(), transformedVertex.getZ()));
		}
		
		return transformedVertices;
	}
}
