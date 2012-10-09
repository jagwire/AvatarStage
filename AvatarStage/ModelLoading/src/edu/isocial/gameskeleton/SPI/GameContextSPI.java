/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.isocial.gameskeleton.SPI;

import org.jdesktop.mtgame.ShadowMapRenderBuffer;

/**
 *
 * @author Ryan
 */
public interface GameContextSPI {
    public void setShadowBuffer(ShadowMapRenderBuffer buffer);
    
    public ShadowMapRenderBuffer getShadowBuffer();
}
