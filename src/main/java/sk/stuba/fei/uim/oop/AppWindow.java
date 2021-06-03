package sk.stuba.fei.uim.oop;

import javax.swing.*;
import java.awt.*;

public class AppWindow{

    private final int height;
    private final int width;

    private JFrame frame;
    private JPanel basic_panel;
    private AppCanvas canvas;
    private JPanel panel_menu;
    private JLabel label;

    public AppWindow(int height, int width){
        this.height = height;
        this.width = width;
        this.initFrame();
        this.initCanvas();
        this.initMenu();
    }

    public void initFrame(){
        this.frame = new JFrame("Application");
        frame.setSize(height, width);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);
        basic_panel = new JPanel();
        basic_panel.setVisible(true);
        basic_panel.setLayout(new BorderLayout());
        frame.add(basic_panel);
    }

    public void initCanvas(){
        canvas = new AppCanvas(this);
        canvas.setSize(new Dimension(700, 700));
        canvas.setFocusable(true);
        canvas.setBackground(Color.LIGHT_GRAY);
        basic_panel.add(canvas, BorderLayout.PAGE_START);
        canvas.createBufferStrategy(2);
    }

    public void initMenu(){
        panel_menu = new JPanel();
        panel_menu.setLayout(new GridLayout(1, 4));

        JButton tree_button = new JButton("Tree");
        tree_button.addActionListener(canvas);
        panel_menu.add(tree_button);

        JButton move_button = new JButton("Move");
        move_button.addActionListener(canvas);
        panel_menu.add(move_button);

        JButton color_button = new JButton("Change Color");
        color_button.addActionListener(canvas);
        panel_menu.add(color_button);

        label = new JLabel();
        panel_menu.add(label);

        panel_menu.validate();
        basic_panel.add(panel_menu, BorderLayout.CENTER);
    }

    public void setLabelText(String text){
        this.label.setText(text);
    }

    public void setForeground(Color c){
        this.label.setForeground(c);
    }
}

