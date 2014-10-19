package gameObject;

import gameObject.Ball.COLOR;

import java.util.ArrayList;
import java.util.Timer;

import GameTool.GameSignal;
import GameTool.GameSignal.TYPE;
import GameTool.GameTimer;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

public class BallHandler implements Object_interface
{
 public BallHandler(int n,Ball.COLOR color,Context context)
 {
	 
	 score=0;//score is null at beginning
	 if(n>0)
	 maxSize=n;
	 else 
		maxSize=10;
	 
	 timer=new GameTimer();
	 if(paint==null)
	 {
	 paint=new Paint(Paint.ANTI_ALIAS_FLAG);
	 paint.setStyle(Paint.Style.FILL);
	 }
	 balls=new ArrayList<Ball>();
	  signalList=new ArrayList<GameSignal>();
	 frequency=1000;//100ms
	 activeMode=MODE.NORMAL;
	 WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		Display display = wm.getDefaultDisplay();
		//Point size = new Point();
		//display.getSize(size);
		
		res = context.getResources();
		 this.ContextWidth = display.getWidth();//deprecated
		 this.ContextHeight = display.getHeight();//depracated
		 this.context=context;
		 colorHandler=color;
		 
		 for(int i=0;i<maxSize;i++)
		 {
			 balls.add(generate());
			 
		 }
		 
 }
 public void setMode()
 {
	 if(score>easyLimit)
		 activeMode=MODE.NORMAL;
	 else if(score>normalLimit)
		 activeMode=MODE.HARD;
	 else if(score>hardLimit)
		 activeMode=MODE.EXTREMELY_HARD;
 }
 public void setFrequency(float newFrequency)
 {
	 if(newFrequency>0)
	 frequency=newFrequency;
 }
 public void changeMaxSize(int n)
 {
	 if (n>0)
	    maxSize=n; 
 }
 public Ball generate()
 {
	double  lambda= Math.random();
	final int min =0;
	final int max=100;
	double rNumber=lambda*min+(1-lambda)*max;//random number betwen 0-100
	
	lambda= Math.random();
	double rNumber2=lambda*min+(1-lambda)*max;
	lambda= Math.random();
	double rNumber3=lambda*min+(1-lambda)*max;
	lambda= Math.random();
	double rNumber4=lambda*min+(1-lambda)*max;
	
	float radius[]=generateRadius(rNumber);
	float x,y;
	x=this.treatRandomPosX(rNumber2, radius[0]);
	y=this.treatRandomPosY(rNumber3, radius[0]);
	Ball.SPEED speed=this.generateSpeed(rNumber4);
	lambda= Math.random();
	double angle=lambda*(-180)+(1-lambda)*180;
	Ball b=new Ball(Ball.STATE.ACTIVE,colorHandler,speed,paint,radius[0],radius[1],x,y,(float)angle);
	b.setUpWindowParam(context);
	return b;
 }
 
 private boolean inter(double a,double b,double x)
 {
	 if (a<b)
	 {
		 return x>=a && x<=b;
	 }
	 else
		 return false;
 }
 
 private float treatRandomPosX(double rNumber,float radius)
 {
	//ther're 4 configurations 
	 if(inter(0,40,rNumber))
	 {
		 return 2*radius;
	 }
	 else if(inter(40,50,rNumber))
		 return 2*radius+12;
	 else if(inter(50,60,rNumber))
		 return ContextWidth-2*radius-12;
	 else
		 return ContextWidth-2*radius;
		 
	 
 }
 private float treatRandomPosY(double rNumber,float radius)
 {
	 float alpha=1/11f;
	 float scale=alpha*ContextHeight;
	 if(inter(0,alpha*100,rNumber))
	   return 3*radius;
	 //we cut the screen is 11 parts in height
	 for(int i=1;i<11;i++)
	 {
		 float bounds[]=new float [2];
		 bounds[0]=alpha*i*100;
		 bounds[1]=alpha*(i+1)*100;
		 if(inter(bounds[0],bounds[1],rNumber))
			{
			 return scale*i-radius;
		    }
		 
	 }
	 return 0;
		 
		 
	 
 }
 private float[]  generateRadius(double rNumber)
 {
	 
	 float rad[]=new float[2];
	 switch (activeMode)
	 {
	 //generating  minR and MaxR with scale params which depend on the difficulty
	 //rare case represent short intervals
	    case EASY :
	    	
	    	if(inter(0,10,rNumber))
	    	{
	    		rad[0]=scaleEasyNormal[0]*ContextHeight;
	    		rad[1]=scaleEasyNormal[1]*ContextHeight;
	    	}
	    	else
	    	{
	    		rad[0]=scaleEasyNormal[1]*ContextHeight;
	    		rad[1]=scaleEasyNormal[3]*ContextHeight;
	    	}
	    		
	    	break;
	    case NORMAL :
	    	if(inter(0,20,rNumber))
	    	{
	    		rad[0]=scaleEasyNormal[0]*ContextHeight;
	    		rad[1]=scaleEasyNormal[1]*ContextHeight;
	    	}
	    	else if(inter(20,50,rNumber))
	    	{
	    		rad[0]=scaleEasyNormal[1]*ContextHeight;
	    		rad[1]=scaleEasyNormal[2]*ContextHeight;
	    	}
	    	else if(inter(50,80,rNumber))
	    	{
	    		rad[0]=scaleEasyNormal[1]*ContextHeight;
	    		rad[1]=scaleEasyNormal[3]*ContextHeight;
	    	}
	    	else
	    	{
	    		rad[0]=scaleEasyNormal[1]*ContextHeight;
	    		rad[1]=scaleEasyNormal[2]*ContextHeight;
	    	}
	    	break;
	    case HARD :
	    	if(colorHandler== COLOR.BLUE)
	    	{
	    		if(inter(0,20,rNumber))//20%
		    	{
		    		rad[0]=scaleHardSHard[0]*ContextHeight;
		    		rad[1]=scaleHardSHard[1]*ContextHeight;
		    	}
		    	else if(inter(20,65,rNumber)|| inter(90,100,rNumber))//55%
		    	{
		    		rad[0]=scaleHardSHard[2]*ContextHeight;
		    		rad[1]=scaleHardSHard[3]*ContextHeight;
		    	}
		    	else if(inter(65,80,rNumber))//15%
		    	{
		    		rad[0]=scaleHardSHard[1]*ContextHeight;
		    		rad[1]=scaleHardSHard[3]*ContextHeight;
		    	}
		    	else//10%
		    	{
		    		rad[0]=scaleHardSHard[0]*ContextHeight;
		    		rad[1]=scaleHardSHard[2]*ContextHeight;
		    	}
	    	}
	    	else
	    	{
	    		//repartition between three sizes
	    		if(inter(0,15,rNumber))//15%
		    	{
		    		rad[0]=scaleHardSHard[3]*ContextHeight;
		    		rad[1]=scaleHardSHard[3]*ContextHeight;
		    	}
		    	else if(inter(15,50,rNumber)|| inter(84,100,rNumber))//51%
		    	{
		    		rad[0]=scaleHardSHard[2]*ContextHeight;
		    		rad[1]=scaleHardSHard[2]*ContextHeight;
		    	}
		    	else //34%
		    	{
		    		rad[0]=scaleHardSHard[1]*ContextHeight;
		    		rad[1]=scaleHardSHard[1]*ContextHeight;
		    	}
	    		
	    	}
	    	break;
	    case EXTREMELY_HARD :
	    	if(colorHandler== COLOR.BLUE)
	    	{
	    	if(inter(0,20,rNumber))
	    	{
	    		rad[0]=scaleHardSHard[0]*ContextHeight;
	    		rad[1]=scaleHardSHard[1]*ContextHeight;
	    	}
	    	else if(inter(20,45,rNumber)|| inter(90,100,rNumber))
	    	{
	    		rad[0]=scaleHardSHard[2]*ContextHeight;
	    		rad[1]=scaleHardSHard[3]*ContextHeight;
	    	}
	    	else if(inter(65,75,rNumber))//10%
	    	{
	    		rad[0]=scaleHardSHard[1]*ContextHeight;
	    		rad[1]=scaleHardSHard[3]*ContextHeight;
	    	}
	    	else if(inter(75,80,rNumber))
	    	{
	    		rad[0]=scaleHardSHard[0]*ContextHeight;
	    		rad[1]=scaleHardSHard[4]*ContextHeight;
	    	}
	    	else 
	    	{
	    		rad[0]=scaleHardSHard[0]*ContextHeight;
	    		rad[1]=scaleHardSHard[3]*ContextHeight;
	    	}
	    	}
	    	else
	    	{
	    		//repartition between three sizes
	    		if(inter(0,3,rNumber)|| inter(95,100,rNumber))//8%
		    	{
		    		rad[0]=scaleHardSHard[3]*ContextHeight;
		    		rad[1]=scaleHardSHard[3]*ContextHeight;
		    	}
		    	else if(inter(15,50,rNumber)|| inter(84,100,rNumber))//51%
		    	{
		    		rad[0]=scaleHardSHard[2]*ContextHeight;
		    		rad[1]=scaleHardSHard[2]*ContextHeight;
		    	}
		    	else //41%
		    	{
		    		rad[0]=scaleHardSHard[1]*ContextHeight;
		    		rad[1]=scaleHardSHard[1]*ContextHeight;
		    	}
	    		
	    	}
	    	break;
	 
	 }
	 if(rad[0]>rad[1])
	 {
		 float sw;
		 sw=rad[0];
		 rad[0]=rad[1];
		 rad[1]=sw;
	 }
	 
	 return rad;
 }
 private Ball.SPEED generateSpeed(double rNumber)
 {
	 float epsilon=0.001f;
	 switch (activeMode)
	 {
	    case EASY :
	    	if(inter(0,10,rNumber)||inter(40,50,rNumber) || inter(80,90,rNumber))
	    	{
	    		return Ball.SPEED.SLOW;
	    	}
	    	else
	    		return Ball.SPEED.NORMAL;
	    	
	    	
	    case NORMAL :
	    	if(inter(0,10,rNumber)||inter(40,55,rNumber) )//15%
	    	{
	    		return Ball.SPEED.SLOW;
	    	}
	    	else if(inter(10,40,rNumber)||inter(55,80,rNumber))//65%
	    		return Ball.SPEED.NORMAL;
	    	else //20%
	    		return Ball.SPEED.FAST;
	    	
	    	
	    case HARD :
	    	if(colorHandler==Ball.COLOR.BLUE)
	    	{
	    	if(inter(0,10,rNumber) )//10%
	    	{
	    		return Ball.SPEED.SLOW;
	    	}
	    	else if(inter(15,40,rNumber)||inter(55,90,rNumber))//70%
	    		return Ball.SPEED.NORMAL;
	    	else //20%
	    		return Ball.SPEED.FAST;
	    	}
	    	else
	    	{
	    		if(inter(0,5,rNumber) )//5%
		    	{
		    		return Ball.SPEED.SLOW;
		    	}
		    	else if(inter(5,40,rNumber)||inter(55,60,rNumber))//40%
		    		return Ball.SPEED.NORMAL;
		    	else //55%
		    		return Ball.SPEED.FAST;
	    		
	    	}
	    	
	    case EXTREMELY_HARD :
	    	if(colorHandler==Ball.COLOR.BLUE)
	    	{
	    	if(inter(0,5,rNumber) )//5%
	    	{
	    		return Ball.SPEED.SUPER_FAST;
	    	}
	    	else if(inter(15,40,rNumber)||inter(55,90,rNumber))//70%
	    		return Ball.SPEED.NORMAL;
	    	else //25%
	    		return Ball.SPEED.FAST;
	    	}
	    	else
	    	{
	    		if(inter(0,15,rNumber) )//15%
		    	{
		    		return Ball.SPEED.SUPER_FAST;
		    	}
		    	else if(inter(5,40,rNumber)||inter(55,60,rNumber))//40%
		    		return Ball.SPEED.NORMAL;
		    	else //45%
		    		return Ball.SPEED.FAST;
	    		
	    	}
	    	
	    	
	 
	 }
	 return null;
 }
 public void sendSignal(GameSignal sig,BallHandler handler)
 {
	 if(handler!=null && handler!=this)//we can't send a signal to us
	 {
		 handler.signalList.add(sig);
	 }
 }
 private void treatSignalList()
 {
	 if(this.signalList.isEmpty())
		 return;
	 GameSignal signal=this.signalList.get(0);
	 
	 switch(signal.type)
	 {
	 case PINK_TOUCH :
		 //nearest balls speed are accelerated;
		 for(int i=0;i<balls.size();i++)
		 {
			 float X=balls.get(i).getX();
				float Y=balls.get(i).getY();
				
		    	
		    		float dx=(X-signal.x);
		    		float dy=(Y-signal.y);
		    		float dSquare=(dx*dx)+(dy*dy);
		    		float rSquare=signal.radius*signal.radius;
		    		if(dSquare<=rSquare)
		    		{
		    			balls.get(i).Accelerate();
		    		}
		 }
		 break;
	 case RED_TOUCH :
		 //nearest balls are destroyed
		 for(int i=0;i<balls.size();i++)
		 {
			 float X=balls.get(i).getX();
				float Y=balls.get(i).getY();
				
		    	
		    		float dx=(X-signal.x);
		    		float dy=(Y-signal.y);
		    		float dSquare=(dx*dx)+(dy*dy);
		    		float rSquare=signal.radius*signal.radius;
		    		if(dSquare<=rSquare)
		    		{
		    			balls.get(i).instaKill();
		    		}
		 }
		 break;
	 case GREEN_TOUCH :
		 //les boules environnantes sont ralenties
		 for(int i=0;i<balls.size();i++)
		 {
			 float X=balls.get(i).getX();
				float Y=balls.get(i).getY();
				
		    	
		    		float dx=(X-signal.x);
		    		float dy=(Y-signal.y);
		    		float dSquare=(dx*dx)+(dy*dy);
		    		float rSquare=signal.radius*signal.radius;
		    		if(dSquare<=rSquare)
		    		{
		    			balls.get(i).slow();
		    		}
		 }

		 
		 break;
	 case K_TOUCH :
		 //all balls are destroyed
		 for(int i=0;i<balls.size();i++)
		 {
		 balls.get(i).instaKill();
		 }
		 break;
	 
	 }
	 
	 this.signalList.remove(0);
 }
 public void update()
 {
	 treatSignalList();
	 for(int i=0;i<balls.size();i++)
	 {
		 if(balls.get(i).getState()==Ball.STATE.INSTAKILLED)
			 balls.remove(i);
	 }
	
	 if(balls.size()<maxSize)
	 {
		 
		 
		 switch(timer.run(frequency))
		 {
		 case 0 :
			 break;
		 case 1 :
			 balls.add(generate());
			 timer.restart();
			 break;
		 case 2:
		 break;
		 }
		 
	 }
	 for(int i=0;i<balls.size();i++)
	 {
		 
			 
		 balls.get(i).update();
		 
		 //ne pas oublier de prendre en compte les collision avec le K
		 //et les interactions entre balles
		 //futures ameliorations 
	 }
	 
 }
 @Override
 public void pause() {
 	// TODO Auto-generated method stub
	 for(int i=0;i<balls.size();i++)
	 {
		 balls.get(i).pause();
		
	 }
 }

 @Override
 public void start() {
 	// TODO Auto-generated method stub
	 for(int i=0;i<balls.size();i++)
	 {
		 balls.get(i).start();
		 
	 }
 }

 @Override
 public void restart() {
 	// TODO Auto-generated method stub
	 for(int i=0;i<balls.size();i++)
	 {
		 balls.get(i).restart();
		 
	 }
 }

 @Override
 public void draw(Canvas canvas)
 {
 	// TODO Auto-generated method stub
	 for(int i=0;i<balls.size();i++)
	 {
		 balls.get(i).draw(canvas);
		 
	 }
 }
 
 public boolean onTouch(View v, MotionEvent event)
 {
	 if(v==null||event==null)
		 return false;
	 //getting max radius
	    float maxRadius=0f;
	    boolean result=false;
	    for(int i=0;i<balls.size();i++)
	    {
	    	if(balls.get(i).getRadius()>maxRadius)
	    		maxRadius=balls.get(i).getRadius();
	    }
	    
	    float xTouch=event.getX();
	    float yTouch=event.getY();
	    for(int i=0;i<balls.size();i++)
	    {
	    	float X=balls.get(i).getX();
			float Y=balls.get(i).getY();
			
	    	if(inter(xTouch-maxRadius,xTouch+maxRadius,X)&& inter(yTouch-maxRadius,yTouch+maxRadius,Y))
	    	{
	    		float dx=(X-xTouch);
	    		float dy=(Y-yTouch);
	    		float dSquare=(dx*dx)+(dy*dy);
	    		float rSquare=balls.get(i).getRadius()*balls.get(i).getRadius();
	    		if(dSquare<=1.1*rSquare)
	    		{
	    			balls.get(i).instaKill();
	    			result=true;
	    		}
	    	}
	    }
		
		
		return result;//result is true if a ball has been touched
 }

 private ArrayList<Ball> balls;
 private int maxSize;
 private  Paint paint;
 private float frequency;//time between two apparition of balls en ms
 private GameTimer timer;
 public enum MODE{EASY,NORMAL,HARD,VERY_HARD,EXTREMELY_HARD};
 private MODE activeMode;
 private Ball.COLOR colorHandler;
 private  int score;
 private int Kx,Ky;
 private int ContextWidth;
 private int ContextHeight;
 private Context context;
 private Resources res;
 private ArrayList<GameSignal> signalList;
 //-----------------
 private final int easyLimit=400;
 private final int normalLimit=1000;
 private final int hardLimit=1700;
 private final float scaleEasyNormal[]={1/27f,1/22f,1/18f,1/15.5f};
 private final float scaleHardSHard[]={1/40f,1/30f,1/26f,1/22f,1/20.5f};
}
