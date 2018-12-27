package by.training.provider.command;

public class Router {  //todo name place
    private String page;
    private DirectionType directionType;

    public Router(String page, DirectionType directionType) {
        this.page = page;
        this.directionType = directionType;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public DirectionType getDirectionType() {
        return directionType;
    }

    public void setDirectionType(DirectionType directionType) {
        this.directionType = directionType;
    }
}
