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
		// TODO Auto-generated method stub
		// the ball is moving along a line determine by the angle
		//y=tan(angle)x +b
		if(this.ContextHeight==0||this.ContextWidth==0)//if context has not been set up the we don't update
			return;
		//the context window is needed for collision
		
		Resources res = context.getResources();
		float r =4;
		switch(activeSpeed)
		{
		case SLOW :
			
			r = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2, res.getDisplayMetrics());
			break;
		case FAST :
			r = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 6, res.getDisplayMetrics());
			break;
		case NONE :
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
			else if (circle.getX()>=wlimit)//right border collision
			{
				angle=180-angle;
				 angleRad=Math.toRadians(angle);
				 addX=(float) (r*Math.cos(angleRad));
				 addY=(float) (r*Math.sin(angleRad));
				 circle.addY(addY);
				 circle.addX(addX);
				
			}
			else if (circle.getY()>=hlimit)//bottom border collision
			{
				angle=-angle;
				 angleRad=Math.toRadians(angle);
				 addX=(float) (r*Math.cos(angleRad));
				 addY=(float) (r*Math.sin(angleRad));
				 circle.addY(addY);
				 circle.addX(addX);
				
				
			} 
			else if (circle.getY()<=0)//top border collision
			{
				angle=-angle;
				 angleRad=Math.toRadians(angle);
				 addX=(float) (r*Math.cos(angleRad));
				 addY=(float) (r*Math.sin(angleRad));
				 circle.addY(addY);
				 circle.addX(addX);
				
				
			} 
			else if (circle.getX()<=0)//left border collision
			{
				angle=180-angle;
				 angleRad=Math.toRadians(angle);
				 addX=(float) (r*Math.cos(angleRad));
				 addY=(float) (r*Math.sin(angleRad));
				 circle.addY(addY);
				 circle.addX(addX);
				
				
			}
			
		}
		
		
	}
	@Override
	public void draw(Canvas canvas) {
		// TODO Auto-generated method stub
		if(this.ContextHeight==0||this.ContextWidth==0)//if context has not been set up the we don't draw
			return;
		
		
		if(canvas!=null)
		circle.draw(canvas);
	}
public void setUpWindowParam(Context context)	
{
	WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
	Display display = wm.getDefaultDisplay();
	//Point size = new Point();
	//display.getSize(size);
	
	
	 this.ContextWidth = display.getWidth();//deprecated
	 this.ContextHeight = display.getHeight();//depracated
	 this.context=context;
}
	
private Circle circle;

//---------enums--------------
public enum STATE{ACTIVE,INACTIVE,PAUSED}
public enum COLOR{RED,BLUE,BLACK,PINK};
public enum SPEED{SLOW,NORMAL,FAST,SUPER_FAST,NONE};
//----------------
private SPEED activeSpeed;
private STATE activeState;
private COLOR activeColor;
private float minRadius,maxRadius;//the ball radiusis between min and max
private float angle;

}
