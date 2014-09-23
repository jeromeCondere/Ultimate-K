package GameTool;

import android.graphics.Canvas;
import android.graphics.Paint;


public interface Graphics 
{
//getters
 public int getX();
 public int getY();
 

 //setters
 public void setX(int X);
 public void setY(int Y);
 public void setPaint(Paint p);
 
 //add/sub methods
 public void addX(int X);
 public void addY(int Y);
 public void subX(int X);
 public void subY(int Y);
 //
 public void draw(Canvas canvas);
	
}
