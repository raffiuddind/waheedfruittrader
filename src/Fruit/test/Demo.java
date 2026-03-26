/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Fruit.test;

/**
 *
 * @author raffiuddin
 */
        import java.awt.*;
        import java.awt.event.*;
        import javax.swing.*;
        public class Demo implements MouseListener, MouseMotionListener {
        private int startDragX, startDragY;
        private boolean inDrag = false;
        private JLabel sprite = new JLabel("drag me");
        JFrame frame = new JFrame("drag demo");
        public Demo() {
        
        frame.setLayout(null);
        frame.setMinimumSize(new Dimension(400, 300));
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        sprite.setBounds(10, 10, 80, 20);
        sprite.addMouseListener(this);
        sprite.addMouseMotionListener(this);
        frame.add(sprite);
        frame.setVisible(true);
        }
        @Override
        public void mouseEntered(MouseEvent e) {
        // not interested
        }
        @Override
        public void mouseExited(MouseEvent e) {
        // not interested
        }
        @Override
        public void mousePressed(MouseEvent e) {
        startDragX = e.getX();
        startDragY = e.getY();
        }
        @Override
        public void mouseReleased(MouseEvent e) {
        if (inDrag) {
        System.out.println("Sprite dragged to " + frame.getX() + ", " + frame.getY());
        inDrag = false;
        }
        }
        @Override
        public void mouseClicked(MouseEvent e) {
        // not interested
        }
        @Override
        public void mouseDragged(MouseEvent e) {
        int newX = frame.getX() + (e.getX() - startDragX);
        int newY = frame.getY() + (e.getY() - startDragY);
        frame.setLocation(newX, newY);
        inDrag = true;
        }
        @Override
        public void mouseMoved(MouseEvent arg0) {
        // not interested
        }
        public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
        public void run() {
        Demo demo = new Demo();
        }
        });
        }
        }