import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Main {

    public static void main(String[] args) {
        // setup OpenGL Version 2
        GLProfile profile = GLProfile.get(GLProfile.GL2);
        GLCapabilities capabilities = new GLCapabilities(profile);

        // The canvas is the widget that's drawn in the JFrame
        GLCanvas glcanvas = new GLCanvas(capabilities);
        glcanvas.addGLEventListener(new Circle());
        glcanvas.setSize( 300, 300 );

        JFrame frame = new JFrame( "Hello World" );
        frame.getContentPane().add( glcanvas);

        // shutdown the program on windows close event
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent ev) {
                System.exit(0);
            }
        });

        frame.setSize( frame.getContentPane().getPreferredSize() );
        frame.setVisible( true );
    }
}
