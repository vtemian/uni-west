package uvt.gui;


import com.jogamp.opengl.GL;
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

    private void drawNewCircle(GL2 gl, double x, double y, double radius, double red, double green, double blue, boolean exterior) {
        int i;
        int triangleAmount = 200;

        double twicePi = 2.0f * Math.PI;

        if (!exterior) {
            drawNewCircle(gl, x, y, radius + 0.005, 41, 0, 2, true);
        }

        gl.glBegin(GL2.GL_TRIANGLE_FAN);

        gl.glColor3d(red / 255.0, green / 255.0, blue / 255.0);

        for(i = 0; i <= triangleAmount;i++) {
            gl.glVertex3d(
                    x + (radius * Math.cos(i *  twicePi / triangleAmount)),
                    y + (radius * Math.sin(i * twicePi / triangleAmount)), 0
            );
        }

        gl.glEnd();

    }

    private void drawNewRectangle(GL2 gl, double x, double y, double width, double height, double red, double green, double blue, boolean exterior) {
        gl.glBegin(GL2.GL_POLYGON);

        gl.glColor3d(red / 255.0, green / 255.0, blue / 255.0);

        gl.glVertex3d(x, y, 0.0);
        gl.glVertex3d(x + width, y , 0.0);
        gl.glVertex3d(x + width, y - height, 0.0);
        gl.glVertex3d(x, y - height, 0.0);
        gl.glVertex3d(x, y, 0.0);

        gl.glEnd();

        if (!exterior) {
            //double offset = 0.0025;
            double offset = 0.005;
            drawNewRectangle(gl, x - offset, y + offset, width + 2 * offset, offset, 41, 0, 2, true);
            drawNewRectangle(gl, x - offset, y + offset, offset,  height + offset, 41, 0, 2, true);
            drawNewRectangle(gl, x - offset, y - height, width + 2 * offset, offset, 41, 0, 2, true);
            drawNewRectangle(gl, x + width, y + offset, offset, height + offset, 41, 0, 2, true);
        }
    }

    public void display(GLAutoDrawable gLDrawable)
    {
        final GL2 gl = gLDrawable.getGL().getGL2();
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);

        double x_units = 25;
        double y_units = 16;

        // ground
        double x = -1 + 5 / x_units;
        double y = -12 / y_units;
        double width = (2 * x_units - 9) / x_units;
        double height = 1 / y_units;
        drawNewRectangle(gl, x, y, width, height, 41, 0, 2, false);

        // table: left foot
        x = -1 + 10 / x_units;
        y = -1 / y_units;
        width = 2 / x_units;
        height = 11 / y_units;
        drawNewRectangle(gl, x, y, width, height, 28, 28, 28, false);

        // table: right foot
        x = 4 / x_units;
        y = -1 / y_units;
        width = 2 / x_units;
        height = 11 / y_units;
        drawNewRectangle(gl, x, y, width, height, 28, 28, 28, false);

        // table: top
        x = -1 + 10 / x_units;
        y = 1 / y_units;
        width = 21 / x_units;
        height = 2 / y_units;
        drawNewRectangle(gl, x, y, width, height, 28, 28, 28, false);

        // chair: right
        x = 1 - 9 / x_units;
        y = 1 - 5 / y_units;
        width = 2 / x_units;
        height = (2 * y_units - 9) / y_units;
        drawNewRectangle(gl, x, y, width, height, 1, 255, 254, false);

        // chair: left
        x = 9 / x_units;
        y = -3 / y_units;
        width = 2 / x_units;
        height = 9 / y_units;
        drawNewRectangle(gl, x, y, width, height, 1, 255, 254, false);

        // chair: top
        x = 9 / x_units;
        y = -1 / y_units;
        width = 7 / x_units;
        height = 2 / y_units;
        drawNewRectangle(gl, x, y, width, height, 1, 255, 254, false);

        // yellow circle
        x = -7 / x_units;
        y = 4 / y_units;
        drawNewCircle(gl, x, y, 2 / x_units, 255, 255, 0, false);

        // yellow circle
        x = -4 / x_units;
        y = 4 / y_units;
        drawNewCircle(gl, x, y, 2 / x_units, 255, 0, 0, false);

        // green box
        x = -10 / x_units;
        y = 4 / y_units;
        width = 9 / x_units;
        height = 3 / y_units;
        drawNewRectangle(gl, x, y, width, height, 1, 255, 0, false);

    }

    public void displayChanged(GLAutoDrawable gLDrawable, boolean modeChanged, boolean deviceChanged)
    {
    }

    public void init(GLAutoDrawable gLDrawable)
    {
        GL2 gl = gLDrawable.getGL().getGL2();
        gl.glShadeModel(GL2.GL_FLAT);
        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
    }

    public void reshape(GLAutoDrawable gLDrawable, int x, int y, int width, int height)
    {
    }


    public void dispose(GLAutoDrawable arg0)
    {
    }
}
