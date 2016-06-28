/*
     * To change this license header, choose License Headers in Project Properties.
     * To change this template file, choose Tools | Templates
     * and open the template in the editor.
 */
package test;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author jsvhqr
 */
public class Test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        System.out.println("Storlek på espresson ?");
        Scanner scan = new Scanner(System.in);
        int goalWater = scan.nextInt();

        List<Jug> jugs = new ArrayList<>();

        for (String s : args) {

            jugs.add(new Jug(Integer.parseInt(s)));

        }

        List<String> steps = getSteps(goalWater, jugs);

        if (steps != null) {
            for (int i = 0; i < steps.size(); i++) {

                System.out.print("Step " + i + " : ");
                System.out.print(steps.get(i));
                System.out.println();
            }
        } else {
            System.out.println("No solution");
        }

    }
    private static List<String> getSteps(int goalWater, List<Jug> jugs) {

        List<String> steps = null;
        
        int checksLeft = jugs.size() - 1;
        
        Jug largestJug = getLargestJug(jugs);

        List<Jug> otherJugs = getOtherJugs(jugs, largestJug);
        
        while(checksLeft > 0){
            
           List<String> new_steps = getStepsWithThisLargestJug(largestJug, otherJugs, goalWater);
           if(steps == null || ((new_steps != null) && steps.size() > new_steps.size())){
               steps = new_steps;
           }
           otherJugs = getOtherJugs(jugs, largestJug);
           largestJug = getLargestJug(otherJugs);
           otherJugs = getOtherJugs(otherJugs, largestJug);
           checksLeft--;
        }

        return steps;

    }

    private static Jug getLargestJug(List<Jug> jugs) {

        Jug jug = jugs.get(0);
        for (Jug j : jugs) {
            if (jug.getCAPACITY() < j.getCAPACITY()) {
                jug = j;
            }
        }
        return jug;
    }

    static int getGCDFromTwoNumbers(int a, int b) {
        while (a != 0 && b != 0) 
        {
            int c = b;
            b = a % b;
            a = c;
        }
        return a + b; 
    }

    private static boolean gcdIsNotMultipleOf(int gcd, int goalWater) {

        int check = goalWater % gcd;

        if (check == 0) {
            return true;
        } else {
            return true;
        }

    }

    private static List<Jug> getOtherJugs(List<Jug> jugs, Jug j) {
        
        List<Jug> otherJugs = new ArrayList<>();
        otherJugs.addAll(jugs);
        otherJugs.remove(j);
        return otherJugs;

    }

    private static List<String> getStepsForTheseTwoJugs(Jug largestJug, Jug currentJug, int goalWater) {

        List<String> steps = new ArrayList<>();

        int gcd = getGCDFromTwoNumbers(largestJug.getCAPACITY(), currentJug.getCAPACITY());

        if (gcd != 1 && gcdIsNotMultipleOf(gcd, goalWater)) {
            return null;
        } else {
            while (largestJug.getCurrentContent() != goalWater) {
                if (largestJug.getCAPACITY() > largestJug.getCurrentContent() + currentJug.getCAPACITY()) {
                    largestJug.setCurrentContent(largestJug.getCurrentContent() + currentJug.getCAPACITY());
                    steps.add("Poored from jug with capacity (" + currentJug.getCAPACITY() + " cl) into jug with capacity (" + largestJug.getCAPACITY() + " cl). Larger Jug now has " + largestJug.getCurrentContent() + " cls in it");
                } else {
                    int difference = (largestJug.getCurrentContent() + currentJug.getCAPACITY() - largestJug.getCAPACITY());
                    largestJug.setCurrentContent(difference);
                    steps.add("Poored from jug with capacity (" + currentJug.getCAPACITY() + " cl) into jug with capacity (" + largestJug.getCAPACITY() + " cl) then emptied jug with capacity (" + largestJug.getCAPACITY() + " cl) then poored remainder (" + difference + " cl) into larger jug");
                }

            }
            System.out.println("goal reached in: " + steps.size() + " steps");
            return steps;
        }
    }

    private static List<String> getStepsWithThisLargestJug(Jug largestJug, List<Jug> otherJugs, int goalWater) {
        List<String> steps = null;
        while (otherJugs.size() > 0) {
            Jug currentJugg = otherJugs.get(0);
            largestJug.setCurrentContent(0);
            List<String> new_steps = getStepsForTheseTwoJugs(largestJug, currentJugg, goalWater);
             if(steps == null || ((new_steps != null) && steps.size() > new_steps.size())){
                steps = new_steps;
            }
            otherJugs = getOtherJugs(otherJugs, currentJugg);
        }
        
        return steps;
    }
}
