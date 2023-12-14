package christmas.domain.manager;

public class ScheduleManager {
    /*
    방문 날짜,
    해당 혜택이 가능 한지
    요일 반환
     */

    private int visitDate;

    public ScheduleManager(int visitDate) {
        //날짜 추가 유효 검사
        this.visitDate = visitDate;
    }

    public int getVisitDate(){
        return visitDate;
    }
    //요일 반환
}
