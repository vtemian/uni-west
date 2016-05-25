package uvt.gui;


import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.glu.GLU;

public class Triangle implements GLEventListener{
    private GLU glu = new GLU();

    private void drawNewTriangle(GL2 gl, double x, double y, double size, double red, double green, double blue) {

        gl.glBegin(GL2.GL_TRIANGLES);

        gl.glColor3d(red / 255.0, green / 255.0, blue / 255.0);

        gl.glVertex3d(x, y, 0.0);
        gl.glVertex3d(x + size, y - size, 0.0);
        gl.glVertex3d(x - size, y - size, 0.0);

        gl.glEnd();
    }

    public void display(GLAutoDrawable gLDrawable)
    {
        int i;
        final GL2 gl = gLDrawable.getGL().getGL2();

        double x = 0.4;
        double y = 0.0;
        double size = 0.4;

        drawNewTriangle(gl, x, y, size, 128, 127, 255);
        drawNewTriangle(gl, x - size, y + size, size, 255, 128, 130);
        drawNewTriangle(gl, x - size * 2, y, size, 129, 254, 128);

    }

    public void displayChanged(GLAutoDrawable gLDrawable, boolean modeChanged, boolean deviceChanged)
    {
    }

    public void init(GLAutoDrawable gLDrawable)
    {
        GL2 gl = gLDrawable.getGL().getGL2();
        gl.glShadeModel(GL2.GL_FLAT);
    }

    public void reshape(GLAutoDrawable gLDrawable, int x, int y, int width, int height)
    {
    }


    public void dispose(GLAutoDrawable arg0)
    {
    }
}
