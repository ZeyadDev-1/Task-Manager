package Package_1;

import java.io.Serializable;

public class Task implements Serializable {
    private String title;
    private String description;

    public Task(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title.toLowerCase()  ; 
    }

    public void setTitle(String title) {
        this.title = title;
        
    }

    public String getDescription() {
        return description;
    }

    
    // add a method to set the description for option 1 . 
    public void setDescription(String description) {
        this.description = description;
    }
    
    @Override
    public String toString() {
    	// TODO Auto-generated method stub
    	return super.toString();
    }
}



