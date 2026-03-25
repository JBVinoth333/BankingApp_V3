package model;

import java.time.LocalDateTime;

class Sms implements ShowDate{
    final String title;
    final LocalDateTime time;
    final String discription;
    final Account account;

    Sms(Account senter,String title,LocalDateTime time,String discription) {
        this.title = title;
        this.time = time;
        this.discription = discription;
        account = senter;
    }

    public String getTitle() {
        return title;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public String getDiscription() {
        return discription;
    }

    public Account getAccount() {
        return account;
    }



}
