package model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyWindow {

    private int balance = 0;
    private JLabel balanceLabel;
 
    public MyWindow(){

        //Создаем фрейм (окно)
        JFrame frame = new JFrame("My cool window");
        
        //Просим программу закрыться при закрытии фрейма
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Создадим лейбл и поместим его во фрейм
        balanceLabel = new JLabel("Баланс: " + balance);
        frame.add(balanceLabel, BorderLayout.CENTER);
        
        //Создадим кнопку "Увеличить" и поместим ее во фрейм
        JButton btnIncrease = new JButton("Увеличить");
        frame.add(btnIncrease, BorderLayout.SOUTH);

        //Добавим к кнопке слушатель события
        btnIncrease.addActionListener(new ActionListener(){
          public void actionPerformed(ActionEvent e) {
            balance++;
            updateBalance();
          }
        });
        
        //Установка размеров окна
        //1 метод. Позиция окна на экране монитора + размеры
        // ( leftPos, topPos, windowWidth, windowHeight)
        //frame.setBounds(100, 200, 640, 480); 
        
        //2 метод. Размеры окна
        //frame.setSize(400,400);

        //3 метод. Выровнять размер фрейма под контент
        frame.pack();

        //Показать фрейм
        frame.setVisible(true);
    }
    
    private void updateBalance () {
        //Обновляем содержимое Лейбла
        balanceLabel.setText("Баланс: " + balance);
    }
 
    public static void main(String[] args) {
        MyWindow app = new MyWindow();

    }
}