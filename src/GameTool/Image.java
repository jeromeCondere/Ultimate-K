package GameTool;

import java.util.ArrayList;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;

public class Image extends AbstractGraphics {

	public Image(int X,int Y)
	{
		super(X,Y);
		//we init paint
		paint=new Paint();
		paint.setStyle(Paint.Style.FILL);
		paint.setColor(Color.WHITE);
		
		width=0;
		height=0;
	}
	public Image(int X,int Y,Paint p)
	{
		
		super(X,Y,p);
		width=0;
		height=0;
	}
	public Image(int X,int Y,Paint p,Bitmap b)
	{
		
		super(X,Y,p);
		bmpList=new ArrayList<Bitmap>();
		bmpList.add(b);
		width=b.getWidth();
		height=b.getHeight();
	}
	
	public Image(int X,int Y,Paint p,ArrayList<Bitmap> b)
	{
		super(X,Y,p);
		if(b!=null)
			bmpList=b;
		if(!b.isEmpty())
		{
			//by default active image is the first
			width=b.get(0).getWidth();
			height=b.get(0).getHeight();
		}
	}
	
	public Image(int X,int Y,Bitmap b)
	{
		super(X,Y);
		bmpList=new ArrayList<Bitmap>();
		bmpList.add(b);
		paint=new Paint();
		paint.setStyle(Paint.Style.FILL);
		paint.setColor(Color.WHITE);
		
	}
	public Image(int X,int Y,ArrayList<Bitmap> b)
	{
		super(X,Y);
		if(b!=null)
			bmpList=b;
		paint=new Paint();
		paint.setStyle(Paint.Style.FILL);
		paint.setColor(Color.WHITE);
		
	}
	
	
	public void setBitmapList(ArrayList<Bitmap> list)
	{
		if(bmpList==null)
			bmpList=list;
	}
	public void addBitmap(Bitmap b)
	{
		//if the list was not initialized we create a new one
		if(bmpList==null)
			bmpList=new ArrayList<Bitmap>();
		
		bmpList.add(b);
		
	}
	
	
	@Override
	public void draw(Canvas canvas) 
	{
		// TODO Auto-generated method stub
		if(bmpList==null)
			return;
			
			
        if(bmpList.isEmpty()==false)
        {
        	if(bmpList.get(0)!=null)
        	canvas.drawBitmap(bmpList.get(0), x, y, paint);
        }
        	
		
	}
	 public void draw(Canvas canvas,int i)
     {
		 if(bmpList==null)
				return;
		 
		 if(i<0 || i>=bmpList.size())
				return ;
		 
		 
				
				
	        if(bmpList.isEmpty()==false)
	        {
	        	if(bmpList.get(i)!=null)
	        	canvas.drawBitmap(bmpList.get(i), x, y, paint);
	        }
     }
	
	
	//return dimension of first element
	public int getWidth()
	{
		if(bmpList==null)//error
			return -1;
		
		if(bmpList.get(0)!=null)
			return bmpList.get(0).getWidth();
		
		return -1;
	}
	public int getHeight()
	{
		if(bmpList.get(0)!=null)
			return bmpList.get(0).getHeight();
		
		return -1;
	}
	
	public int getWidth(int i)
	{
		if(bmpList==null)
			return -1;
		
		if(i<0 || i>=bmpList.size())
			return -1;
			
			if(bmpList.get(i)!=null)
				return bmpList.get(0).getWidth();
		
		return -1;
	}
	public int getHeight(int i)
	{
		if(bmpList==null)
			return -1;
		
		if(i<0 || i>=bmpList.size())
			return -1;
			
			if(bmpList.get(i)!=null)
				return bmpList.get(0).getHeight();
		
		return -1;
	}
	
	public int size()
	{
		if(bmpList==null)
			return -1;
		return bmpList.size();
	}

	public void rotate(float angle)
	{
		Bitmap b=bmpList.get(0);
		Matrix m=new Matrix();
		m.postRotate(angle);
		Bitmap rotatedBitmap = Bitmap.createBitmap(b , 0, 0, b .getWidth(), b .getHeight(), m, true);
		bmpList.set(0, rotatedBitmap);
	}
	
	
	private ArrayList<Bitmap> bmpList;//we take arrayList for animation
	//dimension of the active image
	private int width;
	private int height;

}
