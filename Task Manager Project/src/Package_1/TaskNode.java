package Package_1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TaskNode implements Serializable {
    private Task task;
    private List<TaskNode> children;

    public TaskNode(Task task) {
        this.task = task;
        this.children = new ArrayList<>();
    }

    public Task getTask() {
        return task;
    }

    public List<TaskNode> getChildren() {
        return children;
    }

    public void addChild(TaskNode child) {
        children.add(child);
    }
}


