package AffineTransforms;

import java.util.ArrayList;
import java.util.List;

import ru.vsu.cs.Math.*;

/**
 * Класс для аффинных преобразований модели
 * 
 * */
public class AffineTransform {
	
	private Matrix4f scale = Matrix4f.createUnitMatrix() ;
	private Matrix4f rotate = Matrix4f.createUnitMatrix();
	private Matrix4f translate = Matrix4f.createUnitMatrix();
	
	public AffineTransform() {

	}
	
	public Matrix4f getTransformedMatrix() {
		return translate.multiply(rotate).multiply(scale);
	}
	
	public void setScale(float x, float y, float z) {
		scale = AffineMatrices.initScale(x, y, z);
	}
	
	public void setRotate(float x, float y, float z) {
		rotate = AffineMatrices.initRotation(x, y, z);
	}
	
	public void setTranslate(float x, float y, float z) {
		translate = AffineMatrices.initTranslation(x, y, z);
	}
	
	public void setScaleMatrix(Matrix4f scale) {
		this.scale = scale;
	}
	
	public void setRotateMatrix(Matrix4f rotate) {
		this.scale = rotate;
	}
	
	public void setTranslateMatrix(Matrix4f translate) {
		this.translate = translate;
	}
	
	public Matrix4f getScaleMatrix4f() {
		return scale;
	}
	
	public Matrix4f getRotateMatrix4f() {
		return rotate;
	}
	
	public Matrix4f getTranslateMatrix4f() {
		return translate;
	}
	
	public ArrayList<Vector3f> applyTransformToVertices(ArrayList<Vector3f> vertices, Matrix4f transformMatrix){
		return AffineMatrices.applyTransformToVertices(vertices, transformMatrix);
	}
	
	public void translateModel(Model model) {
		model.vertices = applyTransformToVertices(model.vertices, getTranslateMatrix4f());
	}
	
	public void scaleModel(Model model) {
		model.vertices = applyTransformToVertices(model.vertices, getScaleMatrix4f());
	}
	
	public void rotateModel(Model model) {
		model.vertices = applyTransformToVertices(model.vertices, getRotateMatrix4f());
	}
}
