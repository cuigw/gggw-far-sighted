package com.gggw.read.file;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by cuigaowei on 2017/8/24.
 */
public class ReadFileTest {

    public static void main(String[] args) {
        //read("/etc/caocao/schedule/sysConf.properties");
        read();
    }

    public static void read(String pathname) {

        //在使用FileChannel之前，必须先打开它。但是，我们无法直接打开一个FileChannel，
        //需要通过使用一个InputStream、OutputStream或RandomAccessFile来获取一个FileChannel实例。
        FileInputStream fin = null;
        try {
            fin = new FileInputStream(new File(pathname));
            FileChannel channel = fin.getChannel();

            int capacity = 1000;// 字节
            ByteBuffer bf = ByteBuffer.allocate(capacity);
            System.out.println("限制是" + bf.limit() + ", 容量是" + bf.capacity()
                    + ", 位置是" + bf.position());
            int length = -1;

            while ((length = channel.read(bf)) != -1) {

                /*
                 * 注意，读取后，将位置置为0，将limit置为容量, 以备下次读入到字节缓冲中，从0开始存储
                 */
                bf.clear();
                byte[] bytes = bf.array();
                System.out.write(bytes, 0, length);
                System.out.println();

                System.out.println("限制是" + bf.limit() + ", 容量是" + bf.capacity()
                        + ", 位置是" + bf.position());

            }

            channel.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fin != null) {
                try {
                    fin.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }


    public static void read() {
        try{
            RandomAccessFile aFile = new RandomAccessFile("/etc/caocao/schedule/sysConf.properties", "rw");
            FileChannel fileChannel = aFile.getChannel();
            //分配缓存区大小
            ByteBuffer buf = ByteBuffer.allocate(48);
            int bytesRead = fileChannel.read(buf);
            while (bytesRead != -1) {
                System.out.println("Read " + bytesRead);
                //buf.flip()的调用，首先读取数据到Buffer，然后反转Buffer,接着再从Buffer中读取数据（注：flip：空翻，反转）
                buf.flip();
                //判断是否有剩余（注：Remaining：剩余的）
                while(buf.hasRemaining()){
                    System.out.println((char) buf.get());
                }
                buf.clear();
                bytesRead = fileChannel.read(buf);
            }
            aFile.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
