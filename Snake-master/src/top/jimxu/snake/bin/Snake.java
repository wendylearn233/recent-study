package top.jimxu.snake.bin;

import javax.swing.JFrame;

public class Snake {
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setBounds(450,200, 900, 720);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		SnakePanel th = new SnakePanel();
		frame.add(th);//Ïò¿ò¼ÜÌí¼ÓÔªËØ
		
		frame.setVisible(true);
	}
	
}