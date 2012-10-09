/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.isocial.gameskeleton;

import edu.isocial.gameskeleton.SPI.GameContextSPI;
import edu.isocial.gameskeleton.SPI.TestSPI;
import edu.isocial.gameskeleton.core.DefaultGameFrame;
import edu.isocial.gameskeleton.core.GameSkeletonCORE;
import java.awt.Canvas;
import org.jdesktop.mtgame.RenderBuffer;
import org.jdesktop.mtgame.WorldManager;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.util.Lookup;
import org.openide.util.NbBundle;
import org.openide.windows.TopComponent;

/**
 *
 * @author Ryan
 */


@ConvertAsProperties(dtd = "-//edu.isocial.gameskeleton//sample//EN",
autostore = false)
@TopComponent.Description(preferredID = "ModelGame",
//iconBase="SET/PATH/TO/ICON/HERE", 
persistenceType = TopComponent.PERSISTENCE_ALWAYS)
@TopComponent.Registration(mode = "editor", openAtStartup = true)
@ActionID(category = "Window", id = "edu.isocial.gameskeleton.ModelGame")
@ActionReference(path = "Menu/Window" /*
 * , position = 333
 */)
@TopComponent.OpenActionRegistration(displayName = "#CTL_sampleAction",
preferredID = "ModelGame")
@NbBundle.Messages({
    "CTL_sampleAction=Model Game",
    "CTL_sampleTopComponent=sample Window",
    "HINT_sampleTopComponent=This is a sample window"
})
public class ModelGame extends GameSkeletonCORE {

    public ModelGame() {
        super();
    }
    
    @Override
    protected void createUI(WorldManager worldManager, RenderBuffer renderBuffer, Canvas canvas, int width, int height) {
        DefaultGameFrame frame = new DefaultGameFrame(worldManager, renderBuffer, canvas, width, height);
        
        add(frame.getContentPane());
    }

    @Override
    protected void createScene(WorldManager worldManager) {
//        TestSPI test = Lookup.getDefault().lookup(TestSPI.class);
//        test.print("meh!");
        
        context().setShadowBuffer(this.getShadowMapBuffer());
        
    }
    
    private GameContextSPI context() {
        return Lookup.getDefault().lookup(GameContextSPI.class);
    }
    
    
        // <editor-fold defaultstate="collapsed" desc="Required Top Component Methods">
    @Override
    public void componentOpened() {
        // TODO add custom code on component opening
    }

    @Override
    public void componentClosed() {
        // TODO add custom code on component closing
    }

    void writeProperties(java.util.Properties p) {
        // better to version settings since initial version as advocated at
        // http://wiki.apidesign.org/wiki/PropertyFiles
        p.setProperty("version", "1.0");
        // TODO store your settings
    }

    void readProperties(java.util.Properties p) {
        String version = p.getProperty("version");
        // TODO read your settings according to their version
    }
    //</editor-fold>
    
}
