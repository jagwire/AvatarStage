/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.isocial.gameskeleton;

import edu.isocial.gameskeleton.SPI.TestSPI;
import javax.swing.JOptionPane;
import org.openide.util.lookup.ServiceProvider;

/**
 *
 * @author Ryan
 */
@ServiceProvider(service=TestSPI.class)
public class TestServiceProvider implements TestSPI {

    @Override
    public void print(String s) {
        for(int i = 0; i < 100; i++) {
            System.out.println(s+"\n");
        }
    }
    
}
