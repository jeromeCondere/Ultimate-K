package GameTool;

import android.graphics.Canvas;
import android.graphics.Paint;

public abstract class AbstractGraphics implements Graphics {

	public AbstractGraphics(int X,int Y)
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
	public AbstractGraphics(int X,int Y,Paint p)
	{
		x=X;
		y=Y;
		paint=p;
		
	}
	
	@Override
	public int getX() {
		// TODO Auto-generated method stub
		return x;
	}

	@Override
	public int getY() {
		// TODO Auto-generated method stub
		return y;
	}
	public Paint getPaint()
	{
		return paint;
	}

	@Override
	public void setX(int X) {
		// TODO Auto-generated method stub
     x=X;
	}

	@Override
	public void setY(int Y) {
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
	public void addX(int X) {
		// TODO Auto-generated method stub
      x+=X;
	}

	@Override
	public void addY(int Y) {
		// TODO Auto-generated method stub
     y+=Y;
	}

	@Override
	public void subX(int X) {
		// TODO Auto-generated method stub
      x-=X;
	}

	@Override
	public void subY(int Y) {
		// TODO Auto-generated method stub
      y-=Y;
	}

	@Override
	public abstract void draw(Canvas canvas);
	protected int x;
	protected int y;
	protected Paint paint=null;

}
