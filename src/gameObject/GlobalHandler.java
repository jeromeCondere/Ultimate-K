package gameObject;

import gameObject.Ball.COLOR;
import GameTool.Circle;
import GameTool.Collision;
import GameTool.GameSignal;
import GameTool.GameSignal.TYPE;
import GameTool.Point2;
import GameTool.Vecteur;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Point;
import android.view.MotionEvent;
import android.view.View;

public class GlobalHandler extends Abstract_Object {

	public GlobalHandler(Context context)
	{
		this.context=context;
		HandlerBlack=new BallHandler(initBall[0], COLOR.BLACK, context);
		HandlerBlue=new BallHandler(initBall[1], COLOR.BLUE, context);
		HandlerGreen=new BallHandler(initBall[2], COLOR.GREEN, context);
		HandlerPink=new BallHandler(initBall[3], COLOR.PINK, context);
		HandlerRed=new BallHandler(initBall[4], COLOR.RED, context);
		
		//set frequencies
		HandlerBlack.setFrequency(initfrequency[0]);
		HandlerBlue.setFrequency(initfrequency[1]);
		HandlerGreen.setFrequency(initfrequency[2]);
		HandlerPink.setFrequency(initfrequency[3]);
		HandlerRed.setFrequency(initfrequency[4]);
		activeScoreHit=scoreHit[0];
		this.setUpWindowParam(context);
		
		K=new KHandler(ContextWidth, ContextHeight);
		
		
	}
	
	
	@Override
	public void pause() {
		// TODO Auto-generated method stub

		K.pause();
		HandlerBlack.pause();
		HandlerBlue.pause();
		HandlerGreen.pause();
		HandlerPink.pause();
		HandlerRed.pause();
	}

	@Override
	public void start() {
		// TODO Auto-generated method stub
 
		K.start();
		HandlerBlack.start();
		HandlerBlue.start();
		HandlerGreen.start();
		HandlerPink.start();
		HandlerRed.start();
		
	}

	@Override
	public void restart() {
		// TODO Auto-generated method stub
         K.restart();
		HandlerBlack.restart();
		HandlerBlue.restart();
		HandlerGreen.restart();
		HandlerPink.restart();
		HandlerRed.restart();
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
	
		Point2 A=K.getPoint();
		
		K.update();
		HandlerBlack.update();
		HandlerBlue.update();
		HandlerGreen.update();
		HandlerPink.update();
		HandlerRed.update();
		for(int i=0;i<HandlerBlack.getSize();i++)
		{
			Ball b=HandlerBlack.getBall(i, this);
			Circle C=new Circle(b.getX(),b.getY(),b.getRadius());
			if(Collision.CollisionCircleRectangle(A, K.getWidth(), K.getHeight(), C))
			{
				K.receiveSignal(new GameSignal(TYPE.K_HIT));//we send signal to the KHandler
				b.instaKill();
				return;
			}
		}
		
	}
    
	@Override
	public void draw(Canvas canvas) {
		// TODO Auto-generated method stub
       if(canvas!=null)
       {
    	
    	HandlerBlack.draw(canvas);
   		HandlerBlue.draw(canvas);
   		HandlerGreen.draw(canvas);
   		HandlerPink.draw(canvas);
   		HandlerRed.draw(canvas);
   		K.draw(canvas);
       }
	}
	public boolean onTouch(View v, MotionEvent event)
	 {
		if(HandlerBlack.onTouch(v, event))
		{
			score+=activeScoreHit;//update score
		}
		if(HandlerBlue.onTouch(v, event))
		{
			score+=activeScoreHit;//update score
			
		}
		if(HandlerGreen.onTouch(v, event))
		{
			score+=activeScoreHit;//update score
			//sending appropriate signal
			HandlerGreen.sendSignal(new GameSignal(event.getX(),event.getY() ,150f, TYPE.GREEN_TOUCH),HandlerBlack);
			HandlerGreen.sendSignal(new GameSignal(event.getX(),event.getY() ,150f, TYPE.GREEN_TOUCH),HandlerBlue);
			HandlerGreen.sendSignal(new GameSignal(event.getX(),event.getY() ,150f, TYPE.GREEN_TOUCH),HandlerPink);
			HandlerGreen.sendSignal(new GameSignal(event.getX(),event.getY() ,150f, TYPE.GREEN_TOUCH),HandlerRed);
			
		}
		if(HandlerPink.onTouch(v, event))
		{
			score+=activeScoreHit;//update score
			//sending appropriate signal
			HandlerPink.sendSignal(new GameSignal(event.getX(),event.getY() ,150f, TYPE.PINK_TOUCH),HandlerBlack);
			HandlerPink.sendSignal(new GameSignal(event.getX(),event.getY() ,150f, TYPE.PINK_TOUCH),HandlerBlue);
			
		}
		if(HandlerRed.onTouch(v, event))
		{
			score+=activeScoreHit;//update score
			//sending appropriate signal
			HandlerRed.sendSignal(new GameSignal(event.getX(),event.getY() ,150f, TYPE.RED_TOUCH),HandlerBlack);
			HandlerRed.sendSignal(new GameSignal(event.getX(),event.getY() ,150f, TYPE.RED_TOUCH),HandlerBlue);
			HandlerRed.sendSignal(new GameSignal(event.getX(),event.getY() ,150f, TYPE.RED_TOUCH),HandlerPink);
			HandlerRed.sendSignal(new GameSignal(event.getX(),event.getY() ,150f, TYPE.RED_TOUCH),HandlerGreen);
			
		}
		
		return true;
	 }

	
	private int score=0;
	private final int initBall[]={5,2,1,1,1};//black,blue,green,pink,red
	private final float initfrequency[]={1000f,1500f,10000f,10000f,13000f};
	private final int scoreHit[]={20,40,50,60};//easy,normal,hard,very/extremely hard
	private int activeScoreHit;
    private BallHandler HandlerBlack;
    private BallHandler HandlerBlue;
    private BallHandler HandlerGreen;
    private BallHandler HandlerPink;
    private BallHandler HandlerRed;
    private KHandler K;
    
}
