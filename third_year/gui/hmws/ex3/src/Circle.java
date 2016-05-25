import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.glu.GLU;

public class Circle implements GLEventListener{
    private GLU glu = new GLU();

    public void display(GLAutoDrawable gLDrawable)
    {
        int i;
        final GL2 gl = gLDrawable.getGL().getGL2();
        int lineAmount = 200;

        double x = 0.01;
        double y = 0.01;
        double radius = 0.5f;
        double twicePi = 2.0f * Math.PI;

        gl.glBegin(GL2.GL_LINE_LOOP);

        for(i = 0; i <= lineAmount;i++) {
            gl.glVertex2d(
                    x + (radius * Math.cos(i *  twicePi / lineAmount)),
                    y + (radius * Math.sin(i * twicePi / lineAmount))
            );
        }

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
