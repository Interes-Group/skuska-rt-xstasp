package sk.stuba.fei.uim.oop;

import lombok.Getter;
import lombok.Setter;

import java.awt.*;

@Getter
@Setter
public class Tree {
    private Color farba;
    private int painting_x;
    private int painting_y;

    private int painting_width;
    private int painting_height;

    private int circle_x;
    private int circle_y;
    private int circle_width;
    private int circle_height;

    private int rect_x;
    private int rect_y;
    private int rect_width;
    private int rect_height;

    public Tree(int x, int y, int width, int height, Color c){

        this.farba = c;

        this.painting_x = x;
        this.painting_y = y;
        this.painting_width = width;
        this.painting_height = height;

        this.circle_x = x;
        this.circle_y = y;
        this.circle_width = painting_width;
        this.circle_height = painting_height/3 * 2;

        this.rect_width = painting_width/3;
        this.rect_height = painting_height/3;
        this.rect_y = this.painting_y + (painting_height/3 * 2);
        this.rect_x = this.painting_x + (painting_width/3);
    }

    public void setPainting_height(int new_height) {
        this.painting_height = new_height;
        this.rect_y = this.painting_y + (painting_height/3 * 2) - 2 ;
        this.circle_height = this.painting_height/3 * 2;
        this.rect_height = this.painting_height/3;
    }

    public void setPainting_width(int new_width){
        this.painting_width = new_width;
        this.rect_x = this.painting_x + (painting_width/3) - 2 ;
        this.circle_width = painting_width;
        this.rect_width = painting_width/3;
    }
}
