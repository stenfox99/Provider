package by.training.provider.command;

/**
 * The Class Router.
 */
public class Router {
    
    /** The page. */
    private String page;
    
    /** The direction type. */
    private DirectionType directionType;

    /**
     * Instantiates a new router.
     *
     * @param page the page
     * @param directionType the direction type
     */
    public Router(String page, DirectionType directionType) {
        this.page = page;
        this.directionType = directionType;
    }

    /**
     * Instantiates a new router.
     */
    public Router(){}

    /**
     * Gets the page.
     *
     * @return the page
     */
    public String getPage() {
        return page;
    }

    /**
     * Sets the page.
     *
     * @param page the new page
     */
    public void setPage(String page) {
        this.page = page;
    }

    /**
     * Gets the direction type.
     *
     * @return the direction type
     */
    public DirectionType getDirectionType() {
        return directionType;
    }

    /**
     * Sets the direction type.
     *
     * @param directionType the new direction type
     */
    public void setDirectionType(DirectionType directionType) {
        this.directionType = directionType;
    }
}
