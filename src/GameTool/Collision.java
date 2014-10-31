package GameTool;

import android.graphics.Point;

public class Collision 
{
	public static boolean CollisionPointCircle(Point2 A ,Circle C,float epsilon)
	{
		//epsilon represent the amount of error that we accept
		Vecteur u=new Vecteur();
		u.x=A.x-C.getX();
		u.y=A.y-C.getY();
		float r=C.getRadius();
		return (Math.abs(u.NormeSquare()-r*r)<=epsilon*epsilon);
	}
	public static boolean CollisionPointDisk(Point2 A ,Circle C,float epsilon)
	{
		//epsilon represent the amount of error that we accept
		Vecteur u=new Vecteur();
		u.x=A.x-C.getX();
		u.y=A.y-C.getY();
		float r=C.getRadius();
		return u.Norme()<=r*(1+epsilon);
	}
	public static boolean CollisionCircleDroite(Point2 A,Point2 B,Circle C)
	{
		
		 Vecteur u=new Vecteur(0,0);
		   u.x = B.x - A.x;
		   u.y = B.y - A.y;
		   Vecteur AC=new Vecteur(0,0);
		   AC.x = C.getX() - A.x;
		   AC.y = C.getY() - A.y;
		   float numerateur = u.x*AC.y - u.y*AC.x;   // norme du vecteur v
		   if (numerateur <0)
		      numerateur = -numerateur ;   // valeur absolue ; si c'est négatif, on prend l'opposé.
		   float denominateur = (float) Math.sqrt(u.x*u.x + u.y*u.y);  // norme de u
		   float CI = numerateur / denominateur;
		   if (CI<C.getRadius())
		      return true;
		   else
		      return false;
	}
	public static boolean CollisionCircleSegment(Point2 A,Point2 B,Circle C)
	{
		if (CollisionCircleDroite(A,B,C) == false)
		     return false;  // si on ne touche pas la droite, on ne touchera jamais le segment
		   Vecteur AB=new Vecteur();
		   Vecteur BC=new Vecteur();
		   Vecteur AC=new Vecteur();
		   
		   
		   AB.x = B.x - A.x;
		   AB.y = B.y - A.y;
		   AC.x = C.x - A.x;
		   AC.y = C.y - A.y;
		   BC.x = C.x - B.x;
		   BC.y = C.y - B.y;
		   float pscal1 = AB.x*AC.x + AB.y*AC.y;  // produit scalaire
		   float pscal2 = (-AB.x)*BC.x + (-AB.y)*BC.y;  // produit scalaire
		   if (pscal1>=0 && pscal2>=0)
		      return true;   // I entre A et B, ok.
		   // dernière possibilité, A ou B dans le cercle
		   if (CollisionPointDisk(A,C,0))
		     return true;
		   if (CollisionPointDisk(B,C,0))
		     return true;
		   return false;
	}
	public static boolean CollisionCircleCircle(Circle D,Circle C)
	{
		Vecteur CD=new Vecteur();
		CD.x=D.getX()-C.getX();
		CD.y=D.getY()-C.getY();
		return CD.Norme()<=(C.getRadius()+D.getRadius());
	}
	public static boolean CollisionCircleRectangle(Point2 A,float width,float height,Circle circle)
	{
		/*
		 *     A         B
		 *   
		 *     D         C
		 */
		Point2 B=new Point2();
		Point2 C=new Point2();
		Point2 D=new Point2();
		B.x=A.x+width;
		B.y=A.y;
		C.x=A.x+width;
		C.y=A.y+height;
		D.x=A.x;
		D.y=A.y+height;
		
		boolean res=CollisionCircleSegment(A,B,circle)||CollisionCircleSegment(B,C,circle)||CollisionCircleSegment(C,D,circle)||CollisionCircleSegment(D,A,circle);
		
		return res;
	}
	public static boolean CollisionCircleRectangle(Point2 A,float width,float height,Circle circle,float epsilon)
	{
		/*
		 *     A         B
		 *   
		 *     D         C
		 */
		Point2 B=new Point2();
		Point2 C=new Point2();
		Point2 D=new Point2();
		Point2 Ap=new  Point2(A.x*(1+epsilon),A.y*(1+epsilon));
		B.x=Ap.x+width*(1-epsilon);
		B.y=Ap.y;
		C.x=Ap.x+width*(1-epsilon);
		C.y=Ap.y+height*(1-epsilon);
		D.x=Ap.x;
		D.y=Ap.y+height*(1-epsilon);
		
		boolean res=CollisionCircleSegment(A,B,circle)||CollisionCircleSegment(B,C,circle)||CollisionCircleSegment(C,D,circle)||CollisionCircleSegment(D,A,circle);
		
		return res;
	}
}
