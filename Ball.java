import java.awt.*;
import java.util.Random;

public class Ball extends Rectangle {

    int xVelocity;
    int yVelocity;
    int initialspeed=5;

    Random random;

    Ball(int x,int y,int width,int height)
    {
        super(x, y, width, height);
        random = new Random();

        int RandomXdirection=random.nextInt(2); // 2 to move ball either left or right
        if(RandomXdirection==0)
        {
            RandomXdirection--;
        }
        else{

        }
        setXDirction(RandomXdirection);

        int RandomYDirection=random.nextInt(2);
        if(RandomYDirection==0)
        {
            RandomYDirection--;
        }
        setYDirection(RandomYDirection);

    }

    public void setYDirection(int randomYDirection) {
        yVelocity=randomYDirection;
    }

    public void setXDirction(int randomXdirection) {
        xVelocity=randomXdirection;

    }

    public void move()
    {
        x=x+xVelocity;
        y=y+yVelocity;
    }

    public void draw(Graphics g)
    {
        g.setColor(Color.yellow);
        g.fillOval(x,y,width,height);

        g.setColor(Color.white);
        g.drawLine(1000/2,0,1000/2,555);
    }
}
