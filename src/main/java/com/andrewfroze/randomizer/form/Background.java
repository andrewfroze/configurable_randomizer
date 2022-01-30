package com.andrewfroze.randomizer.form;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class Background extends JPanel {
    private JFrame frame;
    private Image background;
    private ShadowText innerLabel;

    public Background(Image background, JFrame frame) {
        this.background = background;
        this.frame = frame;
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                innerLabel.updateWindowSize(frame);
            }
            @Override
            public void componentMoved(ComponentEvent e) {}
        });
        setLayout(new BorderLayout());
    }

    public void setInnerLabel(ShadowText label) {
        innerLabel = label;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, frame.getWidth(), frame.getHeight(), null);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(background.getWidth(this), background.getHeight(this));
    }
}
