package com.thedeadlines.mafiap2p.common;

import android.os.Message;

import java.nio.ByteBuffer;
import java.util.Arrays;

import static com.thedeadlines.mafiap2p.common.Constants.CAPACITY;

public abstract class MessageShaper {

    public static byte[] recycle(final int accessType,
                                 final int contentType,
                                 final Object obj) {
        final byte[] aClass = Serializer.serialize(obj.getClass());
        return ByteBuffer.allocate(CAPACITY)
                .putInt(accessType)
                .putInt(contentType)
                .putInt(aClass.length)
                .put(aClass)
                .put(Serializer.serialize(obj))
                .array();
    }

    public static Message getMessage(final byte[] buffer) {
        try {
            final int classSize = ByteBuffer.wrap(Arrays.copyOfRange(buffer, 8, 12)).getInt();
            final Class objClass = (Class) Serializer
                    .deserialize(Arrays.copyOfRange(buffer, 12, 12 + classSize));
            final Message message = new Message();
            message.obj = Serializer
                    .deserialize(Arrays.copyOfRange(buffer, 12 + classSize, buffer.length));
            objClass.cast(message.obj);
            message.arg1 = ByteBuffer.wrap(Arrays.copyOfRange(buffer, 0, 4)).getInt();
            message.what = ByteBuffer.wrap(Arrays.copyOfRange(buffer, 4, 8)).getInt();
            return message;
        } catch (final ClassCastException e) {
            e.printStackTrace();
            return new Message();
        }
    }
}
