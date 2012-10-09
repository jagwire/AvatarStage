/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.isocial.gameskeleton;

import edu.isocial.gameskeleton.SPI.GameContextSPI;
import org.jdesktop.mtgame.ShadowMapRenderBuffer;
import org.openide.util.lookup.ServiceProvider;

/**
 *
 * @author Ryan
 */
@ServiceProvider(service=GameContextSPI.class)
public class GameContext implements GameContextSPI{
    private ShadowMapRenderBuffer buffer;

    @Override
    public void setShadowBuffer(ShadowMapRenderBuffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public ShadowMapRenderBuffer getShadowBuffer() {
        return buffer;
    }
    
}
