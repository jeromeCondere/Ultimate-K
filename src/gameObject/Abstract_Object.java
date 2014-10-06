package gameObject;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.view.Display;
import android.view.WindowManager;

public abstract class Abstract_Object implements Object_interface {

	@Override
	public abstract void pause(); 

	@Override
	public abstract void start();

	@Override
	public abstract void restart() ;

	@Override
	public abstract void update() ;

	@Override
	public abstract void draw(Canvas canvas) ;
	
	public void setUpWindowParam(Context context)	
	{
		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		Display display = wm.getDefaultDisplay();
		//Point size = new Point();
		//display.getSize(size);
		
		res = context.getResources();
		 this.ContextWidth = display.getWidth();//deprecated
		 this.ContextHeight = display.getHeight();//depracated
		 this.context=context;
		 
	}
	
	//dimension of context in which the object is drawn
	protected int ContextWidth=0;
	protected int ContextHeight=0;
	protected Context context;
	protected Resources res;
}
