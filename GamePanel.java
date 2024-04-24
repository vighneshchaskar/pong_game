import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class GamePanel extends Panel implements Runnable{
    int Width = 1000;
    int Height = (int)(Width*(0.555));
    Dimension screen =new Dimension(Width,Height);

    int Paddle_Height = 100;
    int Paddle_Width =25;

    int Ball_Diameter = 20;

    Image image;
    Graphics graphics;
    Paddle p1,p2;
    Ball ball;
    Score score=new Score(Width,Height);

    Thread GameThread;



    GamePanel()
    {
        setPreferredSize(screen);
        setFocusable(true);

        GameThread = new Thread(this);
        GameThread.start();

        addKeyListener(new AL());

        newPaddle();
        newBall();



    }

    // creating the ball
    private void newBall() {
        Random random = new Random();
        ball = new Ball(Width/2,random.nextInt(Height-Ball_Diameter),Ball_Diameter,Ball_Diameter);
    }


    //creating the Paddle
    private void newPaddle() {

        p1 = new Paddle(0,(Height-Paddle_Height)/2,Paddle_Width,Paddle_Height,1);
        p2 = new Paddle(Width-Paddle_Width, (Height-Paddle_Height)/2,Paddle_Width,Paddle_Height,2);
    }


    @Override
    public void paint(Graphics g) {
        super.paint(g);

        image = createImage(getWidth(),getHeight()); // making image
        graphics = image.getGraphics();
        draw(graphics); // to draw
        g.drawImage(image,0,0,this);


    }

    private void draw(Graphics g) {

        p1.draw(g);   // drawing pannel
        p2.draw(g);
        ball.draw(g);  //drawing ball
        score.draw(g);
    }




    //runing thread to identify how system is working
    @Override
    public void run() {
        // ---------also use for any other game------------
        //to uniform the game , for no lagging in game , to smooothing the game

        long lastTime = System.nanoTime();  //to identify time of system
        double amountofTicks = 60; //60 in time
        double nanosecond = 1000000000/amountofTicks; // 1 tick mai kitne nanosecond
        double delta = 0; // to find time is working or not

        while(true)
        {
            long Now = System.nanoTime(); //current time
            delta += (Now-lastTime)/nanosecond;
            lastTime = Now;
            if(delta>=1)
            {
                repaint();
                move();
                CheckCollision();
                delta--;
            }
        }
    }

    private void CheckCollision() {
        if(ball.y<=0)
        {
            ball.setYDirection(-ball.yVelocity);
        }
        if(ball.y>=Height-Ball_Diameter)
        {
            ball.setYDirection(-ball.yVelocity);
        }
        if(ball.intersects(p1))
        {
            ball.xVelocity=-ball.xVelocity;
            ball.xVelocity++;
            if(ball.yVelocity>0)
            {
                ball.yVelocity++;
            }else{
                ball.yVelocity--;
            }
            ball.setYDirection(ball.yVelocity);
            ball.setXDirction(ball.xVelocity);
        }
        if(ball.intersects(p2))
        {
            ball.xVelocity=-ball.xVelocity;
            ball.xVelocity--;
            if(ball.yVelocity>0)
            {
                ball.yVelocity++;
            }else{
                ball.yVelocity--;
            }
            ball.setYDirection(ball.yVelocity);
            ball.setXDirction(ball.xVelocity);
        }

        if(p1.y<=0)
        {
            p1.y=0;
        }
        if(p1.y>=Height-Paddle_Height)
        {
            p1.y=Height-Paddle_Height;
        }
        if(p2.y<=0)
        {
            p2.y=0;
        }
        if(p2.y>=Height-Paddle_Height)
        {
            p2.y=Height-Paddle_Height;
        }
        if(ball.x>=Width-Ball_Diameter)
        {
            newBall();
            newPaddle();
            score.player1++;
        }
        if(ball.x<=0)
        {
            newBall();
            newPaddle();
            score.player2++;
        }







    }

    private void move() {
        p1.move();
        p2.move();
        ball.move();
    }


    public class AL extends KeyAdapter{

        @Override
        public void keyPressed(KeyEvent e) {

            p1.KeyPressed(e);
            p2.KeyPressed(e);
        }


        @Override
        public void keyReleased(KeyEvent e) {

            p1.KeyReleased(e);
            p2.KeyReleased(e);
        }
    }


}
