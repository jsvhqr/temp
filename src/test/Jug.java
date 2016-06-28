/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

/**
 *
 * @author jsvhqr
 */
public class Jug {
    
    
    private final int CAPACITY;
    
    private int currentContent;

    public int getCAPACITY() {
        return CAPACITY;
    }
    
    public Jug(int capacity){
        this.CAPACITY = capacity;
    }

    public int getCurrentContent() {
        return currentContent;
    }

    public void setCurrentContent(int currentContent) {
        this.currentContent = currentContent;
    }
    
    
}
