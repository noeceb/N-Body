public class Body{

	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;
	private static final double g = 6.67e-11;

	public Body(double xP, double yP, double xV, double yV, double m, String img){
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}

	public Body(Body b){
		xxPos = b.xxPos;
		yyPos = b.yyPos;
		xxVel = b.xxVel;
		yyVel = b.yyVel;
		mass = b.mass;
		imgFileName = b.imgFileName;
	}

	public double calcDistance(Body b){
		double distance_x = this.xxPos - b.xxPos; 
		double distance_y = this.yyPos - b.yyPos;

		return Math.sqrt((distance_x * distance_x) + (distance_y * distance_y));
	}			

	public double calcForceExertedBy(Body b){
		return (g * this.mass * b.mass) / (this.calcDistance(b) * this.calcDistance(b));
	}							

	public double calcForceExertedByX(Body b){
	    return (this.calcForceExertedBy(b) * (b.xxPos - this.xxPos)) / this.calcDistance(b);
	}

	public double calcForceExertedByY(Body b){
	    return (this.calcForceExertedBy(b) * (b.yyPos - this.yyPos) )/ this.calcDistance(b);
	}
 
    public double calcNetForceExertedByX(Body[] b){
    	double force_net_x = 0;
    	for (Body item : b){
			if (!this.equals(item)){
   				force_net_x += this.calcForceExertedByX(item);
   			}
		}
		return force_net_x;
    }

    public double calcNetForceExertedByY(Body[] b){
    	double force_net_y = 0;
    	for (Body item : b){
			if (!this.equals(item)){
   				force_net_y += this.calcForceExertedByY(item);
   			}
		}
		return force_net_y;
    }

    public void update(double dt, double fX, double fY){
    	double acceleration_x = fX / mass;
    	double acceleration_y = fY / mass;
    	xxVel = xxVel + dt * acceleration_x;
    	yyVel = yyVel + dt * acceleration_y;
    	xxPos = xxPos + dt * xxVel;
    	yyPos = yyPos + dt * yyVel;
    }

    public void draw(){
    	StdDraw.picture(xxPos, yyPos, "images/" + imgFileName);
    }


}