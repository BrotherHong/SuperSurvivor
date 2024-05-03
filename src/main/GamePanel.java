package main;

import api.Drawable;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GamePanel extends JPanel {

    private final List<Drawable> itemToDraw;

    public GamePanel() {
        setPanelSize();

        itemToDraw = new ArrayList<>();
    }

    private void setPanelSize() {
        Dimension size = new Dimension(1280, 800);
        setMinimumSize(size);
        setPreferredSize(size);
        setMaximumSize(size);
    }

    public void draw(Drawable item) {
        itemToDraw.add(item);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        itemToDraw.forEach(item -> item.draw(g));
    }
}
