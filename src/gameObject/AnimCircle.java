package gameObject;

import gameObject.Ball.COLOR;
import GameTool.Circle;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class AnimCircle extends GameAnim {

	public  AnimCircle(COLOR color,float x,float y,int fps,float minR,float maxR)
	{
		super(fps);
		if(minR<0)
			this.minR=0;
		
		if(minR<maxR)
		{
			this.minR=minR;
			this.maxR=maxR;
		}
		else
		{
			this.minR=maxR;
			this.maxR=minR;
		}
		duration=200.0f;//0.2s
		q=(duration*0.001f)/(fps);
		this.activeColor=color;
		paint=new Paint( Paint.ANTI_ALIAS_FLAG );
		paint.setStyle(Paint.Style.STROKE);
		paint.setStrokeWidth(2f);
		this.initColor(COLOR.BLACK, paint);
		c=new Circle(x,y,minR,paint);
	}
	//must factorize (2 same versions)
	private void initColor(COLOR c,Paint p)
	{
		switch(c)
		{
		case RED :
			p.setColor(Color.RED);
			break;
		case BLUE :
			p.setColor(Color.BLUE);
			break;
		case BLACK :
			p.setColor(Color.BLACK);
			break;
		case PINK :
			p.setColor(Color.rgb(255, 174,201));
			break;
		case GREEN :
			p.setColor(Color.rgb(34, 177,76));
			break;
		
		}
	}
	public  AnimCircle(float duration,COLOR color,float x,float y,int fps,float minR,float maxR)
	{
		super(fps,duration);
		if(minR<0)
			this.minR=0;
		
		if(minR<maxR)
		{
			this.minR=minR;
			this.maxR=maxR;
		}
		else
		{
			this.minR=maxR;
			this.maxR=minR;
		}
		duration=200.0f;//0.2s
		q=(duration*0.001f)/(fps);
		this.activeColor=color;
		c=new Circle(x,y,minR);
	
	
	}
	
	
	@Override
	public void update() 
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void draw(Canvas canvas) {
		// TODO Auto-generated method stub

		
	}
	private COLOR activeColor;
	private float minR,maxR;
	private float q;
	private Circle c;
	private Paint paint;
}
