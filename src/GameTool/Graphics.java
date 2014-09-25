package GameTool;

import android.graphics.Canvas;
import android.graphics.Paint;


public interface Graphics 
{
//getters
 public float getX();
 public float getY();
 

 //setters
 public void setX(float X);
 public void setY(float Y);
 public void setPaint(Paint p);
 
 //add/sub methods
 public void addX(float X);
 public void addY(float Y);
 public void subX(float X);
 public void subY(float Y);
 //
 public void draw(Canvas canvas);
	
}
