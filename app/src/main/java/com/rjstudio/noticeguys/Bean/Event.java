package com.rjstudio.noticeguys.Bean;

/**
 * Created by r0man on 2018/11/25.
 */

public class Event {

    // Keep 0-9 to set role
    private static int ROLEID_ME = 1;
    private static int ROLEID_COLLEAGUS = 2;
    private static int ROLEID_EXPERT = 3;

    //Keep 10-19 to set TicketStatus
    private static int TICKET_TIME_STATUS_IN_USE = 11;
    private static int TICKET_TIME_STATUS_OUT_DATE = 12;

    //Keep 20-29 to set EventOperationStatus
    private static int EVENT_OPERATION_STATUS_INPUT = 21;
    private static int EVENT_OPERATION_STATUS_HANDLING= 22;
    private static int EVENT_OPERATION_STATUS_HANDUP= 23;
    //private static int EVENT_OPERATION_STATUS_HANGUP_LIMIT_TIMEOUT = 22;
    //private static int EVENT_OPERATION_STATUS_HANDLE_TIMEOUT = 23;

    //角色默认是"ME"
    //处理时效默认是 "IN_USE"
    //事件默认是"HANDLING"
    private String ticketNumber;
    private int roleID = 1;
    private int ticketEffect=11;
    private int event=21;

    public Event() {

    }

    public Event(String ticketNumber, int ticketEffect, int roleID, int event) {
        this.ticketNumber = ticketNumber;
        this.ticketEffect = ticketEffect;
        this.roleID = roleID;
        this.event = event;
    }

    public String getTicketNumber() {
        return ticketNumber;
    }

    public void setTicketNumber(String ticketNumber) {
        this.ticketNumber = ticketNumber;
    }

    public int getTicketEffect() {
        return ticketEffect;
    }

    public void setTicketEffect(int ticketEffect) {
        this.ticketEffect = ticketEffect;
    }

    public int getRoleID() {
        return roleID;
    }

    public void setRoleID(int roleID) {
        this.roleID = roleID;
    }

    public int getEvent() {
        return event;
    }

    public void setEvent(int event) {
        this.event = event;
    }
}
