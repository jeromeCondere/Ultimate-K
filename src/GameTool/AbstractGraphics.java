package GameTool;

import android.graphics.Canvas;
import android.graphics.Paint;

public abstract class AbstractGraphics implements Graphics {

	public AbstractGraphics(float X,float Y)
	{
		x=X;
		y=Y;
	}
	public AbstractGraphics()
	{
		x=0;
		y=0;
		//we don't initialize paint-> the users must do it otherwise we don't draw
	}
	public AbstractGraphics(float X,float Y,Paint p)
	{
		x=X;
		y=Y;
		paint=p;
		
	}
	
	@Override
	public float getX() {
		// TODO Auto-generated method stub
		return x;
	}

	@Override
	public float getY() {
		// TODO Auto-generated method stub
		return y;
	}
	public Paint getPaint()
	{
		return paint;
	}

	@Override
	public void setX(float X) {
		// TODO Auto-generated method stub
     x=X;
	}

	@Override
	public void setY(float Y) {
		// TODO Auto-generated method stub
      y=Y;
	}

	@Override
	public void setPaint(Paint p)
	{
		if(paint==null)
			paint=p;
	}
	
	
	@Override
	public void addX(float X) {
		// TODO Auto-generated method stub
      x+=X;
	}

	@Override
	public void addY(float Y) {
		// TODO Auto-generated method stub
     y+=Y;
	}

	@Override
	public void subX(float X) {
		// TODO Auto-generated method stub
      x-=X;
	}

	@Override
	public void subY(float Y) {
		// TODO Auto-generated method stub
      y-=Y;
	}

	@Override
	public abstract void draw(Canvas canvas);
	protected float x;
	protected float y;
	protected Paint paint=null;

}
