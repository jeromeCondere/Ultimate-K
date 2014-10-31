package gameObject;

import gameObject.Ball.STATE;

import java.util.ArrayList;

import GameTool.GameSignal;
import GameTool.GameSignal.TYPE;
import GameTool.Point2;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

public class KHandler extends Abstract_Object {

	public KHandler(int width,int height)
	{
		x=width/2f;
		y=height/2f-20f;
		imgW=30;
		imgH=30;
		
		signalList=new ArrayList<GameSignal>();
		activeState=STATE.ACTIVE;
		paint=new Paint( Paint.ANTI_ALIAS_FLAG );
		paint.setStyle(Paint.Style.STROKE);
		paint.setStrokeWidth(1.25f);
		paint.setColor(Color.rgb(150, 24, 98));
		
	}
	
	public float getX()
	{
		
		return x;
	}
	public float getY()
	{
		return y;
	}
	public float getWidth()
	{
		return imgW;
	}
	public float getHeight()
	{
		return imgH;
	}
	
	public Point2 getPoint()
	{
		return new Point2(x,y);
	}
	public float getLife()
	{
		return life;
	}
	
	public void addLife()
	{
		if(life<3)//we can't have more than 3 lives
			life++;
	}
	
	@Override
	public void pause() {
		// TODO Auto-generated method stub
		activeState=STATE.PAUSED;
	}

	@Override
	public void start() {
		// TODO Auto-generated method stub

	}

	@Override
	public void restart() {
		// TODO Auto-generated method stub
		activeState=STATE.ACTIVE;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		treatSignal();

	}

	@Override
	public void draw(Canvas canvas) {
		// TODO Auto-generated method stub
         canvas.drawRect(x,y,x+imgW,y+imgH, paint);
	}
	private void treatSignal()
	{
		if(signalList.isEmpty())
		return;
		if(activeState==STATE.ACTIVE)
		{
			if(signalList.get(0).type==TYPE.K_HIT)
				life--;
				//don't forget putting time invincible after being hit
			   
			signalList.remove(0);
		}
		
	}
	public void receiveSignal(GameSignal sig)
	{
		if(signalList!=null && sig!=null)
			signalList.add(sig);
	}
	private ArrayList<GameSignal> signalList;
	private int life=3;
	private float x,y;
	private int imgW,imgH;
	private Ball.STATE activeState;
	private Paint paint;
	private Rect R;

}
