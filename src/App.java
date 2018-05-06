import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.math3.optim.MaxIter;
import org.apache.commons.math3.optim.OptimizationData;
import org.apache.commons.math3.optim.PointValuePair;
import org.apache.commons.math3.optim.linear.LinearConstraint;
import org.apache.commons.math3.optim.linear.LinearConstraintSet;
import org.apache.commons.math3.optim.linear.LinearObjectiveFunction;
import org.apache.commons.math3.optim.linear.NonNegativeConstraint;
import org.apache.commons.math3.optim.linear.Relationship;
import org.apache.commons.math3.optim.linear.SimplexSolver;
import org.apache.commons.math3.optim.nonlinear.scalar.GoalType;

public class App {
	 public static void main(String[] args) {
		 /*
		  * A calculator company produces a scientific calculator and a graphing calculator. 
		  * Long-term projections indicate an expected demand of at least 100 scientific and 80 graphing calculators each day. 
		  * Because of limitations on production capacity, no more than 200 scientific and 170 graphing calculators can be made daily. 
		  * To satisfy a shipping contract, a total of at least 200 calculators much be shipped each day. 
		  * If each scientific calculator sold results in a $2 loss, but each graphing calculator produces a $5 profit. 
		  * How many of each type should be made daily to maximize net profits?
		  */
		 LinearObjectiveFunction f = new LinearObjectiveFunction(new double[] { -2, 5}, 0);
		 
		 Collection constraints = new ArrayList();
		 //100 <= x <= 200 
		 constraints.add(new LinearConstraint(new double[] { 1, 0}, Relationship.LEQ, 200));
		 constraints.add(new LinearConstraint(new double[] { 1, 0}, Relationship.GEQ, 100));
		 //80 <=  y <= 170
		 constraints.add(new LinearConstraint(new double[] { 0, 1}, Relationship.LEQ, 170));
		 constraints.add(new LinearConstraint(new double[] { 0, 1}, Relationship.GEQ, 80));
		 //x+y>=200
		 constraints.add(new LinearConstraint(new double[] { 1, 1}, Relationship.GEQ, 200));
		 
		 //create and run solver
		 PointValuePair solution = null;
		 
		 try {
			 solution = new SimplexSolver()
					 .optimize(new MaxIter(10),f,new LinearConstraintSet(constraints),GoalType.MAXIMIZE);
		 }
		 catch (Exception e) {
			 e.printStackTrace();
		 }
		 
		 if (solution != null) {
			 //get solution
			 double max = solution.getValue();
			 System.out.println("Optimization: $" + max);
			 
			 //print decision variables
			 for (int i = 0; i < 2; i++) {
				 System.out.print(solution.getPoint()[i] + "\t");
			 }
		 }
	}
}
