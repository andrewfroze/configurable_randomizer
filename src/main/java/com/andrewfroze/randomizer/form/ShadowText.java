package com.andrewfroze.randomizer.form;

import javax.swing.*;
import java.awt.*;
import java.awt.font.TextLayout;

import static com.andrewfroze.randomizer.configuration.Configuration.*;

public class ShadowText extends JLabel {
    private int windowWidth;
    private int windowHeight;
    private double fontIndex;
    private int fontSize;

    public ShadowText(String text, int windowWidth, int windowHeight) {
        this.setText(text);
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
        this.fontSize = START_FONT_SIZE;
        this.fontIndex = ((double) windowWidth) / fontSize;
    }

    public void updateWindowSize(JFrame frame) {
        windowWidth = frame.getWidth();
        windowHeight = frame.getHeight();
        fontSize = (int) (windowWidth / fontIndex);
        repaint();
    }

    public void setFontIndex(double fontIndex) {
        this.fontIndex = fontIndex;
        fontSize = (int) (windowWidth / fontIndex);
    }

    public void paint(Graphics g) {
        Font font = new Font(FONT, FONT_STYLE, fontSize);
        Graphics2D g1 = (Graphics2D) g;
        TextLayout textLayout = new TextLayout(this.getText(), font, g1.getFontRenderContext());
        double textWidth = textLayout.getBounds().getWidth();
        double textHeight = textLayout.getBounds().getHeight();
        g1.setPaint(SHADOW_COLOR);
        int x = (int) (windowWidth / 2 - textWidth / 2 + windowWidth * TEXT_X_POSITION);
        int y = (int) (windowHeight / 2 + textHeight / 2 + windowHeight * TEXT_Y_POSITION);
        textLayout.draw(g1, x + SHADOW_SIZE_X, y + SHADOW_SIZE_Y);
        g1.setPaint(TEXT_COLOR);
        textLayout.draw(g1, x, y);
    }
}