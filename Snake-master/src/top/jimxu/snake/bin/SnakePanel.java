package top.jimxu.snake.bin;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class SnakePanel extends JPanel implements KeyListener,ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//加载所有图片
	ImageIcon up = new ImageIcon("up.png");
	ImageIcon down = new ImageIcon("down.png");
	ImageIcon left = new ImageIcon("left.png");
	ImageIcon right = new ImageIcon("right.png");
	ImageIcon title = new ImageIcon("title.jpg");
	ImageIcon body = new ImageIcon("body.png");
	ImageIcon food = new ImageIcon("food.png");
	
	//蛇的数据结构设计
	int[] snakex = new int[750];//750个位置
	int[] snakey = new int[750];
	int len = 3;
	String direction = "R";//R右L左U上D下
	
	//食物生成
	Random r = new Random();
	int foodx = 150;//r.nextInt(34)*25+25; // 34个格子，一个格子25排像素，还有25像素空白
	int foody = 150;//r.nextInt(24)*25+75; // 24个格子，一个格子25排像素，还有75像素空白
	//格子的xy坐标
	
	//游戏是否开始
	boolean isStarted = false;
	
	//游戏是否失败
	boolean isFaild = false;

	
	//	初始化蛇
	public void initSnake(){
		isStarted = false;
		isFaild = false;
		len = 3;
		direction = "R";
		snakex[0] = 100;
		snakey[0] = 100;
		snakex[1] = 75;
		snakey[1] = 100;
		snakex[2] = 50;
		snakey[2] = 100;
	}
	
	public SnakePanel() {//构造函数
		this.setFocusable(true);
		initSnake(); //放置静态蛇；
		this.addKeyListener(this);//添加键盘监听接口
		timer.start();
	}
	//设置蛇移动速度
	Timer timer = new Timer(50, this);//每150毫秒触发一次事件
	
	public void paint(Graphics g){
		//设置背景黑色
		this.setBackground(Color.WHITE);
		g.fillRect(25, 75, 850, 600);
		//设置标题
		title.paintIcon(this, g, 25, 11);
		
		//画蛇头
		if(direction.equals("R")){
			right.paintIcon(this, g, snakex[0], snakey[0]);//画头上去 方向
		}else if(direction.equals("L")){
			left.paintIcon(this, g, snakex[0], snakey[0]);
		}else if(direction.equals("U")){
			up.paintIcon(this, g, snakex[0], snakey[0]);
		}else if(direction.equals("D")){
			down.paintIcon(this, g, snakex[0], snakey[0]);
		}
		//画蛇身
		for(int i=1;i<len;i++){
			body.paintIcon(this, g, snakex[i],snakey[i]);
		}
		
		//画开始提示语
		if(!isStarted){
			g.setColor(Color.WHITE);
			g.setFont(new Font("arial",Font.BOLD,30));
			g.drawString("price kongge start", 230, 350);
		}
		//画失败提示语
		if (isFaild) {
			g.setColor(Color.WHITE);
			g.setFont(new Font("arial",Font.BOLD,30));
			g.drawString("Game Over,Press Space to Start", 230, 350);
		}
		
		//画食物
		food.paintIcon(this, g, foodx, foody);
		
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		//实现空格暂停 继续
		if(keyCode == KeyEvent.VK_SPACE){
			if(isFaild){
				initSnake();
			}
			else{
			isStarted = !isStarted;
			}

		}//实现转向
		else if(keyCode == KeyEvent.VK_UP && !direction.equals("D")){//狗头不朝向和自己相反的方向
			direction="U";
		}else if(keyCode == KeyEvent.VK_DOWN && !direction.equals("U")){
			direction="D";
		}else if(keyCode == KeyEvent.VK_LEFT && !direction.equals("R")){
			direction="L";
		}else if(keyCode == KeyEvent.VK_RIGHT && !direction.equals("L")){
			direction="R";
		}
		
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	/*
	 * 1.定个闹钟
	 * 2.蛇移动
	 * 3.重画一次蛇
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		timer.start();
		
		if(isStarted && !isFaild){
			//移动身体
			for(int i=len;i>0;i--){
				snakex[i] = snakex[i-1];
				snakey[i] = snakey[i-1];
			}
			//头移动
			if(direction.equals("R")){
				//横坐标+25
				snakex[0] = snakex[0]+25;
				if(snakex[0]>850) {
					
					isFaild = true;//只有右边墙是死，
				}
				
				
			}else if(direction.equals("L")){
				//横坐标-25
				snakex[0] = snakex[0]-25;
				if(snakex[0]<25) snakex[0] = 850;
			}else if(direction.equals("U")){
				//纵坐标-25
				snakey[0] = snakey[0]-25;
				if(snakey[0]<75) snakey[0] = 650;
			}else if(direction.equals("D")){
				//纵坐标+25
				snakey[0] = snakey[0]+25;
				if(snakey[0]>650) snakey[0] = 75;
			}
			//吃食物
			if(snakex[0] == foodx && snakey[0] == foody){
				len++;
				foodx = r.nextInt(34)*25+25;
				foody = r.nextInt(24)*25+75;
			}
			//判断游戏失败
			for(int i=1;i<len;i++){
				if(snakex[0] == snakex[i] && snakey[0] == snakey[i]){
					isFaild = true;
				}
			}
		}
		repaint();//重新画，清屏
	}
}
