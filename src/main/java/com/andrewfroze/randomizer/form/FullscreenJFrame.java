package com.andrewfroze.randomizer.form;

import com.andrewfroze.randomizer.util.NumberFormat;
import com.andrewfroze.randomizer.util.PropertyReader;
import com.andrewfroze.randomizer.util.RandomUtil;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;

import static com.andrewfroze.randomizer.configuration.Configuration.*;
import static com.andrewfroze.randomizer.util.PropertyReader.properties;

public class FullscreenJFrame extends JFrame implements KeyListener {
    private Background background;
    private ShadowText label;
    private boolean FullScreenMode = false;
    private int PrevX, PrevY, PrevWidth, PrevHeight;
    private int row = 0;
    private int place = 0;

    public FullscreenJFrame() throws IOException {
        super("Randomizer");
        background = new Background(ImageIO.read(new File(PATH_TO_BACKGROUND_IMAGE)), this);
        label = new ShadowText(getText(), START_WIDTH, START_HEIGHT);
        addKeyListener(this);
        setLayout(null);
        add(background, BorderLayout.CENTER);
        background.setInnerLabel(label);
        setContentPane(background);
        add(label);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(START_WIDTH, START_HEIGHT);
        setVisible(true);
    }

    public String getText() {
        return String.format(TWO_VARIABLES_TEXT_PATTERN,
                NumberFormat.convertNumberToString(row),
                NumberFormat.convertNumberToString(place));
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            FullScreenToggle();
        } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            ExitApplication();
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            row = RandomUtil.getRandomRow();
            label.setText(getText());
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            place = RandomUtil.getRandomPlace();
            label.setText(getText());
        } else if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
            row = 0;
            place = 0;
            label.setText(getText());
        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
            showOnScreen(1, this);
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            showOnScreen(0, this);
        } else if (e.getKeyCode() == KeyEvent.VK_A) {
            TEXT_X_POSITION -= CHANGE_STEP;
            label.repaint();
        } else if (e.getKeyCode() == KeyEvent.VK_D) {
            TEXT_X_POSITION += CHANGE_STEP;
            label.repaint();
        } else if (e.getKeyCode() == KeyEvent.VK_W) {
            TEXT_Y_POSITION -= CHANGE_STEP;
            label.repaint();
        } else if (e.getKeyCode() == KeyEvent.VK_S) {
            TEXT_Y_POSITION += CHANGE_STEP;
            label.repaint();
        } else if (e.getKeyCode() == KeyEvent.VK_R) {
            resetLabel();
        } else if (e.getKeyCode() == KeyEvent.VK_T) {
            PropertyReader.saveProperties();
        } else if (e.getKeyCode() == KeyEvent.VK_1) {
            if (START_FONT_SIZE > 0) {
                START_FONT_SIZE -= 1;
            }
            label.setFontIndex(((double) START_WIDTH) / START_FONT_SIZE);
            label.repaint();
        } else if (e.getKeyCode() == KeyEvent.VK_2) {
            START_FONT_SIZE += 1;
            label.setFontIndex(((double) START_WIDTH) / START_FONT_SIZE);
            label.repaint();
        }
    }

    private void resetLabel() {
        TEXT_X_POSITION = Double.parseDouble(properties.getProperty("TEXT_X_POSITION", "0"));
        TEXT_Y_POSITION = Double.parseDouble(properties.getProperty("TEXT_Y_POSITION", "0"));
        START_FONT_SIZE = Integer.parseInt(properties.getProperty("FONT_SIZE", "25"));
        label.setFontIndex(((double) START_WIDTH) / START_FONT_SIZE);
        label.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    public void FullScreenToggle() {
        if (!FullScreenMode) {
            PrevX = getX();
            PrevY = getY();
            PrevWidth = getWidth();
            PrevHeight = getHeight();
            dispose();
            setUndecorated(true);
            setBounds(0, 0, getToolkit().getScreenSize().width, getToolkit().getScreenSize().height);
            setVisible(true);
            FullScreenMode = true;
            label.updateWindowSize(this);
        } else {
            dispose();
            setUndecorated(false);
            setBounds(PrevX, PrevY, PrevWidth, PrevHeight);
            setVisible(true);
            FullScreenMode = false;
            label.updateWindowSize(this);
        }
    }

    public void ExitApplication() {
        System.exit(0);
    }

    public static void showOnScreen(int screen, JFrame frame) {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice[] gs = ge.getScreenDevices();
        if (screen > -1 && screen < gs.length) {
            gs[screen].setFullScreenWindow(frame);
        } else if (gs.length > 0) {
            gs[0].setFullScreenWindow(frame);
        } else {
            throw new RuntimeException("No Screens Found");
        }
    }
}
