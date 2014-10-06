package gameObject;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.TypedValue;
import android.view.Display;
import android.view.WindowManager;
import GameTool.Circle;
import GameTool.GameTimer;

public class Ball extends Abstract_Object implements Object_interface {

	public  Ball()
	{
		activeState=STATE.ACTIVE;
		activeColor=COLOR.BLACK;
		activeSpeed=SPEED.SLOW;
		angle=0.0f;
		minRadius=10;
		maxRadius=30;
		
		Paint p=new Paint( Paint.ANTI_ALIAS_FLAG );
		p.setStyle(Paint.Style.FILL);
		this.initColor(COLOR.BLACK, p);
		circle=new Circle(0,0,minRadius,p);
		timer=new GameTimer();
		//default parameters
	}
	public Ball(int initX,int initY)
	{
		activeState=STATE.ACTIVE;
		activeColor=COLOR.BLACK;
		activeSpeed=SPEED.SLOW;
		angle=0.0f;
		minRadius=10;
		maxRadius=30;
		Paint p=new Paint( Paint.ANTI_ALIAS_FLAG );
		p.setStyle(Paint.Style.FILL);
		this.initColor(COLOR.BLACK, p);
		circle=new Circle(initX,initY,minRadius,p);
		timer=new GameTimer();
	}
	public Ball(STATE state,COLOR color,SPEED speed)
	{
		init(10,30,0);//default params
		activeState=state;
		activeColor=color;
		activeSpeed=speed;
		
		Paint p=new Paint( Paint.ANTI_ALIAS_FLAG );
		p.setStyle(Paint.Style.FILL);
		this.initColor(color, p);
		circle=new Circle(0,0,minRadius,p);//default position is (0,0)
		timer=new GameTimer();
	}
	
	public Ball(STATE state,COLOR color,SPEED speed,float minR,float maxR,float angle)
	{
		init(minR,maxR,angle);//default params
		activeState=state;
		activeColor=color;
		activeSpeed=speed;
		Paint p=new Paint( Paint.ANTI_ALIAS_FLAG );
		p.setStyle(Paint.Style.FILL);
		this.initColor(color, p);
		circle=new Circle(0,0,minRadius,p);//default position is (0,0)
		timer=new GameTimer();
	}
	public Ball(STATE state,COLOR color,SPEED speed,float minR,float maxR,float X,float Y,float angle)
	{
		init(minR,maxR,angle);//default params
		activeState=state;
		activeColor=color;
		activeSpeed=speed;
		Paint p=new Paint( Paint.ANTI_ALIAS_FLAG );
		p.setStyle(Paint.Style.FILL);
		this.initColor(color, p);
		circle=new Circle(X,Y,minRadius,p);//default position is (X,Y)
		timer=new GameTimer();
	}
	public Ball(STATE state,COLOR color,SPEED speed,Paint p,float minR,float maxR,float X,float Y,float angle)
	{
		init(minR,maxR,angle);//default params
		activeState=state;
		activeColor=color;
		activeSpeed=speed;
		this.initColor(color, p);
		circle=new Circle(X,Y,minRadius,p);//default position is (X,Y)
		timer=new GameTimer();
	}
	
	
	private void init(float minR,float maxR,float Angle)
	{
		if(minR<maxR &&minR>=0)
		{
		minRadius=minR;
		maxRadius=maxR;
		}
		else
		{
			minRadius=10;
			maxRadius=30;
		}
		this.angle=Angle;
	}
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
	@Override
	public void pause() {
		// TODO Auto-generated method stub
		activeState=STATE.PAUSED;//the state is paused

	}

	@Override
	public void start() {
		// TODO Auto-generated method stub

		
	}

	@Override
	public void restart() {
		// TODO Auto-generated method stub
		if(activeState==STATE.PAUSED)
		{
			activeState=STATE.ACTIVE;//if game has been paused we remake it active
		}

	}

	@Override
	public void update() 
	{
		// we must not initialize object here since this function will be called in the render loop via Ball Handler
		
		
		// the ball is moving along a line determine by the angle
		//y=tan(angle)x +b
		//the context window is needed for collision
		if(this.ContextHeight==0||this.ContextWidth==0)//if context has not been set up the we don't update
			return;
		
		if(this.activeState==STATE.INSTAKILLED)
		{
			//destroy();
			return;
		}
		
		
		float r =4;
		switch(activeSpeed)//avance dip->px of speed
		{
		case SLOW :
			
			r = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2, res.getDisplayMetrics());
			break;
		case FAST :
			r = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 6, res.getDisplayMetrics());
			break;
		case NONE :
			r = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 0.5f, res.getDisplayMetrics());
			break;
		case NORMAL :
			r = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, res.getDisplayMetrics());
			break;
		case SUPER_FAST :
			r = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 9, res.getDisplayMetrics());
			break;
		
		
		}
		
		
		if(activeState==STATE.ACTIVE || activeState==STATE.INACTIVE)
		{
		double angleRad=Math.toRadians(angle);
		float addX=(float) (r*Math.cos(angleRad));
		float addY=(float) (r*Math.sin(angleRad));
		float hlimit=ContextHeight-circle.getRadius();
		float wlimit=ContextWidth-circle.getRadius();
		
		
		//collisions
			if(circle.getX()<=wlimit && circle.getY()<=hlimit && circle.getX()>=0 && circle.getY()>=0)
			{
				//we are in screen zone
		    circle.addY(addY);
		    circle.addX(addX);
			}
			if (circle.getX()>=wlimit)//right border collision
			{
				angle=180-angle;
				 angleRad=Math.toRadians(angle);
				 addX=(float) (r*Math.cos(angleRad));
				 addY=(float) (r*Math.sin(angleRad));
				 circle.addY(addY);
				 circle.addX(addX);
				
			}
			 if (circle.getY()>=hlimit)//bottom border collision
			{
				angle=-angle;
				 angleRad=Math.toRadians(angle);
				 addX=(float) (r*Math.cos(angleRad));
				 addY=(float) (r*Math.sin(angleRad));
				 circle.addY(addY);
				 circle.addX(addX);
				
				
			} 
			 if ((circle.getY()-circle.getRadius())<=0)//top border collision
			{
				angle=-angle;
				 angleRad=Math.toRadians(angle);
				 addX=(float) (r*Math.cos(angleRad));
				 addY=(float) (r*Math.sin(angleRad));
				 circle.addY(addY);
				 circle.addX(addX);
				
				
			} 
			 if ((circle.getX()-circle.getRadius())<=0)//left border collision
			{
				angle=180-angle;
				 angleRad=Math.toRadians(angle);
				 addX=(float) (r*Math.cos(angleRad));
				 addY=(float) (r*Math.sin(angleRad));
				 circle.addY(addY);
				 circle.addX(addX);
				
				
			}
			
			sizeChanging();
		}
		
		
	}
	
	private boolean grow(int fps)//the function return false while the ball is growing to maxRadius
	{
		final int parts=25;
		if(n%3!=0)
		{
			n=0;
			return false;
		}
		
		if(fps<0)
			return false;
		if(circle.getRadius()>=minRadius && circle.getRadius()<maxRadius)
		{
			float addR=(maxRadius-minRadius)/parts;
			n++;
			circle.addRadius(addR);
			return false;
		}
		else if(circle.getRadius()>=maxRadius)//finish growing
		{
			if(circle.getRadius()!=maxRadius)
			circle.setRadius(maxRadius);
			isGrowing=false;
			return true;
		}
		
		return false;
	}
	
	private boolean reduce(int fps)//the function return false while the ball is reducing to minRadius
	{
		final int parts=25;
		if(n%3!=0)
		{
			n=0;
			return false;
		}
		
		if(fps<0)
			return false;
		if(circle.getRadius()>minRadius && circle.getRadius()<=maxRadius)
		{
			float subR=(maxRadius-minRadius)/parts;
			n++;
			circle.subRadius(subR);
			return false;
		}
		else if(circle.getRadius()<=minRadius)//finish reducing
		{
			if(circle.getRadius()!=minRadius)
			circle.setRadius(minRadius);
			isGrowing=true;
			return true;
		}
		
		return false;
	}
	
	private void sizeChanging()
	{
		
		if(activeColor==COLOR.BLUE)
		{
			
			if( isGrowing)
			{
				/*if(timer.switched)
				{
					timer.switched=false;
					timer.restart();
				}
				if(timer.run(1000)==1)//if there is more than 1 sec elapsed
				{*/
				grow(20);
				return;
				//}
			}
			else
			{
				/*if(!timer.switched)
				{
					timer.switched=true;
					timer.restart();
				}
				if(timer.run(1000)==1)*/
				reduce(20);
			}
		}
	}
	
	private void destroy()//
	{
		if(activeState==STATE.KILLED ||activeState==STATE.INSTAKILLED)
		{
		}
	}
	public void instaKill()
	{
		if(activeState==STATE.ACTIVE )//we can't kill a ball that was paused or inactive
		{
			activeState=STATE.INSTAKILLED;
			circle.setRadius(0);
		}
	}
	
	@Override
	public void draw(Canvas canvas) {
		// TODO Auto-generated method stub
		if(this.ContextHeight==0||this.ContextWidth==0||circle.getRadius()<=0)//if context has not been set up the we don't draw
			return;
		
		
		if(canvas!=null)
		{
			//if(activeState!=STATE.INSTAKILLED)
		     circle.draw(canvas);
		}
	}
	
	

	

public float getX()
{
	return circle.getX();
}
public float getY()
{
	return circle.getY();
}

public float getRadius()
{
	return circle.getRadius();
}
public Ball.STATE getState()
{
	return activeState;
}
public void slow()
{
	/*
	 activeSpeed=NONE;
	  
	 */
	 
	switch(activeSpeed)
	{
	case SLOW :
		activeSpeed=SPEED.NONE;
		break;
	case NORMAL :
		activeSpeed=SPEED.SLOW;
		break;
	default :
		activeSpeed=SPEED.NORMAL;
		break;
	}
	
	
}
public void Accelerate()
{
	
	 
	switch(activeSpeed)
	{
	case NONE :
		activeSpeed=SPEED.NORMAL;
		break;
	case SLOW :
		activeSpeed=SPEED.NORMAL;
		break;
	case NORMAL :
		activeSpeed=SPEED.FAST;
		break;
	default :
		activeSpeed=SPEED.SUPER_FAST;
		break;
	}
	
	
}
private Circle circle;

//---------enums--------------
public enum STATE{ACTIVE,INACTIVE,PAUSED,KILLED,INSTAKILLED};
public enum COLOR{RED,BLUE,BLACK,PINK,GREEN};
public enum SPEED{SLOW,NORMAL,FAST,SUPER_FAST,NONE};
//----------------
private SPEED activeSpeed;
private STATE activeState;
private COLOR activeColor;
private float minRadius,maxRadius;//the ball radius between min and max
private float angle;

private boolean isGrowing=true;
private int n;


private GameTimer timer;
}
