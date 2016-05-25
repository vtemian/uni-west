import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.glu.GLU;

public class Circle implements GLEventListener{
    private GLU glu = new GLU();

    public void display(GLAutoDrawable gLDrawable)
    {
        final GL2 gl = gLDrawable.getGL().getGL2();
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);

        gl.glBegin(GL2.GL_TRIANGLES);
        gl.glEnd();

    }


    public void displayChanged(GLAutoDrawable gLDrawable, boolean modeChanged, boolean deviceChanged)
    {
    }

    public void init(GLAutoDrawable gLDrawable)
    {
        GL2 gl = gLDrawable.getGL().getGL2();
        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        gl.glShadeModel(GL2.GL_FLAT);
    }

    public void reshape(GLAutoDrawable gLDrawable, int x, int y, int width, int height)
    {
    }


    public void dispose(GLAutoDrawable arg0)
    {
    }
}
