
package cn.com.onlinetool.protocol.file.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.DefaultFileRegion;
import io.netty.channel.FileRegion;

import java.io.File;
import java.io.RandomAccessFile;

public class FileServerHandler extends ChannelInboundHandlerAdapter {

    private static final String SEPARATOR = "*_*";

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String path = msg.toString();
        File file = new File(path);
        if (file.exists()) {
            if (!file.isFile()) {
                ctx.writeAndFlush("Not a file : " + file + SEPARATOR);
                return;
            }
            ctx.write(file + " " + file.length() + SEPARATOR);
            RandomAccessFile randomAccessFile = new RandomAccessFile(path, "r");
            FileRegion region = new DefaultFileRegion(
                    randomAccessFile.getChannel(), 0, randomAccessFile.length());
            ctx.write(region);
            ctx.writeAndFlush(SEPARATOR);
            randomAccessFile.close();
        } else {
            ctx.writeAndFlush("File not found: " + file + SEPARATOR);
        }

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
