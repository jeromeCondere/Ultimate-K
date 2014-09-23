package GameTool;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Circle extends AbstractGraphics {

	public Circle(int X,int Y,float R,Paint p)
	{
		super(X,Y,p);
		
		radius=R;
	}
	public Circle(int X,int Y,float R)
	{
		super(X,Y);
		radius=R;
	}
	public Circle()
	{
		super();
		radius=10;//default value
		
		
	}
	@Override
	public void draw(Canvas canvas) {
		// TODO Auto-generated method stub
		if(paint!=null)
           canvas.drawCircle(x, y, radius, paint);
	}
	
	private float radius;

}
