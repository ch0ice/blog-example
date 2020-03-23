package cn.com.onlinetool.serialization;

import cn.com.onlinetool.serialization.messagepack.MsgPackMember;
import cn.com.onlinetool.serialization.serializable.Member;
import com.alibaba.fastjson.JSON;
import org.msgpack.MessagePack;
import org.msgpack.template.Templates;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author choice
 * @description:
 * @date 2018-12-26 20:05
 *
 */
public class SerializationTest {
    public static void main(String[] args) throws IOException {
        List<MsgPackMember> packMembers = new ArrayList<>();
        List<Member> members = new ArrayList<>();
        for (int i = 0; i < 10; i ++){
            Member member = new Member();
            member.setMid("M_ID_"  + i);
            member.setName("CHOICE_" + i);
            member.setAge(10);
            member.setSalary(1.1);
            members.add(member);

            MsgPackMember packMember = new MsgPackMember();
            packMember.setMid("PACK_ID_"  + i);
            packMember.setName("CHOICE_" + i);
            packMember.setAge(10);
            packMember.setSalary(1.1);
            packMembers.add(packMember);
        }

        MessagePack messagePack = new MessagePack();
        byte[] packData = messagePack.write(packMembers);
        System.out.println("MessagePack序列化方式的长度 " + packData.length);
        {
            //将数据反序列化
            List<MsgPackMember> read = messagePack.read(packData, Templates.tList(messagePack.lookup(MsgPackMember.class)));
            System.out.println(read.toString());
        }


        byte[] mData = JSON.toJSONString(packMembers).getBytes();
        System.out.println("Java原生序列化(Serializable)后的长度" + mData.length);


    }
}
