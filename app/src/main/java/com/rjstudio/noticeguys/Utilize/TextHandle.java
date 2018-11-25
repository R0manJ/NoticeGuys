package com.rjstudio.noticeguys.Utilize;

import android.util.Log;

import com.rjstudio.noticeguys.Bean.Event;

import junit.framework.Test;

/**
 * Created by r0man on 2018/11/25.
 */

public class TextHandle {
    private Event event;
    private String orgMsg;

    public TextHandle(String message)
    {
        orgMsg = message;
        this.event = new Event();
    }


    public void getTicketNumber()
    {
        String content[] = orgMsg.split("ID:");
        for (int i =0 ; i < content.length;i++)
        {
            Log.d("CONTENT", i + "getTicketNumber: "+ content[i]);
        }

        HandleMessage();
    }

    private void HandleMessage()
    {
        //判断是否为二线单
        String content[] = orgMsg.split("二线管理员你好,");
        if (content.length > 1)
        {
            Log.d("TEST", "HandleMessage: 0. "+content[0] + " 1. "+content[1]);

            String ticketNumber = content[1].substring(2,16);
            Log.d("Ticket", "TicketNumber " + ticketNumber);
            String action = content[1].substring(16,19);
            Log.d("Ticket", "Action "+ action);

            //角色设置为二线专家
            //事件设置为转入
            event.setRoleID(3);
            event.setEvent(21);

        }
        //判断超时
        content = orgMsg.split("请注意！");
        if (content.length > 1)
        {
            Log.d("TEST", "HandleMessage: 0. "+content[0] + " 1. "+content[1]);

            String ticketNumber = content[1].substring(2,16);
            Log.d("Ticket", "TicketNumber " + ticketNumber);
            //判断时效
            String content_2[] = content[1].split("已临");
            String action = null;
            String timeLimit = null;
            if (content_2.length > 1){
                action = "处理";
                timeLimit = "临近";
            }

            content_2 = content[1].split("已处");

            if (content_2.length > 1)
            {
                action = "处理";
                timeLimit = "超时";
                event.setTicketEffect(12);
            }

            content_2 = content[1].split("挂起临近");

            if (content_2.length > 1)
            {
                action = "挂起";
                timeLimit = "临近";
                event.setEvent(23);
            }

            content_2 = content[1].split("已挂");

            if (content_2.length > 1)
            {
                action = "挂起";
                timeLimit = "超时";
                event.setTicketEffect(12);
                event.setEvent(23);
            }

            event.setTicketNumber(ticketNumber);

            Log.d("Ticket", "Action : "+ action + " / Effect :"+timeLimit);
        }
    }

    //写入数据库

    public TextHandle(Event event) {
        this.event = event;
    }
}
