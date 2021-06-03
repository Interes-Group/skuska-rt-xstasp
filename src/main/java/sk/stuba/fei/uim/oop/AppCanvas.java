package sk.stuba.fei.uim.oop;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class AppCanvas extends Canvas implements ActionListener, MouseMotionListener, MouseListener {

    private AppWindow window;

    private boolean tree_clicked = false;
    private boolean move_clicked = false;
    private int color_counter = 0;
    private ArrayList<Color> colors= new ArrayList<Color>();
    private ArrayList<Tree> tree_objects = new ArrayList<Tree>();
    private Tree actual_tree;
    private Tree moving_tree;

    private int xpos;
    private int ypos;

    private int move_xpos;
    private int move_ypos;

    @Override
    public void actionPerformed(ActionEvent button_event) {
        var button = button_event.getActionCommand();

        if(button.equals("Tree")){
            this.window.setLabelText("Kreslenie");
            move_clicked = false;
            this.tree_clicked = true;
        }

        if(button.equals("Change Color")){
            if(color_counter==2){
                color_counter = color_counter - 2;
            }
            else{
                color_counter++;
            }
            Color c = colors.get(color_counter);
            this.window.setForeground(c);
        }

        if(button.equals("Move")){
            move_clicked = true;
            tree_clicked = false;
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if(tree_clicked){
            int dx = e.getX();
            int dy = e.getY();
            if(actual_tree != null){
                if(dx > xpos && dy > ypos){
                    actual_tree.setPainting_width(dx-xpos);
                    actual_tree.setPainting_height(dy-ypos);
                }
                if(dx<xpos && dy>ypos){
                    actual_tree.setPainting_x(dx);
                    actual_tree.setPainting_width(xpos-dx);
                    actual_tree.setPainting_height((dy-ypos));
                }
                if(dx>xpos && dy <ypos){
                    actual_tree.setPainting_y(dy);
                    actual_tree.setPainting_width(dx-ypos);
                    actual_tree.setPainting_height(ypos-dy);
                }
                if(dx<xpos && dy<ypos){
                    actual_tree.setPainting_x(dx);
                    actual_tree.setPainting_y(dy);
                    actual_tree.setPainting_width(xpos-dy);
                    actual_tree.setPainting_height(ypos-dy);
                }
                repaint();
            }
        }
        if(move_clicked && moving_tree!=null){
            int dx = e.getX();
            int dy = e.getY();

            int x_distance = moving_tree.getPainting_x() - dx;
            int y_distance = moving_tree.getPainting_y() - dy;

            moving_tree.setPainting_y(moving_tree.getPainting_y()+y_distance);
            moving_tree.setPainting_x(moving_tree.getPainting_x()+x_distance);
            repaint();
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    public AppCanvas(AppWindow window){
        this.window = window;
        this.tree_clicked = false;
        this.move_clicked = false;
        this.color_counter = 0;     // 0 1  2 -> 3 colors
        this.actual_tree = null;
        colors.add(Color.BLACK);
        colors.add(Color.BLUE);
        colors.add(Color.RED);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }

    public void paint(Graphics g){
            if(!tree_objects.isEmpty()){
                for(Tree s: tree_objects){
                    g.setColor(s.getFarba());
                    g.fillRect(s.getRect_x(), s.getRect_y(), s.getRect_width(), s.getRect_height());
                    g.fillOval(s.getCircle_x(), s.getCircle_y(), s.getCircle_width(), s.getCircle_height());
                }
            }
            if(actual_tree != null){
                g.setColor(actual_tree.getFarba());
                g.fillRect(actual_tree.getRect_x(), actual_tree.getRect_y(), actual_tree.getRect_width(), actual_tree.getRect_height());
                g.fillOval(actual_tree.getCircle_x(), actual_tree.getCircle_y(), actual_tree.getCircle_width(), actual_tree.getCircle_height());
            }
            if(moving_tree != null){
                g.setColor(moving_tree.getFarba());
                g.fillRect(moving_tree.getRect_x(), moving_tree.getRect_y(), moving_tree.getRect_width(), moving_tree.getRect_height());
                g.fillOval(moving_tree.getCircle_x(), moving_tree.getCircle_y(), moving_tree.getCircle_width(), moving_tree.getCircle_height());
            }

    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(tree_clicked){
            System.out.println("Pressed");
            xpos = e.getX();
            ypos = e.getY();
            actual_tree = new Tree(xpos, ypos, 1, 1, colors.get(color_counter));
        }
        if(move_clicked){
            move_xpos = e.getX();
            move_ypos = e.getY();

            for(Tree s: tree_objects){
                if(move_xpos >= s.getPainting_x() && move_xpos <= s.getPainting_x()+s.getPainting_width()){
                    if(move_ypos >= s.getPainting_y() && move_ypos <= s.getPainting_y()+s.getPainting_height()){
                        moving_tree = s;
                        tree_objects.remove(s);
                    }
                }
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(tree_clicked){
            tree_objects.add(actual_tree);
            repaint();
            actual_tree = null;
        }
        if(move_clicked){
            tree_objects.add(moving_tree);
            repaint();
            moving_tree = null;
            move_clicked = false;
        }

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
