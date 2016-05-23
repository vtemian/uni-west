package edu.jogl;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;

import com.jogamp.newt.event.WindowAdapter;
import com.jogamp.opengl.GL;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLBase;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;

public class Main {


	public static void main(String[] args) {
		// setup OpenGL Version 2
    	GLProfile profile = GLProfile.get(GLProfile.GL2);
    	GLCapabilities capabilities = new GLCapabilities(profile);
 
    	// The canvas is the widget that's drawn in the JFrame
    	GLCanvas glcanvas = new GLCanvas(capabilities);
    	glcanvas.addGLEventListener(new Renderer());
    	glcanvas.setSize( 300, 300 );
 
        JFrame frame = new JFrame( "Hello World" );
        frame.getContentPane().add( glcanvas);
 
        frame.setSize( frame.getContentPane().getPreferredSize() );
        frame.setVisible( true );
    }

	
}
