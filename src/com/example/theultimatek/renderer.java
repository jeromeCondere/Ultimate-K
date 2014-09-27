package com.example.theultimatek;

import gameObject.Ball;
import GameTool.Circle;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.Display;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.WindowManager;
import android.view.MotionEvent;
import android.view.View;
import android.util.Log;



public class renderer extends SurfaceView implements SurfaceHolder.Callback,View.OnTouchListener {

	private SurfaceHolder Holder = null;
	private DrawingThread mThread;//thread gérant le dessin
	private int n;
	private int width;
	private int height;
	private int w ,h;
	private int xTouch,yTouch;
	Bitmap b,b1;
	Ball ball;
	private boolean isTouched=false;
	
	private Circle c1;
	private GameTool.Image i;
	public renderer(Context context) {
		
		super(context);
		Holder = getHolder();
        Holder.addCallback(this);
		mThread=new DrawingThread();
		// TODO Auto-generated constructor stub
		 b=Bitmap.createBitmap(128, 128, Bitmap.Config.ARGB_8888);
		 b1=BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
		  i=new GameTool.Image(40,40,b1);
		n=0;
		
		Paint p=new Paint();
		p.setStyle(Paint.Style.FILL);
		p.setColor(Color.rgb(255, 0, 0));
		p.setTextSize(25);
		
		c1=new Circle(100,100,40,p);
		setWindowParam(context);
		this.setOnTouchListener(this);
		xTouch=0;
		yTouch=0;
		 ball=new Ball(Ball.STATE.ACTIVE,Ball.COLOR.BLACK,Ball.SPEED.NORMAL,50,80,120,120,25);
		 ball.setUpWindowParam(context);
		
	}
	private void setWindowParam( Context context)//obtenir la taille de la view
	{
		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		Display display = wm.getDefaultDisplay();
		//Point size = new Point();
		//display.getSize(size);
		//deprecated
		w=this.getWidth();
		h=this.getHeight();
		 width = display.getWidth();
		 height = display.getHeight();
		
	}
	
	@Override
	protected void onDraw(Canvas canvas)
	{
		//dessin
		
		
		
			Paint p=new Paint();
			p.setStyle(Paint.Style.FILL);
			p.setColor(Color.rgb(0, 0, 0));
			p.setTextSize(25);
			//canvas.drawBitmap(b,0,0,p);
			canvas.drawColor(Color.rgb(255, 255, 255));
			canvas.drawText("xTouch= "+xTouch+"\n yTouch= "+yTouch+" ", 0, 20, p);
			canvas.drawText("width= "+w+" height= "+ h, 0, 70, p);
			
			
			
			c1.draw(canvas);
			//i.rotate(2f);
			i.draw(canvas);
			
			ball.update();
			ball.draw(canvas);
			
			
			/*if(n!=0)
			{
			canvas.drawText("arrrwa", 100, n, p);
			}
			else
			{
				canvas.drawText("anyway", 100, 40, p);
			}
			c.draw(canvas);
		    */
		
		
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		mThread.start();
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		
		// TODO Auto-generated method stub
		
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) 
	{
		// TODO Auto-generated method stub
		 mThread.keepDrawing = false;
	        
	        boolean joined = false;
	        while (!joined) {
	            try {
	                mThread.join();
	                joined = true;
	            } catch (InterruptedException e) {}
	        }
		
	}
	//classe du thread
	private class DrawingThread extends Thread {
		// Utilisé pour arrêter le dessin quand il le faut
	        boolean keepDrawing = true;

	       @SuppressLint("WrongCall") @Override
	        public void run() {
			    
	            while (keepDrawing) {
	                Canvas canvas = null;

	                try {
			    // On récupère le canvas pour dessiner dessus
	                    canvas = Holder.lockCanvas();
			    // On s'assure qu'aucun autre thread n'accède au holder
	                    synchronized (Holder) {
				// Et on dessine
	                        onDraw(canvas);
	                    }
	                } finally {
			    // Notre dessin fini, on relâche le Canvas pour que le dessin s'affiche
	                    if (canvas != null)
	                        Holder.unlockCanvasAndPost(canvas);
	                }

	                //  50 fps
	                try {
	                    Thread.sleep(20);
	                    n++;
	                    if(n%50==0)
	                    {
	                    	n=0;
	                    }
	                } catch (InterruptedException e) {}
	            }
	        }
	    }
	
	@Override
	public boolean onTouch(View v, MotionEvent event)
	{
		
		if(isTouched==false)
			{
			if(event.getAction()==MotionEvent.ACTION_DOWN)
			{
				
				isTouched=true;
				return true;
			}
			}
			else
			{
				if(event.getAction()==MotionEvent.ACTION_UP)
				{
					xTouch=(int)event.getX();
					yTouch=(int)event.getY();
					isTouched=false;
					float X=ball.getX();
					float Y=ball.getY();
					float dSquare=(X-xTouch)*(X-xTouch)+(Y-yTouch)*(Y-yTouch);
					float rSquare=ball.getRadius()*ball.getRadius();
					
					if(dSquare<=rSquare)
					{
						ball.instaKill();
					}
					return true;
				}
			}
		
		
		
		// TODO Auto-generated method stub
		return false;
	}

}
