package com.thedeadlines.mafiap2p.common;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import static com.thedeadlines.mafiap2p.common.Constants.CAPACITY;
import static com.thedeadlines.mafiap2p.common.Constants.CHAT_NEEDLE;

class ChatNeedle implements Runnable {
    private static final String TAG = ChatNeedle.class.getSimpleName();
    private final Socket socket;
    private final Handler handler;

    public ChatNeedle(final Socket socket, final Handler handler) {
        this.socket = socket;
        this.handler = handler;
    }

    private OutputStream oStream;

    @Override
    public void run() {
        try {
            final InputStream iStream = socket.getInputStream();
            oStream = socket.getOutputStream();
            final byte[] buffer = new byte[CAPACITY];
            handler.obtainMessage(CHAT_NEEDLE, this)
                    .sendToTarget();
            while (iStream.read(buffer) != -1) {
                final Message message = new Message();
                message.what = 0;
                message.obj = buffer;
                handler.obtainMessage(message.what, message.obj).sendToTarget();
            }
        } catch (final IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (final IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void write(final byte[] buffer) {
        try {
            Log.d(TAG, "WRITING MESSAGE " + buffer.length);
            oStream.write(buffer);
        } catch (final IOException ignored) {
            Log.d(TAG, "IGNORED MESSAGE");
            ignored.printStackTrace();
        }
    }

}
