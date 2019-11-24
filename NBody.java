public class NBody{
	public static double readRadius(String name){
		In in = new In(name);
		in.readDouble();
		return in.readDouble();
	}

    public static Body[] readBodies(String name){
		In in = new In(name);
		int length = in.readInt(); //size of the array
		Body[] array = new Body[length]; //new array which will have parameters from the txt file
        in.readDouble();
        int elem = 0;
        while(elem < length){
        	array[elem] = new Body(in.readDouble(),in.readDouble(),in.readDouble(),in.readDouble(),in.readDouble(),in.readString());    
            elem ++;
        }
        return array;
	}

    public static void main(String[] args){
    	double t = Double.parseDouble(args[0]);
    	double dt = Double.parseDouble(args[1]);
    	String filename = args[2];
    	double radius = readRadius(filename);
    	Body[] bodies = readBodies(filename);

    	StdDraw.setScale(-radius, radius);
    	

    

        StdDraw.enableDoubleBuffering();


        for (int time = 0; time < t; time += dt){
            double xForce[] = new double[bodies.length];
            double yForce[] = new double[bodies.length];

            for(int i = 0; i < bodies.length; i++){
                xForce[i] = bodies[i].calcNetForceExertedByX(bodies);
                yForce[i] = bodies[i].calcNetForceExertedByY(bodies);
            }
            for(int j = 0; j < bodies.length; j++){
                bodies[j].update(dt, xForce[j], yForce[j]);
            }
            
            StdDraw.picture(0,0, "images/starfield.jpg");
            for(int k = 0; k < bodies.length; k++){
            bodies[k].draw();
            }

            StdDraw.show();
            StdDraw.pause(10);

            }
        StdOut.printf("%d\n", bodies.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < bodies.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    bodies[i].xxPos, bodies[i].yyPos, bodies[i].xxVel,
                    bodies[i].yyVel, bodies[i].mass, bodies[i].imgFileName);   


        }     
    }

}