package ru.home.chapter15;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class DailyAdviceServer {

    String[] adviceList = {"Ешьте меньшими порциями", "Купите облегчающие джинсы. Нет, они не делают вас полнее.",
            "Два слова: не годится", "Будьте честны хотя бы сегодня. Скажите своему начальнику все, что вы *на самом деле* о нем думаете.",
            "Возможно, вам стоит подобрать другую прическу."};

    public static void main(String[] args) {
        DailyAdviceServer server = new DailyAdviceServer();
        server.go();
    }

    public void go() {
        try {
            ServerSocket serverSocket = new ServerSocket(4242);

            while (true) {
                Socket sock = serverSocket.accept();

                PrintWriter writer = new PrintWriter(sock.getOutputStream());
                String advice = getAdvice();
                writer.println(advice);
                writer.close();
                System.out.println(advice);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getAdvice() {
        int random = (int) (Math.random() * adviceList.length);
        return adviceList[random];
    }
}
