package gameObject;

import android.content.Context;
import android.graphics.Canvas;

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
	
	//dimension of context in which the object is drawn
	protected int ContextWidth=0;
	protected int ContextHeight=0;
	protected Context context;
}
