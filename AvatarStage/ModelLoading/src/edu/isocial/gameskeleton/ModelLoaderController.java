/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.isocial.gameskeleton;

//import com.jme.entity.Entity;
import com.jme.math.FastMath;
import com.jme.math.Quaternion;
import com.jme.math.Vector3f;
import com.jme.scene.Controller;
import com.jme.scene.Node;
import com.jme.scene.Spatial;
import com.jme.scene.state.CullState;
import com.jme.scene.state.RenderState;
import com.jme.scene.state.RenderState.StateType;
import com.jmex.model.collada.ThreadSafeColladaImporter;
import edu.isocial.gameskeleton.SPI.GameContextSPI;
import edu.isocial.gameskeleton.core.GameSkeletonCORE;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import org.jdesktop.mtgame.Entity;
import org.jdesktop.mtgame.RenderComponent;
import org.jdesktop.mtgame.RenderManager;
import org.jdesktop.mtgame.WorldManager;
import org.openide.util.Exceptions;
import org.openide.util.Lookup;

/**
 *
 * @author Ryan
 */
public class ModelLoaderController {

    private final JComponent view;

    public ModelLoaderController(JComponent view) {
        this.view = view;
    }

    public void handleLoadModelButtonActionPerformed(ActionEvent evt) {
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(view);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            openFile(fileChooser.getSelectedFile());

        } else {
            //fail gracefully
        }

    }

    private void openFile(File selectedFile) {
        FileInputStream stream = null;
        try {
            ThreadSafeColladaImporter loader = new ThreadSafeColladaImporter(selectedFile.getName());
            stream = new FileInputStream(selectedFile);
            loader.load(stream);
            Node model = loader.getModel();
            //create entity

            
            CullState culls = (CullState) WorldManager.getDefaultWorldManager().getRenderManager().createRendererState(StateType.Cull);
            culls.setCullFace(CullState.Face.Back);
            model.setRenderState(culls);
            
            float unitMeter = loader.getUnitMeter();
            model.setLocalScale(unitMeter * 6.0f);
            
            
           
            processModel(model);
            System.out.println("!!!!!!!!!!!!!!!");
            rotateNode90Degrees(model);
            
            Entity entity = new Entity(selectedFile.getName());
            RenderComponent rc = renderManager().createRenderComponent(model);
            entity.addComponent(RenderComponent.class, rc);
            WorldManager.getDefaultWorldManager().addEntity(entity);

            
            GameContextSPI context = Lookup.getDefault().lookup(GameContextSPI.class);
            context.getShadowBuffer().addRenderScene(rc);
            
            loader.cleanUp();
        } catch (FileNotFoundException ex) {
            Exceptions.printStackTrace(ex);
        } finally {
            try {
                stream.close();
            } catch (IOException ex) {
                Exceptions.printStackTrace(ex);
            }
        }


    }

    private RenderManager renderManager() {
        return WorldManager.getDefaultWorldManager().getRenderManager();
    }

    private void processModel(Spatial model) {
        documentControllers(model);

        if (model instanceof Node) {
            for (Spatial child : ((Node) model).getChildren()) {
                processModel(child);
            }
        }
    }
    
    public void rotateNode90Degrees(Node base) {
        Quaternion current = base.getLocalRotation();
        Quaternion currentRotated = new Quaternion();
        currentRotated = currentRotated.fromAngleAxis(-90 * FastMath.DEG_TO_RAD, new Vector3f(1, 0, 0));
    
        
        base.setLocalRotation(current.mult(currentRotated));
        
    }

    private void documentControllers(Spatial model) {
        System.out.println(model.getName() + ": CONTROLLERS: " + model.getControllerCount());

        for (Controller controller : model.getControllers()) {
            System.out.println(controller.getClass().getName());
        }
    }
}
