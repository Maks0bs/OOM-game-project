package com.oom.game.main.gameCore;

import com.oom.game.main.gameCore.eventSystem.IEventManager;
import com.oom.game.main.gameCore.keyboard.KeyPressedEvent;
import com.oom.game.main.gameCore.keyboard.KeyReleasedEvent;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;


@SuppressWarnings("serial")
public final class Game extends JComponent implements KeyListener {
    private String title;
    private int width = 1080;
    private int height = 720;

    private int targetUpdates = 60;
    private int targetRenders = 60;
    private IRenderable renderable;
    private int updates = 0;
    private int frames = 0;
    public static int checks = 0;

    private JFrame frame;
    private Timer updateTimer;
    private Timer renderTimer;
    private Timer secondsTimer;

    private IEventManager eventManager;

    /**
     *
     * @param title         the title of the window
     * @param width         the width of the window
     * @param height        die height of the window
     * @param targetUpdates the amount of updates that should occur per second
     * @param updatable     the {@linkplain IUpdatable#update}-method of
     *                      this object should be called during an update
     * @param targetRenders the amount of renders that should occur per second
     * @param renderable    the {@linkplain IRenderable#render)}-method of
     *                      this object should be called during a render
     */
    public Game(String title, int width, int height, int targetUpdates, IUpdatable updatable, int targetRenders,
                IRenderable renderable, IEventManager eventManager) {
        this.title = title;
        this.width = width;
        this.height = height;
        this.targetUpdates = targetUpdates;
        this.targetRenders = targetRenders;
        this.renderable = renderable;
        this.eventManager = eventManager;

        frame = new JFrame(title);

        renderTimer = new Timer();
        updateTimer = new Timer();
        secondsTimer = new Timer();

        setDoubleBuffered(true);
        setPreferredSize(new Dimension(width, height));

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.add(this);
        frame.addKeyListener(this);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {

                long lastTime = System.currentTimeMillis();

                while (true) {
                    if (updateTimer.getDeltaTime() >= 1d / targetUpdates) {
                        long thisTime = System.currentTimeMillis();
                        long deltaTime = thisTime - lastTime;

                        updatable.update(deltaTime);
                        updates++;
                        updateTimer.reset();

                        lastTime = thisTime;
                    }
                    if (renderTimer.getDeltaTime() >= 1d / targetRenders) {
                        repaint();
                        frames++;
                        renderTimer.reset();
                    }
                    if (secondsTimer.getDeltaTime() >= 1) {
                        secondsTimer.reset();
                    }

                    updateTimer.update();
                    renderTimer.update();
                    secondsTimer.update();
                }
            }
        });
        thread.start();
    }

    public void paintComponent(Graphics g) {
        renderable.render(new Renderer(g));
    }

    public String getTitle() {
        return title;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getTargetUpdates() {
        return targetUpdates;
    }

    public int getTargetRenders() {
        return targetRenders;
    }

    public void setTargetUpdates(int targetUpdates) {
        this.targetUpdates = targetUpdates;
    }

    public void setTargetRenders(int targetRenders) {
        this.targetRenders = targetRenders;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        eventManager.fire(new KeyPressedEvent(e.getKeyCode()));
    }

    @Override
    public void keyReleased(KeyEvent e) {
        eventManager.fire(new KeyReleasedEvent(e.getKeyCode()));
    }
}