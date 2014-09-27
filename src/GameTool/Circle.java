package GameTool;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Circle extends AbstractGraphics {

	public Circle(float X,float Y,float R,Paint p)
	{
		super(X,Y,p);
		
		radius=R;
	}
	public Circle(float X,float Y,float R)
	{
		super(X,Y);
		radius=R;
	}
	public Circle()
	{
		super();
		radius=10;//default value
		
		
	}
	public float getRadius()
	{
		return radius;
	}
	public void addRadius(float addR)
	{
		if((addR+radius)>=0)
			radius+=addR;
	}
	public void subRadius(float subR)
	{
		if((radius-subR)>=0)
			radius-=subR;
	}
	public void setRadius(float r)
	{
		radius=r;
	}
	@Override
	public void draw(Canvas canvas) {
		// TODO Auto-generated method stub
		if(paint!=null)
           canvas.drawCircle(x, y, radius, paint);
	}
	
	private float radius;

}
